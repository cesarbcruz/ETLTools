package com.cesar.etltools.dao;

import com.cesar.etltools.dominio.Entity;
import org.hibernate.Session;

import com.cesar.etltools.dominio.Task;
import java.util.List;

public class EntityDao {

    private final Session session;

    public EntityDao(Session session) {
        this.session = session;
    }

    public Entity porId(int id) {
        return (Entity) session.load(Entity.class, id);
    }

    public List<Entity> porTask(Task t) {
        return (List<Entity>) session.createQuery("from Entity s where task = :task")
                .setParameter("task", t).list();
    }

    public List<Entity> list() {
        return (List<Entity>) session.createQuery("from Entity s").list();
    }

    public void salvar(Entity o) {
        try {
            session.getTransaction().begin();
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public void atualizar(Entity r) {
        try {
            session.getTransaction().begin();
            session.merge(r);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }

    }

    public void deletar(Entity s) {
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
