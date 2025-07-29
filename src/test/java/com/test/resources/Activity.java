package com.test.resources;

import java.time.LocalDateTime;

//Used for adding activities in a list
public class Activity {
	
	public LocalDateTime startDateTime;
	public LocalDateTime endDateTime;
	public String activity;
	public String location;
	public double price;
	
	public Activity(LocalDateTime startDateTime, LocalDateTime endDateTime, String activity, String location, double price) {
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.activity = activity;
		this.location = location;
		this.price = price;
	}
	
	public LocalDateTime getStartDateTime() {
		return this.startDateTime;
	}
	
	public LocalDateTime getEndDateTime() {
		return this.endDateTime;
	}
	
	public String getActivity() {
		return this.activity;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	
	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return activity + " | " + startDateTime + " → " + (endDateTime != null ? endDateTime : "-") + " | ₹" + price;
	}
}

