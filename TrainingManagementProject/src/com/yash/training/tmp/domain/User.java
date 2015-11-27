package com.yash.training.tmp.domain;

public class User {

	private int user_id;
	private String name;
	private String contact;
	private String emailid;
	private int designation;
	private int role;
	private int status;
	private String username;
	private String password;
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getEmailid() {
		return emailid;
	}
	
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	public int getDesignation() {
		return designation;
	}
	
	public void setDesignation(int designation) {
		this.designation = designation;
	}
	
	public int getRole() {
		return role;
	}
	
	public void setRole(int role) {
		this.role = role;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
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
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", contact=" + contact + ", emailid=" + emailid
				+ ", designation=" + designation + ", role=" + role + ", status=" + status + ", username=" + username
				+ ", password=" + password + "]";
	}
	
}
