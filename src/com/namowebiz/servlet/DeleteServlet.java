package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.user.UserDAO;

public class DeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String user_id = req.getParameter("user_id");
		
     	UserDAO userDAO = new UserDAO();
     	userDAO.resignation(user_id);
		
		out.println("<script>");
		out.println("location.href='/namowebiz/Resources/pages/list.jsp'");
		out.println("</script>");
	}

}
