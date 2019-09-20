package daos;

import entities.ReclamoEntity;
import exceptions.ReclamoException;
import hibernate.HibernateUtil;
import modelo.Reclamo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
        List<Reclamo> resultado = new ArrayList();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List<ReclamoEntity> reclamos = s.createQuery("from ReclamoEntity").list();
        Iterator iterator = reclamos.iterator();

        while(iterator.hasNext()) {
            ReclamoEntity e = (ReclamoEntity)iterator.next();
            resultado.add(this.toNegocio(e));
        }

        s.getTransaction().commit();
        return resultado;
    }

    public void save(Reclamo reclamo) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.save(new ReclamoEntity(reclamo.getDocumento(), reclamo.getCodigo(), reclamo.getUbicacion(), reclamo.getDescripcion(), reclamo.getIdentificador()));
        s.getTransaction().commit();
    }

    Reclamo toNegocio(ReclamoEntity e) throws ReclamoException {
        if (e != null) {
            return new Reclamo(e.getIdReclamo(), e.getDocumento(), e.getCodigo(), e.getUbicacion(), e.getDescripcion(), e.getIdentificador());
        } else {
            throw new ReclamoException("No se pudieron recuperar los reclamos");
        }
    }

}
