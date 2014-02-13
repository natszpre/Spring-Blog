package org.springframework.blog.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.blog.dao.Comments;
import org.springframework.blog.dao.DaoComments;
import org.springframework.blog.dao.DaoPost;
import org.springframework.blog.dao.Post;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewPosts {

	private static Logger slf4jLogger = LoggerFactory
			.getLogger(ViewPosts.class);
	
	@Autowired
	private DaoPost daoPost;
	
	@Autowired
	private DaoComments daoComments;

	@RequestMapping("/viewposts")
	public ModelAndView displayPosts(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("viewposts");
		
		User user = null;
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Post post = new Post();

		if (title != null && content != null) {
			post.setTitle(title);
			post.setContent(content);
			post.setDate(new Date());
			post.setEnabled(0);
			try {
				user = (User) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
				slf4jLogger.info("User <{}> added post.",
						user.getUsername() + 1);
				post.setUser(user.getUsername().toString());
			} catch (ClassCastException e) {
				// jeśli niezalogowany użytkownik
				slf4jLogger.info("User <anonim> added post.");
				post.setUser("ROLE_ANONIM");
			}			
			daoPost.persistPost(post);
		}

		List<Post> list = daoPost.getPosts(0, 10);
		model.addObject("posts", list);

		model.addObject("numOfPages", (daoPost.getNumberOfPosts() / 10) + 1);

		return model;
	}

	@RequestMapping("/viewposts/{pageNum}")
	public ModelAndView displayPostNextPage(
			@PathVariable(value = "pageNum") int pageNum) {

		ModelAndView model = new ModelAndView("viewposts");

		int firstResult = (pageNum - 1) * 10;
		int maxResults = 10;

		List<Post> list = daoPost.getPosts(firstResult, maxResults);
		model.addObject("posts", list);

		model.addObject("numOfPages", (daoPost.getNumberOfPosts() / 10) + 1);

		return model;
	}

	@RequestMapping("/viewposts/{postid}/display")
	public ModelAndView displayPost(@PathVariable(value = "postid") int id,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("viewpost");

		Post post = daoPost.getPost(id);

		model.addObject("title", post.getTitle());
		model.addObject("content", post.getContent());

		return model;
	}
	
	@RequestMapping("/viewposts/{postid}/update")
	public ModelAndView displayUpdatePostConfirmation(@PathVariable(value = "postid") int id,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("updatepostsuccess");

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		daoPost.updatePost(id, title, content);

		model.addObject("title", title);
		model.addObject("content", content);

		return model;
	}

	@RequestMapping("/viewposts/{postid}/drop")
	public ModelAndView displayDropPostConfirmation(
			@PathVariable(value = "postid") int id) {
	
		User user = (User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		slf4jLogger.info("User <{}> dropped post.", user.getUsername());

		daoPost.dropPost(id);
		return new ModelAndView("droppostsuccess");
	}
	
	@RequestMapping("/viewposts/{postid}/comments")
	public ModelAndView displayPostsWithComments(
			@PathVariable(value = "postid") int id,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("addcommentssuccess");
		
		String content = request.getParameter("content");
		String email = request.getParameter("email");

		Comments comments = new Comments();
		User user = null;

		if (content != null) {
			comments.setContent(content);
			comments.setEmail(email);
			comments.setEnabled(0);
			comments.setPost_id(id);
			try {
				user = (User) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
				slf4jLogger.info("User <{}> added comment.",
						user.getUsername() + 1);
				comments.setUsername(user.getUsername().toString());
			} catch (ClassCastException e) {
				// jeśli niezalogowany użytkownik
				slf4jLogger.info("User <anonim> added comment.");
				comments.setUsername("ROLE_ANONIM");
			}			
			daoComments.persistComments(comments);
		}

		return model;
	}
	
}
