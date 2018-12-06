package com.namowebiz.click;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namowebiz.date.TotalDate;
import com.namowebiz.date.TotalDateDAO;
import com.namowebiz.annual.*;

// When comback button is clicked
// 복귀 버튼 클릭 시
public class CombackClick extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");		
		HttpSession session = req.getSession();
		
		String user_id = null;
		user_id = (String) session.getAttribute("user_id");
		
		TotalDateDAO totalDAO = new TotalDateDAO();
		AnnualDAO annualDAO = new AnnualDAO();
		
		PrintWriter out = resp.getWriter();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		ArrayList<TotalDate> list = totalDAO.totalDateSelect(user_id);
		if(list != null) {
			for(TotalDate i : list) {
				if(i.getTotal_outside_time() != null) {

					try {
						totalDAO.combackUpdate(user_id);
						annualDAO.returnUpdate(user_id);

						ArrayList<TotalDate> tlist = totalDAO.totalDateSelect(user_id);
						if(tlist != null) {
							for(TotalDate j : tlist) {

								Date dCom = df.parse(j.getTotal_comback_time());
								session.setAttribute("comback", df.format(dCom));
								
								out.println("<script>");
								out.println("alert('"+user_id+"님, 복귀하였습니다.')");
								out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
								out.println("</script>");
							}
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					out.println("<script>");
					out.println("alert('외근을 먼저 눌러주세요.')");
					out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
					out.println("</script>");
				}
			}
		}
	
	}
}

