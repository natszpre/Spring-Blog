package org.springframework.blog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DaoPost {

	@PersistenceContext
	EntityManager entityManager;


	@Transactional
	public void persistPost(Post post) {
		entityManager.persist(post);
	}

	@Transactional
	public int getNumberOfPosts() {
		return entityManager.createQuery(
				"SELECT post FROM Post post").getResultList().size();
	}
	
	@Transactional
	public int getNumberOfUnpublishedPosts(String user) {
		return entityManager.createQuery(
				"SELECT post FROM Post post WHERE enabled =0 AND user=:user")
				.setParameter("user", user)
				.getResultList()
				.size();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Post> getPosts(int firstResult, int maxResults) {
		Query query = entityManager.createQuery("SELECT post FROM Post post WHERE post.enabled =1 ORDER BY post.date DESC");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List<Post> results = query.getResultList();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Post> getUnpublishedPosts(int firstResult, int maxResults, String user) {
		Query query = entityManager.createQuery(
				"SELECT post FROM Post post WHERE post.enabled =0 AND post.user=:user ORDER BY post.date DESC")
				.setParameter("user", user);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List<Post> results = query.getResultList();
		return results;
	}
	
	
	@Transactional
	public Post getPost(int id) {
		return entityManager.find(Post.class, id);		
	}
	
	@Transactional
	public void updatePost(int id, String title, String content) {
		entityManager.createQuery(
				"UPDATE Post post SET title=:title, content=:content WHERE id=:id")
				.setParameter("id", id)
				.setParameter("content", content)
				.setParameter("title", title)
				.executeUpdate();  
	}
	
	@Transactional
	public void publishPost(int id) {
		entityManager.createQuery(
				"UPDATE Post post SET post.enabled =1 WHERE post.id=:id")
				.setParameter("id", id)
				.executeUpdate();  
	}
	
	@Transactional
	public void dropPost(int id) {
		entityManager.createQuery("DELETE FROM Post post where id=:id")
				.setParameter("id", id)
				.executeUpdate();	
	}

}
