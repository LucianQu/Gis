package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;


public class ImportRiverBeanEx implements Parcelable {

	private String countyName ;	//区县名称
	private String rsnm;       //水库名称
	private String valleySq  ; //流域面积
	private String irrigwatlev  ; //兴利水位
	private String efst  ;	//兴利库容
	private String floodlv  ;	//汛限水位
	private String floodst  ;  //汛限库容
	private String warnlv  ; //警戒水位
	private String warnst  ; //警戒库容
	private String ckflz  ;	//校核水位
	private String chkst  ; //校核库容
	private String spillForm;//溢洪道    型 式
	private String rz;			//实时水位
	private String ratioLv;		//比汛限水位(根据时间不同表现为比超蓄水位、比汛末水位)
	private String w;			//实时蓄水量
	private String ratioSt;		//比汛限库容(根据时间不同表现为比超蓄库容、比汛末库容)
	private String otqHole;		//输水洞出库流量
	private String otqRode;		//溢洪道出库流量
	
	
	
	
	private String fldControlLv;//汛限水位(根据时间不同表现为允许超蓄水位、汛末水位)
	private String fldControlSt;//汛限库容(根据时间不同表现为允许超蓄库容、汛末库容)
	private String stcd;
	private String dataTime;
	private String style = null;		//控制实时数据显示形式	1--显示红色，2--闪烁
			
	
	private String supStoLv;	//允许超蓄水位
	private String supStoSt;	//允许超蓄库容
	private String floodEndLv;	//汛末蓄水位
	private String floodEndSt;	//汛末库容

	
	

	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
		dest.writeString(countyName);
		dest.writeString(rsnm);
		dest.writeString(valleySq);
		dest.writeString(irrigwatlev);
		dest.writeString(efst);
		dest.writeString(floodlv);
		dest.writeString(floodst);
		dest.writeString(warnlv);
		dest.writeString(warnst);
		dest.writeString(ckflz);
		dest.writeString(chkst);
		dest.writeString(spillForm);
		dest.writeString(rz);
		dest.writeString(ratioLv);
		dest.writeString(w);
		dest.writeString(ratioSt);
		dest.writeString(otqHole);
		dest.writeString(otqRode);
		
		
		dest.writeString(fldControlLv);
		dest.writeString(fldControlSt);
		dest.writeString(stcd);
		dest.writeString(dataTime);
		dest.writeString(style);
		dest.writeString(supStoLv);
		dest.writeString(supStoSt);
		dest.writeString(floodEndLv);
		dest.writeString(floodEndSt);
		
		

	}
	
	
	

	public static final Parcelable.Creator<ImportRiverBeanEx> CREATOR = new Creator<ImportRiverBeanEx>() {

		@Override
		public ImportRiverBeanEx[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ImportRiverBeanEx[size];
		}

		@Override
		public ImportRiverBeanEx createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ImportRiverBeanEx mImportRiverBeanEx = new ImportRiverBeanEx();
			mImportRiverBeanEx.countyName= source.readString();
			mImportRiverBeanEx.rsnm = source.readString();
			mImportRiverBeanEx.valleySq= source.readString();
			mImportRiverBeanEx.irrigwatlev = source.readString();
			mImportRiverBeanEx.efst = source.readString();
			mImportRiverBeanEx.floodlv = source.readString();
			mImportRiverBeanEx.floodst = source.readString();
			mImportRiverBeanEx.warnlv = source.readString();
			mImportRiverBeanEx.warnst= source.readString();
			mImportRiverBeanEx.ckflz = source.readString();
			mImportRiverBeanEx.chkst= source.readString();
			mImportRiverBeanEx.spillForm = source.readString();
			mImportRiverBeanEx.rz = source.readString();
			mImportRiverBeanEx.ratioLv = source.readString();
			mImportRiverBeanEx.w = source.readString();
			mImportRiverBeanEx.ratioSt = source.readString();
			mImportRiverBeanEx.otqHole = source.readString();
			mImportRiverBeanEx.otqRode = source.readString();
			mImportRiverBeanEx.fldControlLv = source.readString();
			mImportRiverBeanEx.fldControlSt = source.readString();
			mImportRiverBeanEx.stcd = source.readString();
			mImportRiverBeanEx.dataTime = source.readString();
			mImportRiverBeanEx.style = source.readString();
			mImportRiverBeanEx.supStoLv = source.readString();
			mImportRiverBeanEx.supStoSt = source.readString();
			mImportRiverBeanEx.floodEndLv = source.readString();
			mImportRiverBeanEx.floodEndSt = source.readString();
			

			
			return mImportRiverBeanEx;
		}
	};






	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getRsnm() {
		return rsnm;
	}

	public void setRsnm(String rsnm) {
		this.rsnm = rsnm;
	}

	public String getValleySq() {
		return valleySq;
	}

	public void setValleySq(String valleySq) {
		this.valleySq = valleySq;
	}

	public String getIrrigwatlev() {
		return irrigwatlev;
	}

	public void setIrrigwatlev(String irrigwatlev) {
		this.irrigwatlev = irrigwatlev;
	}

	public String getEfst() {
		return efst;
	}

	public void setEfst(String efst) {
		this.efst = efst;
	}

	public String getFloodlv() {
		return floodlv;
	}

	public void setFloodlv(String floodlv) {
		this.floodlv = floodlv;
	}

	public String getFloodst() {
		return floodst;
	}

	public void setFloodst(String floodst) {
		this.floodst = floodst;
	}

	public String getWarnlv() {
		return warnlv;
	}

	public void setWarnlv(String warnlv) {
		this.warnlv = warnlv;
	}

	public String getWarnst() {
		return warnst;
	}

	public void setWarnst(String warnst) {
		this.warnst = warnst;
	}

	public String getCkflz() {
		return ckflz;
	}

	public void setCkflz(String ckflz) {
		this.ckflz = ckflz;
	}

	public String getChkst() {
		return chkst;
	}

	public void setChkst(String chkst) {
		this.chkst = chkst;
	}

	public String getSpillForm() {
		return spillForm;
	}

	public void setSpillForm(String spillForm) {
		this.spillForm = spillForm;
	}

	public String getRz() {
		return rz;
	}

	public void setRz(String rz) {
		this.rz = rz;
	}

	public String getRatioLv() {
		return ratioLv;
	}

	public void setRatioLv(String ratioLv) {
		this.ratioLv = ratioLv;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getRatioSt() {
		return ratioSt;
	}

	public void setRatioSt(String ratioSt) {
		this.ratioSt = ratioSt;
	}

	public String getOtqHole() {
		return otqHole;
	}

	public void setOtqHole(String otqHole) {
		this.otqHole = otqHole;
	}

	public String getOtqRode() {
		return otqRode;
	}

	public void setOtqRode(String otqRode) {
		this.otqRode = otqRode;
	}

	public String getFldControlLv() {
		return fldControlLv;
	}

	public void setFldControlLv(String fldControlLv) {
		this.fldControlLv = fldControlLv;
	}

	public String getFldControlSt() {
		return fldControlSt;
	}

	public void setFldControlSt(String fldControlSt) {
		this.fldControlSt = fldControlSt;
	}

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSupStoLv() {
		return supStoLv;
	}

	public void setSupStoLv(String supStoLv) {
		this.supStoLv = supStoLv;
	}

	public String getSupStoSt() {
		return supStoSt;
	}

	public void setSupStoSt(String supStoSt) {
		this.supStoSt = supStoSt;
	}

	public String getFloodEndLv() {
		return floodEndLv;
	}

	public void setFloodEndLv(String floodEndLv) {
		this.floodEndLv = floodEndLv;
	}

	public String getFloodEndSt() {
		return floodEndSt;
	}

	public void setFloodEndSt(String floodEndSt) {
		this.floodEndSt = floodEndSt;
	}


	
	
}
