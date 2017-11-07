package com.techelevator.npgeek.model.park;

public class Weather {
	private String parkCode;
	private int fiveDayForecastValue;
	private int low;
	private int high;
	private String forecast;
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}
	public int getLow() {
		return low;
	}
	public void setLow(int low) {
		this.low = low;
	}
	public int getHigh() {
		return high;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public String getImageName() {
		if (forecast.equals("partly cloudy")) {
			return "partlyCloudy.png";
		} else {
		return forecast + ".png";
		}
	}
	
	public String getRecommendation() {
		String message = ""; 
		
		message += forecast.equals("snow") ? "Pack snowshoes! " : "";
		message += forecast.equals("rain") ? "Pack rain gear and wear waterproof shoes! " : "";
		message += forecast.equals("thunderstorms") ? "SEEK SHELTER! AVOID WALKING ON EXPOSED RIDGES. " : "";
		message += forecast.equals("sun") ? "Pack sunblock! " : "";
		message += (high > 75) ? "Bring an extra gallon of water. " : "";
		message += ((high - low) > 20) ? "Wear breathable layers. " : "";
		message += (low < 20) ? "Be aware of exposure to frigid temperature. " : "";
		message += message.length() == 0 ? "Today is a great day to visit!" : "";
		
		return message;

	}
	
	 
}
