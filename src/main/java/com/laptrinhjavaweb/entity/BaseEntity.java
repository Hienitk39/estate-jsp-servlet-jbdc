package com.laptrinhjavaweb.entity;

import java.sql.Timestamp;

import com.laptrinhjavaweb.annotation.Column;

public class BaseEntity {
	@Column (name="id")
	private Long id;
	
	@Column (name="modifieddate")
	private Timestamp modifieDate;
	
	@Column (name="createdby")
	private String createdBy;
	
	@Column (name="modifiedby")
	private String modifiedBy;
	
	@Column (name="createddate")
	private Timestamp createdDate;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	


	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifieDate() {
		return modifieDate;
	}

	public void setModifieDate(Timestamp modifieDate) {
		this.modifieDate = modifieDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
