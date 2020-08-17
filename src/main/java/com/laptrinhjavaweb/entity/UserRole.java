package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;

@Entity
public class UserRole extends BaseEntity {
	@Column (name ="userid")
	private String userId;
	
	@Column (name ="roleid")
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
