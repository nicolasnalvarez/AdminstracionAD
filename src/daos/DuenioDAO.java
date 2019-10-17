package daos;

import entities.DuenioEntity;
import exceptions.PersonaException;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DuenioDAO {

	private static DuenioDAO instancia;
	
	private DuenioDAO() { }
	
	public static DuenioDAO getInstancia() {
		if(instancia==null)
			instancia = new DuenioDAO();
		return instancia;
	}

	public Persona findByID(String documento) throws PersonaException {
		Persona resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		DuenioEntity persona = (DuenioEntity) s.createQuery("from DuenioEntity p where p.persona.documento = ?").setString(0, documento).setMaxResults(1).uniqueResult();
		s.getTransaction().commit();
		if(persona != null) {
			resultado = toNegocio(persona);
			return resultado;
		}
		else
			throw new PersonaException("No existe una persona con el documento " + documento);

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

	Persona toNegocio(DuenioEntity i) throws PersonaException {
		if(i != null) {
			Persona persona = new Persona(i.getPersona().getDocumento(), i.getPersona().getNombre());
			return persona;
		} else {
			throw new PersonaException("No se pudo recuperar el duenio");
		}
	}
}
