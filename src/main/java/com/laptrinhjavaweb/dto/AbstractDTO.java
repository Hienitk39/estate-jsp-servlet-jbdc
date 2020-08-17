package com.laptrinhjavaweb.dto;

import java.sql.Timestamp;
public class AbstractDTO {
	private Long id;
	private Timestamp modifieDate;
	private String createdBy;
	private String modifiedBy;
	private Timestamp createdDate;
	private int maxPageItem=10;
	private int page=1;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public int getMaxPageItem() {
		return maxPageItem;
	}
	public void setMaxPageItem(int maxPageItem) {
		this.maxPageItem = maxPageItem;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	
}
