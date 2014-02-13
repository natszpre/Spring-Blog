package org.springframework.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.blog.dao.DaoComments;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewComments {
	
	@Autowired
	private DaoComments daoComments;

	@RequestMapping("/viewcomments/{postid}")
	public ModelAndView viewComments(@PathVariable(value = "postid") int id) {

		ModelAndView model = new ModelAndView("viewcomments");
		model.addObject("postid", id);

		model.addObject("comments", daoComments.getComments());
		
		return model;
	}
	@RequestMapping("/viewcomments/viewunpublishedcomments")
	public ModelAndView viewUnpublishedComments() {

		ModelAndView model = new ModelAndView("viewunpublishedcomments");

		model.addObject("comments", daoComments.getUnpublishedComments());
		
		return model;
	}
	
	@RequestMapping("/viewcomments/{postid}/publish")
	public ModelAndView publishComments(@PathVariable(value = "postid") int id) {

		ModelAndView model = new ModelAndView("publishcommentsuccess");
		model.addObject("postid", id);

		daoComments.publishComments(id);
		
		return model;
	}
}
