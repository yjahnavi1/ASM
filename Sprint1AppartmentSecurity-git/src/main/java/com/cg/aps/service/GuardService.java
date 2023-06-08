package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.Guard;
import com.cg.aps.exception.RecordNotFoundException;



public interface GuardService {
	
	public List<Guard> getAllGuards();

	public List<Guard> add(Guard bean);
	
	public List<Guard> update(Guard bean);
	
	public List<Guard> deleteById(String guardId);
	
	public Guard findByName(String name);
	
	public Guard findGuard(String id) throws RecordNotFoundException;
	
	
	//public Guard search(Guard bean) throws RecordNotFoundException;

	List<Guard> save(Guard bean);

	public List<Guard> getByName(String name);

	//List<Guard> saveAll();
	
	
}
