package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class PumponoffRecordBean implements Parcelable{
	private static final String TAG ="PumponoffRecordBean";
	private String wellno;//水井编码
	private String wellname;//水井名称
	private String welladdr;//水井地址
	private String pumpontime;//开泵时间
	private String pumpofftime;//关泵时间
	private String type;//数据类型
	
	
	
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




	public PumponoffRecordBean() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PumponoffRecordBean [wellno=" + wellno + ", wellname=" + wellname
				+ ", welladdr=" + welladdr +", pumpontime=" + pumpontime + ", pumpofftime=" + pumpofftime +"]";
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
	
	public static final Parcelable.Creator<PumponoffRecordBean> CREATOR = new Creator<PumponoffRecordBean>() {

		@Override
		public PumponoffRecordBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new PumponoffRecordBean[size];
		}

		@Override
		public PumponoffRecordBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			PumponoffRecordBean mImportRiverBean = new PumponoffRecordBean();
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
