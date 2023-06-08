package com.cg.aps.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_login")
public class AdminLogin {
	
	@Id
	private String loginId;
	private String password;
	private String role;
	
	public AdminLogin() {
		super();
	}

	public AdminLogin(String loginId, String password, String role) {
		super();
		this.loginId = loginId;
		this.password = password;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AdminLogin [loginId=" + loginId + ", password=" + password + ", role=" + role + "]";
	}
	

}
