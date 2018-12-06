package com.namowebiz.save;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.namowebiz.jdbc.ConnectMySQL;

public class SaveDAO {
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
	
	public SaveDAO() {
		ConnectMySQL cMysql = new ConnectMySQL();
		conn = cMysql.getConn();
	}
	
	public int saveInsert(String user_id, String sDate, String startTime, String EndTime, String OutTime,float hours, 
			boolean directWork, boolean directOff, boolean out, boolean retn, boolean holi, boolean sLeave, boolean half) {
		String saveIn_SQL = "insert into save_tb values (0,?,?,?,?,?,?,?,?,?,?,?,?,?,0)";
		
		try {
			pstmt = conn.prepareStatement(saveIn_SQL);
			pstmt.setString(1, user_id);
			pstmt.setString(2, sDate);
			pstmt.setString(3, startTime);
			pstmt.setString(4, EndTime);
			pstmt.setString(5, OutTime);
			pstmt.setFloat(6, hours);
			pstmt.setBoolean(7, directWork);
			pstmt.setBoolean(8, directOff);
			pstmt.setBoolean(9, out);
			pstmt.setBoolean(10, retn);
			pstmt.setBoolean(11, holi);
			pstmt.setBoolean(12, sLeave);
			pstmt.setBoolean(13, half);
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}

	// float 형 시간 2자리 이하 버림
	public float timeSet(String user_id, String date) {
		String time = "select TRUNCATE(save_working_hours, 2) as timeSet from save_tb where save_id=? and save_date=?";
		try {
			pstmt = conn.prepareStatement(time);
			pstmt.setString(1, user_id);
			pstmt.setString(2, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				return rs.getFloat("timeSet");
			}
			return 0.0f;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0f;
	}
	
	public ArrayList<Save> mainSelect(String user_id, String date) {
		String saveSelect = "select * from save_tb where save_id=? and save_date=?";
				
		ArrayList<Save> list = new ArrayList<Save>();
	
		try {
			pstmt = conn.prepareStatement(saveSelect);
			pstmt.setString(1, user_id);
			pstmt.setString(2, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Save save = new Save();
				save.setSave_number(rs.getInt("save_number"));
				save.setSave_id(rs.getString("save_id"));
				save.setSave_date(rs.getDate("save_date"));				
				save.setSave_start_time(rs.getString("save_start_time"));
				save.setSave_end_time(rs.getString("save_end_time"));
				save.setSave_out_time(rs.getString("save_out_time"));
				save.setSave_working_hours(rs.getFloat("save_working_hours"));
				save.setSave_direct_working(rs.getBoolean("save_direct_working"));
				save.setSave_direct_offwork(rs.getBoolean("save_direct_offwork"));
				save.setSave_outside(rs.getBoolean("save_outside"));
				save.setSave_return(rs.getBoolean("save_return"));
				save.setSave_holiday(rs.getBoolean("save_holiday"));
				save.setSave_sick_leave(rs.getBoolean("save_sick_leave"));
				save.setSave_half(rs.getBoolean("save_half"));
				save.setSave_business(rs.getBoolean("save_business"));
				
				list.add(save);
			}
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Save> dayDetailSelect(String user_id, String date) {
		String saveSelect = "select * from save_tb where save_id=? and  save_date=?";
				
		ArrayList<Save> list = new ArrayList<Save>();
	
		try {
			pstmt = conn.prepareStatement(saveSelect);
			pstmt.setString(1, user_id);
			pstmt.setString(2, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Save save = new Save();
				save.setSave_number(rs.getInt("save_number"));
				save.setSave_id(rs.getString("save_id"));
				save.setSave_date(rs.getDate("save_date"));				
				save.setSave_start_time(rs.getString("save_start_time"));
				save.setSave_end_time(rs.getString("save_end_time"));
				save.setSave_out_time(rs.getString("save_out_time"));
				save.setSave_working_hours(rs.getFloat("save_working_hours"));
				save.setSave_direct_working(rs.getBoolean("save_direct_working"));
				save.setSave_direct_offwork(rs.getBoolean("save_direct_offwork"));
				save.setSave_outside(rs.getBoolean("save_outside"));
				save.setSave_return(rs.getBoolean("save_return"));
				save.setSave_holiday(rs.getBoolean("save_holiday"));
				save.setSave_sick_leave(rs.getBoolean("save_sick_leave"));
				save.setSave_half(rs.getBoolean("save_half"));
				save.setSave_business(rs.getBoolean("save_business"));
				
				list.add(save);
				return list;
			}
			return null;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int dayDiff(String start, String end) {
		String saveSelect = "select datediff(?,?) as diff";
					
		try {
			pstmt = conn.prepareStatement(saveSelect);
			pstmt.setString(1, end);
			pstmt.setString(2, start);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				return rs.getInt("diff");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int clickSelect(String user_id, String date) {
		String saveSelect = "select * from save_tb where save_id=? and save_date=?";
	
		try {
			pstmt = conn.prepareStatement(saveSelect);
			pstmt.setString(1, user_id);
			pstmt.setString(2, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				return 1;
			}
			return 0;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<Save> saveSelect(String user_id) {
		String saveSelect = "select * from save_tb where save_id=? order by save_date ASC";
				
		ArrayList<Save> list = new ArrayList<Save>();
		
		try {
			pstmt = conn.prepareStatement(saveSelect);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Save save = new Save();
				save.setSave_number(rs.getInt("save_number"));
				save.setSave_id(rs.getString("save_id"));
				save.setSave_date(rs.getDate("save_date"));				
				save.setSave_start_time(rs.getString("save_start_time"));
				save.setSave_end_time(rs.getString("save_end_time"));
				save.setSave_out_time(rs.getString("save_out_time"));
				save.setSave_working_hours(rs.getFloat("save_working_hours"));
				save.setSave_direct_working(rs.getBoolean("save_direct_working"));
				save.setSave_direct_offwork(rs.getBoolean("save_direct_offwork"));
				save.setSave_outside(rs.getBoolean("save_outside"));
				save.setSave_return(rs.getBoolean("save_return"));
				save.setSave_holiday(rs.getBoolean("save_holiday"));
				save.setSave_sick_leave(rs.getBoolean("save_sick_leave"));
				save.setSave_half(rs.getBoolean("save_half"));
				save.setSave_business(rs.getBoolean("save_business"));
				
				list.add(save);
			}
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Save> saveSelect(String user_id, Timestamp startTime) {
		String saveSelect = "select * from save_tb where save_id=? and save_start_time=?";
				
		ArrayList<Save> list = new ArrayList<Save>();
	
		try {
			pstmt = conn.prepareStatement(saveSelect);
			pstmt.setString(1, user_id);
			pstmt.setTimestamp(2, startTime);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Save save = new Save();
				save.setSave_number(rs.getInt("save_number"));
				save.setSave_id(rs.getString("save_id"));
				save.setSave_date(rs.getDate("save_date"));				
				save.setSave_start_time(rs.getString("save_start_time"));
				save.setSave_end_time(rs.getString("save_end_time"));
				save.setSave_out_time(rs.getString("save_out_time"));
				save.setSave_working_hours(rs.getFloat("save_working_hours"));
				save.setSave_direct_working(rs.getBoolean("save_direct_working"));
				save.setSave_direct_offwork(rs.getBoolean("save_direct_offwork"));
				save.setSave_outside(rs.getBoolean("save_outside"));
				save.setSave_return(rs.getBoolean("save_return"));
				save.setSave_holiday(rs.getBoolean("save_holiday"));
				save.setSave_sick_leave(rs.getBoolean("save_sick_leave"));
				save.setSave_half(rs.getBoolean("save_half"));
				save.setSave_business(rs.getBoolean("save_business"));
				
				list.add(save);
			}
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Save> saveSelect(String user_id, String number) {
		String saveSelect = "select * from save_tb where save_id=? and save_number=?";
				
		ArrayList<Save> list = new ArrayList<Save>();
	
		try {
			pstmt = conn.prepareStatement(saveSelect);
			pstmt.setString(1, user_id);
			pstmt.setString(2, number);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Save save = new Save();
				save.setSave_number(rs.getInt("save_number"));
				save.setSave_id(rs.getString("save_id"));
				save.setSave_date(rs.getDate("save_date"));				
				save.setSave_start_time(rs.getString("save_start_time"));
				save.setSave_end_time(rs.getString("save_end_time"));
				save.setSave_out_time(rs.getString("save_out_time"));
				save.setSave_working_hours(rs.getFloat("save_working_hours"));
				save.setSave_direct_working(rs.getBoolean("save_direct_working"));
				save.setSave_direct_offwork(rs.getBoolean("save_direct_offwork"));
				save.setSave_outside(rs.getBoolean("save_outside"));
				save.setSave_return(rs.getBoolean("save_return"));
				save.setSave_holiday(rs.getBoolean("save_holiday"));
				save.setSave_sick_leave(rs.getBoolean("save_sick_leave"));
				save.setSave_half(rs.getBoolean("save_half"));
				save.setSave_business(rs.getBoolean("save_business"));
				
				list.add(save);
			}
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 일별
	public String days(String user_id) {
		String saveSelect = "select sum(save_working_hours) as shour from save_tb where save_date = ? and save_id=?";
		
		// save로 저장함
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date currentTime = new Date();
		String sDate = df.format(currentTime);
		System.out.println(sDate);
		try {
			pstmt = conn.prepareStatement(saveSelect);
			pstmt.setString(1, sDate);
			pstmt.setString(2, user_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("shour") == null) {
					return "0.00";
				}else {
					float day = rs.getFloat("shour");
					System.out.println(day);
	                return String.format("%.2f", day);		
				}
            }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 주별
	public String week(String user_id) {
		String saveSelect = "select sum(save_working_hours) as shour from save_tb where save_date BETWEEN ? and ? and save_id=?";
				
		Calendar now = Calendar.getInstance();
		 
		DecimalFormat df = new DecimalFormat("00");
		
		now.add(Calendar.DATE, 1 - now.get(Calendar.DAY_OF_WEEK)); 
		String firstWeekDay = df.format(now.get(Calendar.DATE));   
		
		now.add(Calendar.DATE, 7 - now.get(Calendar.DAY_OF_WEEK)); 
        String lastWeekDay = df.format(now.get(Calendar.DATE)); 
        
        String month  = df.format(now.get(Calendar.MONTH) + 1);
        String year  = df.format(now.get(Calendar.YEAR));
        
        String startDay = year+'-'+month+'-'+firstWeekDay;
        String endDay = year+'-'+month+'-'+lastWeekDay;

		try {
			pstmt = conn.prepareStatement(saveSelect);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			pstmt.setString(3, user_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				float week =  rs.getFloat("shour");
                return String.format("%.2f", week);	
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 월별
	public String month(String user_id) {
		String saveSelect = "select sum(save_working_hours) as shour from save_tb where save_date BETWEEN ? and ? and save_id=?";
				
		Calendar now = Calendar.getInstance();
		 
		DecimalFormat df = new DecimalFormat("00");
		
		String lastDay =  df.format(now.getActualMaximum(Calendar.DAY_OF_MONTH ));
        
        String month  = df.format(now.get(Calendar.MONTH) + 1);
        String year  = df.format(now.get(Calendar.YEAR));
        
        String startDay = year+'-'+month+'-'+01;
        String endDay = year+'-'+month+'-'+lastDay;

		try {
			pstmt = conn.prepareStatement(saveSelect);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			pstmt.setString(3, user_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				float fmonth = rs.getFloat("shour");
				return String.format("%.2f", fmonth);	
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 년별
	public String year(String user_id) {
		String saveSelect = "select sum(save_working_hours) as shour from save_tb where save_date BETWEEN ? and ? and save_id=?";
				
		Calendar now = Calendar.getInstance();
		 
		DecimalFormat df = new DecimalFormat("00");
        
        String month  = df.format(now.get(Calendar.MONTH) + 1);
        String year  = df.format(now.get(Calendar.YEAR));
        
        String startDay = year+'-'+01+'-'+01;
        String endDay = year+'-'+12+'-'+31;

		try {
			pstmt = conn.prepareStatement(saveSelect);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			pstmt.setString(3, user_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				float fyear = rs.getFloat("shour");
				return String.format("%.2f", fyear);		
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String special(String user_id, String startTime) {
		ArrayList<Save> list = saveSelect(user_id, startTime);

        String DRworking = "직출";
       	String DRoff = "직퇴";
       	String out = "외근";
       	String rtrn = "복귀";
       	String holi = "휴가";
       	String sick = "병가";
       	String half = "반차";
       	String specialNote ="";
       	
       	if(list != null){
       		for(Save i : list){
       			if(i.isSave_direct_working() == true){
       				specialNote += DRworking ;
       			}
       			if(i.isSave_direct_offwork() == true){
       				specialNote+= DRoff ;
       			}
       			if(i.isSave_outside() == true) {
       				specialNote += out ;
       			}
       			if(i.isSave_return() == true) {
       				specialNote += rtrn ;
       			}
       			if(i.isSave_holiday() == true) {
       				specialNote += holi ;
       			}
       			if(i.isSave_sick_leave() == true) {
       				specialNote += sick ;
       			}
       			if(i.isSave_half() == true) {
       				specialNote += half ;
       			}
       		}
       	}
		return specialNote;
        
	}
	
	public ArrayList<Save> selectInfo(String start, String end, String save_id){
		String info_SQl = "select * from save_tb where save_id=? and save_date between date(?) and date(?) order by save_date ASC";
		
		ArrayList<Save> list = new ArrayList<Save>();
	
		try {
			pstmt = conn.prepareStatement(info_SQl);
			pstmt.setString(1, save_id);
			pstmt.setString(2, start);
			pstmt.setString(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Save save = new Save();
				save.setSave_number(rs.getInt("save_number"));
				save.setSave_id(rs.getString("save_id"));
				save.setSave_date(rs.getDate("save_date"));				
				save.setSave_start_time(rs.getString("save_start_time"));
				save.setSave_end_time(rs.getString("save_end_time"));
				save.setSave_out_time(rs.getString("save_out_time"));
				save.setSave_working_hours(rs.getFloat("save_working_hours"));
				save.setSave_direct_working(rs.getBoolean("save_direct_working"));
				save.setSave_direct_offwork(rs.getBoolean("save_direct_offwork"));
				save.setSave_outside(rs.getBoolean("save_outside"));
				save.setSave_return(rs.getBoolean("save_return"));
				save.setSave_holiday(rs.getBoolean("save_holiday"));
				save.setSave_sick_leave(rs.getBoolean("save_sick_leave"));
				save.setSave_half(rs.getBoolean("save_half"));
				save.setSave_business(rs.getBoolean("save_business"));
				
				list.add(save);
			}
			return list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 출장 신청 시 자동으로 출 퇴근 저장
	public int businessInsert(String user_id, String sDate) {
		String saveIn_SQL = "insert into save_tb values (0,?,?,?,?,null,8,0,0,0,0,0,0,0,1)";
		
		try {
			pstmt = conn.prepareStatement(saveIn_SQL);
			pstmt.setString(1, user_id);
			pstmt.setString(2, sDate);
			pstmt.setString(3, sDate+" 10:00:00");
			pstmt.setString(4, sDate+" 19:00:00");
						
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;	
	}
	
}
