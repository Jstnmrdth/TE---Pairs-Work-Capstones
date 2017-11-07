package com.techelevator;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.park.JdbcParksDao;
import com.techelevator.npgeek.model.park.JdbcWeatherDao;
import com.techelevator.npgeek.model.park.ParksDao;
import com.techelevator.npgeek.model.park.Weather;
import com.techelevator.npgeek.model.park.WeatherDao;

public class WeatherDaoIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private WeatherDao weatherDao;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		weatherDao = new JdbcWeatherDao(dataSource);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void return_todays_forecast() {
		Weather todaysWeather = weatherDao.getForecastToday("GNP");
		
		Assert.assertEquals("GNP should be showing snow in the forecast", "snow", todaysWeather.getForecast());
		Assert.assertEquals("GNP should be showing the high temperature", 40, todaysWeather.getHigh());
		Assert.assertEquals("GNP should be showing the low temperative", 27, todaysWeather.getLow());
	}
	
	@Test
	public void return_four_day_forecast() {
		List<Weather> fourDayForecast = weatherDao.getFourDayForecast("GNP");
		
		Weather day4 = fourDayForecast.get(2);
		
		Assert.assertEquals("GNP should be showing snow in the forecast", "cloudy", day4.getForecast());
		Assert.assertEquals("GNP should be showing the high temperature", 34, day4.getHigh());
		Assert.assertEquals("GNP should be showing the low temperative", 24, day4.getLow());
	}
	
}

