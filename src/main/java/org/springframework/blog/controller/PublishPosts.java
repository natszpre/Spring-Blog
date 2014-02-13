package org.springframework.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.blog.dao.DaoPost;
import org.springframework.blog.dao.Post;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PublishPosts {
	
	private static Logger slf4jLogger = LoggerFactory.getLogger(PublishPosts.class);
	
	@Autowired
	private DaoPost daoPost;
	
	@RequestMapping("/unpublishedposts")
	public ModelAndView displayUnpublishedPosts() {
		
		ModelAndView model = new ModelAndView("unpublishedposts");
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		slf4jLogger.info("User <{}> is online.", user.getUsername());
		
		List<Post> list = daoPost.getUnpublishedPosts(0, 10, user.getUsername().toString());
		model.addObject("posts", list);

		model.addObject("numOfPages", (daoPost.getNumberOfUnpublishedPosts(user.getUsername().toString()) / 10) + 1);
		
		return model;

	}
	
	@RequestMapping("/unpublishedposts/{pageNum}")
	public ModelAndView displayUnpublishedPostsNextPage(@PathVariable(value = "pageNum") int pageNum) {
		
		ModelAndView model = new ModelAndView("unpublishedposts");
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		slf4jLogger.info("User <{}> is online.", user.getUsername());
		
		int firstResult = (pageNum - 1) * 10;
		int maxResults = 10;
		
		List<Post> list = daoPost.getUnpublishedPosts(firstResult, maxResults, user.getUsername().toString());
		model.addObject("posts", list);

		model.addObject("numOfPages", (daoPost.getNumberOfUnpublishedPosts(user.getUsername().toString()) / 10) + 1);
		
		return model;

	}
	
	@RequestMapping(value="/unpublishedposts/{postid}/publishpost", method=RequestMethod.GET)
	public ModelAndView publishPost( @PathVariable(value="postid") int id) {		
		
		ModelAndView model = new ModelAndView("publishpostsuccess");	
		
		daoPost.publishPost(id);
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		slf4jLogger.info("User <{}> published post.", user.getUsername());
		
		Post post = daoPost.getPost(id);
		model.addObject("post", post);	
		
		return model;
	}
	
}

