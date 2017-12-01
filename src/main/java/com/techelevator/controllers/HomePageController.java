package com.techelevator.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.capstone.dao.BreweryDao;
import com.techelevator.capstone.model.Brewery;

@Controller
public class HomePageController {
	
	private BreweryDao breweryDao;
	
	@Autowired
	public HomePageController(BreweryDao breweryDao) {
		this.breweryDao = breweryDao;
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String displayHomePage(HttpServletRequest request) {
		Brewery landGrant = breweryDao.getBreweryByBreweryId(1);
		request.setAttribute("landGrant", landGrant);
		return "homePage";
	}

}
