package com.cesar.etltools.dao;

import com.cesar.etltools.dominio.Field;
import org.hibernate.Session;

import com.cesar.etltools.dominio.Task;
import java.util.List;

public class FieldDao {

    private final Session session;

    public FieldDao(Session session) {
        this.session = session;
    }

    public Field porId(int id) {
        return (Field) session.load(Field.class, id);
    }

    public List<Field> porTask(Task t) {
        return (List<Field>) session.createQuery("from Field s where task = :task")
                .setParameter("task", t).list();
    }

    public List<Field> list() {
        return (List<Field>) session.createQuery("from Field s").list();
    }

    public void salvar(Field o) {
        try {
            session.getTransaction().begin();
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public void atualizar(Field r) {
        try {
            session.getTransaction().begin();
            session.merge(r);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }

    }

    public void deletar(Field s) {
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
