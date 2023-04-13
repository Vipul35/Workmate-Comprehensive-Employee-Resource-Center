package com.example.SpringAssignment3;

public class Organization {
	private int id;
	private String name;
	private String code;
	private String location;
	private int maxEmpAllowed;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getMaxEmpAllowed() {
		return maxEmpAllowed;
	}
	public void setMaxEmpAllowed(int maxEmpAllowed) {
		this.maxEmpAllowed = maxEmpAllowed;
	}
}
