package com.bojian.gis.entity;

public class RainConditionBean {
	private String sitename;
	private String siteadd;
	private String level;
	private String rainfall;
	private String earlywarningvalue;
	private String lon;
	private String lat;
	
	
	public RainConditionBean() {
		super();
	}
	public RainConditionBean(String sitename, String siteadd, String level,
			String rainfall, String earlywarningvalue, String lon, String lat) {
		super();
		this.sitename = sitename;
		this.siteadd = siteadd;
		this.level = level;
		this.rainfall = rainfall;
		this.earlywarningvalue = earlywarningvalue;
		this.lon = lon;
		this.lat = lat;
	}
	/**
	 * @return the sitename
	 */
	public String getSitename() {
		return sitename;
	}
	/**
	 * @param sitename the sitename to set
	 */
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	/**
	 * @return the siteadd
	 */
	public String getSiteadd() {
		return siteadd;
	}
	/**
	 * @param siteadd the siteadd to set
	 */
	public void setSiteadd(String siteadd) {
		this.siteadd = siteadd;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the rainfall
	 */
	public String getRainfall() {
		return rainfall;
	}
	/**
	 * @param rainfall the rainfall to set
	 */
	public void setRainfall(String rainfall) {
		this.rainfall = rainfall;
	}
	/**
	 * @return the earlywarningvalue
	 */
	public String getEarlywarningvalue() {
		return earlywarningvalue;
	}
	/**
	 * @param earlywarningvalue the earlywarningvalue to set
	 */
	public void setEarlywarningvalue(String earlywarningvalue) {
		this.earlywarningvalue = earlywarningvalue;
	}
	/**
	 * @return the lon
	 */
	public String getLon() {
		return lon;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}
	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RainCondition [sitename=" + sitename + ", siteadd=" + siteadd
				+ ", level=" + level + ", rainfall=" + rainfall
				+ ", earlywarningvalue=" + earlywarningvalue + ", lon=" + lon
				+ ", lat=" + lat + "]";
	}
	
}
