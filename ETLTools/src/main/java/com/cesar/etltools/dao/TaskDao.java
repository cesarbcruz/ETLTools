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
        try {
            session.getTransaction().begin();
            if(task.getId()>0){
                session.merge(task);                
            }else{
                session.save(task);                
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }

    }
    public void deletar(Task task) {
        try {
            session.getTransaction().begin();
            session.delete(task);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }
}
