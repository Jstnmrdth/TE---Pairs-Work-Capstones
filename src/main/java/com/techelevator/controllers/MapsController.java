package com.techelevator.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.capstone.dao.BreweryDao;
import com.techelevator.capstone.model.Brewery;

@Controller
public class MapsController {
	
	private BreweryDao breweryDao;
	
	@Autowired
	public MapsController(BreweryDao breweryDao) {
		this.breweryDao = breweryDao;
	}
	
	@RequestMapping(path = "/maps", method = RequestMethod.GET)
	public String displayTheMapsPage(HttpServletRequest request) {
		List<Brewery> breweryList = breweryDao.getAllBreweries();
		request.setAttribute("breweryList", breweryList);
		return "maps";
		
	}

}
