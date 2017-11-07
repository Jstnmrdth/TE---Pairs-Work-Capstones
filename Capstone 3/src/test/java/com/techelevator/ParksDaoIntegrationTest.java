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
import com.techelevator.npgeek.model.park.Parks;
import com.techelevator.npgeek.model.park.ParksDao;


public class ParksDaoIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private ParksDao parksDao;
	
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
		parksDao = new JdbcParksDao(dataSource);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void gets_all_parks() {
		List<Parks> parks = parksDao.getAllParks();
		
		Assert.assertEquals(10, parks.size());
	}
	
	@Test
	public void gets_park_by_code() {
		Parks park = parksDao.getParkByCode("GSMNP");
		String parkName = park.getParkName();
		String state = park.getState();
		
		Assert.assertEquals("Great Smoky Mountains National Park", parkName);
		Assert.assertEquals("Tennessee", state);
	}

}
