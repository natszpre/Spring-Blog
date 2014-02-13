package org.springframework.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.blog.dao.DaoPost;
import org.springframework.blog.dao.DaoUsers;
import org.springframework.blog.dao.Users;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {

	private PasswordEncoder encoder = new Md5PasswordEncoder();

	@Autowired
	private DaoUsers daoUsers;

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username != null && password != null) {
			Users users = new Users();
			users.setPassword(encoder.encodePassword(password, username));// username as salt
			users.setUsername(username);
			users.setEnabled(1);
			users.setRole("ROLE_USER");
			daoUsers.persistUsers(users);
		}
		
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public ModelAndView loginerror() {
		ModelAndView model = new ModelAndView("login");
		model.addObject("error", "true");
		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		return new ModelAndView("login");
	}

}