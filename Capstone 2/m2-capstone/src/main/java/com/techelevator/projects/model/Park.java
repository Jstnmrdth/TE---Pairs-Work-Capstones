package com.techelevator.projects.model;

import java.time.LocalDate;
import java.util.Date;

public class Park {
	public static Integer chosenPark;
	private Integer parkId;
	private String name;
	private String location;
	private LocalDate establishDate;
	private Integer area;
	private Integer visitors;
	private String description;
	
	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setDate(LocalDate establishDate) {
		this.establishDate = establishDate;
	}
	
	public void setArea(Integer area) {
		this.area = area;
	}
	
	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getParkId() {
		return parkId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public LocalDate getEstablishDate() {
		return establishDate;
	}
	
	public Integer getArea() {
		return area;
	}
	
	public Integer getVisitors() {
		return visitors;
	}
	
	public String getDescription() {
		return description;
	}
	
}
