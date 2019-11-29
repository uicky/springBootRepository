package com.hqyj.modules.account.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
* <p>Title: RoleResource</p>  
* <p>Description: </p>  
* @author zhaopeng
* @date 2019年11月28日
 */
@Entity
@Table(name = "m_role_resource")
public class RoleResource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userRoleId;
	private int userId;
	private int roleId;

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "RoleResource [userRoleId=" + userRoleId + ", userId=" + userId + ", roleId=" + roleId + "]";
	}

	public RoleResource(int userRoleId, int userId, int roleId) {
		super();
		this.userRoleId = userRoleId;
		this.userId = userId;
		this.roleId = roleId;
	}

	public RoleResource() {
	}

}
