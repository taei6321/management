package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.retouch.RetouchDAO;
import com.namowebiz.user.User;
import com.namowebiz.user.UserDAO;

@SuppressWarnings("serial")
public class ModifyServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String retouch_id = req.getParameter("save_id");
		String retouch_name = "";
		String[] save_date = req.getParameterValues("date");		// 출근 클릭 날짜
		String[] inDate = req.getParameterValues("inDate");
		String[] inTime = req.getParameterValues("inTime");
		
		String[] retouch_PK = req.getParameterValues("save_number");
		
		int[] i_retouch_pk = new int[retouch_PK.length];
		if(retouch_PK != null) {
			for(int i=0; i<retouch_PK.length; i++) {
				i_retouch_pk[i] = Integer.parseInt(retouch_PK[i]);
			}
		}
		
		String[] retouch_specialNote = req.getParameterValues("retouch");
		String[] retouch_reason = req.getParameterValues("reason");
		
		UserDAO userDAO = new UserDAO();
		ArrayList<User> list = userDAO.userSelect(retouch_id);
		if(list != null) {
			for(User i : list) {
				retouch_name = i.getUser_name();
			}
		}
		
		int result = 0;
		RetouchDAO retouchDAO = new RetouchDAO();
		for(int i=0; i<save_date.length; i++) {
			result = retouchDAO.retouchInsert(retouch_id, retouch_name, retouch_specialNote[i], retouch_reason[i], inDate[i], inTime[i], i_retouch_pk[i]);
		}
		
		if(result == 1) {
			out.println("<script>");
        	out.println("alert('정정요청을 성공적으로 전송하였습니다.')");
    		out.println("location.href='/namowebiz/Resources/pages/dayDetails.jsp'");
    		out.println("</script>");
		}
		else {
			out.println("<script>");
        	out.println("alert('정정요청을 실패하였습니다.')");
    		out.println("location.href='/namowebiz/Resources/pages/dayDetails.jsp'");
    		out.println("</script>");
		}
	}
}
