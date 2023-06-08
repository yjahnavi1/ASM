package com.cg.aps.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;


//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "VISITORTYPE")
public class Visitor {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="visitor_id")
	private String id;
	//@NotEmpty(message = "visitor MobileNo Can not be empty")
	private String visitorMobileNo;
	//@NotEmpty(message = "visitor Name Can not be empty")
	private String name;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime arrivalTime;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime departureTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
//	@ManyToMany(cascade=CascadeType.ALL)
//	@JoinTable(
//			  name = "flat_visitor", 
//			  joinColumns = @JoinColumn(name = "visitor_id"), 
//			  inverseJoinColumns = @JoinColumn(name = "flat_id"))
//	List<Flat> flats;
	
	protected String flatNo;
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name ="vehicleId")
//	Vehicle vehicle;
	protected String vehicleId;
	
	public Visitor(String id, String visitorMobileNo, String name, LocalTime arrivalTime, LocalTime departureTime,
			LocalDate date, String flatNo, String vehicleId) {
		super();
		this.id = id;
		this.visitorMobileNo = visitorMobileNo;
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.date = date;
		this.flatNo = flatNo;
		this.vehicleId = vehicleId;
	}
	
	
	public Visitor() {
		super();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getVisitorMobileNo() {
		return visitorMobileNo;
	}


	public void setVisitorMobileNo(String visitorMobileNo) {
		this.visitorMobileNo = visitorMobileNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalTime getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public LocalTime getDepartureTime() {
		return departureTime;
	}


	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getFlatNo() {
		return flatNo;
	}


	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}


	public String getVehicleId() {
		return vehicleId;
	}


	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}


	


	@Override
	public String toString() {
		return "Visitor [id=" + id + ", visitorMobileNo=" + visitorMobileNo + ", name=" + name + ", arrivalTime="
				+ arrivalTime + ", departureTime=" + departureTime + ", date=" + date + ", flatNo=" + flatNo
				+ ", vehicleId=" + vehicleId + "]";
	}


	
	

}
	

