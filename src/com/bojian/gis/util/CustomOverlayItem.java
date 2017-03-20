package com.bojian.gis.util;

import android.graphics.drawable.Drawable;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.OverlayItem;

public class CustomOverlayItem extends OverlayItem{
	private String level;
	private int flag;
	private String value;
	
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	public CustomOverlayItem(GeoPoint arg0, String arg1, String arg2,String arg3,int flag,String value) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
		this.level = arg3;
		this.flag = flag;
		this.value =value;
	}

	public CustomOverlayItem(GeoPoint arg0,String arg1,String arg2){
		super(arg0, arg1, arg2);
	}


	/* (non-Javadoc)
	 * @see com.baidu.mapapi.OverlayItem#getMarker(int)
	 */
	@Override
	public Drawable getMarker(int arg0) {
		// TODO Auto-generated method stub
		return super.getMarker(arg0);
	}



	/* (non-Javadoc)
	 * @see com.baidu.mapapi.OverlayItem#getPoint()
	 */
	@Override
	public GeoPoint getPoint() {
		// TODO Auto-generated method stub
		return super.getPoint();
	}



	/* (non-Javadoc)
	 * @see com.baidu.mapapi.OverlayItem#getSnippet()
	 */
	@Override
	public String getSnippet() {
		// TODO Auto-generated method stub
		return super.getSnippet();
	}



	/* (non-Javadoc)
	 * @see com.baidu.mapapi.OverlayItem#getTitle()
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return super.getTitle();
	}



	/* (non-Javadoc)
	 * @see com.baidu.mapapi.OverlayItem#routableAddress()
	 */
	@Override
	public String routableAddress() {
		// TODO Auto-generated method stub
		return super.routableAddress();
	}



	/* (non-Javadoc)
	 * @see com.baidu.mapapi.OverlayItem#setMarker(android.graphics.drawable.Drawable)
	 */
	@Override
	public void setMarker(Drawable arg0) {
		// TODO Auto-generated method stub
		super.setMarker(arg0);
	}

	
	
}
