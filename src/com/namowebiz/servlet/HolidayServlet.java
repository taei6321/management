package com.namowebiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namowebiz.businesstrip.BusinessTripDAO;
import com.namowebiz.holiday.Holiday;
import com.namowebiz.holiday.HolidayDAO;
import com.namowebiz.publicHoliday.PublicHolidayDAO;
import com.namowebiz.retouch.RetouchDAO;
import com.namowebiz.user.User;
import com.namowebiz.user.UserDAO;

public class HolidayServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		HttpSession session = req.getSession();

		String holiday_id = (String)session.getAttribute("user_id");
		String holiday_name = null;
		String holiday_start = req.getParameter("startDay");
		String holiday_end = req.getParameter("endDay");
		String[] value = req.getParameterValues("holiday");
		String reason = req.getParameter("reason");
		
		UserDAO userDAO = new UserDAO();
		BusinessTripDAO businessTripDAO = new BusinessTripDAO();

		ArrayList<User> list = userDAO.userSelect(holiday_id);
		if(list != null) {
			for(User i : list) {
				holiday_name = i.getUser_name();
			}
		}

		PrintWriter out = resp.getWriter();		
		HolidayDAO holidayDAO = new HolidayDAO();
		RetouchDAO retouchDAO = new RetouchDAO();
		PublicHolidayDAO publicHolidayDAO = new PublicHolidayDAO();

		ArrayList<Holiday> list1;

		int result = 0;
		int update = 0;
		int weekCnt = 0;
		int holiCnt = 0;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");	

		System.out.println(holiday_start);
		System.out.println(holiday_end);

		for(int i = 0; i<value.length; i++) {
			try {
				Calendar scal = Calendar.getInstance();
				Date sDate = df.parse(holiday_start);
				scal.setTime(sDate);

				Calendar ecal = Calendar.getInstance();
				Date eDate = df.parse(holiday_end);
				ecal.setTime(eDate);

				while(scal.compareTo(ecal) != 1) {
					int hoResult = businessTripDAO.hoDateSelect(holiday_id, df.format(scal.getTime()));
					System.out.println("ho"+hoResult);
					if(hoResult == 1) {
						scal.add(Calendar.DATE, 1);
						result = -1;
						break;

					}else {
						result = 1;
						scal.add(Calendar.DATE, 1);
					}
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int r = 0;
			if(reason != null) {
				if(result == -1) {
					out.println("<script>");
					out.println("alert('등록실패, 출장기간 중 연차등록을 하였습니다.')");
					out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
					out.println("</script>");
				}else{
					ArrayList<Holiday> hoList = holidayDAO.holiSelect(holiday_id);
					if(hoList != null) {
						try {
							Calendar scal = Calendar.getInstance();
							Date sDate = df.parse(holiday_start);
							scal.setTime(sDate);

							Calendar ecal = Calendar.getInstance();
							Date eDate = df.parse(holiday_end);
							ecal.setTime(eDate);
							

							while(scal.compareTo(ecal) != 1) {
								int hoResult = holidayDAO.dateSelect(holiday_id, df.format(scal.getTime()));
								System.out.println("ho"+hoResult);
								if(hoResult == 1) {
									r = -1;
									out.println("<script>");
									out.println("alert('이미 등록이 되있는 연차 기간입니다.')");
									out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
									out.println("</script>");
									break;
								}else {
									r = 1;
									scal.add(Calendar.DATE, 1);
								}
							}
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(r == -1) {
						
					}else {
						if(value[i].equals("allDay")) {
							result = retouchDAO.reasonInsert(holiday_id, holiday_name, reason, holiday_start, holiday_end, "allDay");
						}else if(value[i].equals("am")) {

							result = retouchDAO.reasonInsert(holiday_id, holiday_name, reason, holiday_start, holiday_end, "am");
						}else if(value[i].equals("pm")){

							result = retouchDAO.reasonInsert(holiday_id, holiday_name, reason, holiday_start, holiday_end, "pm");
						}
						if(result == 1) {
							out.println("<script>");
							out.println("alert('연차 정정요청을 전송하였습니다.')");
							out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
							out.println("</script>");
						}else {
							out.println("<script>");
							out.println("alert('연차 정정요청건 전송을 실패하였습니다.')");
							out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
							out.println("</script>");
						}
					}
				}
			}else {
				if(result == -1) {
					out.println("<script>");
					out.println("alert('등록실패, 출장기간 중 연차등록을 하였습니다.')");
					out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
					out.println("</script>");
				}else {
					try {
						Calendar scal = Calendar.getInstance();
						Date sDate = df.parse(holiday_start);
						scal.setTime(sDate);

						Calendar ecal = Calendar.getInstance();
						Date eDate = df.parse(holiday_end);
						ecal.setTime(eDate);
						

						while(scal.compareTo(ecal) != 1) {
							int hoResult = holidayDAO.dateSelect(holiday_id, df.format(scal.getTime()));
							System.out.println("ho"+hoResult);
							if(hoResult == 1) {
								r = -1;
								out.println("<script>");
								out.println("alert('이미 등록이 되있는 연차 기간입니다.')");
								out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
								out.println("</script>");
								break;
							}else {
								r = 1;
								scal.add(Calendar.DATE, 1);
							}
						}

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(r == -1) {
						
					}else {
						if(value[i].equals("allDay")) {
							result = holidayDAO.holidayInsert(holiday_id, holiday_name, holiday_start, holiday_end, false, false);
							try {
								Date sDate = df.parse(holiday_start);
								Date eDate = df.parse(holiday_end);
								Calendar cal = Calendar.getInstance();
								Calendar cal2 = Calendar.getInstance();
								cal.setTime(sDate);
								cal2.setTime(eDate);
								while(cal.compareTo(cal2) != 1) {
									int week = holidayDAO.weekCheck(df.format(cal.getTime()), df.format(sDate));
									int holiResult = publicHolidayDAO.userSelect(df.format(cal.getTime()));
									if(holiResult == 1) {
										holiCnt += 1;
									}
									if(week == 1) {
										weekCnt += 1;
										cal.add(Calendar.DATE, 1);
									}else {
										cal.add(Calendar.DATE, 1);
									}
								}

							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							holidayDAO.dayDIff(holiday_id, holiday_end, holiday_start);
							list1 = holidayDAO.holiSelect(holiday_id,holiday_start,holiday_end);
							int holidays = 0;
							if(list != null) {
								for(Holiday i1 : list1) {
									holidays = i1.getHolidays_day();
									update = userDAO.holidayupdate(holiday_id, 1*(holidays+1-weekCnt-holiCnt));
								}
							}
							if(result == 1 && update == 1) {
								out.println("<script>");
								out.println("alert('연차등록을 완료하였습니다.')");
								out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
								out.println("</script>");
							}else {
								out.println("<script>");
								out.println("alert('연차등록을 실패하였습니다.')");
								out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
								out.println("</script>");
							}

						}else if(value[i].equals("am")) {
							result = holidayDAO.holidayInsert(holiday_id, holiday_name, holiday_start, holiday_end, true, false);
							try {
								Date sDate = df.parse(holiday_start);
								Date eDate = df.parse(holiday_end);
								Calendar cal = Calendar.getInstance();
								Calendar cal2 = Calendar.getInstance();
								cal.setTime(sDate);
								cal2.setTime(eDate);
								while(cal.compareTo(cal2) != 1) {
									int week = holidayDAO.weekCheck(df.format(cal.getTime()), df.format(sDate));
									int holiResult = publicHolidayDAO.userSelect(df.format(cal.getTime()));
									if(holiResult == 1) {
										holiCnt += 1;
									}
									if(week == 1) {
										weekCnt += 1;
										cal.add(Calendar.DATE, 1);
									}else {
										cal.add(Calendar.DATE, 1);
									}
								}

							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							holidayDAO.dayDIff(holiday_id, holiday_end, holiday_start);
							list1 = holidayDAO.holiSelect(holiday_id,holiday_start,holiday_end);
							int holidays = 0;
							if(list != null) {
								for(Holiday i1 : list1) {
									holidays = i1.getHolidays_day();
									update = userDAO.holidayupdate(holiday_id, 0.5f*(holidays+1-weekCnt-holiCnt));
								}
							}
							if(result == 1 && update == 1) {
								out.println("<script>");
								out.println("alert('연차등록을 완료하였습니다.')");
								out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
								out.println("</script>");
							}else {
								out.println("<script>");
								out.println("alert('연차등록을 실패하였습니다.')");
								out.println("location.href='/namowebiz/Resources/pages/index.jsp");
								out.println("</script>");
							}

						}else if(value[i].equals("pm")) {
							result = holidayDAO.holidayInsert(holiday_id, holiday_name, holiday_start, holiday_end, false, true);
							try {
								Date sDate = df.parse(holiday_start);
								Date eDate = df.parse(holiday_end);
								Calendar cal = Calendar.getInstance();
								Calendar cal2 = Calendar.getInstance();
								cal.setTime(sDate);
								cal2.setTime(eDate);
								while(cal.compareTo(cal2) != 1) {
									int week = holidayDAO.weekCheck(df.format(cal.getTime()), df.format(sDate));
									int holiResult = publicHolidayDAO.userSelect(df.format(cal.getTime()));
									if(holiResult == 1) {
										holiCnt += 1;
									}
									if(week == 1) {
										weekCnt += 1;
										cal.add(Calendar.DATE, 1);
									}else {
										cal.add(Calendar.DATE, 1);
									}
								}

							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							holidayDAO.dayDIff(holiday_id, holiday_end, holiday_start);
							list1 = holidayDAO.holiSelect(holiday_id,holiday_start,holiday_end);
							int holidays = 0;
							if(list != null) {
								for(Holiday i1 : list1) {
									holidays = i1.getHolidays_day();
									update = userDAO.holidayupdate(holiday_id, 0.5f*(holidays+1-weekCnt-holiCnt));
								}
							}

							if(result == 1 && update == 1) {
								out.println("<script>");
								out.println("alert('연차등록을 완료하였습니다.')");
								out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
								out.println("</script>");
							}else {
								out.println("<script>");
								out.println("alert('연차등록을 실패하였습니다.')");
								out.println("location.href='/namowebiz/Resources/pages/index.jsp'");
								out.println("</script>");

							}
						}
					}
				}
			}
		}
	}
}