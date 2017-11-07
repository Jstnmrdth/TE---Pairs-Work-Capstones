package com.techelevator.npgeek.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.npgeek.model.park.Parks;
import com.techelevator.npgeek.model.park.ParksDao;

@Controller
public class NPWHomeController {
	
	@Autowired
	private ParksDao dao;
	
	@RequestMapping(path= "/", method=RequestMethod.GET)
	public String displayHomePage(HttpServletRequest request) {
		List<Parks> parks = dao.getAllParks();
		request.setAttribute("parks", parks);
		return "homePage";
	}
}
