package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindBusiDateServlet extends HttpServlet{
	
	public static String BFINDSTART = "";
	public static String BFINDEND = "";
	
	public FindBusiDateServlet() {
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int firstDay = 1;
        int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
 
        BFINDSTART = year + "-" + month + "-" + firstDay;
        BFINDEND = year + "-" + month + "-" + endDay;
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
                
                
        if(start !=null && end != null) {
        	BFINDSTART = start;
        	BFINDEND = end;
        	System.out.println(BFINDSTART);
        	System.out.println(BFINDEND);
        	
        	out.println("<script>");
    		out.println("location.href='/namowebiz/Resources/pages/business.jsp'");
    		out.println("</script>");
        	
        }else {
        	out.println("<script>");
        	out.println("alert('날짜를 잘못입력하였습니다.')");
        	out.println("location.href='/namowebiz/Resources/pages/business.jsp'");
    		out.println("</script>");
        }
	}
}
