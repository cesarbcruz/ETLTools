package com.cesar.etltools.dao;

import org.hibernate.Session;

import com.cesar.etltools.dominio.Task;

public class TaskDao{ 

	private final Session session;

	public TaskDao(Session session) {
		this.session = session;
	}
	
	public Task porId(int id) {
		return (Task) session.load(Task.class, id);
	}
	
	public Task porDescricao(String description, String email) {
		return (Task) session.createQuery("from Task t where t.description = :description")
				.setParameter("description", description)
				.uniqueResult();
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
}
