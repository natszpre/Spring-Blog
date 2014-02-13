package org.springframework.blog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DaoComments {

	@PersistenceContext
	EntityManager entityManager;


	@Transactional
	public void persistComments(Comments comment) {
		entityManager.persist(comment);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Comments> getComments() {
		return entityManager.createQuery("SELECT comments FROM Comments comments WHERE comments.enabled=1")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Comments> getUnpublishedComments() {
		return entityManager.createQuery("SELECT comments FROM Comments comments WHERE comments.enabled=0")
				.getResultList();
	}
	
	@Transactional
	public void publishComments(int id) {
		entityManager.createQuery(
				"UPDATE Comments comments SET enabled=1 WHERE id=:id")
				.setParameter("id", id)
				.executeUpdate();  
	}
	
	@Transactional
	public String getMail(int id) {
		return entityManager.createQuery("SELECT email FROM Comments comments WHERE comments.id=:id")
				.setParameter("id", id)
				.getSingleResult()
				.toString();
	}
	
	@Transactional
	public String getLanguage(int id) {
		return entityManager.createQuery("SELECT language FROM Comments comments WHERE comments.id=:id")
				.setParameter("id", id)
				.getSingleResult()
				.toString();
	}
}
