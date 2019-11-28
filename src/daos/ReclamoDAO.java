package daos;

import entities.ImagenEntity;
import entities.ReclamoEntity;
import exceptions.ReclamoException;
import hibernate.HibernateUtil;
import modelo.Imagen;
import modelo.Reclamo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ReclamoDAO {

    private static ReclamoDAO instancia;

    private ReclamoDAO() { }

    public static ReclamoDAO getInstancia() {
        if(instancia==null)
            instancia = new ReclamoDAO();
        return instancia;
    }

    public int save(Reclamo reclamo, List<Imagen> imagenes) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        ReclamoEntity reclamoEntity = new ReclamoEntity(reclamo.getPersona().toEntity(), reclamo.getEdificio().toEntity(), reclamo.getUnidad().toEntity(), reclamo.getUbicacion(), reclamo.getDescripcion());
        int id = (Integer) s.save(reclamoEntity);
        imagenes.forEach(imagen -> s.save(new ImagenEntity(imagen.getPath(), imagen.getTipo(), reclamoEntity)));
        s.getTransaction().commit();
        return id;
    }

    public Reclamo getById(int idReclamo) throws ReclamoException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        ReclamoEntity reclamo = (ReclamoEntity) s.createQuery("from ReclamoEntity r where r.id = ?").setInteger(0, idReclamo).uniqueResult();
        s.getTransaction().commit();
        if (reclamo == null) {
            throw new ReclamoException("No existe el reclamo con id " + idReclamo);
        }
        return reclamo.toNegocio();
    }

    public List<Reclamo> getByDNI(String documento) throws ReclamoException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List<ReclamoEntity> reclamos = (List<ReclamoEntity>) s.createQuery("from ReclamoEntity r where r.persona.documento = ?").setString(0, documento).list();
        s.getTransaction().commit();
        if (reclamos == null || reclamos.isEmpty()) {
            throw new ReclamoException("No existen reclamos para la persona con DNI " + documento);
        }
        return reclamos.stream().map(ReclamoEntity::toNegocio).collect(Collectors.toList());
    }
}
