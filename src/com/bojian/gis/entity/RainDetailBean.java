package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class RainDetailBean implements Parcelable{
	
	private String sitename;
	private String siteid;
	private String siteadd;
	private String rainfall;
	private String lon;
	private String lat;
	
	
	/**
	 * @return the siteid
	 */
	public String getSiteid() {
		return siteid;
	}

	/**
	 * @param siteid the siteid to set
	 */
	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public RainDetailBean() {
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
		return "RainDetailBean [sitename=" + sitename + ", siteid=" + siteid
				+ ", siteadd=" + siteadd + ", rainfall=" + rainfall + ", lon="
				+ lon + ", lat=" + lat + "]";
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
		dest.writeString(siteid);
		dest.writeString(siteadd);
		dest.writeString(rainfall);
		dest.writeString(lon);
		dest.writeString(lat);
	}

	public static final Parcelable.Creator<RainDetailBean> CREATOR = new Creator<RainDetailBean>() {

		@Override
		public RainDetailBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new RainDetailBean[size];
		}

		@Override
		public RainDetailBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			RainDetailBean areal = new RainDetailBean();
			areal.sitename = source.readString();
			areal.siteid = source.readString();
			areal.siteadd = source.readString();
			areal.rainfall = source.readString();
			areal.lon = source.readString();
			areal.lat = source.readString();	
			return areal;
		}
	};

}
