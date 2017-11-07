package com.techelevator;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.junit.Assert;

public class JDBCCampsiteIntegrationTest {
	private static SingleConnectionDataSource dataSource;
	private JDBCCampsiteDAO dao;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
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
		dao = new JDBCCampsiteDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void get_all_campsites() {
		List<Campsite> allCampsites = dao.getAllCampsites();
		
		Assert.assertNotNull("The results should NOT be null", allCampsites);
		Assert.assertTrue("Search should return at least one campsite", allCampsites.size() > 0);
	}
}
