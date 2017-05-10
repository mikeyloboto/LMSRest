package com.gcit.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static String usrName = "javaUser";
	private static String password = "javaPass";
	private static String url = "jdbc:mysql://localhost/library";
	private static String driver = "com.mysql.cj.jdbc.Driver";
	
	@Deprecated
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, usrName, password);
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
	}

}
