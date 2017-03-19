package com.revature.ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.util.Password;

public class UserDao {
	private Connection conn;
	
	public UserDao(Connection conn){
		this.conn = conn;
	}
	
	public User getOne(String username) throws Exception, SQLException{
		String sql = "SELECT ERS_USER_ID, CONCAT(u.USER_FIRST_NAME , concat(' ', u.USER_LAST_NAME)) AS NAME "
					+", u.USER_EMAIL" 
					+", u.ERS_USERNAME"
					+", u.USER_FIRST_NAME"
					+", u.USER_LAST_NAME"
					+", ur.* " 
					+"FROM ERS_USERS u "
					+"INNER JOIN ERS_USER_ROLES ur "
					+"ON u.ERS_USER_ID = ur.ERS_USER_ROLE_ID "
					+"WHERE u.ERS_USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql); //parameterize sql stmt
		stmt.setString(1, username); //bind values to "?" placeholder. starts with 1 not 0
		ResultSet rs = stmt.executeQuery();
		User user = new User();
		while(rs.next()){
			user.setUserId(rs.getInt("ERS_USER_ID"));
			user.setFullName(rs.getString("NAME"));
			user.setEmail(rs.getString("USER_EMAIL"));	
			user.setUsername(rs.getString("ERS_USERNAME"));
			user.setFirstName(rs.getString("USER_FIRST_NAME"));
			user.setRole(new UserRoles(rs.getString("USER_ROLE"),rs.getInt("ERS_USER_ROLE_ID")));
		}
		return user;
		
	}
	
	public List<User> getAll() throws Exception{
		String sql = "SELECT ERS_USER_ID, CONCAT(u.USER_FIRST_NAME , concat(' ', u.USER_LAST_NAME)) AS NAME "
				+", u.USER_EMAIL" 
				+", u.ERS_USERNAME "
				+", ur.USER_ROLE "
				+"FROM ERS_USERS u "
				+"RIGHT JOIN ERS_USER_ROLES ur "
				+"ON u.ERS_USER_ID = ur.ERS_USER_ROLE_ID ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<User> list = new ArrayList<User>();		
		
		while(rs.next()){
			User user = new User();
			user.setUserId(rs.getInt("ERS_USER_ID"));
			user.setFullName(rs.getString("NAME"));
			user.setUsername(rs.getString("ERS_USERNAME"));
			user.setEmail(rs.getString("USER_EMAIL"));
			user.setRole(new UserRoles(rs.getString("USER_ROLE"), rs.getInt("ERS_USER_ID")));
			list.add(user);
		}
		
		return list;	
	}
	
	public boolean isUser(String username, String password) throws Exception{
		// use password class methods in util package to verify pw input against hashed pw in db 
		boolean isUser = new Password().checkPassword(username, password);
		return isUser;
	}
	
	public void createUser(String username
			, String password
			, String first_name
			, String last_name
			, String email
			, int role_id
			) throws Exception{
	
		Password pw = new Password();
		password = pw.hashPassword(password);
		
		String sql = "INSERT INTO ERS_USERS VALUES(?, ?, ? ,?, ?, ?, ?)";
//		 cols: username, password, f_name, l_name, email, role_id 
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, 1);
		stmt.setString(2, username);
		stmt.setString(3, password);
		stmt.setString(4, first_name);
		stmt.setString(5, last_name);
		stmt.setString(6, email);
		stmt.setInt(7, role_id);
		
		stmt.executeQuery();
		
	}

//	public void save (User user) throws Exception{
//		String sql="INSERT INTO ERS_USERS";
//	}
	public static void main(String[] args) throws Exception{
//		List<User> result = new UserDao(ConnectionFactory.getConnection()).getAll();
//		new UserDao(ConnectionFactory.getConnection()).createUser("new_me", "abc1232", "new", "user", "user@example.com", 1);
		System.out.println(new UserDao(ConnectionFactory.getConnection()).isUser("new_me", "abc1232"));

	}
}
