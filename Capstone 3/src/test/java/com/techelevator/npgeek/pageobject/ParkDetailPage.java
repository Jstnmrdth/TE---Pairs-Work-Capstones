package com.techelevator.npgeek.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ParkDetailPage {
	
private WebDriver webDriver;
	
	public ParkDetailPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public void chooseTemperature() {
		WebElement button = webDriver.findElement(By.className("temp-btn"));
		button.click();
	}
	
	public boolean isShown() {
		try {
			WebElement parkDetailHeader = webDriver.findElement(By.className("parkDetailHeader"));
			return parkDetailHeader != null;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
