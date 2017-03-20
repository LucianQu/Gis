package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class WaterUsingBean implements Parcelable{
	private static final String TAG ="WaterUsingBean";
	private String villiage;           //所在村
	private String name;			   //用户名
	private String waterconsumption;   //用水量
	private String startpumptime; //开泵时间
	private String shutpumptime;   //关泵时间
//	private String waterusing_swipecard;//刷卡次数
	private String type;//数据类型
	
	
	
	public String getWaterconsumption() {
		return waterconsumption;
	}




	public void setWaterconsumption(String waterconsumption) {
		this.waterconsumption = waterconsumption;
	}




	public String getStartpumptime() {
		return startpumptime;
	}




	public void setStartpumptime(String startpumptime) {
		this.startpumptime = startpumptime;
	}




	public String getShutpumptime() {
		return shutpumptime;
	}




	public void setShutpumptime(String shutpumptime) {
		this.shutpumptime = shutpumptime;
	}




	public String getVilliage() {
		return villiage;
	}




	public void setVilliage(String villiage) {
		this.villiage = villiage;
	}

	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public WaterUsingBean() {
		super();
	}

	
	
	
	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WaterUsingBean [villiage="+ villiage +", name=" + name + ", waterusing_water=" + waterconsumption
				+ ", waterusing_starttm=" + startpumptime +", waterusing_offtm=" + shutpumptime +"]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(villiage);
		dest.writeString(name);
		dest.writeString(waterconsumption);
		dest.writeString(startpumptime);
		dest.writeString(shutpumptime);
//		dest.writeString(waterusing_swipecard);
		dest.writeString(type);
	}
	
	public static final Parcelable.Creator<WaterUsingBean> CREATOR = new Creator<WaterUsingBean>() {

		@Override
		public WaterUsingBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new WaterUsingBean[size];
		}

		@Override
		public WaterUsingBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			WaterUsingBean mImportRiverBean = new WaterUsingBean();
			mImportRiverBean.villiage = source.readString();
			mImportRiverBean.name= source.readString();
			mImportRiverBean.waterconsumption= source.readString();
			mImportRiverBean.startpumptime = source.readString();
			mImportRiverBean.shutpumptime= source.readString();
//			mImportRiverBean.waterusing_swipecard = source.readString();
			mImportRiverBean.type = source.readString();
			return mImportRiverBean;
		}
	};

}
