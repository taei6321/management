package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.businesstrip.Business;
import com.namowebiz.businesstrip.BusinessTripDAO;
import com.namowebiz.holiday.HolidayDAO;
import com.namowebiz.save.SaveDAO;
import com.namowebiz.user.User;
import com.namowebiz.user.UserDAO;

public class BusinessManageServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
					
		String[] business_id = req.getParameterValues("user_id");
		String business_name = null;
		String business_start = req.getParameter("busyStart");
		String business_end = req.getParameter("busyEnd");
		String business_content = req.getParameter("reason");
		
		HolidayDAO holidayDAO = new HolidayDAO();
		UserDAO userDAO = new UserDAO();
		SaveDAO saveDAO = new SaveDAO();
		ArrayList<User> list = null;
				
		System.out.println(business_id);
		for(int i = 0; i < business_id.length; i++) {
			System.out.println(business_id[i]);
			list = userDAO.userSelect(business_id[i]);
			if(list != null) {
				for(User j : list) {
					business_name = j.getUser_name();
				}
			}
		}
		
		System.out.println(business_name);
		System.out.println(business_start);
		System.out.println(business_end);
		
		PrintWriter out = resp.getWriter();
		
		BusinessTripDAO businessTripDAO = new BusinessTripDAO();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        
        long diff = 0;
		
		int result = 0;

		try {
			java.util.Date sDate = df.parse(business_start);
			java.util.Date eDate = df.parse(business_end);
			Calendar scal = Calendar.getInstance();
			Calendar ecal = Calendar.getInstance();
			scal.setTime(sDate);
			ecal.setTime(eDate);

			for(int i = 0; i < business_id.length; i++) {
				
				while(scal.compareTo(ecal) != 1) {
					int hoResult = holidayDAO.dateSelect(business_id[i], df.format(scal.getTime()));
					if(hoResult == 1) {
						scal.add(Calendar.DATE, 1);
						result = -1;
						break;
					}else {
						scal.add(Calendar.DATE, 1);
						result = 1;
					}
			        
				}
			}
	        
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result == 1) {
			for(int i = 0; i < business_id.length; i++) {
				 ArrayList<Business> bList = businessTripDAO.busySelect(business_id[i]);
					if(bList != null) {
						try {
							Calendar scal = Calendar.getInstance();
							Date sDate = df.parse(business_start);
							scal.setTime(sDate);

							Calendar ecal = Calendar.getInstance();
							Date eDate = df.parse(business_end);
							ecal.setTime(eDate);
							

							while(scal.compareTo(ecal) != 1) {
								int hoResult = businessTripDAO.hoDateSelect(business_id[i], df.format(scal.getTime()));
								
								if(hoResult == 1) {
									out.println("<script>");
									out.println("alert('이미 등록이 되있는 출장 기간입니다.')");
									out.println("location.href='/namowebiz/Resources/pages/business.jsp'");
									out.println("</script>");
									break;
								}else {
									scal.add(Calendar.DATE, 1);
								}
							}
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				try {
					java.util.Date Sdate = df.parse(business_start);
					cal.setTime(Sdate);
					diff = diffOfDate(business_start, business_end);
					System.out.println(diff);
					
					saveDAO.businessInsert(business_id[i], business_start);
					
					for(int j = 1; j <= diff; j++) {
				        cal.add(Calendar.DATE, 1);
						saveDAO.businessInsert(business_id[i], df.format(cal.getTime()));
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				businessTripDAO.tripInsert(business_id[i], business_name, business_start, business_end, business_content);
			}
			out.println("<script>");
			out.println("alert('출장등록을 완료하였습니다.')");
			out.println("location.href='/namowebiz/Resources/pages/business.jsp'");
			out.println("</script>");
		}
		else if(result == -1) {
			out.println("<script>");
			out.println("alert('등록실패, 휴가기간에 출장을 등록하였습니다.')");
			out.println("location.href='/namowebiz/Resources/pages/business.jsp'");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('출장등록을 실패하였습니다.')");
			out.println("location.href='/namowebiz/Resources/pages/business.jsp'");
			out.println("</script>");
		}
	}
	
	 public static long diffOfDate(String begin, String end)
	  {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    long diff = 0;
	    long diffDay = 0;
	    
	    try {
		    java.util.Date beginDate = df.parse(begin);
		    java.util.Date endDate = df.parse(end);
		    System.out.println("diff="+beginDate+beginDate.getTime());
		    System.out.println(endDate+""+endDate.getTime());

		    diff = endDate.getTime() - beginDate.getTime();
		    diffDay = diff / (24*60*60*1000);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    
	    return diffDay;
	  }
	
}
