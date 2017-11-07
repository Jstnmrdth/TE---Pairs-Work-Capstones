package com.techelevator.ssg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeightController {

	@RequestMapping("/weightInput")
	public String handleInput() {
		return "weightInput";
	}
	@RequestMapping("/weightOutput")
	public String handleOutput(HttpServletRequest request){
		String planet = request.getParameter("planet");
		String stringEarthWeight = request.getParameter("earthweight");
		Integer earthWeight =Integer.parseInt(stringEarthWeight);
		Double planetWeightRatio = null;
		
		switch (planet){
			case "Mercury":
				planetWeightRatio = .37;
				break;
			case "Jupiter":
				planetWeightRatio = 2.65;
				break;
			case "Earth":
				planetWeightRatio = 1.0;
				break;
			case "Mars":
				planetWeightRatio = .38;
				break;
			case "Neptune":
				planetWeightRatio = 1.43;
				break;
			case "Saturn":
				planetWeightRatio = 1.13;
				break;
			case "Uranus":
				planetWeightRatio = 1.09;
				break;
			case "Venus":
				planetWeightRatio = .9;
				break;
			case "Pluto":
				planetWeightRatio = .04;
				break;
			default:
				break;
		}
		
		request.setAttribute("planetweight", earthWeight * planetWeightRatio);
		request.setAttribute("planet", planet);
		request.setAttribute("earthWeight", earthWeight);
		return "weightOutput";
	}
}
