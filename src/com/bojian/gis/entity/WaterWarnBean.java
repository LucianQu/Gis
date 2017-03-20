package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class WaterWarnBean implements Parcelable{
	private String sitename;
	private String siteadd;
	private String level;
	private String waterlevel;
	private String earlywarningvalue;
	private String lon;
	private String lat;
	
	public WaterWarnBean() {
		super();
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
	 * @return the waterlevel
	 */
	public String getWaterlevel() {
		return waterlevel;
	}

	/**
	 * @param waterlevel the waterlevel to set
	 */
	public void setWaterlevel(String waterlevel) {
		this.waterlevel = waterlevel;
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
		return "WaterWarnBean [sitename=" + sitename + ", siteadd=" + siteadd
				+ ", level=" + level + ", waterlevel=" + waterlevel
				+ ", earlywarningvalue=" + earlywarningvalue + ", lon=" + lon
				+ ", lat=" + lat + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(sitename);
		dest.writeString(siteadd);
		dest.writeString(level);
		dest.writeString(waterlevel);
		dest.writeString(earlywarningvalue);
		dest.writeString(lon);
		dest.writeString(lat);
	}

	public static final Parcelable.Creator<WaterWarnBean> CREATOR = new Creator<WaterWarnBean>() {

		@Override
		public WaterWarnBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new WaterWarnBean[size];
		}

		@Override
		public WaterWarnBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			WaterWarnBean areal = new WaterWarnBean();
			areal.sitename = source.readString();
			areal.siteadd = source.readString();
			areal.level = source.readString();
			areal.waterlevel = source.readString();
			areal.earlywarningvalue = source.readString();
			areal.lon = source.readString();
			areal.lat = source.readString();
			return areal;
		}
	};
	
	
	
	
}
