package daos;

import entities.ImagenEntity;
import entities.PersonaEntity;
import entities.ReclamoEntity;
import exceptions.ImagenException;
import exceptions.PersonaException;
import hibernate.HibernateUtil;
import modelo.Imagen;
import modelo.Persona;
import modelo.Reclamo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ImagenDAO {

	private static ImagenDAO instancia;

	private ImagenDAO() { }
	
	public static ImagenDAO getInstancia() {
		if(instancia==null)
			instancia = new ImagenDAO();
		return instancia;
	}

	public List<Imagen> findByReclamoId(Integer idReclamo) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		List<ImagenEntity> imagenes = (List<ImagenEntity>) s.createQuery("from ImagenEntity i where i.reclamo.id = ?").setInteger(0, idReclamo).list();
		s.getTransaction().commit();
		if(imagenes != null) {
			return toNegocio(imagenes);
		} else {
			return Collections.emptyList();
		}
	}
	
	List<Imagen> toNegocio(List<ImagenEntity> imagenesEntities) {
		return imagenesEntities.stream().map(ie -> new Imagen(ie.getPath(),ie.getTipo())).collect(Collectors.toList());
	}


	public int saveImage(Reclamo reclamo, List<Imagen> imagenes) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		int id = (Integer) s.save(new ReclamoEntity(reclamo.getPersona().toEntity(), reclamo.getEdificio().toEntity(), reclamo.getUnidad().toEntity(), reclamo.getUbicacion(), reclamo.getDescripcion()));
		imagenes.forEach(imagen -> s.save(new ImagenEntity(imagen.getPath(), imagen.getTipo(), reclamo.toEntity())));
		s.getTransaction().commit();
		return id;
	}
}
