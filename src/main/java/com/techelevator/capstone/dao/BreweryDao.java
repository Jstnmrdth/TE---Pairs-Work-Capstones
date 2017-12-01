package com.techelevator.capstone.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.capstone.model.Brewery;

@Component
public interface BreweryDao {
	public List<Brewery> getAllBreweries();
	public Brewery getBreweryByBreweryId(int breweryId);
	public void addBreweryToDb(String name, String address,
			String Description, int yearFounded);
	public void updateBrewery(String name, String address, String description, int yearFounded, int breweryId);

}
