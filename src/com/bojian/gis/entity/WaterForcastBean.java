package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class WaterForcastBean implements Parcelable {
	private String rvsectId;
	private String sitename;
	private String time;
	private String latestrainfall;
	private String peakdischarge;
	private String floodvalue;
	private String duration;
	private String peaktime;

	public WaterForcastBean() {
		super();
	}

	/**
	 * @return the rvsectId
	 */
	public String getRvsectId() {
		return rvsectId;
	}

	/**
	 * @param rvsectId
	 *            the rvsectId to set
	 */
	public void setRvsectId(String rvsectId) {
		this.rvsectId = rvsectId;
	}

	/**
	 * @return the sitename
	 */
	public String getSitename() {
		return sitename;
	}

	/**
	 * @param sitename
	 *            the sitename to set
	 */
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the latestrainfall
	 */
	public String getLatestrainfall() {
		return latestrainfall;
	}

	/**
	 * @param latestrainfall
	 *            the latestrainfall to set
	 */
	public void setLatestrainfall(String latestrainfall) {
		this.latestrainfall = latestrainfall;
	}

	/**
	 * @return the peakdischarge
	 */
	public String getPeakdischarge() {
		return peakdischarge;
	}

	/**
	 * @param peakdischarge
	 *            the peakdischarge to set
	 */
	public void setPeakdischarge(String peakdischarge) {
		this.peakdischarge = peakdischarge;
	}

	/**
	 * @return the floodvalue
	 */
	public String getFloodvalue() {
		return floodvalue;
	}

	/**
	 * @param floodvalue
	 *            the floodvalue to set
	 */
	public void setFloodvalue(String floodvalue) {
		this.floodvalue = floodvalue;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the peaktime
	 */
	public String getPeaktime() {
		return peaktime;
	}

	/**
	 * @param peaktime
	 *            the peaktime to set
	 */
	public void setPeaktime(String peaktime) {
		this.peaktime = peaktime;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WaterForcastBean [rvsectId=" + rvsectId + ", sitename="
				+ sitename + ", time=" + time + ", latestrainfall="
				+ latestrainfall + ", peakdischarge=" + peakdischarge
				+ ", floodvalue=" + floodvalue + ", duration=" + duration
				+ ", peaktime=" + peaktime + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(rvsectId);
		dest.writeString(sitename);
		dest.writeString(time);
		dest.writeString(latestrainfall);
		dest.writeString(peakdischarge);
		dest.writeString(floodvalue);
		dest.writeString(duration);
		dest.writeString(peaktime);
	}
	public static final Parcelable.Creator<WaterForcastBean> CREATOR = new Creator<WaterForcastBean>() {

		@Override
		public WaterForcastBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new WaterForcastBean[size];
		}

		@Override
		public WaterForcastBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			WaterForcastBean mImportRiverBean = new WaterForcastBean();
			mImportRiverBean.rvsectId= source.readString();
			mImportRiverBean.sitename= source.readString();
			mImportRiverBean.time = source.readString();
			mImportRiverBean.latestrainfall = source.readString();
			mImportRiverBean.peakdischarge = source.readString();
			mImportRiverBean.floodvalue = source.readString();
			mImportRiverBean.duration = source.readString();
			mImportRiverBean.peaktime = source.readString();
			return mImportRiverBean;
		}
	};
}
