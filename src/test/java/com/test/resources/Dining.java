package com.test.resources;

//Used for adding dining details to a list
public class Dining {
	
	private String name;
	private String rate;
	private String price;
	private String time;
	private String address;
	
	public Dining(String name, String rate, String price, String time, String address) {
		this.name = name;
		this.rate = rate;
		this.price = price;
		this.time = time;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRate() {
		return rate;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getAddress() {
		return address;
	}
	
}
