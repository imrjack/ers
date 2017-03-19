package com.revature.ers;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static final String url = "jdbc:oracle:thin:@hello.cgbbs6xdwjwh.us-west-2.rds.amazonaws.com:1521:ORCL";
	private static final String user = "IMRJACK";
	private static final String password = "abc123";


	public static Connection getConnection() throws Exception{
		// Load driver
		Class.forName("oracle.jdbc.OracleDriver");
		return DriverManager.getConnection(url,user,password);
	}
}
