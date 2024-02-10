package com.mall.cloud.rbac.model;

import com.mall.cloud.common.model.BaseModel;

//用户与角色对应关系

public class UserRole extends BaseModel {

	/**
	 * 关联id
	 */
	private Long id;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 角色ID
	 */
	private Long roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRole{" + "id=" + id + ", userId=" + userId + ", roleId=" + roleId + "} " + super.toString();
	}

}
