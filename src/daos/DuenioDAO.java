package daos;

import java.util.ArrayList;
import java.util.List;

import entities.InquilinoEntity;
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

	public Persona findByID(String documento) throws PersonaException {
		Persona resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		DuenioEntity persona = (DuenioEntity) s.createQuery("from DuenioEntity p where p.persona.documento = ?").setString(0, documento).uniqueResult();
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
		DuenioEntity duenio = (DuenioEntity) s.createQuery("from DuenioEntity i where i.documento = ?").setString(0, documento).uniqueResult();
		if (duenio == null) {
			throw new PersonaException("No se pudo recuperar el dueño");
		}
		return duenio.getUnidad().toNegocio();
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
