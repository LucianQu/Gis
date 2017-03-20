package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class RainBean  implements Parcelable{
	private String hour1count ;
	private String hour2count;
	private String hour3count;
	private String hour6count;
	private String hour12count;
	private String hour24count;
	
	public RainBean() {
		super();
	}
	/**
	 * @return the hour1count
	 */
	public String getHour1count() {
		return hour1count;
	}

	/**
	 * @param hour1count the hour1count to set
	 */
	public void setHour1count(String hour1count) {
		this.hour1count = hour1count;
	}

	/**
	 * @return the hour2count
	 */
	public String getHour2count() {
		return hour2count;
	}
	/**
	 * @param hour2count the hour2count to set
	 */
	public void setHour2count(String hour2count) {
		this.hour2count = hour2count;
	}
	
	/**
	 * @return the hour3count
	 */
	public String getHour3count() {
		return hour3count;
	}

	/**
	 * @param hour3count the hour3count to set
	 */
	public void setHour3count(String hour3count) {
		this.hour3count = hour3count;
	}

	/**
	 * @return the hour6count
	 */
	public String getHour6count() {
		return hour6count;
	}

	/**
	 * @param hour6count the hour6count to set
	 */
	public void setHour6count(String hour6count) {
		this.hour6count = hour6count;
	}

	/**
	 * @return the hour12count
	 */
	public String getHour12count() {
		return hour12count;
	}

	/**
	 * @param hour12count the hour12count to set
	 */
	public void setHour12count(String hour12count) {
		this.hour12count = hour12count;
	}

	/**
	 * @return the hour24count
	 */
	public String getHour24count() {
		return hour24count;
	}

	/**
	 * @param hour24count the hour24count to set
	 */
	public void setHour24count(String hour24count) {
		this.hour24count = hour24count;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RainBean [hour1count=" + hour1count + ", hour2count="
				+ hour2count + ", hour3count=" + hour3count + ", hour6count="
				+ hour6count + ", hour12count=" + hour12count
				+ ", hour24count=" + hour24count + "]";
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(hour1count);
		dest.writeString(hour2count);
		dest.writeString(hour3count);
		dest.writeString(hour6count);
		dest.writeString(hour12count);
		dest.writeString(hour24count);
	}
	
	
	public static final Parcelable.Creator<RainBean> CREATOR = new Creator<RainBean>() {

		@Override
		public RainBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new RainBean[size];
		}

		@Override
		public RainBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			RainBean mRainBean = new RainBean();
			mRainBean.hour1count= source.readString();
			mRainBean.hour2count= source.readString();
			mRainBean.hour3count = source.readString();
			mRainBean.hour6count = source.readString();
			mRainBean.hour12count= source.readString();
			mRainBean.hour24count = source.readString();
			return mRainBean;
		}
	};

}
