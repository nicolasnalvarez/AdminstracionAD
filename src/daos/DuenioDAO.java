package daos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entities.InquilinoEntity;
import entities.UnidadEntity;
import modelo.Edificio;
import modelo.Unidad;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.DuenioEntity;
import exceptions.PersonaException;
import hibernate.HibernateUtil;
import modelo.Persona;

public class DuenioDAO {

	private static DuenioDAO instancia;
	
	private DuenioDAO() { }
	
	public static DuenioDAO getInstancia() {
		if(instancia==null)
			instancia = new DuenioDAO();
		return instancia;
	}

	public List<Persona> getDueniosByUnidad(int id) throws PersonaException {
		List<Persona> resultado = new ArrayList<>();

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		List<DuenioEntity> duenios = (List<DuenioEntity>) s.createQuery("from DuenioEntity de where de.unidad.id = ?")
					.setInteger(0, id)
					.list();
		s.getTransaction().commit();
		if(duenios != null) {
			for(DuenioEntity d : duenios)
				resultado.add(PersonaDAO.getInstancia().toNegocio(d.getPersona()));
			return resultado;		
		}
		else
			throw new PersonaException("No se pudo recuperar los duenios");
		
	}


	public Unidad getUnidadByDocumento(String documento) throws PersonaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		DuenioEntity duenio = (DuenioEntity) s.createQuery("from DuenioEntity d where d.persona.documento = ?").setString(0, documento).uniqueResult();
		if (duenio == null) {
			throw new PersonaException("No se pudo recuperar el dueño");
		}
		return duenio.getUnidad().toNegocio();
	}

	public List<Edificio> getEdificiosByDocumento(String documento) throws PersonaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		List<DuenioEntity> duenios = (List<DuenioEntity>) s.createQuery("from DuenioEntity d where d.persona.documento = ?").setString(0, documento).list();
		if (duenios == null) {
			throw new PersonaException("No se pudo recuperar el dueño");
		}
		return duenios.stream().map(de -> de.getUnidad().getEdificio().toNegocio()).collect(Collectors.toList());
	}

	public List<Unidad> getUnidadesByDocumentoYEdificio(String documento, int idEdificio) throws PersonaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		List<DuenioEntity> duenios = (List<DuenioEntity>) s.createQuery("from DuenioEntity d where d.persona.documento = ?").setString(0, documento).list();
		if (duenios == null) {
			throw new PersonaException("No se pudo recuperar el dueño");
		}
		return duenios.stream()
				.filter(de -> de.getUnidad().getEdificio().getId().equals(idEdificio))
				.map(de -> de.getUnidad().toNegocio())
				.collect(Collectors.toList());
	}
}
