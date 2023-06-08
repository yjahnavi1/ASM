package com.cg.aps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aps.entities.Flat;


public interface FlatDAO extends JpaRepository<Flat,String>{
	public Flat findByOwnerName(String ownerName);
	List<Flat> getByOwnerName(String ownerName);

}
