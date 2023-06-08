package com.cg.aps.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cg.aps.entities.Visitor;

@Repository
public interface VisitorDAO extends JpaRepository<Visitor,String>{

		public List<Visitor> findByName(String visitorName);
		//public List<Visitor> search(Visitor bean);
		

}