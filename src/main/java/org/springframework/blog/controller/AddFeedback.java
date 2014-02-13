package org.springframework.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddFeedback {
	
	@RequestMapping("/addfeedback/{postid}")
	public ModelAndView addNewPost(@PathVariable(value = "postid") int id,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	ModelAndView model = new ModelAndView("addfeedback");
	model.addObject("postid", id);
	
	return model;
	}
}
