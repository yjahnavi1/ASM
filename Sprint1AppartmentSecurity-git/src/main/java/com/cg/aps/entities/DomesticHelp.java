package com.cg.aps.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
//@DiscriminatorValue("DomesticHelp")
public class DomesticHelp {
	
	@Id
	private String id;
	@NotEmpty(message = "helpType Can not be empty")
	private String helpType;
	
	public DomesticHelp() {
		super();
	}
//	public DomesticHelp(String helpType,String id, @NotEmpty(message = "visitor MobileNo Can not be empty") String visitorMobileNo,
//			@NotEmpty(message = "visitor Name Can not be empty") String name,
//			@NotEmpty(message = "arrivalTime Name Can not be empty") LocalTime arrivalTime,
//			@NotEmpty(message = "departureTime Name Can not be empty") LocalTime departureTime, LocalDate date,
//			String flatNo, String vehicleId) {
//		super(id,visitorMobileNo,  name, arrivalTime, departureTime, date,
//				flatNo,  vehicleId);
//		this.helpType = helpType;
//	}
	

	public DomesticHelp(String helpType) {
		super();
		this.helpType = helpType;
	}

	public DomesticHelp(String id, @NotEmpty(message = "helpType Can not be empty") String helpType) {
	super();
	this.id = id;
	this.helpType = helpType;
}
	public String getHelpType() {
		return helpType;
	}

	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}
	
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "DomesticHelp [id=" + id + ", helpType=" + helpType + "]";
	}


//	@Override
//	public String toString() {
//		return "DomesticHelp [helpType=" + helpType + ", flats=" + flatNo + ", vehicle=" + vehicleId + "]";
//	}
	

	

	
}
