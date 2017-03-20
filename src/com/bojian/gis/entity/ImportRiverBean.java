package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ImportRiverBean implements Parcelable{
	private String sitename;
	private String siteid;
	private String siteadd;
	private String time;
	private String waterlevel;
	private String warninglevel;
	private String lon;
	private String lat;
	
	
	public ImportRiverBean() {
		super();
	}
	
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
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
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
	 * @return the warninglevel
	 */
	public String getWarninglevel() {
		return warninglevel;
	}
	/**
	 * @param warninglevel the warninglevel to set
	 */
	public void setWarninglevel(String warninglevel) {
		this.warninglevel = warninglevel;
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
		return "ImportRiver [sitename=" + sitename + ", siteadd=" + siteadd
				+ ", time=" + time + ", waterlevel=" + waterlevel
				+ ", warninglevel=" + warninglevel + ", lon=" + lon + ", lat="
				+ lat + "]";
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
		dest.writeString(time);
		dest.writeString(waterlevel);
		dest.writeString(warninglevel);
		dest.writeString(lon);
		dest.writeString(lat);
	}
	
	public static final Parcelable.Creator<ImportRiverBean> CREATOR = new Creator<ImportRiverBean>() {

		@Override
		public ImportRiverBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ImportRiverBean[size];
		}

		@Override
		public ImportRiverBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ImportRiverBean mImportRiverBean = new ImportRiverBean();
			mImportRiverBean.sitename= source.readString();
			mImportRiverBean.siteid = source.readString();
			mImportRiverBean.siteadd= source.readString();
			mImportRiverBean.time = source.readString();
			mImportRiverBean.waterlevel = source.readString();
			mImportRiverBean.warninglevel = source.readString();
			mImportRiverBean.lon = source.readString();
			mImportRiverBean.lat = source.readString();
			return mImportRiverBean;
		}
	};
}
