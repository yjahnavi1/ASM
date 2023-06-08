package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.AdminLogin;
import com.cg.aps.exception.RecordNotFoundException;

public interface AdminLoginService {
	
	public List<AdminLogin> getAllAdminLogins();

	public List<AdminLogin> add(AdminLogin bean);
	
	public AdminLogin findAdminLogin(String id) throws RecordNotFoundException;
	
	
	//public User search(User bean) throws RecordNotFoundException;

	List<AdminLogin> save(AdminLogin bean);

}
