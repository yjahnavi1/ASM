package com.cg.aps.repository;

import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.aps.entities.Guard;

@Repository
public interface GuardDAO extends JpaRepository<Guard,String>{

//	public long add(Guard bean);
//	
//	public void update(Guard bean);
//	
//	public void delete(Guard bean);
//	
	public Guard findByGuardName(String name);
//	
	//public Guard findById(String id);
	
	List<Guard> getByGuardName(String name);
//	
//	public List<Guard> search(Guard bean);
//	
	
}
