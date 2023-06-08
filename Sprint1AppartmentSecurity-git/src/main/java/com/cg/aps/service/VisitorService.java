package com.cg.aps.service;


import java.util.List;

import com.cg.aps.entities.Flat;
import com.cg.aps.entities.Visitor;
import com.cg.aps.exception.RecordNotFoundException;



public interface VisitorService {
	
	public List<Visitor> getAllVisitorRecords();

	public List<Visitor> add(Visitor bean);
	
	public List<Visitor> update(Visitor bean);
	
	public List<Visitor> deleteById(String id);
	
	public List<Visitor> findByName(String visitorName);
	
	public Visitor findById(String id) throws RecordNotFoundException;
	

	List<Visitor > save(Visitor bean);


	public List<Visitor> getByVisitorName(String visitorName);
	
	//public List<Flat>  flatsVisitedByVisitor(String id) throws RecordNotFoundException;

	//List<Visitor> saveAll();
	
	
}

