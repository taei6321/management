package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namowebiz.user.UserDAO;

public class PwCheckServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		
		UserDAO userDAO = new UserDAO();
		
		PrintWriter out = resp.getWriter();
		String user_id = (String) session.getAttribute("user_id");
		String user_pw = req.getParameter("user_pw");
		
		System.out.println(user_id);
		System.out.println(user_pw);
		int result = userDAO.pwSelect(user_id, user_pw);
		
		if(result == 1) {
			out.println("<script>");
			out.println("location.href='/namowebiz/Resources/pages/setting.jsp'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('입력하신 비밀번호가 불일치합니다.')");
			out.println("location.href='/namowebiz/Resources/pages/pwCheck.jsp'");
			out.println("</script>");
		}
	}

}
