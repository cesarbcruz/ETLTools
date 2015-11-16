package com.cesar.etltools.dao;


import com.cesar.etltools.dominio. AddressSource;
import org.hibernate.Session;

import com.cesar.etltools.dominio.Task;
import java.util.List;

public class AddressSourceDao {

    private final Session session;

    public AddressSourceDao(Session session) {
        this.session = session;
    }

    public  AddressSource porId(int id) {
        return ( AddressSource) session.load( AddressSource.class, id);
    }

    public List< AddressSource> porTask(Task t) {
        return (List< AddressSource>) session.createQuery("from  AddressSource a where task = :task")
                .setParameter("task", t).list();
    }

    public List< AddressSource> list() {
        return (List< AddressSource>) session.createQuery("from  AddressSource a").list();
    }

    public void salvar( AddressSource a) {
        try {
            session.getTransaction().begin();
            session.save(a);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public void atualizar(AddressSource a) {
        try {
            session.getTransaction().begin();
            session.merge(a);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }

    }

    public void deletar( AddressSource a) {
        try {
            session.getTransaction().begin();
            session.delete(a);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }

    }
}
