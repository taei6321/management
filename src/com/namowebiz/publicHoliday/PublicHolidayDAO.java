package com.namowebiz.publicHoliday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.namowebiz.jdbc.ConnectMySQL;
import com.namowebiz.user.User;
import com.namowebiz.user.UserDAO;

public class PublicHolidayDAO {
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
		
	public PublicHolidayDAO() {
		ConnectMySQL cMysql = new ConnectMySQL();
		conn = cMysql.getConn();
	}
	
	public int userInsert(String holidays_name, String holidays_date) {
		new UserDAO();
		String userInsert = "insert into Public_Holidays_TB values (0,?,?)";
		
		try {
		pstmt = conn.prepareStatement(userInsert);
		pstmt.setString(1, holidays_name);
		pstmt.setString(2, holidays_date);
		
		pstmt.executeUpdate();
		return 1;
		
		}catch (Exception e) {
		e.printStackTrace();
		}
		
		return -1;
	}
	
	public int holidayUpdate(String pk, String name, String date) {
		String dateInsert_SQL = "update Public_Holidays_TB set holidays_name=?, holidays_date=? where holidays_num=?";
				
		try {
			pstmt = conn.prepareStatement(dateInsert_SQL);
			pstmt.setString(1, name);
			pstmt.setString(2, date);
			pstmt.setString(3, pk);
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public ArrayList<PublicHoliday> userSelect() {
		String userSelect = "select * from Public_Holidays_TB order by holidays_date ASC";
				
		ArrayList<PublicHoliday> list = new ArrayList<PublicHoliday>();
				
		try {
			pstmt = conn.prepareStatement(userSelect);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				PublicHoliday publicHoliday = new PublicHoliday();

				publicHoliday.setHolidays_num(rs.getString("holidays_num"));
				publicHoliday.setHolidays_name(rs.getString("holidays_name"));
				publicHoliday.setHolidays_date(rs.getString("holidays_date"));
										
				list.add(publicHoliday);
			}
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int userSelect(String date) {
		String userSelect = "select * from Public_Holidays_TB where holidays_date =?";
								
		try {
			pstmt = conn.prepareStatement(userSelect);
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				if(rs.getString("holidays_name") != null) {
					return 1;
				}else {
					return 0;
				}
			}
			return -1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -2;
	}

}
