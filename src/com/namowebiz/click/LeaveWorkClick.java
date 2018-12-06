package com.namowebiz.click;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namowebiz.annual.Annual;
import com.namowebiz.annual.AnnualDAO;
import com.namowebiz.date.*;
import com.namowebiz.save.SaveDAO;

// When Leave work button is clicked
// 퇴근 버튼 클릭 시
public class LeaveWorkClick extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");	
		
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date currentTime = new Date();
		String sDate = dateFormat.format(currentTime);

		String user_id = (String) session.getAttribute("user_id");
		String workTime = null;
		String offWork = null;
		String outside = null;
		float hours = 0.0f;
		boolean directWork = false;
		boolean directOff = false;
		boolean bOutside = false;
		boolean bReturn = false;
		boolean holiday = false;
		boolean sick = false;
		boolean half = false;
		
		String direct = req.getParameter("directO");
		
		TotalDateDAO totalDAO = new TotalDateDAO();
		AnnualDAO annualDAO = new AnnualDAO();
		SaveDAO saveDAO = new SaveDAO();
		
		System.out.println(direct);
		
		int result = 0;
		int saveResult = 0;
		
		int workClick = totalDAO.workTime(user_id);
		System.out.println(workClick);
		
		if(direct != null && direct.equals("직퇴")) {
			annualDAO.directOffWorkUpdate(user_id);
		}
		
		if(workClick == 1) {
			totalDAO.offWorkUpdate(user_id);
			
			// 총 근무시간 구해줌
			result = totalDAO.workingHoursUpdate(user_id);
				
			if(result == 1) {
				System.out.println("총 근무 시간 저장 성공");
				totalDAO.totalDateSelect(user_id);
				annualDAO.annualSelect(user_id);
			}
			else {
				System.out.println("총 근무 시간 저장 실패");
			}	
			
			ArrayList<TotalDate> list = totalDAO.totalDateSelect(user_id);
			if(list != null) {
				for(TotalDate i : list) {
					workTime = i.getTotal_working_time();
					offWork = i.getTotal_offWork_time();
					outside = i.getTotal_outside_time();
					hours = i.getTotal_working_hours();
					session.setAttribute("offTime", i.getTotal_offWork_time());
				}
			}
			
			ArrayList<Annual> alist = annualDAO.annualSelect(user_id);
			if(alist != null) {
				for(Annual i : alist) {
					directWork = i.isAnnual_direct_working();
					directOff = i.isAnnual_direct_offwork();
					bOutside = i.isAnnual_outside();
					bReturn = i.isAnnual_return();
					holiday = i.isAnnual_holidays();
					sick = i.isAnnual_sick_leave();
					half = i.isAnuual_half();
					
				}
			}
			
			saveResult = saveDAO.saveInsert(user_id, sDate, workTime, offWork, outside, hours, directWork, directOff, bOutside, bReturn, holiday, sick, half);
			
			if(saveResult == 1) {
				
				System.out.println("save_TB 저장 성공");
				
				out.println("<script>");
				out.println("alert('"+user_id+"님, 퇴근하였습니다.')");
				out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
				out.println("</script>");
			}
			else {
				System.out.println("save_TB 저장 실패");
				out.println("<script>");
				out.println("alert('저장을 실패하였습니다. 관리자에게 문의바랍니다.')");
				out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
				out.println("</script>");
			}
			
		}else if(workClick == 0) {
			out.println("<script>");
			out.println("alert('출근을 누르지 않았습니다.')");
			out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
			out.println("</script>");
			
		}else {
			out.println("<script>");
			out.println("alert('데이터베이스 오류')");
			out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
			out.println("</script>");
		}
	}
}
