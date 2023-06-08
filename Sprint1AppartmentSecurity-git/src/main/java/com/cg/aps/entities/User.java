package com.cg.aps.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	private String id;
	private String loginId;
	private String password;
	private String mobileNo;
	private String role;
	
	public User() {
		super();
	}

	public User(String id, String loginId, String password, String mobileNo, String role) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.password = password;
		this.mobileNo = mobileNo;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginId=" + loginId + ", password=" + password + ", mobileNo=" + mobileNo
				+ ", role=" + role + "]";
	}

	
	

}
