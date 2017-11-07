package com.techelevator.npgeek.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.park.ParksDao;
import com.techelevator.npgeek.model.park.Weather;
import com.techelevator.npgeek.model.park.WeatherDao;

@SessionAttributes("temperature")
@Controller
public class ParkDetailController {

	@Autowired
	private ParksDao parksDao;
	
	@Autowired
	private WeatherDao weatherDao;
	
	@RequestMapping(path="/parksDetailsPage", method=RequestMethod.GET)
	public String displayParksDetails(HttpServletRequest request, ModelMap model) {
		if(!model.containsAttribute("temperature")) {
			model.addAttribute("temperature", "fahrenheit");
		}
		
		String parkCodeParam = request.getParameter("parkCode");
		String parkCode = parkCodeParam;
		
		Weather today = weatherDao.getForecastToday(parkCode);
		List<Weather> fourDay = weatherDao.getFourDayForecast(parkCode);
		
		String temperature = (String)model.get("temperature");
		if(temperature.equals("celsius")) {
			today = weatherDao.convertToCelsius(today);
			for(int i = 0; i < fourDay.size(); i++) {
				Weather thisDay = fourDay.remove(0);
				thisDay = weatherDao.convertToCelsius(thisDay);
				fourDay.add(thisDay);
			}
		}
		
		request.setAttribute("park", parksDao.getParkByCode(parkCode));
		request.setAttribute("weather", today);
		request.setAttribute("fourday", fourDay);
		return "parksDetailsPage";
	}
	
	@RequestMapping(path="/parksDetailsPage", method=RequestMethod.POST)
	public String chooseTemperatureUnits(@RequestParam String temperature,
										@RequestParam String parkCode,
										ModelMap model,
										RedirectAttributes attrs) {
		model.addAttribute("temperature", temperature);
		attrs.addAttribute("parkCode", parkCode);
		return "redirect:/parksDetailsPage";
	}
	
	
}
