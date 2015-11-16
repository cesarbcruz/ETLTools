package com.cesar.etltools.dao;


import com.cesar.etltools.dominio.Destination;
import org.hibernate.Session;

import com.cesar.etltools.dominio.Task;
import java.util.List;

public class DestinationDao {

    private final Session session;

    public DestinationDao(Session session) {
        this.session = session;
    }

    public Destination porId(int id) {
        return (Destination) session.load(Destination.class, id);
    }

    public List<Destination> porTask(Task t) {
        return (List<Destination>) session.createQuery("from Destination d where task = :task")
                .setParameter("task", t).list();
    }

    public List<Destination> list() {
        return (List<Destination>) session.createQuery("from Destination d").list();
    }

    public void salvar(Destination o) {
        try {
            session.getTransaction().begin();
            session.save(o);
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
