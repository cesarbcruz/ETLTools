package com.cesar.etltools.dao;

import com.cesar.etltools.dominio.Entity;
import com.cesar.etltools.dominio.Source;
import org.hibernate.Session;

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

    public void deleteBySource(Source s) {
        try {
            session.getTransaction().begin();
            List<Entity> entitys = (List<Entity>) session.createQuery("from Entity e where source = :source")
                    .setParameter("source", s).list();
            for (Entity e : entitys) {
                session.createQuery("DELETE FROM Field e where entity = :entity")
                        .setParameter("entity", e).executeUpdate();
            }
            session.createQuery("DELETE FROM Entity e where source = :source")
                    .setParameter("source", s).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }
}
