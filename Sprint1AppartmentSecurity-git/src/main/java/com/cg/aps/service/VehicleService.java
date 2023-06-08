package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.Vehicle;
import com.cg.aps.exception.RecordNotFoundException;

public interface VehicleService {
	
	public List<Vehicle> add(Vehicle bean);

	List<Vehicle> update(Vehicle bean);

	List<Vehicle> save(Vehicle bean);

	List<Vehicle> deleteById(String id);

	Vehicle findById(String id) throws RecordNotFoundException;

	List<Vehicle> getAllVehicleRecords();
	
	public Vehicle search(Vehicle bean) throws RecordNotFoundException;

	List<Vehicle> getByVehicleNo(String no);


}
