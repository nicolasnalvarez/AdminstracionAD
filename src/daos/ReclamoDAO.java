package daos;

import entities.ImagenEntity;
import entities.ReclamoEntity;
import exceptions.ReclamoException;
import hibernate.HibernateUtil;
import modelo.Imagen;
import modelo.Reclamo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ReclamoDAO {

    private static ReclamoDAO instancia;

    private ReclamoDAO() { }

    public static ReclamoDAO getInstancia() {
        if(instancia==null)
            instancia = new ReclamoDAO();
        return instancia;
    }

    public List<Reclamo> getAll() throws ReclamoException {
        List<Reclamo> resultado = new ArrayList<>();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List<ReclamoEntity> reclamos = s.createQuery("from ReclamoEntity").list();
        for (ReclamoEntity e : reclamos) {
            resultado.add(this.toNegocio(e));
        }
        s.getTransaction().commit();
        return resultado;
    }

    public int save(Reclamo reclamo, List<Imagen> imagenes) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        int id = (Integer) s.save(new ReclamoEntity(reclamo.getPersona().toEntity(), reclamo.getEdificio().toEntity(), reclamo.getUnidad().toEntity(), reclamo.getUbicacion(), reclamo.getDescripcion()));
        imagenes.forEach(imagen -> s.save(new ImagenEntity(imagen.getPath(), imagen.getTipo(), imagen.getReclamo().toEntity())));
        s.getTransaction().commit();
        return id;
    }

    private Reclamo toNegocio(ReclamoEntity reclamoEntity) throws ReclamoException {
        if (reclamoEntity != null) {
            return new Reclamo(reclamoEntity.getId(), reclamoEntity.getPersona().toNegocio(), reclamoEntity.getEdificio().toNegocio(), reclamoEntity.getUnidad().toNegocio(), reclamoEntity.getUbicacion(), reclamoEntity.getDescripcion());
        } else {
            throw new ReclamoException("No se pudieron recuperar los reclamos");
        }
    }

    public Reclamo getById(int idReclamo) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        ReclamoEntity reclamo = (ReclamoEntity) s.createQuery("from ReclamoEntity r where r.id = ?").setInteger(0, idReclamo).uniqueResult();
        s.getTransaction().commit();
        return reclamo.toNegocio();
    }
}
