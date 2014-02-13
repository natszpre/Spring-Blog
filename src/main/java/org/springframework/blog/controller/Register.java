package org.springframework.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Register {
	
	@RequestMapping("/register")
	public ModelAndView addNewPost(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	return new ModelAndView("register");
	}
}
