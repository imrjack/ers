package com.revature.ers;


public class User {
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", fullName=" + fullName + ", email=" + email + ", role="
				+ role + "]";
	}
	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	private UserRoles role;
	
	
	
	public User(int userId, String username, String password, String firstName, String lastName, String email,
			UserRoles role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	
	public UserRoles getRole() {
		return role;
	}


	public void setRole(UserRoles role) {
		this.role = role;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFullName(String fullName){
		this.fullName = fullName;
	}
	public String getFullName(){
		return fullName;
	}
}
