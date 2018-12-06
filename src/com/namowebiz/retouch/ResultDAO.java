package com.namowebiz.retouch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.namowebiz.jdbc.ConnectMySQL;

public class ResultDAO {
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;

	public ResultDAO() {
		ConnectMySQL cMysql = new ConnectMySQL();
		conn = cMysql.getConn();
	}
	
	public int acceptInsert( String id, String name, String note, String reason, String pk, String indate, String intime) {
		String accept_SQL = "insert into retouch_result_tb values (0,now(),?,?,?,?,?,null,?,?,?,null,null)";
		
		try {
			pstmt = conn.prepareStatement(accept_SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setBoolean(3, true);
			pstmt.setString(4, note);
			pstmt.setString(5, reason);
			pstmt.setString(6, pk);
			pstmt.setString(7, indate);
			pstmt.setString(8, intime);

			pstmt.executeUpdate();

			return 1;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public int holidayInsert( String id, String name, String note, String reason, String pk, String start, String end) {
		String accept_SQL = "insert into retouch_result_tb values (0,now(),?,?,?,?,?,null,?,null,null,?,?)";
		
		try {
			pstmt = conn.prepareStatement(accept_SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setBoolean(3, true);
			pstmt.setString(4, note);
			pstmt.setString(5, reason);
			pstmt.setString(6, pk);
			pstmt.setString(7, start);
			pstmt.setString(8, end);

			pstmt.executeUpdate();

			return 1;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public int refusalInsert( String id, String name, String note, String reason, String falseReason, String pk, String indate, String intime) {
		String refusal_SQL = "insert into retouch_result_tb values (0,now(),?,?,?,?,?,?,?,?,?,null,null)";
		
		try {
			pstmt = conn.prepareStatement(refusal_SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setBoolean(3, false);
			pstmt.setString(4, note);
			pstmt.setString(5, reason);
			pstmt.setString(6, falseReason);
			pstmt.setString(7, pk);
			pstmt.setString(8, indate);
			pstmt.setString(9, intime);

			pstmt.executeUpdate();

			return 1;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public int holidayRefusal( String id, String name, String note, String reason, String falseReason, String pk, String start, String end) {
		String refusal_SQL = "insert into retouch_result_tb values (0,now(),?,?,?,?,?,?,?,null,null,?,?)";
		
		try {
			pstmt = conn.prepareStatement(refusal_SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setBoolean(3, false);
			pstmt.setString(4, note);
			pstmt.setString(5, reason);
			pstmt.setString(6, falseReason);
			pstmt.setString(7, pk);
			pstmt.setString(8, start);
			pstmt.setString(9, end);

			pstmt.executeUpdate();

			return 1;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public ArrayList<Result> alramSelect(String user_id) {
		String find_SQL = "select * from retouch_result_tb where DATE_ADD(retouch_date, INTERVAL 7 DAY)>now() and retouch_date<now() and retouch_id=? order by retouch_date ASC";
		ArrayList<Result> list = new ArrayList<Result>();

		try {
			pstmt = conn.prepareStatement(find_SQL);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Result result = new Result();
				result.setResult_number(rs.getInt("retouch_number"));
				result.setResult_date(rs.getString("retouch_date"));
				result.setResult_id(rs.getString("retouch_id"));
				result.setResult_name(rs.getString("retouch_name"));
				result.setResult_or(rs.getBoolean("retouch_or"));
				result.setResult_specialNote(rs.getString("retouch_specialNote"));
				result.setResult_reason(rs.getString("retouch_reason"));
				result.setResult_false_reason(rs.getString("retouch_false_reason"));
				result.setResult_save_pk(rs.getInt("retouch_save_pk"));
				if(rs.getString("retouch_indate") == null) {
					result.setRetouch_holidaystart(rs.getString("retouch_holidaystart"));
					result.setRetouch_holidayend(rs.getString("retouch_holidayend"));
				}else {
					result.setRetouch_indate(rs.getString("retouch_indate"));
					result.setRetouch_intime(rs.getString("retouch_intime"));
				}

				list.add(result);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Result> ReSelect(String user_id) {
		String find_SQL = "select * from retouch_result_tb where retouch_id=? order by retouch_date ASC";
		ArrayList<Result> list = new ArrayList<Result>();

		try {
			pstmt = conn.prepareStatement(find_SQL);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Result result = new Result();
				result.setResult_number(rs.getInt("retouch_number"));
				result.setResult_date(rs.getString("retouch_date"));
				result.setResult_id(rs.getString("retouch_id"));
				result.setResult_name(rs.getString("retouch_name"));
				result.setResult_or(rs.getBoolean("retouch_or"));
				result.setResult_specialNote(rs.getString("retouch_specialNote"));
				result.setResult_reason(rs.getString("retouch_reason"));
				result.setResult_false_reason(rs.getString("retouch_false_reason"));
				result.setResult_save_pk(rs.getInt("retouch_save_pk"));
				if(rs.getString("retouch_indate") == null) {
					result.setRetouch_holidaystart(rs.getString("retouch_holidaystart"));
					result.setRetouch_holidayend(rs.getString("retouch_holidayend"));
				}else {
					result.setRetouch_indate(rs.getString("retouch_indate"));
					result.setRetouch_intime(rs.getString("retouch_intime"));
				}

				list.add(result);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Result> numReSelect(String num) {
		String find_SQL = "select * from retouch_result_tb where retouch_number=? order by retouch_date ASC";
		ArrayList<Result> list = new ArrayList<Result>();

		try {
			pstmt = conn.prepareStatement(find_SQL);
			pstmt.setString(1, num);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Result result = new Result();
				result.setResult_number(rs.getInt("retouch_number"));
				result.setResult_date(rs.getString("retouch_date"));
				result.setResult_id(rs.getString("retouch_id"));
				result.setResult_name(rs.getString("retouch_name"));
				result.setResult_or(rs.getBoolean("retouch_or"));
				result.setResult_specialNote(rs.getString("retouch_specialNote"));
				result.setResult_reason(rs.getString("retouch_reason"));
				result.setResult_false_reason(rs.getString("retouch_false_reason"));
				result.setResult_save_pk(rs.getInt("retouch_save_pk"));
				if(rs.getString("retouch_indate") == null) {
					result.setRetouch_holidaystart(rs.getString("retouch_holidaystart"));
					result.setRetouch_holidayend(rs.getString("retouch_holidayend"));
				}else {
					result.setRetouch_indate(rs.getString("retouch_indate"));
					result.setRetouch_intime(rs.getString("retouch_intime"));
				}

				list.add(result);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Result> findSelect(String startDate, String endDate, String id) {
		String find_SQL = "select * from retouch_result_tb where retouch_id=? and retouch_date between ? and ? order by retouch_date ASC";
		ArrayList<Result> list = new ArrayList<Result>();

		try {
			pstmt = conn.prepareStatement(find_SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Result result = new Result();
				result.setResult_number(rs.getInt("retouch_number"));
				result.setResult_date(rs.getString("retouch_date"));
				result.setResult_id(rs.getString("retouch_id"));
				result.setResult_name(rs.getString("retouch_name"));
				result.setResult_or(rs.getBoolean("retouch_or"));
				result.setResult_specialNote(rs.getString("retouch_specialNote"));
				result.setResult_reason(rs.getString("retouch_reason"));
				result.setResult_false_reason(rs.getString("retouch_false_reason"));
				result.setResult_save_pk(rs.getInt("retouch_save_pk"));
				if(rs.getString("retouch_indate") == null) {
					result.setRetouch_holidaystart(rs.getString("retouch_holidaystart"));
					result.setRetouch_holidayend(rs.getString("retouch_holidayend"));
				}else {
					result.setRetouch_indate(rs.getString("retouch_indate"));
					result.setRetouch_intime(rs.getString("retouch_intime"));
				}

				list.add(result);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	public ArrayList<Result> findSelect() {
		String find_SQL = "select * from retouch_result_tb order by retouch_date ASC";
		ArrayList<Result> list = new ArrayList<Result>();

		try {
			pstmt = conn.prepareStatement(find_SQL);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Result result = new Result();
				result.setResult_number(rs.getInt("retouch_number"));
				result.setResult_date(rs.getString("retouch_date"));
				result.setResult_id(rs.getString("retouch_id"));
				result.setResult_name(rs.getString("retouch_name"));
				result.setResult_or(rs.getBoolean("retouch_or"));
				result.setResult_specialNote(rs.getString("retouch_specialNote"));
				result.setResult_reason(rs.getString("retouch_reason"));
				result.setResult_false_reason(rs.getString("retouch_false_reason"));
				result.setResult_save_pk(rs.getInt("retouch_save_pk"));
				if(rs.getString("retouch_indate") == null) {
					result.setRetouch_holidaystart(rs.getString("retouch_holidaystart"));
					result.setRetouch_holidayend(rs.getString("retouch_holidayend"));
				}else {
					result.setRetouch_indate(rs.getString("retouch_indate"));
					result.setRetouch_intime(rs.getString("retouch_intime"));
				}

				list.add(result);
			}
			return list;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
