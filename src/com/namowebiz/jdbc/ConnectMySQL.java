package com.namowebiz.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQL {
	
	Connection conn = null;
	
	public ConnectMySQL() {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String dbName = "namo";
			
			String dbUser = "namo";
			String dbPwd = "2580";
			String dbURL = "jdbc:mysql://192.168.0.115:3306/"+dbName;
			
			Class.forName(driver);
			conn = DriverManager.getConnection(dbURL, dbUser, dbPwd);
			setConn(conn);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public void connClose() {
		JdbcUtil.close(conn);
	}
	
}
