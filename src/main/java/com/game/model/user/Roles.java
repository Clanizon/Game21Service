package com.game.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@org.hibernate.annotations.DynamicUpdate
@Table(name = "game_roles")
public class Roles {
	public Roles() {
		super();
	}
	@Id
	@Column(name = "ROLE_ID", nullable = false)
    Integer  roleId;	
	String roleType;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
}
