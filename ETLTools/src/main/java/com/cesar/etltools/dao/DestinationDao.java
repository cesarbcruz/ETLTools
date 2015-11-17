package com.cesar.etltools.dao;

import com.cesar.etltools.dominio.Destination;
import com.cesar.etltools.dominio.Source;
import org.hibernate.Session;

import java.util.List;

public class DestinationDao {

    private final Session session;

    public DestinationDao(Session session) {
        this.session = session;
    }

    public Destination byId(int id) {
        return (Destination) session.load(Destination.class, id);
    }

    public Destination bySource(Source s) {
        return (Destination) session.createQuery("from Destination d where source = :source")
                .setParameter("source", s).uniqueResult();
    }

    public List<Destination> list() {
        return (List<Destination>) session.createQuery("from Destination d").list();
    }

    public void salvar(Destination d) {
        try {
            session.getTransaction().begin();
            if (d.getId() > 0) {
                session.merge(d);
            } else {
                session.save(d);
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public void atualizar(Destination r) {
        try {
            session.getTransaction().begin();
            session.merge(r);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }

    }

    public void deletar(Destination s) {
        try {
            session.getTransaction().begin();
            session.delete(s);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }

    }
}
