package com.techelevator.npgeek.model.park;

import java.util.List;

public interface WeatherDao {

	Weather getForecastToday(String parkCode);
	List<Weather> getFourDayForecast(String parkCode);
	Weather convertToCelsius(Weather forecast);
}
