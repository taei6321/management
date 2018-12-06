package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.annual.AnnualDAO;
import com.namowebiz.date.TotalDateDAO;
import com.namowebiz.user.UserDAO;

public class InsertServlet  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		UserDAO userDAO = new UserDAO();
		TotalDateDAO totalDateDAO = new TotalDateDAO();
		AnnualDAO annualDAO = new AnnualDAO();
		
		PrintWriter out = resp.getWriter();
		
		String user_id = req.getParameter("userId");
		if(user_id == null || user_id.trim().equals("")){
			user_id = null;
		}
		String user_pw = req.getParameter("user_pw");
		if(user_pw == null || user_pw.trim().equals("")){
			user_pw = null;
		}
		String user_name = req.getParameter("user_name");
		if(user_name == null || user_name.trim().equals("")){
			user_name = null;
		}
		String user_part = req.getParameter("user_part");
		if(user_part == null || user_part.trim().equals("")){
			user_part = null;
		}
		String user_rank = req.getParameter("user_rank");
		if(user_rank == null || user_rank.trim().equals("")){
			user_rank = null;
		}
		String user_phone = req.getParameter("user_phone");
		if(user_phone == null || user_phone.trim().equals("")){
			user_phone = null;
		}
		String user_email = req.getParameter("user_email");
		if(user_email == null || user_email.trim().equals("")){
			user_email = null;
		}
		String user_holidays_total = req.getParameter("user_holidays");
		if(user_holidays_total == null || user_holidays_total.trim().equals("")){
			user_holidays_total = "0";
		}
		int i_holidays_total = Integer.parseInt(user_holidays_total);
		
		int result = 0;
		
		if(user_id == null) {
			out.println("<script>");
			out.println("alert('아이디를 입력하지 않았습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		else if(user_pw == null) {
			out.println("<script>");
			out.println("alert('비밀번호를 입력하지 않았습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else if(user_name == null) {
			out.println("<script>");
			out.println("alert('이름을 입력하지 않았습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else if(user_part == null) {
			out.println("<script>");
			out.println("alert('부서를 입력하지 않았습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else if(user_rank == null) {
			out.println("<script>");
			out.println("alert('직급을 입력하지 않았습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else if(user_phone == null) {
			out.println("<script>");
			out.println("alert('연락처를 입력하지 않았습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else if(user_email == null) {
			out.println("<script>");
			out.println("alert('이메일을 입력하지 않았습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else if(i_holidays_total == 0) {
			out.println("<script>");
			out.println("alert('휴가일수를 입력하지 않았습니다.')");
			out.println("history.back()");
			out.println("</script>");
			
		}else{
			
			result = userDAO.userInsert(user_id, user_pw, user_name, user_part, user_rank, user_phone, user_email, i_holidays_total);
			totalDateDAO.idInsert(user_id);
			annualDAO.clickInsert(user_id);
			
			System.out.println(result);
			if(result == 1) {						
				out.println("<script>");
				out.println("alert('추가가 성공적으로 완료되었습니다.')");
				out.println("location.href='/namowebiz/Resources/pages/list.jsp'");
				out.println("</script>");
			}
			else {
				out.println("<script>");
				out.println("alert('추가가 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
	}
}
