package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.Delivery;
import com.cg.aps.exception.RecordNotFoundException;

public interface DeliveryService {
	
	public List<Delivery> getAllDeliveryRecords();

	public List<Delivery> add(Delivery bean);
	
	public List<Delivery> update(Delivery bean);
	
	public List<Delivery> deleteById(String id);//id is mobile no
	
	//public List<Delivery> findByName(String name);
	
	public Delivery findDeliveryById(String id) throws RecordNotFoundException;
	
	
	//public Delivery search(Delivery bean) throws RecordNotFoundException;

	List<Delivery> save(Delivery bean);

	//public List<Delivery> getByName(String name);//get by visitor name

	//List<Delivery> saveAll();

}
