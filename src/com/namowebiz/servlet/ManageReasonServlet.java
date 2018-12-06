package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.retouch.ResultDAO;
import com.namowebiz.retouch.RetouchDAO;

public class ManageReasonServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		RetouchDAO retouchDAO = new RetouchDAO();
		ResultDAO resultDAO = new ResultDAO();
		
		String reDate = req.getParameter("reDate");
		String rePK = req.getParameter("rePK");
		String reID = req.getParameter("reID");
		String reName = req.getParameter("reName");
		String reNote = req.getParameter("reNote");
		String reason = req.getParameter("reason");
		String re_reason = req.getParameter("re_reason");
		String inS = req.getParameter("inS");
		String inE = req.getParameter("inE");
				
		System.out.println(reDate);
		System.out.println(reNote);
		System.out.println(rePK);
		System.out.println(reID);
		System.out.println(reName);
		System.out.println(reason);
		System.out.println(re_reason);
		
		int result = 0;
		if(reNote.equals("휴가")) {

			result = retouchDAO.retDelete(reID, reDate, reNote);
			result = resultDAO.refusalInsert(reID, reName, reNote, reason, re_reason, rePK, inS, inE);
		}else {

			result = retouchDAO.reDelete(rePK, reID, reNote);
			result = resultDAO.refusalInsert(reID, reName, reNote, reason, re_reason, rePK, inS, inE);
		}
		
		if(result == 1) {
			out.println("<script>");
        	out.println("alert('정정건을 반려하였습니다.')");
    		out.println("location.href='/namowebiz/Resources/pages/manageRe.jsp'");
    		out.println("</script>");
		}else {
			out.println("<script>");
        	out.println("alert('정정건을 반려에 실패하였습니다.')");
    		out.println("location.href='/namowebiz/Resources/pages/manageRe.jsp'");
    		out.println("</script>");
		}
	}

}
