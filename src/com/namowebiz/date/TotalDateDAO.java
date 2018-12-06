package com.namowebiz.date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.namowebiz.jdbc.ConnectMySQL;

public class TotalDateDAO {
	
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
	
	public TotalDateDAO() {
		ConnectMySQL cMysql = new ConnectMySQL();
		conn = cMysql.getConn();
	}
		
	// 아이디 추가
	public int idInsert(String user_id){
		String dateInsert_SQL = "insert into Total_Date_TB values (?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(dateInsert_SQL);
			pstmt.setString(1, user_id);
			pstmt.setString(2, null);
			pstmt.setString(3, null);
			pstmt.setString(4, null);
			pstmt.setString(5, null);
			pstmt.setString(6, null);
			pstmt.setFloat(7, 0);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
//			JdbcUtil.close(conn);
//			JdbcUtil.close(pstmt);
		}
		return -1;	
	}
	// 출근시간
	public int workingTimeUpdate(String user_id) {
		String dateInsert_SQL = "update Total_Date_TB set total_date=now(), total_working_time=now() where total_id=?";
				
		try {
			pstmt = conn.prepareStatement(dateInsert_SQL);
			pstmt.setString(1, user_id);						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 퇴근시간
		public int offWorkUpdate(String user_id) {
			String dateInsert_SQL = "update Total_Date_TB set total_offWork_time=now() where total_id=?";
					
			try {
				pstmt = conn.prepareStatement(dateInsert_SQL);
				pstmt.setString(1, user_id);						
				pstmt.executeUpdate();
				
				return 1;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;	
		}
	
	// 외근시간
	public int outsideUpdate(String user_id) {
		String dateInsert_SQL = "update Total_Date_TB set total_outside_time=now() where total_id=?";
				
		try {
			pstmt = conn.prepareStatement(dateInsert_SQL);
			pstmt.setString(1, user_id);						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 복귀 시간
	public int combackUpdate(String user_id) {
		String dateInsert_SQL = "update Total_Date_TB set total_comback_time=now() where total_id=?";
				
		try {
			pstmt = conn.prepareStatement(dateInsert_SQL);
			pstmt.setString(1, user_id);						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
		
	
	// 날짜가 지난경우 실행
	public int resetUpdate(String user_id) {
		String reset_SQL = "update Total_Date_TB set total_date=?, total_working_time=?, total_offWork_time=?, total_outside_time=?, total_comback_time=?, total_working_hours=? where total_id=?";
				
		try {
			pstmt = conn.prepareStatement(reset_SQL);
			pstmt.setString(1, null);
			pstmt.setString(2, null);
			pstmt.setString(3, null);
			pstmt.setString(4, null);
			pstmt.setString(5, null);
			pstmt.setFloat(6, 0);	
			pstmt.setString(7, user_id);
			
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	

	// mysql로 시간차 구하는 방법 - 출근, 퇴근
	// SELECT TIMESTAMPDIFF(SECOND, total_working_time, total_offWork_time) from total_date_tb;
	public int workingHoursUpdate(String user_id) {
		String timeDiff = "SELECT TIMESTAMPDIFF(SECOND, total_working_time, total_offWork_time) from Total_Date_TB where total_id=?";
		int diffSec = 0;
		
		String hours_SQl = "update Total_Date_TB set total_working_hours=? where total_id=?";
		float diffhour = 0.0f;
		
		try {
			pstmt = conn.prepareStatement(timeDiff);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				diffSec = rs.getInt("TIMESTAMPDIFF(SECOND, total_working_time, total_offWork_time)");
			}
			else {
				return 0; // 시간차이 구하는 거 실패
			}
			
			diffhour = diffSec/3600.0f;
			
			pstmt = conn.prepareStatement(hours_SQl);
			pstmt.setFloat(1, diffhour);
			pstmt.setString(2, user_id);
			pstmt.executeUpdate();
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
		
	}
	public String workTimeSelect(String user_id) {
		String tDateSelect = "select * from Total_Date_TB where total_id=?";
		
		try {
			pstmt = conn.prepareStatement(tDateSelect);
			pstmt.setString(1, user_id);
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				if(rs.getTimestamp("total_working_time") != null) {
					return rs.getString("total_working_time");
				}
				else {
					return null;
				}
			}	
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int workTime(String user_id) {
		String tDateSelect = "select * from Total_Date_TB where total_id=?";
		
		try {
			pstmt = conn.prepareStatement(tDateSelect);
			pstmt.setString(1, user_id);
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				if(rs.getTimestamp("total_working_time") == null) {
					return 0;
				}
				else {
					return 1;
				}
			}	
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// save_tb에 저장하기 위한 select 과정
	public ArrayList<TotalDate> totalDateSelect(String user_id){
		String tDateSelect = "select * from Total_Date_TB where total_id=?";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		
		new TotalDateDAO();
								
		String id = null;
		String sD = null;
		String work = null;
		String offWork = null;
		String outside = null;
		String comback = null;
		float hours = 0.0f;
		
		ArrayList<TotalDate> list = new ArrayList<TotalDate>();
		
		try {
			pstmt = conn.prepareStatement(tDateSelect);
			pstmt.setString(1, user_id);
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				id = rs.getString("total_id");
				if(rs.getDate("total_date") != null) {
					sD = d.format(rs.getDate("total_date"));
				}else {
					sD = null;
				}				
				if(rs.getTimestamp("total_working_time") != null) {
					work = dateFormat.format(rs.getTimestamp("total_working_time"));
				}
				else {
					work = null;
				}
				
				if(rs.getTimestamp("total_offWork_time") != null) {
					offWork = dateFormat.format(rs.getTimestamp("total_offWork_time"));
				}
				else {
					offWork = null;
				}
				
				if(rs.getTimestamp("total_outside_time") != null) {
					outside = dateFormat.format(rs.getTimestamp("total_outside_time"));
				}
				else {
					outside = null;
				}
				
				if(rs.getTimestamp("total_comback_time") != null) {
					comback = dateFormat.format(rs.getTimestamp("total_comback_time"));
				}
				else {
					comback = null;
				}
				hours = rs.getFloat("total_working_hours");
				
				TotalDate totalDate = new TotalDate();
				totalDate.setTotal_id(id);
				totalDate.setTotal_date(sD);
				totalDate.setTotal_working_time(work);
				totalDate.setTotal_offWork_time(offWork);
				totalDate.setTotal_outside_time(outside);
				totalDate.setTotal_comback_time(comback);
				totalDate.setTotal_working_hours(hours);
				
				list.add(totalDate);
				
			}	
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<TotalDate> dateSelect(String user_id) {
		String TotalDateSelect = "select * from Total_Date_TB where total_id=?";
				
		ArrayList<TotalDate> list = new ArrayList<TotalDate>();
	
		try {
			pstmt = conn.prepareStatement(TotalDateSelect);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				TotalDate totalDate = new TotalDate();
				
				totalDate.setTotal_id(rs.getString("total_id"));
				
				list.add(totalDate);
			}
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
