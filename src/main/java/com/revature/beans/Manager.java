package com.revature.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Manager {
	@Id
	private long manager_id;
	private String username, fname, lname, email, password;

	public Manager() {
		super();
	}

	public long getManager_id() {
		return manager_id;
	}

	public void setManager_id(long client_id) {
		this.manager_id = client_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Manager [manager_id=" + manager_id + ", username=" + username + ", fname=" + fname + ", lname=" + lname
				+ ", email=" + email + ", password=" + password + "]";
	}

}