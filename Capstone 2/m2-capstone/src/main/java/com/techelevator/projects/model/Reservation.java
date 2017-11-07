package com.techelevator.projects.model;

import java.util.Date;

public class Reservation {
	private Integer reservationId;
	private Integer siteId;
	private String name;
	private Date fromDate;
	private Date toDate;
	private Date createDate;
	
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Integer getReservationId() {
		return reservationId;
	}
	
	public Integer getSiteId() {
		return siteId;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	
	public Date getToDate() {
		return toDate;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
}
