package com.namowebiz.click;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namowebiz.annual.AnnualDAO;
import com.namowebiz.businesstrip.Business;
import com.namowebiz.businesstrip.BusinessTripDAO;
import com.namowebiz.date.*;
import com.namowebiz.holiday.Holiday;
import com.namowebiz.holiday.HolidayDAO;
import com.namowebiz.save.SaveDAO;

// When attendance button is clicked
// ��� ��ư Ŭ�� ��
public class AttendanceClick extends HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");		
		HttpSession session = req.getSession();
		
		String user_id = null;
		user_id = (String) session.getAttribute("user_id");	
		
		String direct = req.getParameter("directW");
		
		SimpleDateFormat savedf = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date ();
		String mTime = savedf.format(currentTime);
		
		TotalDateDAO totalDAO = new TotalDateDAO();
		AnnualDAO annualDAO = new AnnualDAO();
		SaveDAO saveDAO = new SaveDAO();
		
		PrintWriter out = resp.getWriter();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		int result = totalDAO.workTime(user_id);
		int startIn = saveDAO.clickSelect(user_id, mTime);
		
		System.out.println(result);
		System.out.println(direct);
		
		SimpleDateFormat todayDF = new SimpleDateFormat("yyyy-MM-dd");
		
		HolidayDAO holidayDAO = new HolidayDAO();
		BusinessTripDAO businessTripDAO = new BusinessTripDAO();
		
		ArrayList<Holiday> hList = holidayDAO.holiSelect(user_id);
		ArrayList<Business> bList = businessTripDAO.busySelect(user_id);

		Date tDate = new Date();
		String today = todayDF.format(tDate);
		Date toDate = null;
		try {
			toDate = todayDF.parse(today);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int h = 0;
		if(hList != null) {
			for(Holiday i : hList) {
				Date d;
				try {
					Calendar scal = Calendar.getInstance();
					d = todayDF.parse(i.getHoliday_start());
					scal.setTime(d);
					
					Calendar ecal = Calendar.getInstance();
					Date eD = todayDF.parse(i.getHoliday_end());
					ecal.setTime(eD);
					
					Calendar tocal = Calendar.getInstance();
					tocal.setTime(toDate);
					
					while(scal.compareTo(ecal) != 1) {
						int hoResult = holidayDAO.dateSelect(user_id, df.format(tocal.getTime()));
						System.out.println("ho"+hoResult);
						if(hoResult == 1) {
							scal.add(Calendar.DATE, 1);
							h = -1;
							break;
						}else {
							h = 1;
							scal.add(Calendar.DATE, 1);
						}
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		int b = 0;
		if(bList != null) {
			for(Business i : bList) {
				Date d;
				try {
					Calendar scal = Calendar.getInstance();
					d = todayDF.parse(i.getBusiness_start());
					scal.setTime(d);
					
					Calendar ecal = Calendar.getInstance();
					Date eD = todayDF.parse(i.getBusiness_end());
					ecal.setTime(eD);
					
					Calendar tocal = Calendar.getInstance();
					tocal.setTime(toDate);
					
					while(scal.compareTo(ecal) != 1) {
						int hoResult = holidayDAO.dateSelect(user_id, df.format(tocal.getTime()));
						System.out.println("ho"+hoResult);
						if(hoResult == 1) {
							scal.add(Calendar.DATE, 1);
							h = -1;
							break;
						}else {
							h = 1;
							scal.add(Calendar.DATE, 1);
						}
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(h == -1) {
			out.println("<script>");
			out.println("alert('���� �Ⱓ �߿��� ����ϽǼ� �����ϴ�.')");
			out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
			out.println("</script>");
		}else if(b == -1) {
			out.println("<script>");
			out.println("alert('���� �Ⱓ �߿��� �ڵ����� ���ó���˴ϴ�.')");
			out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
			out.println("</script>");
		}else {
			if(startIn == 0) {
				if(direct != null && direct.equals("����")) {
					annualDAO.directWorkingUpdate(user_id);
				}
			
	    		if(result == 1){
					out.println("<script>");
					out.println("alert('"+user_id+"��, �̹� ����� �ϼ̽��ϴ�.')");
					out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
					out.println("</script>");
	        	}
				else if(result == 0) {
					totalDAO.workingTimeUpdate(user_id);

					String workTime = totalDAO.workTimeSelect(user_id);
					try {
						Date dWork = df.parse(workTime);
						session.setAttribute("workTime", df.format(dWork));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
					out.println("<script>");
					out.println("alert('"+user_id+"��, ����Ͽ����ϴ�.')");
					out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
					out.println("</script>");
					
				}else {
					out.println("<script>");
					out.println("alert('�����ͺ��̽� ����')");
					out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
					out.println("</script>");
				}
			}else if(startIn == 1){
				out.println("<script>");
				out.println("alert('���� �̹� ��� �ϼ̽��ϴ�.')");
				out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('�����ͺ��̽� ����')");
				out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
				out.println("</script>");
			}
		}
	}
}	
