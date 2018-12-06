package com.namowebiz.save;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.namowebiz.jdbc.ConnectMySQL;

public class ReSaveDAO {
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
	
	public ReSaveDAO() {
		ConnectMySQL cMysql = new ConnectMySQL();
		conn = cMysql.getConn();
	}
	
	// 출근시간
	public int startUpdate(String Pk, String id, String startTime) {
		String SQL = "Update Save_TB set save_start_time=? where save_number=? and save_id=?";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, startTime);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}

	// 퇴근시간
	public int endUpdate(String Pk, String id, String endTime) {
		String SQL = "Update Save_TB set save_end_time=? where save_number=? and save_id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, endTime);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public int workingHoursUpdate(String Pk, String id) {
		String timeDiff = "SELECT TIMESTAMPDIFF(SECOND, save_start_time, save_end_time) from Save_TB where save_id=? and save_number=?";
		int diffSec = 0;
		
		String hours_SQl = "Update Save_TB set save_working_hours = ? where save_number=? and save_id=?";
		float diffhour = 0.0f;
		
		try {
			pstmt = conn.prepareStatement(timeDiff);
			pstmt.setString(1, id);
			pstmt.setString(2, Pk);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				diffSec = rs.getInt("TIMESTAMPDIFF(SECOND, save_start_time, save_end_time)");
			}
			else {
				return 0; // 시간차이 구하는 거 실패
			}
			
			diffhour = diffSec/3600.0f;
			
			pstmt = conn.prepareStatement(hours_SQl);
			pstmt.setFloat(1, diffhour);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
		
	}

	
	// 외근시간
	public int outUpdate(String Pk, String id, String outTime) {
		String SQL = "Update Save_TB set save_out_time=? where save_number=? and save_id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, outTime);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 직출
	public int DirWorkUpdate(String Pk, String id, boolean DirWork) {
		String SQL = "Update Save_TB set save_direct_working=? where save_number=? and save_id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, DirWork);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 직퇴
	public int DirOffUpdate(String Pk, String id, boolean DirOff) {
		String SQL = "Update Save_TB set save_direct_offwork=? where save_number=? and save_id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, DirOff);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 외근
	public int outUpdate(String Pk, String id, boolean out) {
		String SQL = "Update Save_TB set save_outside=? where save_number=? and save_id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, out);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 복귀
	public int retUpdate(String Pk, String id, boolean retu) {
		String SQL = "Update Save_TB set save_return=? where save_number=? and save_id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, retu);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 휴가
	public int holiUpdate(String Pk, String id, boolean holi) {
		String SQL = "Update Save_TB set save_holiday=? where save_number=? and save_id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, holi);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 병가
	public int sickUpdate(String Pk, String id, boolean sick) {
		String SQL = "Update Save_TB set save_sick_leave=? where save_number=? and save_id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, sick);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 반차
	public int halfUpdate(String Pk, String id, boolean half) {
		String SQL = "Update Save_TB set save_half=? where save_number=? and save_id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, half);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 출장
	public int busyUpdate(String Pk, String id, boolean busy) {
		String SQL = "Update Save_TB set save_business=? where save_number=? and save_id=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setBoolean(1, busy);
			pstmt.setString(2, Pk);
			pstmt.setString(3, id);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
}
