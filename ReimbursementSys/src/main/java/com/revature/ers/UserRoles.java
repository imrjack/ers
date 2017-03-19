package com.revature.ers;

public class UserRoles {
	@Override
	public String toString() {
		return "UserRoles [roles=" + role + ", roleId=" + roleId + "]";
	}
	private String role;
	private int roleId;
	
	public UserRoles(String role, int roleId) {
		super();
		this.role = role;
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public UserRoles() {
		super();
		// TODO Auto-generated constructor stub
	}
}
