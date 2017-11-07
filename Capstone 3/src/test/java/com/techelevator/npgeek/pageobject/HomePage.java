package com.techelevator.npgeek.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private WebDriver webDriver;
	
	public HomePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public SurveyInputPage clickSurvey() {
		WebElement surveyLink = webDriver.findElement(By.linkText("Survey"));
		surveyLink.click();
		return new SurveyInputPage(webDriver);
	}
	
	public ParkDetailPage clickPark() {
		WebElement link = webDriver.findElement(By.className("homePage-image"));
		link.click();
		return new ParkDetailPage(webDriver);
	}
	
	public boolean isShown() {
		try {
			WebElement homePageSection = webDriver.findElement(By.className("homePage"));
			return homePageSection != null;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
