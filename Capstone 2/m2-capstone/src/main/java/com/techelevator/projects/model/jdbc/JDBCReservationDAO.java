package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Campsite;
import com.techelevator.projects.model.Reservation;
import com.techelevator.projects.model.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public List<Reservation> getAllReservations() {
		return null;
	}

//	@Override
//	public List<Reservation> getAvailableCampsites(Integer currentCampgroundId) {
//		List<Campsite> availableCampsite = new ArrayList<Campsite>();
////		String getAvailableCampsite = "SELECT * " +
////									  "FROM site "
//		return null;
//	}

	@Override
	public List<Reservation> makeNewReservation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Reservation createReservation(SqlRowSet results) {
		Reservation theReservation = new Reservation();
		theReservation.setReservationId(results.getInt("reservation_id"));
		theReservation.setSiteId(results.getInt("site_id"));
		theReservation.setName(results.getString("name"));
		theReservation.setFromDate(results.getDate("from_date"));
		theReservation.setToDate(results.getDate("to_date"));
		return theReservation;
	}
	@Override
	public List<Reservation> getAvailableCampsites(Integer currentParkId, Integer currentCampgroundId) {
		// TODO Auto-generated method stub
		return null;
	}
}
