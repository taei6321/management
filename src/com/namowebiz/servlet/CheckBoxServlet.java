package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckBoxServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
	
		PrintWriter out = resp.getWriter();
		
		String[] chaeckBox = req.getParameterValues("check");
		
		if(chaeckBox == null) {
			out.println("<script>");
			out.println("alert('정정건을 선택해주세요.')");
    		out.println("history.back()");
    		out.println("</script>");
		}else {
			out.println("<script>");
    		out.println("location.href='/namowebiz/Resources/pages/dayDetailsModal.jsp'");
    		out.println("</script>");
		}
	}

}
