package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.retouch.RetouchDAO;

public class ReasonServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		RetouchDAO retouchDAO = new RetouchDAO();
		
		String retouch_id = req.getParameter("id");
		String retouch_date = req.getParameter("check");
		String retouch_contents = req.getParameter("contents");
		String retouch_reason = req.getParameter("reason");
		
		int result = retouchDAO.reasonUpdate(retouch_contents, retouch_reason, retouch_id, retouch_date);
		
		System.out.println(result);
		
		if(result == 1) {
			out.println("<script>");
			out.println("alert('전송을 성공적으로 보냈습니다.')");
    		out.println("location.href='/namowebiz/Resources/pages/requestItem.jsp");
    		out.println("</script>");
		}
		else {

			out.println("<script>");
			out.println("alert('전송을 실패하였습니다.')");
    		out.println("history.back()");
    		out.println("</script>");
		}
	}
}
