package com.cg.aps.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.aps.entities.DomesticHelp;


@Repository
public interface DomesticHelpDAO extends JpaRepository<DomesticHelp,String>{
//	public DomesticHelp findByName(String helperName);
//	List<DomesticHelp> getByName(String helperName);

}