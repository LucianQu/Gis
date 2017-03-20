package com.bojian.gis.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ArealBean implements Parcelable {
	/**
	 * 
	 */
	private int id;
	private String name;

	public ArealBean() {
		super();
	}

	public ArealBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Areal [id=" + id + ", name=" + name + "]";
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id);
		dest.writeString(name);
	}
	
	public static final Parcelable.Creator<ArealBean> CREATOR = new Creator<ArealBean>() {

		@Override
		public ArealBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ArealBean[size];
		}

		@Override
		public ArealBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			ArealBean areal = new ArealBean();
			areal.id = source.readInt();
			areal.name = source.readString();
			return areal;
		}
	};


}
