package com.cg.aps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.aps.entities.Vehicle;

@Repository
public interface VehicleDAO extends JpaRepository<Vehicle,String>{
	List<Vehicle> getByVehicleNo(String no);

	List<Vehicle> findByVehicleNo(String no);

}
