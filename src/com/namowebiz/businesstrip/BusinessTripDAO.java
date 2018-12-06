package com.namowebiz.businesstrip;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.namowebiz.holiday.Holiday;
import com.namowebiz.holiday.HolidayDAO;
import com.namowebiz.jdbc.ConnectMySQL;
import com.namowebiz.jdbc.JdbcUtil;

public class BusinessTripDAO {
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
	
	public BusinessTripDAO() {
		ConnectMySQL cMysql = new ConnectMySQL();
		conn = cMysql.getConn();
	}
	
	public int tripInsert(String business_id, String business_name, String business_start, String business_end, String business_content) {
		new BusinessTripDAO();
		String trip_SQL = "insert into BusinessTrip_TB values (?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(trip_SQL);
			pstmt.setString(1, business_id);
			pstmt.setString(2, business_name);;
			pstmt.setString(3, business_start);
			pstmt.setString(4, business_end);
			pstmt.setString(5, business_content);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public ArrayList<Business> diffSE(String business_id,String busyStart, String busyEnd){
		new BusinessTripDAO();
		String diff_SQL = "select datediff(?, ?) from BusinessTrip_TB where business_id=?";
		
		ArrayList<Business> list = new ArrayList<Business>();
		try {
			
			
			pstmt = conn.prepareStatement(diff_SQL);
			pstmt.setString(1, busyEnd);
			pstmt.setString(2, busyStart);;
			pstmt.setString(3, business_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Business businessTrip = new Business();
				businessTrip.setBusiness_id(rs.getString("business_id"));
				businessTrip.setBusiness_name(rs.getString("business_name"));
				businessTrip.setBusiness_start(rs.getString("business_start"));
				businessTrip.setBusiness_end(rs.getString("business_end"));
				businessTrip.setBusiness_content(rs.getString("business_content"));
				
				list.add(businessTrip);
			}			
			
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public ArrayList<Business> busiSelect(){
		new BusinessTripDAO();
		String SQL = "select * from BusinessTrip_TB order by business_start ASC";
		
		ArrayList<Business> list = new ArrayList<Business>();
		try {
			
			
			pstmt = conn.prepareStatement(SQL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Business businessTrip = new Business();
				businessTrip.setBusiness_id(rs.getString("business_id"));
				businessTrip.setBusiness_name(rs.getString("business_name"));
				businessTrip.setBusiness_start(rs.getString("business_start"));
				businessTrip.setBusiness_end(rs.getString("business_end"));
				businessTrip.setBusiness_content(rs.getString("business_content"));
				
				list.add(businessTrip);
			}			
			
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public ArrayList<Business> dateSelect(String start, String end){
		new BusinessTripDAO();
		String SQL = "select * from BusinessTrip_TB where business_start >= ? and business_end <= ? order by business_start ASC";
		
		ArrayList<Business> list = new ArrayList<Business>();
		try {
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Business businessTrip = new Business();
				businessTrip.setBusiness_id(rs.getString("business_id"));
				businessTrip.setBusiness_name(rs.getString("business_name"));
				businessTrip.setBusiness_start(rs.getString("business_start"));
				businessTrip.setBusiness_end(rs.getString("business_end"));
				businessTrip.setBusiness_content(rs.getString("business_content"));
				
				list.add(businessTrip);
			}			
			
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	public ArrayList<Business> busySelect(String businessID){
		new HolidayDAO();
		String selectbusy = "select * from BusinessTrip_TB where business_id=?";
		
		ArrayList<Business> list = new ArrayList<Business>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			pstmt = conn.prepareStatement(selectbusy);
			pstmt.setString(1, businessID);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(rs.getTimestamp("business_start").getTime());
				String sDate = df.format(cal.getTime());
				
				cal = Calendar.getInstance();
				cal.setTimeInMillis(rs.getTimestamp("business_end").getTime());
				String eDate = df.format(cal.getTime());
				
				Business businessTrip = new Business();
				businessTrip.setBusiness_id(rs.getString("business_id"));
				businessTrip.setBusiness_name(rs.getString("business_name"));
				businessTrip.setBusiness_start(sDate);
				businessTrip.setBusiness_end(eDate);
				businessTrip.setBusiness_content(rs.getString("business_content"));
				
				list.add(businessTrip);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}	
	
	public int hoDateSelect(String business_id, String date){
		String selectSQL = "select * from BusinessTrip_TB where business_id=?";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, business_id);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Calendar cal = Calendar.getInstance();
				String sDate = rs.getString("business_start");
				Date start = df.parse(sDate);
				cal.setTime(start);
				
				Calendar cal2 = Calendar.getInstance();
				String eDate = rs.getString("business_end");
				Date end = df.parse(eDate);
				cal2.setTime(end);
				
				Calendar calTo = Calendar.getInstance();
				Date toDate = df.parse(date);
				calTo.setTime(toDate);
				
				if(cal.compareTo(calTo) != 1 && cal2.compareTo(calTo) != -1) {
					result = 1;
					break;
				}else {
					result = 0;
				}
			}
			return result;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}	
	
	public int weekCheck(String day, String start) {
		String week_SQL = "SELECT DAYOFWEEK(?) as dayWeek from BusinessTrip_TB where business_start=?";
		try {
			pstmt = conn.prepareStatement(week_SQL);
			pstmt.setString(1, day);
			pstmt.setString(2, start);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				if(rs.getString("dayWeek").equals("7") || rs.getString("dayWeek").equals("1")) {
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
