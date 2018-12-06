package com.namowebiz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.namowebiz.jdbc.*;

public class UserDAO {
	
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
		
	public UserDAO() {
		ConnectMySQL cMysql = new ConnectMySQL();
		conn = cMysql.getConn();
	}
		
	public int loginCheck(String user_id, String user_pw){
		new UserDAO();
		String loginCheck_SQL = "select user_pw = ? as pwCmp from User_TB where user_id = ?";
		try {
			pstmt = conn.prepareStatement(loginCheck_SQL);
			pstmt.setString(1, user_pw);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getBoolean("pwCmp") == true){
					return 1;
				}else if(rs.getBoolean("pwCmp") == false){
					return 0;
				}
			}
			return -1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
			
	public ArrayList<User> userSelect() {
		new UserDAO();
		String userSelect = "select * from User_TB order by user_produce_date ASC";
				
		ArrayList<User> list = new ArrayList<User>();
		
		
		try {
			pstmt = conn.prepareStatement(userSelect);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				User user = new User();
				
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_part(rs.getString("user_part"));
				user.setUser_rank(rs.getString("user_rank"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_manager(rs.getBoolean("user_manager"));
				user.setUser_holidays_use(rs.getFloat("user_holidays_use"));
				user.setUser_holidays_total(rs.getFloat("user_holidays_total"));
				user.setUser_produce_date(rs.getString("user_produce_date"));
				user.setUser_resignation_date(rs.getString("user_resignation_date"));
				user.setUser_resignation(rs.getBoolean("user_resignation"));
										
				list.add(user);
			}
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<User> userSelect(String user_id) {
		new UserDAO();
		String userSelect = "select * from User_TB where user_id=? order by user_produce_date ASC";
				
		ArrayList<User> list = new ArrayList<User>();
				
		try {
			pstmt = conn.prepareStatement(userSelect);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				User user = new User();
				
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_part(rs.getString("user_part"));
				user.setUser_rank(rs.getString("user_rank"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_manager(rs.getBoolean("user_manager"));
				user.setUser_holidays_use(rs.getFloat("user_holidays_use"));
				user.setUser_holidays_total(rs.getFloat("user_holidays_total"));
				user.setUser_produce_date(rs.getString("user_produce_date"));
				user.setUser_resignation_date(rs.getString("user_resignation_date"));
				user.setUser_resignation(rs.getBoolean("user_resignation"));
										
				list.add(user);
			}
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Boolean manageUser(String user_id) {
		new UserDAO();
		String userSelect = "select * from User_TB where user_id=?";
				
		Boolean manager = false;
				
		try {
			pstmt = conn.prepareStatement(userSelect);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				manager = rs.getBoolean("user_manager");
			}
			return manager;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int userUpdate(String name, String user_pw, String user_part, String user_rank, String user_phone, String user_email, String holiday, String user_id) {
		new UserDAO();
		String userSelect = "update User_TB set user_id=?, user_pw=?, user_name=?, user_part=?, user_rank=?, user_phone=?, user_email=?, user_holidays_total=? where user_id=?";
		
		try {
			pstmt = conn.prepareStatement(userSelect);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pw);
			pstmt.setString(3, name);
			pstmt.setString(4, user_part);
			pstmt.setString(5, user_rank);
			pstmt.setString(6, user_phone);
			pstmt.setString(7, user_email);
			pstmt.setString(8, holiday);
			pstmt.setString(9, user_id);
			pstmt.executeUpdate();
						
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int resignation(String user_id) {
		new UserDAO();
		String userDelete = "update User_TB set user_resignation=?, user_resignation_date=now(), user_manager=? where user_id=?";
		try {
			pstmt = conn.prepareStatement(userDelete);
			pstmt.setBoolean(1, true);
			pstmt.setBoolean(2, false);
			pstmt.setString(3, user_id);
			pstmt.executeUpdate();
						
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int manageUpdate(String user_id, boolean change) {
		new UserDAO();
		String userSelect = "update User_TB set user_manager=? where user_id=?";
		
		try {

			pstmt = conn.prepareStatement(userSelect);
			
			if(change == true) {
				pstmt.setBoolean(1, false);
				pstmt.setString(2, user_id);
				pstmt.executeUpdate();	
				return 0;
			}else {
				pstmt.setBoolean(1, true);
				pstmt.setString(2, user_id);
				pstmt.executeUpdate();
				return 1;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public boolean manageSelect(String user_id) {
		new UserDAO();
		String userSelect = "select * from User_TB where user_id=?";
		
		try {
			pstmt = conn.prepareStatement(userSelect);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getBoolean("user_manager");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int userInsert(String user_id, String user_pw, String user_name, String user_part, String user_rank, String user_phone,
							String user_email, float user_holidays_total) {
		new UserDAO();
		String userInsert = "insert into User_TB values (?,?,?,?,?,?,?,?,?,?,now(),null,?)";
				
		try {
			pstmt = conn.prepareStatement(userInsert);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pw);
			pstmt.setString(3, user_name);
			pstmt.setString(4, user_part);
			pstmt.setString(5, user_rank);
			pstmt.setString(6, user_phone);
			pstmt.setString(7, user_email);
			pstmt.setBoolean(8, false);
			pstmt.setInt(9, 0);
			pstmt.setFloat(10, user_holidays_total);
			pstmt.setBoolean(11, false);

			pstmt.executeUpdate();
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	// 사용 휴가일수 업데이트
	public int holidayupdate(String user_id, float holiUse) {
		new UserDAO();
		String holidayUpdate = "Update User_TB set user_holidays_use= user_holidays_use + ? where user_id=?";
		
		try {
			pstmt = conn.prepareStatement(holidayUpdate);
			pstmt.setFloat(1, holiUse);
			pstmt.setString(2, user_id);
			
			pstmt.executeUpdate();
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// 휴가일수 초기화
	public int holidayInupdate(String user_id, int total) {
		new UserDAO();
		String holidayUpdate = "Update User_TB set user_holidays_total=? where user_id=?";
		
		try {
			pstmt = conn.prepareStatement(holidayUpdate);
			pstmt.setFloat(1, total);
			pstmt.setString(2, user_id);
			
			pstmt.executeUpdate();
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// 휴가일수 증가
		public int holidayAdd(String user_id, int total) {
			new UserDAO();
			String holidayUpdate = "Update User_TB set user_holidays_total=user_holidays_total+? where user_id=?";
			
			try {
				pstmt = conn.prepareStatement(holidayUpdate);
				pstmt.setFloat(1, total);
				pstmt.setString(2, user_id);
				
				pstmt.executeUpdate();
				return 1;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
		}
	
	// 프로필 설정 비밀번호 확인
	public int pwSelect(String user_id, String user_pw) {
		new UserDAO();
		String userSelect = "select user_pw=? as pwcheck from User_TB where user_id=?";
		
		try {
			pstmt = conn.prepareStatement(userSelect);
			pstmt.setString(1, user_pw);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getBoolean("pwcheck") == true) {
					return 1;
				}else {
					return 0;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	// ID 중복체크
		public int idCheck(String user_id) {
			new UserDAO();
			String userSelect = "select * from User_TB where user_id=?";
			
			try {
				pstmt = conn.prepareStatement(userSelect);
				pstmt.setString(1, user_id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					return 1;
				}
				return 0;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return -1;
		}
	
	// 프로필 수정
	public int settingPWUpdate(String user_id, String user_pw) {
		new UserDAO();
		String setUpdate = "update User_TB set user_pw=? where user_id=?";
		
		try {
			pstmt = conn.prepareStatement(setUpdate);
			pstmt.setString(1, user_pw);
			pstmt.setString(2, user_id);
			
			pstmt.executeUpdate();
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
		
	}
	// 프로필 수정
	public int settingPhUpdate(String user_id, String phone) {
		new UserDAO();
		String setUpdate = "update User_TB set user_phone=? where user_id=?";
		
		try {
			pstmt = conn.prepareStatement(setUpdate);
			pstmt.setString(1, phone);
			pstmt.setString(2,user_id);
			
			pstmt.executeUpdate();
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
		
	}
	// 프로필 수정
	public int settingEMUpdate(String user_id,String email) {
		new UserDAO();
		String setUpdate = "update User_TB set user_email=? where user_id=?";
		
		try {
			pstmt = conn.prepareStatement(setUpdate);
			pstmt.setString(1,email);
			pstmt.setString(2,user_id);
			
			pstmt.executeUpdate();
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
		
	}
	
}
