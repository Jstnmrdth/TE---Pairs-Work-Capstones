package com.techelevator.capstone.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.capstone.model.Location;

public class JDBCLocationDao implements LocationDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCLocationDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Location> getAllLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getLocationByLocationId(int locationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
