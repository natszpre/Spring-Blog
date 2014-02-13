package org.springframework.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

//admin ma widziec wszytkie posty i przycisk publikuj tylko przy tych nieopublikowanych
//w sumie to uzytkownik powinien miec swoj panel.. dopytac



@Controller
public class AdminPanel {
	
	private static Logger slf4jLogger = LoggerFactory.getLogger(AdminPanel.class);
	
	@Autowired
	private DaoPost daoPost;
	
	@RequestMapping("/adminpanel")
	public ModelAndView adminPanel(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		
		ModelAndView model = new ModelAndView("adminpanel");	

		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		slf4jLogger.info("User <{}> is online.", user.getUsername());
		
		List<Post> list = daoPost.getPosts(0, 10);
		model.addObject("posts", list);

		model.addObject("numOfPages", (daoPost.getNumberOfPosts() / 10) + 1);
		
		return model;
	}
	
	@RequestMapping("/adminpanel/{pageNum}")
	public ModelAndView adminPanelNextPage(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "pageNum") int pageNum
			) throws Exception {		
		
		ModelAndView model = new ModelAndView("adminpanel");	

		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		slf4jLogger.info("User <{}> is online.", user.getUsername());
		
		int firstResult = (pageNum - 1) * 10;
		int maxResults = 10;

		List<Post> list = daoPost.getPosts(firstResult, maxResults);
		model.addObject("posts", list);

		model.addObject("numOfPages", (daoPost.getNumberOfPosts() / 10) + 1);
		
		return model;
	}
	
	@RequestMapping(value="/adminpanel/{postid}/updatepost", method=RequestMethod.GET)
	public ModelAndView updatePost( @PathVariable(value="postid") int id) {		
		
		ModelAndView model = new ModelAndView("updatepost");	
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		slf4jLogger.info("User <{}> updated post.", user.getUsername());
		
		Post post = daoPost.getPost(id);
		model.addObject("post", post);	
		
		return model;
	}
		
	@RequestMapping(value="/adminpanel/{postid}/droppost", method=RequestMethod.GET)
	public ModelAndView dropPost( @PathVariable(value="postid") int id) {		
		
		ModelAndView model = new ModelAndView("droppost");
		model.addObject("postid", id);		
		
		return model;
	}
	
}
