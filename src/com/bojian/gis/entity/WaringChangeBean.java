package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class WaringChangeBean implements Parcelable {
    private String id;
    private String type;
    private String stationId;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
		dest.writeString(id);
		dest.writeString(type);
		dest.writeString(stationId);
		

	}

	public static final Parcelable.Creator<WaringChangeBean> CREATOR = new Creator<WaringChangeBean>() {

		@Override
		public WaringChangeBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new WaringChangeBean[size];
		}

		@Override
		public WaringChangeBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			WaringChangeBean mWaringChangeBean = new WaringChangeBean();
			mWaringChangeBean.id= source.readString();
			mWaringChangeBean.type= source.readString();
			mWaringChangeBean.stationId = source.readString();
			
			return mWaringChangeBean;
		}
	};
	
}
