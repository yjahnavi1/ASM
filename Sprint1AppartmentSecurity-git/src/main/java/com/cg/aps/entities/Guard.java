package com.cg.aps.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
//@Table
public class Guard {
	
	@Id
	private String id;
	
	@NotEmpty(message = "Guard name Can not be empty")
	private String guardName;
	
	@NotEmpty(message = "Guard mobile number Can not be empty")
	private String guardMobileNo;
	@NotNull(message = "shiftTime Can not be empty")
	private LocalTime shiftTime;
	@NotNull(message = "Date Can not be blank or null")
	@JsonFormat(pattern = "yyyy-MM-dd")
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate date;
	
	public Guard() {
		super();
	}

	public Guard(String id, @NotEmpty(message = "Guard name Can not be empty") String guardName,
			@NotEmpty(message = "Guard mobile number Can not be empty") String guardMobileNo,
			@NotNull(message = "shiftTime Can not be empty") LocalTime shiftTime,
			@NotNull(message = "Date Can not be blank or null") LocalDate date) {
		super();
		this.id = id;
		this.guardName = guardName;
		this.guardMobileNo = guardMobileNo;
		this.shiftTime = shiftTime;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGuardName() {
		return guardName;
	}

	public void setGuardName(String guardName) {
		this.guardName = guardName;
	}

	public String getGuardMobileNo() {
		return guardMobileNo;
	}

	public void setGuardMobileNo(String guardMobileNo) {
		this.guardMobileNo = guardMobileNo;
	}

	public LocalTime getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(LocalTime shiftTime) {
		this.shiftTime = shiftTime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Guard [id=" + id + ", guardName=" + guardName + ", guardMobileNo=" + guardMobileNo + ", shiftTime="
				+ shiftTime + ", date=" + date + "]";
	}
	
	
	
}
