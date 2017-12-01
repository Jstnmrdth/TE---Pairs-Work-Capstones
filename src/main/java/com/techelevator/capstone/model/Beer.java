package com.techelevator.capstone.model;

public class Beer {
	private int breweryId;
	private Brewery brewery;
	private int beerId;
	private String name;
	private String description;
	private String beerType;
	private double abv;
	private int ibu;
	private String glassType;
	
	public Brewery getBrewery() {
		return brewery;
	}
	public void setBrewery(Brewery brewery) {
		this.brewery = brewery;
	}
	
	public int getBreweryId() {
		return breweryId;
	}
	public void setBreweryId(int breweryId) {
		this.breweryId = breweryId;
	}
	public int getBeerId() {
		return beerId;
	}
	public void setBeerId(int beerId) {
		this.beerId = beerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBeerType() {
		return beerType;
	}
	public void setBeerType(String beerType) {
		this.beerType = beerType;
	}
	public double getAbv() {
		return abv;
	}
	public void setAbv(double abv) {
		this.abv = abv;
	}
	public int getIbu() {
		return ibu;
	}
	public void setIbu(int ibu) {
		this.ibu = ibu;
	}
	public String getGlassType() {
		return glassType;
	}
	public void setGlassType(String glassType) {
		this.glassType = glassType;
	}

}
