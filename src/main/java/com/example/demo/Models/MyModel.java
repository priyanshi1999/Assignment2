package com.example.demo.Models;

public class MyModel {
	
	private String empId;
	private String name;
	private String location;
	private String email;
	public MyModel(String empId, String name, String location, String email) {
		super();
		this.empId = empId;
		this.name = name;
		this.location = location;
		this.email = email;
	}
	public MyModel() {
		super();
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
