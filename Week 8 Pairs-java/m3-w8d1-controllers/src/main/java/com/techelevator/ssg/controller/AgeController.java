package com.techelevator.ssg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgeController {
	
	@RequestMapping("/ageInput")
	public String handleInput() {
		return "ageInput";
	}
	
	@RequestMapping("/ageOutput")
	public String handleOutput(HttpServletRequest request){
		String planet = request.getParameter("planet");
		String stringEarthAge = request.getParameter("earthage");
		Integer earthAge =Integer.parseInt(stringEarthAge);
		Double planetAgeRatio = null;
		
		switch (planet){
			case "Mercury":
				planetAgeRatio = 365.26/87.96;
				break;
			case "Jupiter":
				planetAgeRatio = 365.26/11.862;
				break;
			case "Earth":
				planetAgeRatio = 1.0;
				break;
			case "Mars":
				planetAgeRatio = 365.26/686.98;
				break;
			case "Neptune":
				planetAgeRatio = 365.26/164.81;
				break;
			case "Saturn":
				planetAgeRatio = 365.26/29.456;
				break;
			case "Uranus":
				planetAgeRatio = 365.26/84.07;
				break;
			case "Venus":
				planetAgeRatio = 365.26/224.68;
				break;
			case "Pluto":
				planetAgeRatio = 365.26/ 247.7;
				break;
			default:
				break;
		}
		
		request.setAttribute("planetage", earthAge * planetAgeRatio);
		request.setAttribute("planet", planet);
		request.setAttribute("earthage", earthAge);
		return "ageOutput";
	}
}
