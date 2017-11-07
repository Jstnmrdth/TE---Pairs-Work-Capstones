package com.techelevator.ssg.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.ssg.model.forum.ForumDao;
import com.techelevator.ssg.model.forum.ForumPost;

@Controller
public class ForumController {
	
	@Autowired
	private ForumDao dao;

	@RequestMapping(path= {"/forumPost"}, method=RequestMethod.GET)
	public String handleInput() {
		return "forumInput";
	}

	@RequestMapping(path= {"/forumPost"}, method=RequestMethod.POST)
	public String handleOutput(ForumPost post) {
		post.setDatePosted(LocalDateTime.now());
		dao.save(post);
		return "redirect:/forum";
	}
	
	@RequestMapping("/forum")
		public String getAllPosts(HttpServletRequest request) {
		request.setAttribute("posts", dao.getAllPosts());
		return "forum";
	}
}