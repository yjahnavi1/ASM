package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.User;
import com.cg.aps.exception.RecordNotFoundException;

public interface UserService {
	
	public List<User> getAllUserRecords();

	public List<User> add(User bean);
	
	public List<User> update(User bean);
	
	public List<User> deleteById(String UserId);
	
	//public User findByName(User name);
	
	public User findUser(String id) throws RecordNotFoundException;
	
	
	public User search(User bean) throws RecordNotFoundException;

	List<User> save(User bean);

	public List<User> getByMobileNo(String mobileNo);

}
