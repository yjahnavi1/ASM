package com.cg.aps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.aps.entities.Delivery;

@Repository
public interface DeliveryDAO extends JpaRepository<Delivery,String>{
	
	//public List<Delivery> findByName(String name);	
	
	//List<Delivery> getByName(String name);

}
