package daos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entities.DuenioEntity;
import entities.EdificioEntity;
import entities.PersonaEntity;
import exceptions.EdificioException;
import exceptions.UnidadException;
import modelo.Edificio;
import modelo.Unidad;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.InquilinoEntity;
import exceptions.PersonaException;
import hibernate.HibernateUtil;
import modelo.Persona;

public class InquilinoDAO {

	private static InquilinoDAO instancia;
	
	private InquilinoDAO() { }
	
	public static InquilinoDAO getInstancia() {
		if(instancia==null)
			instancia = new InquilinoDAO();
		return instancia;
	}

	public List<Persona> getInquilinosByUnidad(int id) throws PersonaException {
		List<Persona> resultado = new ArrayList<Persona>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		List<InquilinoEntity> inquilinos = (List<InquilinoEntity>) s.createQuery("from InquilinoEntity ie where ie.unidad.id = ?")
					.setInteger(0, id)
					.list();
		s.getTransaction().commit();
		if(inquilinos != null) {
			for(InquilinoEntity ie : inquilinos)
				resultado.add(PersonaDAO.getInstancia().toNegocio(ie.getPersona()));
			return resultado;		
		}
		else
			throw new PersonaException("No se pudo recuperar los inquilinos");
		
	}

	public Unidad getUnidadByDocumento(String documento) throws PersonaException {
		Persona resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		InquilinoEntity inquilino = (InquilinoEntity) s.createQuery("from InquilinoEntity i where i.documento = ?").setString(0, documento).uniqueResult();
		if (inquilino == null) {
			throw new PersonaException("No se pudo recuperar el inquilino");
		}
		return inquilino.getUnidad().toNegocio();
	}

	Persona toNegocio(InquilinoEntity i) throws PersonaException {
		if(i != null) {
			Persona persona = new Persona(i.getPersona().getDocumento(), i.getPersona().getNombre());
			return persona;
		} else {
			throw new PersonaException("No se pudo recuperar el inquilino");
		}
	}

	public List<Edificio> getEdificiosByDocumento(String documento) throws PersonaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		List<InquilinoEntity> inquilinos = (List<InquilinoEntity>) s.createQuery("from InquilinoEntity i where i.documento = ?").setString(0, documento).list();
		if (inquilinos == null) {
			throw new PersonaException("No se pudieron recuperar los inquilinos");
		}
		return inquilinos.stream().map(de -> de.getUnidad().getEdificio().toNegocio()).collect(Collectors.toList());
	}

	public List<Unidad> getUnidadesByDocumentoYEdificio(String documento, int idEdificio) throws PersonaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		List<InquilinoEntity> inquilinos = (List<InquilinoEntity>) s.createQuery("from InquilinoEntity i where i.persona.documento = ?").setString(0, documento).list();
		if (inquilinos == null) {
			throw new PersonaException("No se pudo recuperar el inquilino");
		}
		return inquilinos.stream()
				.filter(de -> de.getUnidad().getEdificio().getId().equals(idEdificio))
				.map(de -> de.getUnidad().toNegocio())
				.collect(Collectors.toList());
	}
}
