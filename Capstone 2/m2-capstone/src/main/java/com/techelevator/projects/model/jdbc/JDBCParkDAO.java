package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Park;
import com.techelevator.projects.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<Park>();
		String getAllParks = "SELECT * " +
							"FROM park ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllParks);
		while(results.next()) {
			allParks.add(createPark(results));
		}
		return allParks;
	}
	
	public Park makePark(String name) {
		Park newPark = new Park();
		String findPark = "SELECT * " +
						  "FROM park " +
						  "WHERE name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(findPark, name);
		while(results.next()) {
			newPark = createPark(results);
		}
		return newPark;
	}
	
	private Park createPark(SqlRowSet results) {
		Park newPark = new Park();
		newPark.setParkId(results.getInt("park_id"));
		newPark.setName(results.getString("name"));
		newPark.setLocation(results.getString("location"));
		newPark.setDate(results.getDate("establish_date").toLocalDate());
		newPark.setArea(results.getInt("area"));
		newPark.setVisitors(results.getInt("visitors"));
		newPark.setDescription(results.getString("description"));
		return newPark;
	}
	
	
}
