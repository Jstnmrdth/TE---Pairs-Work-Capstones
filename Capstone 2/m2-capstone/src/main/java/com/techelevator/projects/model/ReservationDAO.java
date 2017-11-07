package com.techelevator.projects.model;

import java.util.List;

public interface ReservationDAO {
	public List<Reservation> getAllReservations();
	public List<Reservation> getAvailableCampsites(Integer currentParkId, Integer currentCampgroundId);
	public List<Reservation> makeNewReservation();
}
