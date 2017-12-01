package com.techelevator.capstone.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.capstone.model.Location;

@Component
public interface LocationDao {
	public List<Location> getAllLocations();
	public Location getLocationByLocationId(int locationId);

}
