package com.techelevator.projects.model;

public class Campsite {
	private Integer siteId;
	private Integer campgroundId;
	private Integer siteNumber;
	private Integer maxOccupancy;
	private Boolean accessibility;
	private Integer maxRVLength;
	private Boolean utilities;
	
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
	public void setCampgroundId(Integer campgroundId) {
		this.campgroundId = campgroundId;
	}
	
	public void setSiteNumber(Integer siteNumber) {
		this.siteNumber = siteNumber;
	}
	
	public void setMaxOccupancy(Integer maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	
	public void setAccessibility(Boolean accessibility) {
		this.accessibility = accessibility;
	}
	
	public void setMaxRVLength(Integer maxRVLength) {
		this.maxRVLength = maxRVLength;
	}
	
	public void setUtilities(Boolean utilities) {
		this.utilities = utilities;
	}
	
	public Integer getSiteId() {
		return siteId;
	}
	
	public Integer getCampgroundId() {
		return campgroundId;
	}
	
	public Integer getSiteNumber() {
		return siteNumber;
	}
	
	public Integer getMaxOccupancy() {
		return maxOccupancy;
	}
	
	public Boolean getAccessibility() {
		return accessibility;
	}
	
	public Integer getMaxRVLength() {
		return maxRVLength;
	}
	
	public Boolean getUtilities() {
		return utilities;
	}
	
}
