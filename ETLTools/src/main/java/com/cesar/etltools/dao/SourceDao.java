package com.cesar.etltools.dao;

import com.cesar.etltools.dominio.AddressSource;
import com.cesar.etltools.dominio.Source;
import org.hibernate.Session;

import com.cesar.etltools.dominio.Task;
import java.util.List;

public class SourceDao {

    private final Session session;

    public SourceDao(Session session) {
        this.session = session;
    }

    public Source porId(int id) {
        return (Source) session.load(Source.class, id);
    }

    public Source byTask(Task t) {
        return (Source) session.createQuery("from Source s where task = :task")
                .setParameter("task", t).uniqueResult();
    }

    public List<Source> list() {
        return (List<Source>) session.createQuery("from Source s").list();
    }

    public void salvar(Source s) {
        try {
            session.getTransaction().begin();
            if (s.getId() > 0) {
                session.merge(s);
            } else {
                session.save(s);
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public void atualizar(AddressSource r) {
        try {
            session.getTransaction().begin();
            session.merge(r);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }

    }

    public void deletar(Source s) {
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
