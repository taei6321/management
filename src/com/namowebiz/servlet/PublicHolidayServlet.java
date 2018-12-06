package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.publicHoliday.PublicHolidayDAO;

public class PublicHolidayServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String holidayName = req.getParameter("holidayName");
		String holidayDate = req.getParameter("holidayDate");
		String updateName = req.getParameter("updateName");
		String upDate = req.getParameter("upDate");
		String holidayPK = req.getParameter("holidayPK");
		
		System.out.println(updateName);
		System.out.println(upDate);
		System.out.println(holidayPK);
		
		PublicHolidayDAO publicHolidayDAO = new PublicHolidayDAO();
		
		if(updateName == null) {
			int result = publicHolidayDAO.userInsert(holidayName, holidayDate);
			
			if(result == 1) {
				out.println("<script>");
	        	out.println("alert('공휴일을 성공적으로 등록하였습니다.')");
	    		out.println("location.href='/namowebiz/Resources/pages/publicHoliday.jsp'");
	    		out.println("</script>");
			}else {
				out.println("<script>");
	        	out.println("alert('공휴일 등록을 실패하였습니다.')");
	    		out.println("location.href='/namowebiz/Resources/pages/publicHoliday.jsp'");
	    		out.println("</script>");
			}
		}else {
			int result = publicHolidayDAO.holidayUpdate(holidayPK, updateName, upDate);
			if(result == 1) {
				out.println("<script>");
	        	out.println("alert('공휴일을 성공적으로 수정하였습니다.')");
	    		out.println("location.href='/namowebiz/Resources/pages/publicHoliday.jsp'");
	    		out.println("</script>");
			}else {
				out.println("<script>");
	        	out.println("alert('공휴일 수정을 실패하였습니다.')");
	    		out.println("location.href='/namowebiz/Resources/pages/publicHoliday.jsp'");
	    		out.println("</script>");
			}
		}
	}
}
