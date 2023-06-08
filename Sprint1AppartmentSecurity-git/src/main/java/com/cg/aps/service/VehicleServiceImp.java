package com.cg.aps.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.aps.entities.Vehicle;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.VehicleDAO;

@Service("vehicleService")
@Transactional
public class VehicleServiceImp implements VehicleService{
	
	Logger logger = LoggerFactory.getLogger(VehicleServiceImp.class);
	@Autowired
	private VehicleDAO vehicleDAO;
	
	@Override
	public List<Vehicle> update(Vehicle bean) {
		Vehicle p=vehicleDAO.save(bean);
		if(p!=null) {
			return getAllVehicleRecords();
		}
		else {
			return null;
		}
	}
	@Override
	public List<Vehicle> save(Vehicle bean) {
		Vehicle p = vehicleDAO.save(bean);
		if(p!=null) {
			return getAllVehicleRecords();
		}
		else {
			return null;
		}
	}
	@Override
	public List<Vehicle> deleteById(String id) {
		vehicleDAO.deleteById(id);		
		return getAllVehicleRecords();
	}
	@Override
	public Vehicle findById(String id) throws RecordNotFoundException{
		Optional<Vehicle> op = vehicleDAO.findById(id);
		if(op.isPresent()) {
			logger.info("Guard is retrieved from database for GuardID "+id);
			return op.get();
		}
		else
		{
			logger.error("Guard is NOT retrieved from database for GuardID "+id);
			throw new RecordNotFoundException("Guard with "+id + " not found");
		}	
	}
	@Override
	public List<Vehicle> getAllVehicleRecords() {
		return vehicleDAO.findAll();
	}
	@Override
	public List<Vehicle> getByVehicleNo(String no) {
		logger.info("Guard is found and retrieved from database for Guard name "+no);
		return vehicleDAO.findByVehicleNo(no);
	}
	@Override
	public List<Vehicle> add(Vehicle bean) {
		Vehicle obj = vehicleDAO.save(bean);
		if(obj !=null) {
			return getAllVehicleRecords();
		}
		else {
			return null;
		}
	}
	@Override
	public Vehicle search(Vehicle bean) throws RecordNotFoundException {
		Optional<Vehicle> op = vehicleDAO.findById(bean.getId());
		if(op.isPresent()) {
			logger.info("Guard is Not there in database for Guard info "+bean);
			return op.get();
		}
		else
		{
			logger.error("Guard is NOT retrieved from database for GuardID "+bean);
			throw new RecordNotFoundException("Guard with "+bean + " not found");
		}	
	}


}
