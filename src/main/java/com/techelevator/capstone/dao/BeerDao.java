package com.techelevator.capstone.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.capstone.model.Beer;

@Component
public interface BeerDao {
	public List<Beer> getAllBeer(int breweryId);
	public Beer getBeerByBeerId(int breweryId);
	public void addBeer(int breweryId, String name, String beerType, String description, double abv, int ibu,
			String glassType);
	void deleteBeer(int beerId);

}
