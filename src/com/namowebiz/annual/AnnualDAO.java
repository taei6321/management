package com.namowebiz.annual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.namowebiz.jdbc.ConnectMySQL;
import com.namowebiz.jdbc.JdbcUtil;

public class AnnualDAO {
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
	
	public AnnualDAO() {
		ConnectMySQL cMysql = new ConnectMySQL();
		conn = cMysql.getConn();
	}
	
	// 처음 추가 시 저장
		public int clickInsert(String user_id) {
			new AnnualDAO();
			String dateInsert_SQL = "insert into User_Annual_TB values (?,?,?,?,?,?,?,?)";
					
			try {
				pstmt = conn.prepareStatement(dateInsert_SQL);
				pstmt.setString(1, user_id);
				pstmt.setBoolean(2, false);;
				pstmt.setBoolean(3, false);
				pstmt.setBoolean(4, true);
				pstmt.setBoolean(5, false);
				pstmt.setBoolean(6, false);
				pstmt.setBoolean(7, false);
				pstmt.setBoolean(8, false);
							
				pstmt.executeUpdate();
				
				return 1;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;	
		}
		
	// 외근 클릭 시
	public int OutsideUpdate(String user_id) {
		new AnnualDAO();
		String outside_SQL = "update User_Annual_TB set annual_outside=? where annual_id=?";
				
		try {
			pstmt = conn.prepareStatement(outside_SQL);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, user_id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}	
	// 복귀 클릭 시
	public int returnUpdate(String user_id) {
		new AnnualDAO();
		String return_SQL = "update User_Annual_TB set annual_return=? where annual_id=?";
				
		try {
			pstmt = conn.prepareStatement(return_SQL);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, user_id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}	
	
	// 직출 클릭 시
	public int directWorkingUpdate(String user_id) {
		new AnnualDAO();
		String directWorking_SQL = "update User_Annual_TB set annual_direct_working=? where annual_id=?";
				
		try {
			pstmt = conn.prepareStatement(directWorking_SQL);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, user_id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 직퇴 클릭 시
	public int directOffWorkUpdate(String user_id) {
		new AnnualDAO();
		String directOffWork_SQL = "update User_Annual_TB set annual_direct_offwork=? where annual_id=?";
				
		try {
			pstmt = conn.prepareStatement(directOffWork_SQL);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, user_id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 휴가 클릭 시
	public int holidaysUpdate(String user_id) {
		new AnnualDAO();
		String holidays_SQL = "update User_Annual_TB set annual_holidays=? where annual_id=?";
				
		try {
			pstmt = conn.prepareStatement(holidays_SQL);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, user_id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 반차 클릭 시
	public int halfUpdate(String user_id) {
		new AnnualDAO();
		String half_SQL = "update User_Annual_TB set anuual_half=? where annual_id=?";
				
		try {
			pstmt = conn.prepareStatement(half_SQL);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, user_id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 병가 클릭 시
	public int sickLeaveUpdate(String user_id) {
		new AnnualDAO();
		String sickLeave_SQL = "update User_Annual_TB set annual_sick_leave=? where annual_id=?";
				
		try {
			pstmt = conn.prepareStatement(sickLeave_SQL);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, user_id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 퇴근 버튼 누를 시 실행
	public int resetUpdate(String user_id) {
		new AnnualDAO();
		String reset_SQL = "update User_Annual_TB set annual_outside=?, annual_return=?, annual_direct_working=?, annual_direct_offwork=?, annual_holidays=?, anuual_half=?, annual_sick_leave=? where annual_id=?";
				
		try {
			pstmt = conn.prepareStatement(reset_SQL);
			pstmt.setBoolean(1, false);
			pstmt.setBoolean(2, false);
			pstmt.setBoolean(3, false);
			pstmt.setBoolean(4, false);
			pstmt.setBoolean(5, false);
			pstmt.setBoolean(6, false);
			pstmt.setBoolean(7, false);
			pstmt.setString(8, user_id);
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// save_tb에 저장하기 위한 select 과정
	public ArrayList<Annual> annualSelect(String user_id){
		new AnnualDAO();
		String annualSelect = "select * from User_Annual_TB where annual_id=?";
		
		Annual ann = new Annual();
		ArrayList<Annual> list = new ArrayList<Annual>();
		
		try {
			pstmt = conn.prepareStatement(annualSelect);
			pstmt.setString(1, user_id);
						
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ann.setAnnual_id(rs.getString("annual_id"));
				ann.setAnnual_outside(rs.getBoolean("annual_outside"));
				ann.setAnnual_return(rs.getBoolean("annual_return"));
				ann.setAnnual_direct_working(rs.getBoolean("annual_direct_working"));
				ann.setAnnual_direct_offwork(rs.getBoolean("annual_direct_offwork"));
				ann.setAnnual_holidays(rs.getBoolean("annual_holidays"));
				ann.setAnuual_half(rs.getBoolean("anuual_half"));
				ann.setAnnual_sick_leave(rs.getBoolean("annual_sick_leave"));
				
				list.add(ann);
			}
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
