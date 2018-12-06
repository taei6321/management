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
import javax.servlet.http.HttpSession;

import com.namowebiz.businesstrip.Business;
import com.namowebiz.businesstrip.BusinessTripDAO;
import com.namowebiz.holiday.HolidayDAO;
import com.namowebiz.save.SaveDAO;
import com.namowebiz.user.User;
import com.namowebiz.user.UserDAO;

public class BusinessTripServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
	
		HttpSession session = req.getSession();
				
		String business_id = (String)session.getAttribute("user_id");
		String business_name = null;
		String business_start = req.getParameter("busyStart");
		String business_end = req.getParameter("busyEnd");
		String business_content = req.getParameter("reason"); 
		
		UserDAO userDAO = new UserDAO();
		SaveDAO saveDAO = new SaveDAO();
		HolidayDAO holidayDAO = new HolidayDAO();
		
		ArrayList<User> list = userDAO.userSelect(business_id);
		if(list != null) {
			for(User i : list) {
				business_name = i.getUser_name();
			}
		}
		PrintWriter out = resp.getWriter();
		
		System.out.println(business_start);
		System.out.println(business_end);
		
		BusinessTripDAO businessTripDAO = new BusinessTripDAO();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        int result = 0;
        
        ArrayList<Business> bList = businessTripDAO.busySelect(business_id);
		if(bList != null) {
			try {
				Calendar scal = Calendar.getInstance();
				Date sDate = df.parse(business_start);
				scal.setTime(sDate);

				Calendar ecal = Calendar.getInstance();
				Date eDate = df.parse(business_end);
				ecal.setTime(eDate);
				

				while(scal.compareTo(ecal) != 1) {
					int hoResult = businessTripDAO.hoDateSelect(business_id, df.format(scal.getTime()));
					
					if(hoResult == 1) {
						out.println("<script>");
						out.println("alert('이미 등록이 되있는 출장 기간입니다.')");
						out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
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

			Calendar scal = Calendar.getInstance();
			Date sDate = df.parse(business_start);
			scal.setTime(sDate);

			Calendar ecal = Calendar.getInstance();
			Date eDate = df.parse(business_end);
			ecal.setTime(eDate);
			
			while(scal.compareTo(ecal) != 1) {
				int hoResult = holidayDAO.dateSelect(business_id, df.format(scal.getTime()));
				System.out.println("ho"+hoResult);
				if(hoResult == 1) {
					scal.add(Calendar.DATE, 1);
					result = -1;
					break;
				}else {
					result = 1;
					scal.add(Calendar.DATE, 1);
				}
			}
	        
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if(result == 1) {

        	businessTripDAO.tripInsert(business_id, business_name, business_start, business_end, business_content);	
        	
        	try {
				Date sDate = df.parse(business_start);
				Date eDate = df.parse(business_end);
				Calendar scal = Calendar.getInstance();
				Calendar ecal = Calendar.getInstance();
				scal.setTime(sDate);
				ecal.setTime(eDate);
				while(scal.compareTo(ecal) != 1) {
					int week = businessTripDAO.weekCheck(df.format(scal.getTime()), df.format(sDate));
					
					if(week == 1) {
						scal.add(Calendar.DATE, 1);
					}else {
			        	try {
							saveDAO.businessInsert(business_id, df.format(scal.getTime()));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						scal.add(Calendar.DATE, 1);
					}
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<script>");
			out.println("alert('출장등록을 완료하였습니다.')");
			out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
			out.println("</script>");
		}
		else if(result == -1) {
			out.println("<script>");
			out.println("alert('등록실패, 연차기간에 출장을 등록하였습니다.')");
			out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('출장등록을 실패하였습니다.')");
			out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
			out.println("</script>");
		}
        
	}
	
	public static long diffOfDate(String begin, String end) throws Exception
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	    java.util.Date beginDate = formatter.parse(begin);
	    java.util.Date endDate = formatter.parse(end);

	    long diff = endDate.getTime() - beginDate.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);

	    return diffDays;
	  }
	
	
}
