package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.CampgroundDAO;
import com.techelevator.projects.model.Park;

public class JDBCCampgroundDAO implements CampgroundDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Campground> getAllCampgrounds(Park currentPark) {
		List<Campground> allCampgrounds = new ArrayList<Campground>();
		String currentParkName = currentPark.getName();
		String getAllCampgrounds = "SELECT * " +
								  "FROM campground " +
								  "JOIN park ON park.park_id = campground.park_id " +
								  "WHERE park.name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllCampgrounds, currentParkName);
		while (results.next()) {
			allCampgrounds.add(createCampground(results));
		}
		return allCampgrounds;
	}

	private Campground createCampground(SqlRowSet results) {
		Campground theCampground = new Campground();
		theCampground.setParkId(results.getInt("park_id"));
		theCampground.setName(results.getString("name"));
		theCampground.setCampgroundId(results.getInt("campground_id"));
		theCampground.setOpenFrom(results.getString("open_from_mm"));
		theCampground.setOpenTo(results.getString("open_to_mm"));
		theCampground.setDailyFee(results.getBigDecimal("daily_fee"));
		return theCampground;
	}

}
