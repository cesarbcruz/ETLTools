package com.cesar.etltools.dao;

import org.hibernate.Session;

import com.cesar.etltools.dominio.Task;
import java.util.List;

public class TaskDao {

    private final Session session;

    public TaskDao(Session session) {
        this.session = session;
    }

    public Task porId(int id) {
        return (Task) session.load(Task.class, id);
    }

    public List<Task> porDescricao(String description) {
        return (List<Task>) session.createQuery("from Task t where t.description = :description")
                .setParameter("description", description).list();
    }
    
    public List<Task> list() {
        return (List<Task>) session.createQuery("from Task t").list();
    }

    public void salvar(Task task) {
        session.save(task);
    }

    public void atualizar(Task task) {
        session.merge(task);
    }

    public void deletar(Task task) {
        session.delete(task);
    }

    public void begin() {
        session.getTransaction().begin();
    }
    
    public void commit() {
        session.getTransaction().commit();
    }
    
    public void rollback() {
        session.getTransaction().rollback();
    }
}
