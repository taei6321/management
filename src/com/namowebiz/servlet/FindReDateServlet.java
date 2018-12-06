package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindReDateServlet extends HttpServlet{
	
	public static String FINDSTART="";
	public static String FINDEND="";
	
	public FindReDateServlet() {
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int firstDay = 1;
        int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
 
        FINDSTART = year + "-" + month + "-" + firstDay;
        FINDEND = year + "-" + month + "-" + endDay;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
				
		PrintWriter out = resp.getWriter();
		
		String start = req.getParameter("findStart");
		String end = req.getParameter("findEnd");
        
        /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
			java.util.Date date = df.parse(end);
			cal.setTime(date);
	        cal.add(Calendar.DATE, 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        String new_endDate = df.format(cal.getTime());*/
                
        if(start !=null && end != null) {
        	FINDSTART = start;
        	FINDEND = end;
        	System.out.println(FINDSTART);
        	System.out.println(FINDEND);
        	
        	out.println("<script>");
    		out.println("location.href='/namowebiz/Resources/pages/requestItem.jsp'");
    		out.println("</script>");
        	
        }else {
        	out.println("<script>");
        	out.println("alert('날짜를 잘못입력하였습니다.')");
        	out.println("location.href='/namowebiz/Resources/pages/requestItem.jsp'");
    		out.println("</script>");
        }
	}
}
