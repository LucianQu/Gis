package com.bojian.gis.entity;

public class Attribute {
	private String hour;
	private String duration;
	
	
	
	public Attribute(String hour, String duration) {
		super();
		this.hour = hour;
		this.duration = duration;
	}
	/**
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}
	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Attribute [hour=" + hour + ", duration=" + duration + "]";
	}
	
}
