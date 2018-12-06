package com.namowebiz.holiday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.namowebiz.jdbc.ConnectMySQL;
import com.namowebiz.jdbc.JdbcUtil;

public class HolidayDAO {
	
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
	
	public HolidayDAO() {
		ConnectMySQL cMysql = new ConnectMySQL();
		conn = cMysql.getConn();
	}

	public int holidayInsert(String holiday_id, String holiday_name, String holiday_start, String holiday_end, 
																boolean holiday_am, boolean holiday_pm) {
		new HolidayDAO();
		String holidays_SQL = "insert into Holidays_TB values (?,?,?,?,0,?,?)";

		try {
			pstmt = conn.prepareStatement(holidays_SQL);
			pstmt.setString(1, holiday_id);
			pstmt.setString(2, holiday_name);
			pstmt.setString(3, holiday_start);
			pstmt.setString(4, holiday_end);
			pstmt.setBoolean(5, holiday_am);
			pstmt.setBoolean(6, holiday_pm);

			pstmt.executeUpdate();

			return 1;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public int dayDIff(String holiday_id, String holiday_end, String holiday_start) {
		new HolidayDAO();
		String holiDayDiff_SQl = "update Holidays_TB set holidays_day = datediff(?, ?) where holidays_id=? and holidays_start = ? and holidays_end = ?"; 
		try {
			pstmt = conn.prepareStatement(holiDayDiff_SQl);
			pstmt.setString(1, holiday_end);
			pstmt.setString(2, holiday_start);
			pstmt.setString(3, holiday_id);
			pstmt.setString(4, holiday_start);
			pstmt.setString(5, holiday_end);
			
			pstmt.executeUpdate();
			
			return 1;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public ArrayList<Holiday> holiSelect(String holiday_id, String holidays_start, String holidays_end){
		new HolidayDAO();
		String selectHoli = "select * from Holidays_TB where holidays_id=? and holidays_start=? and holidays_end=?";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		ArrayList<Holiday> list = new ArrayList<Holiday>();
		try {
			pstmt = conn.prepareStatement(selectHoli);
			pstmt.setString(1, holiday_id);
			pstmt.setString(2, holidays_start);
			pstmt.setString(3, holidays_end);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(rs.getTimestamp("holidays_start").getTime());
				String sDate = df.format(cal.getTime());
				
				cal = Calendar.getInstance();
				cal.setTimeInMillis(rs.getTimestamp("holidays_end").getTime());
				String eDate = df.format(cal.getTime());
				
				Holiday holiday = new Holiday();
				
				holiday.setHoliday_id(rs.getString("holidays_id"));
				holiday.setHoliday_start(sDate);
				holiday.setHoliday_end(eDate);
				holiday.setHolidays_day(rs.getInt("holidays_day"));
				
				list.add(holiday);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
		}	
	
	public ArrayList<Holiday> holiSelect(String holiday_id){
		new HolidayDAO();
		String selectHoli = "select * from Holidays_TB where holidays_id=?";
		
		ArrayList<Holiday> list = new ArrayList<Holiday>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			pstmt = conn.prepareStatement(selectHoli);
			pstmt.setString(1, holiday_id);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(rs.getTimestamp("holidays_start").getTime());
				String sDate = df.format(cal.getTime());
				
				cal = Calendar.getInstance();
				cal.setTimeInMillis(rs.getTimestamp("holidays_end").getTime());
				String eDate = df.format(cal.getTime());
				
				Holiday holiday = new Holiday();
								
				holiday.setHoliday_id(rs.getString("holidays_id"));
				holiday.setHoliday_name(rs.getString("holidays_name"));
				holiday.setHoliday_start(sDate);
				holiday.setHoliday_end(eDate);
				holiday.setHolidays_day(rs.getInt("holidays_day"));
				holiday.setHoliday_am(rs.getBoolean("holidays_am"));
				holiday.setHoliday_pm(rs.getBoolean("holidays_pm"));
				
				list.add(holiday);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
		}	
	
	public int dateSelect(String holidays_id, String date){
		new HolidayDAO();
		String selectHoli = "select * from Holidays_TB where holidays_id=?";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(selectHoli);
			pstmt.setString(1, holidays_id);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Calendar cal = Calendar.getInstance();
				String sDate = rs.getString("holidays_start");
				Date start = df.parse(sDate);
				cal.setTime(start);
				
				Calendar cal2 = Calendar.getInstance();
				String eDate = rs.getString("holidays_end");
				Date end = df.parse(eDate);
				cal2.setTime(end);
				
				Calendar calTo = Calendar.getInstance();
				Date toDate = df.parse(date);
				calTo.setTime(toDate);
				
				System.out.println(start);
				System.out.println(end);
				System.out.println(toDate);
				
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
	
	public ArrayList<Holiday> holiSelect(){
		String selectHoli = "select * from Holidays_TB order by holidays_start ASC";
		
		ArrayList<Holiday> list = new ArrayList<Holiday>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			pstmt = conn.prepareStatement(selectHoli);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(rs.getTimestamp("holidays_start").getTime());
				String sDate = df.format(cal.getTime());
				
				cal = Calendar.getInstance();
				cal.setTimeInMillis(rs.getTimestamp("holidays_end").getTime());
				String eDate = df.format(cal.getTime());
				
				Holiday holiday = new Holiday();
								
				holiday.setHoliday_id(rs.getString("holidays_id"));
				holiday.setHoliday_name(rs.getString("holidays_name"));
				holiday.setHoliday_start(sDate);
				holiday.setHoliday_end(eDate);
				holiday.setHolidays_day(rs.getInt("holidays_day"));
				holiday.setHoliday_am(rs.getBoolean("holidays_am"));
				holiday.setHoliday_pm(rs.getBoolean("holidays_pm"));
				
				list.add(holiday);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
		}
	
	public int weekCheck(String day, String start) {
		String week_SQL = "SELECT DAYOFWEEK(?) as dayWeek from Holidays_TB where holidays_start=?";
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