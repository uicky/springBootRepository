package com.hqyj.modules.account.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
* <p>Title: Resource</p>  
* <p>Description: </p>  
* @author zhaopeng
* @date 2019年12月3日
 */
@Entity
@Table(name = "m_resource")
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resourceId;
	private String resourceUri;
	private String resourceName;
	private String permission;

	@Transient
	private List<Role> roles;

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceUri() {
		return resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "Resource [resourceId=" + resourceId + ", resourceUri=" + resourceUri + ", resourceName=" + resourceName
				+ ", permission=" + permission + ", roles=" + roles + "]";
	}

	public Resource(int resourceId, String resourceUri, String resourceName, String permission, List<Role> roles) {
		super();
		this.resourceId = resourceId;
		this.resourceUri = resourceUri;
		this.resourceName = resourceName;
		this.permission = permission;
		this.roles = roles;
	}

	public Resource() {
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
