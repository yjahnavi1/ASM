package com.cg.aps.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
//@DiscriminatorValue("Delivery")
public class Delivery {
	@Id
	private String id;
	@NotEmpty(message = "company can not be empty")
	private String company;
	
	public Delivery() {
		super();
	}

	public Delivery(String company) {
		super();
		this.company = company;
	}
//	public Delivery(String company,String id, String visitorMobileNo,String name,
//			LocalTime arrivalTime, LocalTime departureTime, LocalDate date,List<Flat> flats, Vehicle vehicle) {
//		super(id,visitorMobileNo,  name, arrivalTime, departureTime, date,
//				flats,  vehicle);
//		this.company = company;
//	}
	public Delivery(String id, @NotEmpty(message = "company can not be empty") String company) {
		super();
		this.id = id;
		this.company = company;
	}
	

	public String getCompany() {
		return company;
	}

	

	public void setCompany(String company) {
		this.company = company;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

//	@Override
//	public String toString() {
//		return "Delivery [company=" + company + ", flats=" + flats + ", vehicle=" + vehicle + "]";
//	}
	


}
