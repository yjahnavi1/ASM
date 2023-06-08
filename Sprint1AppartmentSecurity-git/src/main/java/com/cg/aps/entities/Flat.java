package com.cg.aps.entities;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


//@MappedSuperclass
@Entity
public class Flat {
	
	@Id
	@Column(name ="flat_id")
	private String flatId;
	private String ownerName;
	private String flatNo;
	private String ownerMobileNo;
	
//	@JsonIgnore
//	@ManyToMany(mappedBy = "flats")//(fetch = FetchType.EAGER)
//	List<Visitor> visitors;
	
	public Flat() {
		super();
	}

	public Flat(String ownerName, String flatNo, String flatId, String ownerMobileNo, List<Visitor> visitors) {
		super();
		this.ownerName = ownerName;
		this.flatNo = flatNo;
		this.flatId = flatId;
		this.ownerMobileNo = ownerMobileNo;
		//this.visitors = visitors;
	}
	

	public Flat(String flatId, String ownerName, String flatNo, String ownerMobileNo) {
		super();
		this.flatId = flatId;
		this.ownerName = ownerName;
		this.flatNo = flatNo;
		this.ownerMobileNo = ownerMobileNo;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getFlatId() {
		return flatId;
	}

	public void setFlatId(String flatId) {
		this.flatId = flatId;
	}

	public String getOwnerMobileNo() {
		return ownerMobileNo;
	}

	public void setOwnerMobileNo(String ownerMobileNo) {
		this.ownerMobileNo = ownerMobileNo;
	}

//	public List<Visitor> getVisitor() {
//		return visitors;
//	}
//
//	public void setVisitor(List<Visitor> visitors) {
//		this.visitors = visitors;
//	}

	@Override
	public String toString() {
		return "Flat [flatId=" + flatId + ", ownerName=" + ownerName + ", flatNo=" + flatNo + ", ownerMobileNo="
				+ ownerMobileNo + ", visitor=" + "]";
	}
	

}
