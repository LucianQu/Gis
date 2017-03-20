package com.bojian.gis.entity;

public class ItemBean {
	private String name;
	private int id;
	public ItemBean() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ItemBean(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", id=" + id + "]";
	}
	
}
