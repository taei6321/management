package com.namowebiz.ip;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.namowebiz.jdbc.*;

public class IpInsert {
	
	private PreparedStatement pstmt;
	private Connection conn;
	
	public IpInsert() {
		ConnectMySQL cMysql = new ConnectMySQL();
		conn = cMysql.getConn();
	}
	
	// Save IP to Database
	public int insertIP(String ip, boolean location) {
		
		String i_ip_sql = "INSERT INTO Ip_Check_TB VALUES (?,?)";
		
		try {
			pstmt = conn.prepareStatement(i_ip_sql);			
			pstmt.setString(1, ip);
			pstmt.setBoolean(2, location);
			pstmt.executeUpdate();
								
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
