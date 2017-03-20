package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class TotalRainBean implements Parcelable{
	private static final String TAG ="TotalRainBean";
	private String name;
	private String totalrain_last;
	private String totalrain_year;
	private String totalrain_afteryear;
	private String totalrain_allyear;
	private String type;//数据类型
	
	
	
	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public TotalRainBean() {
		super();
	}

	
	
	
	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getTotalrain_last() {
		return totalrain_last;
	}




	public void setTotalrain_last(String totalrain_last) {
		this.totalrain_last = totalrain_last;
	}




	public String getTotalrain_year() {
		return totalrain_year;
	}




	public void setTotalrain_year(String totalrain_year) {
		this.totalrain_year = totalrain_year;
	}




	public String getTotalrain_afteryear() {
		return totalrain_afteryear;
	}




	public void setTotalrain_afteryear(String totalrain_afteryear) {
		this.totalrain_afteryear = totalrain_afteryear;
	}




	public String getTotalrain_allyear() {
		return totalrain_allyear;
	}




	public void setTotalrain_allyear(String totalrain_allyear) {
		this.totalrain_allyear = totalrain_allyear;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TotalRainBean [name=" + name + ", totalrain_last=" + totalrain_last
				+ ", totalrain_year=" + totalrain_year +", totalrain_afteryear=" + totalrain_afteryear + ", totalrain_allyear=" + totalrain_allyear +"]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeString(totalrain_last);
		dest.writeString(totalrain_year);
		dest.writeString(totalrain_afteryear);
		dest.writeString(totalrain_allyear);
		dest.writeString(type);
	}
	
	public static final Parcelable.Creator<TotalRainBean> CREATOR = new Creator<TotalRainBean>() {

		@Override
		public TotalRainBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new TotalRainBean[size];
		}

		@Override
		public TotalRainBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			TotalRainBean mImportRiverBean = new TotalRainBean();
			mImportRiverBean.name= source.readString();
			mImportRiverBean.totalrain_last= source.readString();
			mImportRiverBean.totalrain_year = source.readString();
			mImportRiverBean.totalrain_afteryear= source.readString();
			mImportRiverBean.totalrain_allyear = source.readString();
			mImportRiverBean.type = source.readString();
			return mImportRiverBean;
		}
	};

}
