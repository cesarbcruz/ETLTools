package com.cesar.etltools.dao;

import com.cesar.etltools.dominio.Entity;
import com.cesar.etltools.dominio.Source;
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

    public List<Entity> bySource(Source s) {
        return (List<Entity>) session.createQuery("from Entity e where source = :source")
                .setParameter("source", s).list();
    }

    public List<Entity> list() {
        return (List<Entity>) session.createQuery("from Entity s").list();
    }

    public void salvar(Entity e) {
        try {
            session.getTransaction().begin();
            if (e.getId() > 0) {
                session.merge(e);
            } else {
                session.save(e);
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public void deletar(Entity e) {
        try {
            session.getTransaction().begin();
            session.delete(e);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public void deletarBySource(Source s) {
        try {
            session.getTransaction().begin();
            session.createQuery("delete from Entity where source.id= :sourceId").setInteger("sourceId", s.getId()).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }
}
