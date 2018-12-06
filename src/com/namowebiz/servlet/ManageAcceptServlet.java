package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.holiday.HolidayDAO;
import com.namowebiz.retouch.ResultDAO;
import com.namowebiz.retouch.Retouch;
import com.namowebiz.retouch.RetouchDAO;
import com.namowebiz.save.ReSaveDAO;
import com.namowebiz.user.UserDAO;

public class ManageAcceptServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		HolidayDAO holidayDAO = new HolidayDAO();
		RetouchDAO retouchDAO = new RetouchDAO();
		ResultDAO resultDAO = new ResultDAO();
		ReSaveDAO reSaveDAO = new ReSaveDAO();
		UserDAO userDAO = new UserDAO();
		
		String reDate = req.getParameter("reDate");
		String rePK = req.getParameter("rePK");
		String reID = req.getParameter("reID");
		String reName = req.getParameter("reName");
		String reNote = req.getParameter("reNote");
		String reason = req.getParameter("reason");
		String inS = req.getParameter("inS");
		String inE = req.getParameter("inE");
		String start = null;
		String end = null;
		String holiday = null;

		String reTime = req.getParameter("reTime");
	
		System.out.println(reDate);
		System.out.println(reNote);
		System.out.println(rePK);
		System.out.println(reID);
		System.out.println(reName);
		System.out.println(reTime);
		System.out.println(reason);
		
		int result = 0;		
		if(reNote.equals("출근시간")) {
			resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
			reSaveDAO.startUpdate(rePK, reID, reTime);
			result = reSaveDAO.workingHoursUpdate(rePK, reID);
		}else if(reNote.equals("퇴근시간")) {
			resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
			reSaveDAO.endUpdate(rePK, reID, reTime);
			result = reSaveDAO.workingHoursUpdate(rePK, reID);
		}else if(reNote.equals("외근시간")) {
			resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
			result = reSaveDAO.outUpdate(rePK, reID, reTime);
		}else if(reNote.equals("직출")){
			resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
			result = reSaveDAO.DirWorkUpdate(rePK, reID, true);
		}else if(reNote.equals("직퇴")){
			resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
			result = reSaveDAO.DirOffUpdate(rePK, reID, true);
		}else if(reNote.equals("외근")){
			resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
			result = reSaveDAO.outUpdate(rePK, reID, true);
		}else if(reNote.equals("복귀")){
			resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
			result = reSaveDAO.retUpdate(rePK, reID, true);
		}else if(reNote.equals("병가")){
			resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
			result = reSaveDAO.sickUpdate(rePK, reID, true);
		}else if(reNote.equals("반차")){
			resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
			result = reSaveDAO.halfUpdate(rePK, reID, true);
			userDAO.holidayupdate(reID, 0.5f);
		}else if(reNote.equals("출장")){
			resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
			result = reSaveDAO.busyUpdate(rePK, reID, true);
		}else if(reNote.equals("휴가")) {
			ArrayList<Retouch> list = retouchDAO.RetSelect(reID, reNote, reDate);
			if(list != null) {
				for(Retouch i : list) {
					start = i.getRetouch_start();
					end = i.getRetouch_end();
					holiday = i.getRetouch_holiday();
				}
			}
			if(holiday.equals("allDay")) {
				resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
				result = holidayDAO.holidayInsert(reID, reName, start, end, false, false);
			}else if(holiday.equals("am")) {
				resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
				result = holidayDAO.holidayInsert(reID, reName, start, end, true, false);
			}else if(holiday.equals("pm")) {
				resultDAO.acceptInsert(reID, reName, reNote, reason, rePK, inS, inE);
				result = holidayDAO.holidayInsert(reID, reName, start, end, false, true);
			}
		}else {
			result = -1;
		}
		
		if(result == 1) {
			if(reNote.equals("휴가")) {
				retouchDAO.retDelete(reID, reDate, reNote);
			}else {
				retouchDAO.reDelete(rePK, reID, reNote);
			}
			out.println("<script>");
        	out.println("alert('정정건을 수락하였습니다.')");
    		out.println("location.href='/namowebiz/Resources/pages/manageRe.jsp'");
    		out.println("</script>");
		}else {
			out.println("<script>");
        	out.println("alert('정정건 수락에 실패하였습니다.')");
    		out.println("location.href='/namowebiz/Resources/pages/manageRe.jsp'");
    		out.println("</script>");
		}
	}
}
