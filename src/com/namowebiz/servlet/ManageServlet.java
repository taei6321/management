package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.user.User;
import com.namowebiz.user.UserDAO;

public class ManageServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String user_id = req.getParameter("user_id");
		
     	UserDAO userDAO = new UserDAO();
		boolean change = userDAO.manageSelect(user_id);
		boolean resignation = false;
		ArrayList<User> list = userDAO.userSelect(user_id);
		if(list != null) {
			for(User i : list) {
				resignation = i.getUser_resignation();
			}
		}
		
		userDAO = new UserDAO();
		if(resignation) {
			out.println("<script>");
			out.println("alert('����ڿ��Դ� ������ ������ �� �� �����ϴ�.')");
			out.println("location.href='/namowebiz/Resources/pages/detailed.jsp?user_id="+user_id+"'");
    		out.println("</script>");
		}else {
			int result = userDAO.manageUpdate(user_id, change);
			
	     	if(result == 1) {

	    		out.println("<script>");
				out.println("alert('������ ������ �ο��Ͽ����ϴ�.')");
	    		out.println("location.href='/namowebiz/Resources/pages/detailed.jsp?user_id="+user_id+"'");
	    		out.println("</script>");
	     	}else if(result == 0) {

	    		out.println("<script>");
				out.println("alert('������ ������ ����Ͽ����ϴ�.')");
				out.println("location.href='/namowebiz/Resources/pages/detailed.jsp?user_id="+user_id+"'");
	    		out.println("</script>");
	     	}else {

	    		out.println("<script>");
				out.println("alert('������ ���̽� ����')");
	    		out.println("history.back()");
	    		out.println("</script>");
	     	}
		}
     	
	}

}
