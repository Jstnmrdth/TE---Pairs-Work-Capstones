package com.techelevator.npgeek.model.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParksDao implements ParksDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcParksDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Parks> getAllParks() {
		List<Parks> allParks = new ArrayList<>();
		String sqlSelectAllParks = "SELECT * FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks);
		
		while(results.next()) {
			Parks park = mapRowToPark(results);
			allParks.add(park);
		}
		return allParks;
	}
	
	@Override 
	public Parks getParkByCode(String parkCode) {
		
		String sqlSelectAllParks = "SELECT * FROM park WHERE parkCode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks, parkCode);
		Parks park = new Parks();
		
		
		if (results.next()) {
			park = mapRowToPark(results);
		}
		
		return park;
		
	}
	
	private Parks mapRowToPark(SqlRowSet row) {
		
		Parks park = new Parks();
		
		park.setParkCode(row.getString("parkCode"));
		park.setParkName(row.getString("parkName"));
		park.setState(row.getString("state"));
		park.setAcreage(row.getInt("acreage"));
		park.setElevationInFeet(row.getInt("elevationInFeet"));
		park.setMilesOfTrail(row.getDouble("milesOfTrail"));
		park.setNumberOfCampsites(row.getInt("numberOfCampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearFounded"));
		park.setAnnualVisitorCount(row.getInt("annualVisitorCount"));
		park.setInspirationalQuote(row.getString("inspirationalQuote"));
		park.setInspirationalQuoteSource(row.getString("inspirationalQuoteSource"));
		park.setParkDescription(row.getString("parkDescription"));
		park.setEntryFee(row.getInt("entryFee"));
		park.setNumberOfAnimalSpecies(row.getInt("numberOfAnimalSpecies"));

		return park;
	}
	
	
} 