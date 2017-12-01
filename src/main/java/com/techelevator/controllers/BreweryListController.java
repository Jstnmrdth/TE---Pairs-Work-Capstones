package com.techelevator.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.capstone.dao.BeerDao;
import com.techelevator.capstone.dao.BreweryDao;
import com.techelevator.capstone.model.Beer;
import com.techelevator.capstone.model.Brewery;

@Controller
public class BreweryListController {
	private BreweryDao breweryDao;
	
	@Autowired
	BeerDao beerDao;
	
	@Autowired
	public BreweryListController(BreweryDao breweryDao) {
		this.breweryDao = breweryDao;		
	}
	
	@RequestMapping(path = "/breweryList", method = RequestMethod.GET)
	public String displayBreweryList(HttpServletRequest request) {
		List<Brewery> breweryList = breweryDao.getAllBreweries();
		request.setAttribute("breweries", breweryList);
		
		return "breweryList";
	}
	
	@RequestMapping(path = "/addBrewery", method = RequestMethod.GET)
	public String displayAddBreweryForm() {
		return "addBrewery";
	}
	
	@RequestMapping(path = "/addBrewery", method = RequestMethod.POST)
	public String updateUnits(@RequestParam String name, @RequestParam String description, @RequestParam int yearFounded, @RequestParam String address) {
		breweryDao.addBreweryToDb(name, address, description, yearFounded);
		return "redirect:/";
	}
	
	@RequestMapping(path = "/breweryDetailsPage", method = RequestMethod.GET)
	public String displayDetailsPage(HttpServletRequest request, @RequestParam int breweryId) {
		Brewery brewery = breweryDao.getBreweryByBreweryId(breweryId);
		request.setAttribute("brewery", brewery);
		
		List<Beer> beer = beerDao.getAllBeer(breweryId);
		request.setAttribute("beer", beer);
		
		return "breweryDetailsPage";
	}

}

















