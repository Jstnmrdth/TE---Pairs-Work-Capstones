package com.techelevator.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.capstone.dao.BeerDao;
import com.techelevator.capstone.dao.BreweryDao;
import com.techelevator.capstone.dao.UserDao;
import com.techelevator.capstone.model.Beer;
import com.techelevator.capstone.model.Brewery;
import com.techelevator.capstone.model.User;

@Controller
public class UserController {
	
	private UserDao userDao;
	private BeerDao beerDao;
	private BreweryDao breweryDao;
	
	
	@Autowired
	public UserController(BeerDao beerDao, BreweryDao breweryDao, UserDao userDao) {
		this.userDao = userDao;
		this.beerDao = beerDao;
		this.breweryDao = breweryDao;
	}
	
	@RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
	public String showUserDashboard(ModelMap model, @PathVariable String username) {
		return "userDashboard";
	}
	
	@RequestMapping(path = "/user/{username}/changePassword", method = RequestMethod.GET)
	public String displayChangePasswordPage(ModelMap model, @PathVariable String username) {
		return "changePassword";
	}
	
	@RequestMapping(path="/user/{userName}/changePassword", method = RequestMethod.POST)
	public String changePassword(@PathVariable String userName, @RequestParam String password) {
		userDao.updatePassword(userName, password);
		return "userDashboard";
	}
	
	@RequestMapping(path="/user/{username}/addBeer", method=RequestMethod.GET)
	public String displayAddBeerPage(HttpServletRequest request, ModelMap model, @PathVariable String username) {
		List<Brewery> breweryList = breweryDao.getAllBreweries();
		request.setAttribute("breweries", breweryList);
		return "addBeer";
	}
	
	@RequestMapping(path="/user/{username}/addBeer", method = RequestMethod.POST)
	public String addBeer(@RequestParam int breweryId, @RequestParam String name, 
			@RequestParam String beerType, @RequestParam String description, 
			@RequestParam double abv, @RequestParam int ibu, @RequestParam String glassType) {
		beerDao.addBeer(breweryId, name, beerType, description, abv, ibu, glassType);
		return "userDashboard";
	}
	
	@RequestMapping(path="/user/{username}/deleteBeer", method = RequestMethod.POST)
	public String deleteBeer(@RequestParam int beerId) {
		beerDao.deleteBeer(beerId);
		return "userDashboard";
	}

	

	@RequestMapping(path="/user/{username}/deleteBeer", method=RequestMethod.GET)
	public String displayDeleteBeerPage(HttpServletRequest request, ModelMap model, @PathVariable String username) {
		User thisUser = userDao.getUserByUsername(username);
		List<Beer> beerList = beerDao.getAllBeer(thisUser.getBrewery().getBreweryId());
		request.setAttribute("beer", beerList);
		return "deleteBeer";
	}
	

	@RequestMapping(path="user/{username}/addBrewer", method = RequestMethod.GET)
	public String createNewBrewerAccount(@PathVariable String username, HttpServletRequest request) {
		List<Brewery> breweryList = breweryDao.getAllBreweries();
		request.setAttribute("breweries", breweryList);
		return "newBrewer";
	}
	
	@RequestMapping(path="/user/new/brewer", method=RequestMethod.POST)
	public String createNewBrewer(@RequestParam String userName, @RequestParam String password, @RequestParam int breweryId) {
		userDao.saveUser(userName, password, "brewer", breweryId);
		return "redirect:/" ;
	}
	
	@RequestMapping(path="/user/new", method=RequestMethod.GET)
	public String createUser() {
		return "newUser" ;
	}
	
	@RequestMapping(path="/user/new", method=RequestMethod.POST)
	public String createUser(@RequestParam String userName, @RequestParam String password) {
		userDao.saveUser(userName, password, "user");
		return "redirect:/login" ;
	}
	
	@RequestMapping(path="/user/deleteAccount", method = RequestMethod.POST)
	public String deleteAccount(ModelMap model, HttpSession session, @RequestParam String username) {
		userDao.deleteUser(username);
		model.remove("currentUser");
		session.removeAttribute("currentUser");
		return "redirect:/";
	}
	
	@RequestMapping(path="/user/{username}/brewer/update", method=RequestMethod.GET)
	public String updateBreweryInfo() {
		return "updateBrewery" ;
	}
	
	@RequestMapping(path = "/updateBrewery", method = RequestMethod.POST)
	public String updateUnits(@RequestParam String name, @RequestParam String description, @RequestParam int yearFounded, @RequestParam String address, @RequestParam int breweryId) {
		breweryDao.updateBrewery(name, address, description, yearFounded, breweryId);
		return "redirect:/";
	}
	@RequestMapping(path="user/{username}/deleteBrewery", method = RequestMethod.GET)
	public String deleteBrewery(@PathVariable String username, HttpServletRequest request) {
		List<Brewery> breweryList = breweryDao.getAllBreweries();
		request.setAttribute("breweries", breweryList);
		return "userDashboard";
	}
	

}

	
	










