package com.namowebiz.servlet;

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

import com.namowebiz.annual.AnnualDAO;
import com.namowebiz.date.TotalDate;
import com.namowebiz.date.TotalDateDAO;
import com.namowebiz.jdbc.ConnectMySQL;
import com.namowebiz.user.*;

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
				
		UserDAO userDAO = new UserDAO();
		
		
		PrintWriter out = resp.getWriter();
		
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		System.out.println(user_id);
		System.out.println(user_pw);
		
		// ��ٽð�, �ܱٽð�, ���ͽð�, ��ٽð� �־��ִ� ���� 

		TotalDateDAO totalDateDAO = new TotalDateDAO();
		ArrayList<TotalDate> sList = totalDateDAO.totalDateSelect(user_id);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String date = df.format(d);
				
		if(sList != null) {
			for(TotalDate i : sList) {
				try {
					if(i.getTotal_date() != null) {
						Date compareDate = df.parse(i.getTotal_date());
						String sDate = df.format(compareDate);
						if(!date.equals(sDate)) {
							if(i.getTotal_outside_time() != null) {
								totalDateDAO.resetUpdate(user_id);
							}else {
								out.println("<script>");
								out.println("alert('����� ������ �����̽��ϴ�. ��ٹ�ư Ŭ�� & �α׾ƿ� �� ���� ��û �����ּ���.')");
								out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
								out.println("</script>");
							}
						}
					}			
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		ArrayList<TotalDate> tList = totalDateDAO.totalDateSelect(user_id);		
		if(tList != null) {
			for(TotalDate i : tList) {
				
				
				if(i.getTotal_working_time() != null) {
					session.setAttribute("workTime", i.getTotal_working_time());
				}else {
					session.setAttribute("workTime", "");
				}
				if(i.getTotal_outside_time() != null) {
					session.setAttribute("outTime", i.getTotal_outside_time());
				}else {
					session.setAttribute("outTime", "");
				}
				if(i.getTotal_comback_time() != null) {
					session.setAttribute("comback", i.getTotal_comback_time());
				}else {
					session.setAttribute("comback", "");
				}
				if(i.getTotal_offWork_time() != null) {
					session.setAttribute("offTime", i.getTotal_offWork_time());
				}else {
					session.setAttribute("offTime", "");
				}
			}
		}		
		
		ArrayList<User> list = userDAO.userSelect(user_id);
		String name = null;
		if(list != null) {
			for(User i : list) {
				name = i.getUser_name();
			}
		}
		
		int result = userDAO.loginCheck(user_id, user_pw);
		
		if(result == 1) {
			session.setAttribute("user_id", user_id);
			session.setAttribute("user_pw", user_pw);
			session.setAttribute("name", name);
			
			out.println("<script>");
			out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
			out.println("</script>");
		}
		else if(result == 0) {
			out.println("<script>");
			out.println("alert('��й�ȣ�� Ʋ���ϴ�.')");
			out.println("history.back()");
			out.println("</script>");
		}
		else if(result == -1) {
			out.println("<script>");
			out.println("alert('���� ���̵� �Դϴ�.')");
			out.println("history.back()");
			out.println("</script>");
		}
		else if(result == -2) {
			out.println("<script>");
			out.println("alert('�����ͺ��̽� ����.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

	// Replace login and logout buttons
	// �α����� �� ��� �α׾ƿ�, �α׾ƿ� �� ��� �α����� ���� ���� �ڵ�
	public String loginSession(HttpServletRequest req, HttpServletResponse resp) {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession();
				
		String user_id = null;
		
		if(session.getAttribute("user_id") != null) {
			user_id = (String) session.getAttribute("user_id");
		}
		return user_id;
	}
	
	// Exit Session on Logout
	// �α׾ƿ� �� ���� ����
	public void logoutSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
				
		HttpSession session = req.getSession();
		ConnectMySQL conn = new ConnectMySQL();
		
		String id = session.getAttribute("user_id").toString();
		
		TotalDateDAO totalDateDAO = new TotalDateDAO();
		AnnualDAO annualDAO = new AnnualDAO();
		
		ArrayList<TotalDate> list = totalDateDAO.totalDateSelect(id);
		if(list != null) {
			for(TotalDate i : list) {
				SimpleDateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
				Date currentTime = null;
				String mTime = null;
				String sDate = null;
				System.out.println(i.getTotal_working_hours());
				if(i.getTotal_working_time() != null) {
					try {
						currentTime = new Date();
						mTime = df.format(currentTime);
						
						Date d = df.parse(i.getTotal_working_time());
						sDate = df.format(d);
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(sDate.equals(mTime)) {
						
					}else {
						totalDateDAO.resetUpdate(id);
						annualDAO.resetUpdate(id);
					}
				}
			}
		}
				
		PrintWriter out = resp.getWriter();
		conn.connClose();
		
		session.invalidate();
		
		out.println("<script>");
		out.println("location.href='/namowebiz/index.html'");
		out.println("</script>");
		
	}
		
}	

