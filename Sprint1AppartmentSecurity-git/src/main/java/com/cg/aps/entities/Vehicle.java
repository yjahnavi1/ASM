package com.cg.aps.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Vehicle {
	@Id
	@Column(name ="vehicle_id")
	private String vehicleId;
	@NotEmpty(message = "vehicleNo Can not be empty")
	private String vehicleNo;
	@NotEmpty(message = "vehicleType Can not be empty")
	private String vehicleType;
	
	public Vehicle() {
		super();
	}

	public Vehicle(String vehicleId, String vehicleNo, String vehicleType) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleNo = vehicleNo;
		this.vehicleType = vehicleType;
	}

	public String getId() {
		return vehicleId;
	}

	public void setId(String id) {
		this.vehicleId = id;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + vehicleId + ", vehicleNo=" + vehicleNo + ", vehicleType=" + vehicleType + "]";
	}

	
}
