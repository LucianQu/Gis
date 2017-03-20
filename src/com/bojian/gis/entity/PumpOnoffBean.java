package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class PumpOnoffBean implements Parcelable{
	private static final String TAG ="PumponoffBean";
	private String wellno;//水井编码
	private String wellname;//水井名称
	private String welladdr;//水井地址
	private String pumpontime;//开泵时间
	private String pumpofftime;//关泵时间
	private String status;//开关泵状态
	private String type;//数据类型
	
	
	
	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getWellno() {
		return wellno;
	}




	public void setWellno(String wellno) {
		this.wellno = wellno;
	}




	public String getWellname() {
		return wellname;
	}




	public void setWellname(String wellname) {
		this.wellname = wellname;
	}




	public String getWelladdr() {
		return welladdr;
	}




	public void setWelladdr(String welladdr) {
		this.welladdr = welladdr;
	}




	public String getPumpontime() {
		return pumpontime;
	}




	public void setPumpontime(String pumpontime) {
		this.pumpontime = pumpontime;
	}




	public String getPumpofftime() {
		return pumpofftime;
	}




	public void setPumpofftime(String pumpofftime) {
		this.pumpofftime = pumpofftime;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public PumpOnoffBean() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PumponoffRecordBean [wellno=" + wellno + ", wellname=" + wellname
				+ ", welladdr=" + welladdr +", pumpontime=" + pumpontime + ", pumpofftime=" + pumpofftime +",status="+ status+"]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(wellno);
		dest.writeString(wellname);
		dest.writeString(welladdr);
		dest.writeString(pumpontime);
		dest.writeString(pumpofftime);
		dest.writeString(type);
	}
	
	public static final Parcelable.Creator<PumpOnoffBean> CREATOR = new Creator<PumpOnoffBean>() {

		@Override
		public PumpOnoffBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new PumpOnoffBean[size];
		}

		@Override
		public PumpOnoffBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			PumpOnoffBean mImportRiverBean = new PumpOnoffBean();
			mImportRiverBean.wellno= source.readString();
			mImportRiverBean.wellname= source.readString();
			mImportRiverBean.welladdr = source.readString();
			mImportRiverBean.pumpontime= source.readString();
			mImportRiverBean.pumpofftime = source.readString();
			mImportRiverBean.type = source.readString();
			return mImportRiverBean;
		}
	};


}
