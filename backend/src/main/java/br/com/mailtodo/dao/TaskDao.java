package br.com.mailtodo.dao;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.mailtodo.entity.Priority;
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
		TypedQuery<Task> typedQuery = em.createQuery("SELECT t FROM Task t WHERE t.id = :id", Task.class).setParameter("id", id);
		try {
			Task task = typedQuery.getSingleResult();
			return Optional.of(task);
		} catch (NoResultException e) {
			Logger.getLogger(TaskDao.class.getName()).info(e.getLocalizedMessage());
		}
		return Optional.empty();
	}

	public void delete(Task task) {
		em.remove(task);
	}
	
	public List<Task> findTasksToMail() {
		return em
				.createQuery("SELECT t FROM Task t WHERE t.done = :done AND t.priority = :priority", Task.class)
				.setParameter("done", false)
				.setParameter("priority", Priority.HIGH)
				.getResultList();
	}

}
