package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namowebiz.user.UserDAO;

public class IDCheck extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession();
		
		PrintWriter out = resp.getWriter();
		String id = req.getParameter("userId");
		
		UserDAO userDAO = new UserDAO();
		int result = userDAO.idCheck(id);
		if(result == 1) {
			session.setAttribute("id", "");
			out.println("<script>");
        	out.println("alert('사용중인 ID입니다.')");
        	out.println("location.href='/namowebiz/Resources/pages/userInsert.jsp'");
    		out.println("</script>");
		}else {
			session.setAttribute("id", id);
			out.println("<script>");
        	out.println("alert('사용 가능한 ID입니다.')");
        	out.println("location.href='/namowebiz/Resources/pages/userInsert.jsp'");
    		out.println("</script>");
		}
	}
}
