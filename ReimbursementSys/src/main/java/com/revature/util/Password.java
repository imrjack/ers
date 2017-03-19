package com.revature.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;
import com.revature.ers.ConnectionFactory;

/* For:
 * 1. hash passwords for newly created users
 * 2. verify simple string passwords against hashed password in db 
 */

public class Password {

	private String hashed_pw;
	private Connection conn;
	public String hashPassword(String password){
		String hash = BCrypt.hashpw(password,BCrypt.gensalt(13));
		return hash;
	}
	public boolean checkPassword(String username, String pw) throws Exception{
		/*
		 * if getHashedPw return true, 
		 *  - this.hash_pw will be set 
		 *  - will check hash_pw against user inputed pw
		 * 	if no match 
		 * 		- return false
		 * else
		 *  - return false
		 */
		boolean pw_matches = false;
		if(getHashedPw(username)){
			
			if(BCrypt.checkpw(pw, this.hashed_pw)){
				return pw_matches = true;
			}
			else{
				return pw_matches;
			}
		}else{
			return false;
		}
	}
	private boolean getHashedPw(String username) throws Exception{
		boolean userExists = false;
		String sql = "SELECT * FROM ers_users "
				+ "WHERE ers_username = ?";
		conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			this.hashed_pw = rs.getString("ers_password");
			return userExists = true;
		}
			return userExists;
	
	}
	
//	public static void main(String[] args) throws Exception{
//		Password pw = new Password();
//		boolean matches = pw.checkPassword("imrjack", "abc123");
//		System.out.println(matches);
	
//	}
}
