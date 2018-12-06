package com.namowebiz.retouch;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.SimpleFormatter;

import com.namowebiz.jdbc.ConnectMySQL;
import com.namowebiz.jdbc.JdbcUtil;

public class RetouchDAO {
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;

	public RetouchDAO() {
		ConnectMySQL cMysql = new ConnectMySQL();
		conn = cMysql.getConn();
	}

	public int retouchInsert(String retouch_id, String retouch_name, String retouch_specialNote, String retouch_reason, String retouch_indate, String retouch_intime, int retouch_PK) {
		String retouchIn_SQL = "insert into retouch_tb values (now(),?,?,?,?,?,?,?,null,null,0)";
		
		try {
			pstmt = conn.prepareStatement(retouchIn_SQL);
			pstmt.setString(1, retouch_id);
			pstmt.setString(2, retouch_name);
			pstmt.setString(3, retouch_specialNote);
			pstmt.setString(4, retouch_reason);
			pstmt.setString(5, retouch_indate);
			pstmt.setString(6, retouch_intime);
			pstmt.setInt(7, retouch_PK);

			pstmt.executeUpdate();

			return 1;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 휴가일수가 0개 이하일때 관리자에게 허가를 받기위한 insert 과정
	public int reasonInsert(String retouch_id, String retouch_name, String retouch_reason, String start, String end, String holiday) {
		String reasonIn_SQL = "insert into retouch_tb values (now(),?,?,'휴가',?,null,null,0,?,?,?)";


		try {
			pstmt = conn.prepareStatement(reasonIn_SQL);
			pstmt.setString(1, retouch_id);
			pstmt.setString(2, retouch_name);
			pstmt.setString(3, retouch_reason);
			pstmt.setString(4, start);
			pstmt.setString(5, end);
			pstmt.setString(6, holiday);

			pstmt.executeUpdate();

			return 1;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 작은 알림창에 띄울 select
	public ArrayList<Retouch> shortSelect(String user_id) {
		String retouchSelect = "select * from retouch_tb where retouch_id=? order by retouch_date ASC";

		ArrayList<Retouch> list = new ArrayList<Retouch>();

		try {
			pstmt = conn.prepareStatement(retouchSelect);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Retouch retouch = new Retouch();
				retouch.setRetouch_date(rs.getString("retouch_date"));
				retouch.setRetouch_id(rs.getString("retouch_id"));
				retouch.setRetouch_name(rs.getString("retouch_name"));
				retouch.setRetouch_specialNote(rs.getString("retouch_specialNote"));
				retouch.setRetouch_reason(rs.getString("retouch_reason"));
				if(rs.getString("retouch_indate") == null) {
					retouch.setRetouch_start(rs.getString("retouch_start"));
					retouch.setRetouch_end(rs.getString("retouch_end"));
				}else {
					retouch.setRetouch_indate(rs.getString("retouch_indate"));
					retouch.setRetouch_intime(rs.getString("retouch_intime"));
				}
				retouch.setRetouch_PK(rs.getInt("retouch_PK"));
				retouch.setRetouch_holiday(rs.getString("retouch_holiday"));

				list.add(retouch);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public ArrayList<Retouch> retouchSelect(String user_id) {
		String retouchSelect = "select * from retouch_tb where retouch_id=? order by retouch_date ASC";

		ArrayList<Retouch> list = new ArrayList<Retouch>();

		try {
			pstmt = conn.prepareStatement(retouchSelect);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Retouch retouch = new Retouch();
				retouch.setRetouch_date(rs.getString("retouch_date"));
				retouch.setRetouch_id(rs.getString("retouch_id"));
				retouch.setRetouch_name(rs.getString("retouch_name"));
				retouch.setRetouch_specialNote(rs.getString("retouch_specialNote"));
				retouch.setRetouch_reason(rs.getString("retouch_reason"));
				retouch.setRetouch_PK(rs.getInt("retouch_PK"));
				if(rs.getString("retouch_indate") == null) {
					retouch.setRetouch_start(rs.getString("retouch_start"));
					retouch.setRetouch_end(rs.getString("retouch_end"));
				}else {
					retouch.setRetouch_indate(rs.getString("retouch_indate"));
					retouch.setRetouch_intime(rs.getString("retouch_intime"));
				}
				retouch.setRetouch_holiday(rs.getString("retouch_holiday"));

				list.add(retouch);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Retouch> retouchSelect() {
		String retouchSelect = "select * from retouch_tb order by retouch_date ASC";

		ArrayList<Retouch> list = new ArrayList<Retouch>();

		try {
			pstmt = conn.prepareStatement(retouchSelect);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Retouch retouch = new Retouch();
				retouch.setRetouch_date(rs.getString("retouch_date"));
				retouch.setRetouch_id(rs.getString("retouch_id"));
				retouch.setRetouch_name(rs.getString("retouch_name"));
				retouch.setRetouch_specialNote(rs.getString("retouch_specialNote"));
				retouch.setRetouch_reason(rs.getString("retouch_reason"));
				if(rs.getString("retouch_indate") == null) {
					retouch.setRetouch_start(rs.getString("retouch_start"));
					retouch.setRetouch_end(rs.getString("retouch_end"));
				}else {
					retouch.setRetouch_indate(rs.getString("retouch_indate"));
					retouch.setRetouch_intime(rs.getString("retouch_intime"));
				}
				retouch.setRetouch_PK(rs.getInt("retouch_PK"));
				retouch.setRetouch_holiday(rs.getString("retouch_holiday"));

				list.add(retouch);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int reasonUpdate(String con, String rea, String id, String date) {
		String reasonUpdate_SQL = "update retouch_tb set retouch_contents=?, retouch_reason=? where retouch_id=? and retouch_date=?";

		try {
			pstmt = conn.prepareStatement(reasonUpdate_SQL);
			pstmt.setString(1, con);
			pstmt.setString(2, rea);
			pstmt.setString(3, id);
			pstmt.setString(4, date);
			pstmt.executeUpdate();

			return 1;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public ArrayList<Retouch> findSelect(String startDate, String endDate) {
		String find_SQL = "select * from retouch_tb where retouch_date between ? and ?";
		ArrayList<Retouch> list = new ArrayList<Retouch>();

		try {
			pstmt = conn.prepareStatement(find_SQL);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Retouch retouch = new Retouch();
				retouch.setRetouch_date(rs.getString("retouch_date"));
				retouch.setRetouch_id(rs.getString("retouch_id"));
				retouch.setRetouch_name(rs.getString("retouch_name"));
				retouch.setRetouch_specialNote(rs.getString("retouch_specialNote"));
				retouch.setRetouch_reason(rs.getString("retouch_reason"));
				if(rs.getString("retouch_indate") == null) {
					retouch.setRetouch_start(rs.getString("retouch_start"));
					retouch.setRetouch_end(rs.getString("retouch_end"));
				}else {
					retouch.setRetouch_indate(rs.getString("retouch_indate"));
					retouch.setRetouch_intime(rs.getString("retouch_intime"));
				}
				retouch.setRetouch_PK(rs.getInt("retouch_PK"));
				retouch.setRetouch_holiday(rs.getString("retouch_holiday"));

				list.add(retouch);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Retouch> findSelect(String startDate, String endDate, String id) {
		String find_SQL = "select * from retouch_tb where retouch_id=? and retouch_date between ? and ?";
		ArrayList<Retouch> list = new ArrayList<Retouch>();

		try {
			pstmt = conn.prepareStatement(find_SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Retouch retouch = new Retouch();
				retouch.setRetouch_date(rs.getString("retouch_date"));
				retouch.setRetouch_id(rs.getString("retouch_id"));
				retouch.setRetouch_name(rs.getString("retouch_name"));
				retouch.setRetouch_specialNote(rs.getString("retouch_specialNote"));
				retouch.setRetouch_reason(rs.getString("retouch_reason"));
				if(rs.getString("retouch_indate") == null) {
					retouch.setRetouch_start(rs.getString("retouch_start"));
					retouch.setRetouch_end(rs.getString("retouch_end"));
				}else {
					retouch.setRetouch_indate(rs.getString("retouch_indate"));
					retouch.setRetouch_intime(rs.getString("retouch_intime"));
				}
				retouch.setRetouch_PK(rs.getInt("retouch_PK"));
				retouch.setRetouch_holiday(rs.getString("retouch_holiday"));
				list.add(retouch);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Retouch> ReSelect(String id, String retouch_PK, String reason) {
		String find_SQL = "select * from retouch_tb where retouch_id=? and retouch_PK = ? and retouch_reason = ?";
		ArrayList<Retouch> list = new ArrayList<Retouch>();

		try {
			pstmt = conn.prepareStatement(find_SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, retouch_PK);
			pstmt.setString(3, reason);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Retouch retouch = new Retouch();
				retouch.setRetouch_date(rs.getString("retouch_date"));
				retouch.setRetouch_id(rs.getString("retouch_id"));
				retouch.setRetouch_name(rs.getString("retouch_name"));
				retouch.setRetouch_specialNote(rs.getString("retouch_specialNote"));
				retouch.setRetouch_reason(rs.getString("retouch_reason"));
				if(rs.getString("retouch_indate") == null) {
					retouch.setRetouch_start(rs.getString("retouch_start"));
					retouch.setRetouch_end(rs.getString("retouch_end"));
				}else {
					retouch.setRetouch_indate(rs.getString("retouch_indate"));
					retouch.setRetouch_intime(rs.getString("retouch_intime"));
				}
				retouch.setRetouch_PK(rs.getInt("retouch_PK"));
				retouch.setRetouch_holiday(rs.getString("retouch_holiday"));

				list.add(retouch);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Retouch> RetSelect(String id, String note, String date) {
		String find_SQL = "select * from retouch_tb where retouch_id=? and retouch_specialNote = ? and retouch_date = ?";
		ArrayList<Retouch> list = new ArrayList<Retouch>();

		try {
			pstmt = conn.prepareStatement(find_SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, note);
			pstmt.setString(3, date);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Retouch retouch = new Retouch();
				retouch.setRetouch_date(rs.getString("retouch_date"));
				retouch.setRetouch_id(rs.getString("retouch_id"));
				retouch.setRetouch_name(rs.getString("retouch_name"));
				retouch.setRetouch_specialNote(rs.getString("retouch_specialNote"));
				retouch.setRetouch_reason(rs.getString("retouch_reason"));
				if(rs.getString("retouch_indate") == null) {
					retouch.setRetouch_start(rs.getString("retouch_start"));
					retouch.setRetouch_end(rs.getString("retouch_end"));
				}else {
					retouch.setRetouch_indate(rs.getString("retouch_indate"));
					retouch.setRetouch_intime(rs.getString("retouch_intime"));
				}
				retouch.setRetouch_PK(rs.getInt("retouch_PK"));
				retouch.setRetouch_holiday(rs.getString("retouch_holiday"));

				list.add(retouch);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// 수락 , 반려시 삭제
	public int reDelete(String PK, String ID, String note) {
		String reasonUpdate_SQL = "delete from retouch_tb where retouch_id=? and retouch_PK=? and retouch_specialNote = ?";

		try {
			pstmt = conn.prepareStatement(reasonUpdate_SQL);
			pstmt.setString(1, ID);
			pstmt.setString(2, PK);
			pstmt.setString(3, note);
			pstmt.executeUpdate();

			return 1;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	// 수락 , 반려시 삭제
		public int retDelete(String ID, String date, String note) {
			String reasonUpdate_SQL = "delete from retouch_tb where retouch_id=? and retouch_date = ? and retouch_specialNote=?";

			try {
				pstmt = conn.prepareStatement(reasonUpdate_SQL);
				pstmt.setString(1, ID);
				pstmt.setString(2, date);
				pstmt.setString(3, note);
				pstmt.executeUpdate();

				return 1;

			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;	
		}
		
	// 배지에 놓을 카운트
	public int Badge() {
		String retouchSelect = "select count(*) as badge from retouch_tb";

		try {
			pstmt = conn.prepareStatement(retouchSelect);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				return rs.getInt("badge");
			}
			return 0;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}
}
