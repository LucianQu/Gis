package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ReChargeBean implements Parcelable{
	private static final String TAG ="ReChargeBean";
	private String villiage;           //所在村
	private String name;			   //用户名
	private String dealtime;   //售水时间	
	private String dealingprice; //售水金额
	private String dealingprice_E;   //售电金额
	private String remainPrice;//剩余水量
	private String remainPrice_E;//剩余电量
	

	public String getDealtime() {
		return dealtime;
	}
	public void setDealtime(String dealtime) {
		this.dealtime = dealtime;
	}
	public String getDealingprice() {
		return dealingprice;
	}
	public void setDealingprice(String dealingprice) {
		this.dealingprice = dealingprice;
	}
	public String getDealingprice_E() {
		return dealingprice_E;
	}
	public void setDealingprice_E(String dealingprice_E) {
		this.dealingprice_E = dealingprice_E;
	}
	public String getRemainPrice() {
		return remainPrice;
	}
	public void setRemainPrice(String remainPrice) {
		this.remainPrice = remainPrice;
	}
	public String getRemainPrice_E() {
		return remainPrice_E;
	}
	public void setRemainPrice_E(String remainPrice_E) {
		this.remainPrice_E = remainPrice_E;
	}
	public String getVilliage() {
		return villiage;
	}
	public void setVilliage(String villiage) {
		this.villiage = villiage;
	}
	public ReChargeBean() {
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
		return "WaterUsingBean [name=" + name + ", dealtime=" + dealtime
				+ ", dealingprice=" + dealingprice +", dealingprice_E=" + dealingprice_E +", remainPrice=" + remainPrice +", remainPrice_E=" + remainPrice_E +"]";
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
		dest.writeString(dealtime);
		dest.writeString(dealingprice);
		dest.writeString(dealingprice_E);
		dest.writeString(remainPrice);
		dest.writeString(remainPrice_E);
	}
	
	public static final Parcelable.Creator<ReChargeBean> CREATOR = new Creator<ReChargeBean>() {

		@Override
		public ReChargeBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ReChargeBean[size];
		}

		@Override
		public ReChargeBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ReChargeBean mImportRiverBean = new ReChargeBean();
			mImportRiverBean.villiage = source.readString();
			mImportRiverBean.name= source.readString();
			mImportRiverBean.dealtime= source.readString();
			mImportRiverBean.dealingprice = source.readString();
			mImportRiverBean.dealingprice_E= source.readString();
			mImportRiverBean.remainPrice = source.readString();
			mImportRiverBean.remainPrice_E = source.readString();
			return mImportRiverBean;
		}
	};

}
