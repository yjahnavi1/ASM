package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.DomesticHelp;
import com.cg.aps.exception.RecordNotFoundException;

public interface DomesticHelpService {
	
	public List<DomesticHelp> getAllDomesticHelpRecords();

	public List<DomesticHelp> addDomesticHelpRecord(DomesticHelp bean);
	
	public List<DomesticHelp> updateDomesticHelpRecord(DomesticHelp bean);
	
	public List<DomesticHelp> deleteDomesticHelpRecordById(String helperId);
	
	//public List<DomesticHelp> findRecordByHelperName(String helperName);
	
	public DomesticHelp findDomesticHelpRecordById(String helperId) throws RecordNotFoundException;
	
	
	public DomesticHelp searchDomesticHelpRecord(DomesticHelp bean) throws RecordNotFoundException;

	List<DomesticHelp> save(DomesticHelp bean);

	public List<DomesticHelp> getByHelpType(String helperName);

	//List<Guard> saveAll();

}
