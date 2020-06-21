package br.com.mailtodo.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.mailtodo.entity.Task;

@Stateless
public class TaskDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Task task) {
		if (task.getId() == null) {
			em.persist(task);
			return;
		}
		em.merge(task);
	}

	public List<Task> findAll() {
		return em.createQuery("FROM Task", Task.class).getResultList();
	}

	public Optional<Task> findById(Integer id) {
		String query = "SELECT t FROM Task t WHERE t.id = :id";
		Task task = em.createQuery(query, Task.class).setParameter("id", id).getSingleResult();
		return Optional.of(task);
	}

	public void delete(Task task) {
		em.remove(task);
	}

}
