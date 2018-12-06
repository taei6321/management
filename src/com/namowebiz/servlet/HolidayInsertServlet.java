package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.user.UserDAO;

public class HolidayInsertServlet  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
				
		PrintWriter out = resp.getWriter();
		
		String id = req.getParameter("user_id");
		String holidayIn = req.getParameter("holidayIn");
		int iHoli = Integer.parseInt(holidayIn);
		
		UserDAO userDAO = new UserDAO();
		
		int result = userDAO.holidayAdd(id, iHoli);
		if(result == 1) {
			out.println("<script>");
        	out.println("alert('휴가일수를 추가하였습니다.')");
    		out.println("location.href='/namowebiz/Resources/pages/holidayMng.jsp'");
    		out.println("</script>");
		}else {
			out.println("<script>");
        	out.println("alert('휴가일수 추가에 실패하였습니다.')");
    		out.println("location.href='/namowebiz/Resources/pages/holidayMng.jsp'");
    		out.println("</script>");
		}
	}
	
}
