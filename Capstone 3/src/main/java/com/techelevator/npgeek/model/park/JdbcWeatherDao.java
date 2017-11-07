package com.techelevator.npgeek.model.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWeatherDao implements WeatherDao{
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		public JdbcWeatherDao(DataSource dataSource) {
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		
		@Override
		public Weather getForecastToday(String parkCode) {
			
			String sqlWeatherToday = "SELECT * FROM weather WHERE fiveDayForecastValue = 1 AND parkCode = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlWeatherToday, parkCode);

			if (results.next()) {
				return mapRowToWeather(results);
			}
			
			return null;
		}
		
		@Override
		public List<Weather> getFourDayForecast(String parkCode) {
			List<Weather> weatherForecast = new ArrayList<>();
			String sqlFourDayForecast = "SELECT * FROM weather WHERE fiveDayForecastValue > 1 AND parkCode = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFourDayForecast, parkCode);
		

			while(results.next()) {
				Weather weather = mapRowToWeather(results);
				weatherForecast.add(weather);
			}
			return weatherForecast;
		}
		
		public Weather convertToCelsius(Weather forecast) {
			int high = forecast.getHigh();
			int low = forecast.getLow();
			forecast.setHigh((high - 32) * 5/9);
			forecast.setLow((low - 32) * 5/9);
			return forecast;
		}
	
		private Weather mapRowToWeather(SqlRowSet row) {
			
			Weather weather = new Weather();
			
			weather.setParkCode(row.getString("parkCode").toLowerCase());
			weather.setFiveDayForecastValue(row.getInt("fiveDayForecastValue"));
			weather.setLow(row.getInt("low"));
			weather.setHigh(row.getInt("high"));
			weather.setForecast(row.getString("forecast"));

			return weather;
		}
			
	} 