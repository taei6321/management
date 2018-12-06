package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.user.UserDAO;

public class UpdateServlet  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		UserDAO userDAO = new UserDAO();
		
		PrintWriter out = resp.getWriter();
		
		String user_id = req.getParameter("user_id");
		String user_name = req.getParameter("user_name");
		String user_pw = req.getParameter("user_pw");
		String user_part = req.getParameter("user_part");
		String user_rank = req.getParameter("user_rank");
		String user_phone = req.getParameter("user_phone");
		String user_email = req.getParameter("user_email");
		String user_holiday = req.getParameter("user_holiday");
		
		int result = userDAO.userUpdate(user_name, user_pw, user_part, user_rank, user_phone, user_email, user_holiday, user_id);
		
		if(result == 1) {						
			out.println("<script>");
			out.println("alert('수정이 성공적으로 완료되었습니다.')");
			out.println("location.href='/namowebiz/Resources/pages/detailed.jsp?user_id="+user_id+"'");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('수정을 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
}
