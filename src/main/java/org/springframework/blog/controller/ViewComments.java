package org.springframework.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.blog.dao.DaoComments;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewComments {
	
	@Autowired
	private DaoComments daoComments;
	
	@Autowired
	private MailSender mailSender;

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

		if(daoComments.getLanguage(id).equals("pl")) {
			sendMail("Spring-blog-app", daoComments.getMail(id), "TEST", "Komentarz jest widoczny");
		} else if(daoComments.getLanguage(id).equals("en")) {
			sendMail("Spring-blog-app", daoComments.getMail(id), "TEST", "Comment is visible.");
		}
		
		return model;
	}
	
	public void sendMail(String from, String to, String subject, String msg) {
		 
		SimpleMailMessage message = new SimpleMailMessage();
 
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}
}
