package com.cesar.etltools.dao;

import com.cesar.etltools.dominio.AddressSource;
import com.cesar.etltools.dominio.Source;
import org.hibernate.Session;

import java.util.List;

public class AddressSourceDao {

    private final Session session;

    public AddressSourceDao(Session session) {
        this.session = session;
    }

    public AddressSource porId(int id) {
        return (AddressSource) session.load(AddressSource.class, id);
    }

    public List< AddressSource> bySource(Source s) {
        return (List< AddressSource>) session.createQuery("from  AddressSource a where source = :source")
                .setParameter("source", s).list();
    }

    public List< AddressSource> list() {
        return (List< AddressSource>) session.createQuery("from  AddressSource a").list();
    }

    public void salvar(AddressSource a) {
        try {
            session.getTransaction().begin();
            if (a.getId() > 0) {
                session.merge(a);
            } else {
                session.save(a);
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public void deletar(AddressSource e) {
        try {
            session.getTransaction().begin();
            session.delete(e);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }
}
