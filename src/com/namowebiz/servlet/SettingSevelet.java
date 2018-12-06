package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namowebiz.user.UserDAO;

public class SettingSevelet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
	
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();		
		
		UserDAO userDAO = new UserDAO();
		
		String user_id = (String) session.getAttribute("user_id");
		String user_pw = req.getParameter("user_pw");
		String user_pwRe = req.getParameter("user_pwRe");
		String user_phone = req.getParameter("user_phone");
		String user_email = req.getParameter("user_email");
		
		System.out.println(user_id);
		System.out.println(user_pw);
		System.out.println(user_pwRe);
		System.out.println(user_phone);
		System.out.println(user_email);
		
		
		if(!user_pw.equals(user_pwRe)) {
			out.println("<script>");
			out.println("alert('비밀번호가 잘못 입력되었습니다.다시입력해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
		else {
			userDAO.settingPWUpdate(user_id, user_pw);
			userDAO.settingPhUpdate(user_id, user_phone);
			userDAO.settingEMUpdate(user_id, user_email);
			
			out.println("<script>");
			out.println("alert('수정이 완료되었습니다.')");
			out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
			out.println("</script>");
		}
	}
}
