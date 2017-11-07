package com.techelevator.npgeek.model.park;

import java.util.List;

public interface ParksDao {
	
	List<Parks> getAllParks();
	Parks getParkByCode(String parkCode);

}
