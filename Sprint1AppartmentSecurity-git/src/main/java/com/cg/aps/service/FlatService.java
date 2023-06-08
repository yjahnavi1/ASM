package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.Flat;
import com.cg.aps.exception.RecordNotFoundException;

public interface FlatService {
	
public List<Flat> add(Flat bean);
	
	public List<Flat> update(Flat bean);
	
	List<Flat> deleteById(String id);
	
	//public void delete(Flat bean);
	
	//public Flat findByOwnerName(String ownerName);	
	
	//public List<Flat> search(Flat bean, long pageNo, int pageSize);
	
	public Flat search(Flat bean) throws RecordNotFoundException;

	public List<Flat> getAllFlatRecords();

	//public Flat findFlat(Integer flatNo) throws RecordNotFoundException;

	//List<Flat> getByownerName(String ownerName);

	public Flat findFlat(String id) throws RecordNotFoundException;

	List<Flat> getByName(String name);
	
	public List<Flat> save(Flat bean);


}
