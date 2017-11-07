package com.techelevator.npgeek.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.npgeek.model.park.Parks;
import com.techelevator.npgeek.model.park.ParksDao;
import com.techelevator.npgeek.model.survey.Survey;
import com.techelevator.npgeek.model.survey.SurveyDao;

@Controller
public class SurveyController {
	
		@Autowired
		private SurveyDao surveyDao;
		@Autowired
		private ParksDao parksDao;
		
		@RequestMapping(path="/survey", method=RequestMethod.GET)
		public String displaySurvey(HttpServletRequest request) {
			List<Parks> parks = parksDao.getAllParks();
			request.setAttribute("parks", parks);
			return "survey";
		}
		
		@RequestMapping(path="/survey", method=RequestMethod.POST)
		public String processSurvey(@RequestParam String parkCode,
								    @RequestParam String emailAddress,
								    	@RequestParam String state,
								    	@RequestParam String activityLevel) {
			
			Survey survey = new Survey();
			survey.setParkCode(parkCode);
			survey.setEmailAddress(emailAddress);
			survey.setState(state);
			survey.setActivityLevel(activityLevel);
			surveyDao.addSurveyResult(survey);
			return "redirect:/surveyResults";
		}
		
		@RequestMapping(path="/surveyResults", method=RequestMethod.GET)
		public String returnMostPopular(HttpServletRequest request) {
			Map<Parks, Integer> parks = surveyDao.getMostPopular();
			request.setAttribute("popularParks", parks);
			return "surveyResults";
		}
}
