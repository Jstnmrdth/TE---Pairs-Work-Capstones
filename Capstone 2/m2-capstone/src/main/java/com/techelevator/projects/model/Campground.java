package com.techelevator.projects.model;

import java.math.BigDecimal;

public class Campground {
	private Integer campgroundId;
	private Integer parkId;
	private String name;
	private String openFrom;
	private String openTo;
	private BigDecimal dailyFee;
	
	public void setCampgroundId(Integer campgroundId) {
		this.campgroundId = campgroundId;
	}
	
	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setOpenFrom(String openFrom) {
		this.openFrom = openFrom;
	}
	
	public void setOpenTo(String openTo) {
		this.openTo = openTo;
	}
	
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
	
	public Integer getCampgroundId() {
		return campgroundId;
	}
	
	public Integer getParkId() {
		return parkId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOpenFrom() {
		return openFrom;
	}
	
	public String getOpenTo() {
		return openTo;
	}
	
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	
	
} 
