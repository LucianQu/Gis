package com.bojian.gis.entity;

public class BaseStationBean {
	private int cellid;
	private  String mobileCountryCode;
	private String mobileNetworkCode;
	private int locationAreaCode;
	private String radioType;
	
	public BaseStationBean() {
		super();
	}
	public BaseStationBean(int cellid, String mobileCountryCode,
			String mobileNetworkCode, int locationAreaCode, String radioType) {
		super();
		this.cellid = cellid;
		this.mobileCountryCode = mobileCountryCode;
		this.mobileNetworkCode = mobileNetworkCode;
		this.locationAreaCode = locationAreaCode;
		this.radioType = radioType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseStation [cellid=" + cellid + ", mobileCountryCode="
				+ mobileCountryCode + ", mobileNetworkCode="
				+ mobileNetworkCode + ", locationAreaCode=" + locationAreaCode
				+ ", radioType=" + radioType + "]";
	}
	/**
	 * @return the cellid
	 */
	public int getCellid() {
		return cellid;
	}
	/**
	 * @param cellid the cellid to set
	 */
	public void setCellid(int cellid) {
		this.cellid = cellid;
	}
	/**
	 * @return the mobileCountryCode
	 */
	public String getMobileCountryCode() {
		return mobileCountryCode;
	}
	/**
	 * @param mobileCountryCode the mobileCountryCode to set
	 */
	public void setMobileCountryCode(String mobileCountryCode) {
		this.mobileCountryCode = mobileCountryCode;
	}
	/**
	 * @return the mobileNetworkCode
	 */
	public String getMobileNetworkCode() {
		return mobileNetworkCode;
	}
	/**
	 * @param mobileNetworkCode the mobileNetworkCode to set
	 */
	public void setMobileNetworkCode(String mobileNetworkCode) {
		this.mobileNetworkCode = mobileNetworkCode;
	}
	/**
	 * @return the locationAreaCode
	 */
	public int getLocationAreaCode() {
		return locationAreaCode;
	}
	/**
	 * @param locationAreaCode the locationAreaCode to set
	 */
	public void setLocationAreaCode(int locationAreaCode) {
		this.locationAreaCode = locationAreaCode;
	}
	/**
	 * @return the radioType
	 */
	public String getRadioType() {
		return radioType;
	}
	/**
	 * @param radioType the radioType to set
	 */
	public void setRadioType(String radioType) {
		this.radioType = radioType;
	}

}
