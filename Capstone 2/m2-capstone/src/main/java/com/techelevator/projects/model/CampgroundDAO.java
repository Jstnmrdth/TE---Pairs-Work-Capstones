package com.techelevator.projects.model;

import java.util.List;

public interface CampgroundDAO {

	public List<Campground> getAllCampgrounds(Park currentPark);
}
