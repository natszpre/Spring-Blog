package org.springframework.blog.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DaoUsers {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void persistUsers(Users users) {
		entityManager.persist(users);
	}

}
