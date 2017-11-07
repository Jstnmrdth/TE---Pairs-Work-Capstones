package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Campsite;
import com.techelevator.projects.model.CampsiteDAO;

public class JDBCCampsiteDAO implements CampsiteDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCCampsiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Campsite> getAllCampsites() {
		List<Campsite> allCampsites = new ArrayList<Campsite>();
		String getAllCampsites = "SELECT * " +
								"FROM site ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllCampsites);
		while(results.next()) {
			allCampsites.add(createCampsite(results));
		}
		return allCampsites;
	}

//	@Override
//	public List<Campsite> getAvailableCampsite(Integer campgroundId, Date to, Date from) {
//		List<Campsite> allAvailableCampsites = new ArrayList<Campsite>();
//		String getAvailableCampsites = "SELECT * " +
//									  "FROM site " +
//									  "WHERE camproundId = ? AND to > ? AND from < ?";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(getAvailableCampsites, campgroundId, to, from);
//		return null;
//	}
	
	private Campsite createCampsite(SqlRowSet results) {
		Campsite newCampsite = new Campsite();
		newCampsite.setSiteId(results.getInt("site_id"));
		newCampsite.setSiteNumber(results.getInt("site_number"));
		newCampsite.setCampgroundId(results.getInt("campground_id"));
		newCampsite.setMaxOccupancy(results.getInt("max_occupancy"));
		newCampsite.setAccessibility(results.getBoolean("accessible"));
		newCampsite.setUtilities(results.getBoolean("utilities"));
		newCampsite.setMaxRVLength(results.getInt("max_rv_length"));
		return newCampsite;
	}
}
