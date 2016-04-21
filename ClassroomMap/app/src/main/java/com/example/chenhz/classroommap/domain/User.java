package com.example.chenhz.classroommap.domain;

import java.io.Serializable;

public class User implements Serializable{
	private String username;
	private String password;
	private int Uday;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, int Uday) {
		super();
		this.username = username;
		this.password = password;
		this.Uday = Uday;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUday() {
		return Uday;
	}
	public void setUday(int uday) {
		this.Uday = uday;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password="
				+ password + ", Uday=" + Uday + "]";
	}
	
}
