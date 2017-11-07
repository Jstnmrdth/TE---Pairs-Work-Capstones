package com.techelevator.ssg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TravelController {

	@RequestMapping("/travelInput")
	public String handleInput() {
		return "travelInput";
	}
	@RequestMapping("/travelOutput")
	public String handleOutput(HttpServletRequest request){
		String planet = request.getParameter("planet");
		String stringEarthAge = request.getParameter("earthage");
		String transportation = request.getParameter("transportation");
		Integer speed;
		Integer earthAge = Integer.parseInt(stringEarthAge);
		Double planetDistance = null;
		
		switch (planet){
			case "Mercury":
				planetDistance = 56974146.0;
				break;
			case "Jupiter":
				planetDistance = 390674710.0;
				break;
			case "Earth":
				planetDistance = 0.0;
				break;
			case "Mars":
				planetDistance = 48678219.0;
				break;
			case "Neptune":
				planetDistance = 2703959960.0;
				break;
			case "Saturn":
				planetDistance = 792248720.0;
				break;
			case "Uranus":
				planetDistance = 1692662530.0;
				break;
			case "Venus":
				planetDistance = 25724767.0;
				break;
			default:
				break;
		}
		
		switch (transportation){
		case "Walking":
			speed = 3;
			break;
		case "Car":
			speed = 100;
			break;
		case "Bullet Train":
			speed = 200;
			break;
		case "Boeing 747":
			speed = 570;
			break;
		case "Concorde":
			speed = 1350;
			break;
		default:
			speed = 1;
	}
		Double travelTime = (((planetDistance / speed) / 24) / 365);
		request.setAttribute("traveltime", travelTime);
		request.setAttribute("planet", planet);
		request.setAttribute("earthage", earthAge);
		request.setAttribute("planetage", earthAge + travelTime);
		request.setAttribute("transportation", transportation);
		return "travelOutput";
	}
}
