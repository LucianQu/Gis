package com.bojian.gis.entity;

import java.io.Serializable;


public class WeatherBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8826394445967097028L;
	
	private String city;
	private String week;
	private String date_y;
	private String temp;
	private String weather;
	
	public WeatherBean() {
		super();
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getDate_y() {
		return date_y;
	}
	public void setDate_y(String date_y) {
		this.date_y = date_y;
	}
	/**
	 * @return the temp
	 */
	public String getTemp() {
		return temp;
	}
	/**
	 * @param temp the temp to set
	 */
	public void setTemp(String temp) {
		this.temp = temp;
	}
	/**
	 * @return the weather
	 */
	public String getWeather() {
		return weather;
	}
	/**
	 * @param weather the weather to set
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Weather [city=" + city + ", week=" + week + ", date_y="
				+ date_y + ", temp=" + temp + ", weather=" + weather + "]";
	}
}
