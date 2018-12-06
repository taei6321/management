package com.namowebiz.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namowebiz.user.User;
import com.namowebiz.user.UserDAO;

public class MailSend extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
				
		UserDAO userDAO = new UserDAO();
		
		// 받는 사람 정보
		String[] toID = req.getParameterValues("uId");
		String toEmail = "";
		String toName = "";
				
		ArrayList<User> tList = null;
		for(int i=0; i<toID.length; i++) {
			tList = userDAO.userSelect(toID[i]);
		}
		if(tList != null) {
			for(User i : tList) {
				toEmail = i.getUser_email();
				toName = i.getUser_name();
			}
		}
		
		PrintWriter out = resp.getWriter();
		System.out.println(toName);
		System.out.println(toEmail);
		try {
			mailSender(req, toName, toEmail);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<script>");
			out.println("alert('메일전송이 실패하였습니다.');");
			out.println("location.href='/namowebiz/Resources/pages/list.jsp';");
			out.println("</script>");
		}
		out.println("<script>");
		out.println("alert('메일이 전송되었습니다.');");
		out.println("location.href='/namowebiz/Resources/pages/list.jsp';");
		out.println("</script>");
		
	}

	/** 자바 메일 발송 * @throws MessagingException * @throws AddressException **/ 
	public void mailSender(HttpServletRequest request, String name, String email) throws AddressException, MessagingException { 
		// 네이버일 경우 smtp.naver.com 을 입력합니다. 
		// Google일 경우 smtp.gmail.com 을 입력합니다. 
		String host = "smtp.naver.com"; 
		final String user = "namowebiz0"; // 네이버 아이디를 입력해주세요. 
		final String pass = "tlfksh93!"; // 네이버 이메일 비밀번호를 입력해주세요. 
		int port=465; //포트번호 네이버:465 구글:587
		
		System.out.println(user);
		System.out.println(pass);
		
		// 메일 내용 
		String recipient = email; //받는 사람의 메일주소를 입력해주세요. 
		String subject = name+"님 Namowebiz 입사를 축하드립니다."; //메일 제목 입력해주세요.
		String body = "<h4>Work 홈페이지 <a href='https://auth.worksmobile.com/'>https://auth.worksmobile.com/</a></h4>"
					+"<br/><br/>이메일 발급이 정상적으로 완료되었습니다.<br/><br/><br/><br/><br/><br/>"
					+"<div><IMG src='http://192.168.0.105/namowebiz/Resources/image/namo.png' style='width:200px;'/></div><br/><strong>백정혜</strong> Manager"
					+"<br/>05836 서울시 송파구 법원로9길 26(문정동, H비지니스파크), C동 11층 1112호<br/><strong>Tel</strong>02-431-4036&nbsp;<strong>Fax</strong> 070-4275-5130"
					+"<br/><strong>Mobile</strong> 010-8730-9864<br/><strong>Email </strong>baikjhh@namowebiz.com"; //메일 내용 입력해주세요.
				
		Properties props = System.getProperties(); // 정보를 담기 위한 객체 생성 
		
		// SMTP 서버 정보 설정
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", port); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.ssl.enable", "true"); 
		props.put("mail.smtp.ssl.trust", host); 
		
		//Session 생성 
		Session session = Session.getInstance(props, new javax.mail.Authenticator() { 
			String un=user; 
			String pw=pass; 
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() { 
				return new javax.mail.PasswordAuthentication(un, pw);
			} 
		}); 
		
		session.setDebug(true); //for debug 
		Message mimeMessage = new MimeMessage(session); //MimeMessage 생성 
		mimeMessage.setFrom(new InternetAddress("namowebiz0@naver.com")); //발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더 입력합니다. 이때는 이메일 풀 주소를 다 작성해주세요. 
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); //수신자셋팅 //.TO 외에 .CC(참조) .BCC(숨은참조) 도 있음 
		
		mimeMessage.setSubject(subject); //제목셋팅 
//		mimeMessage.setText(body); //내용셋팅 
		mimeMessage.setContent(body, "text/html; charset=utf-8");
		Transport.send(mimeMessage); //javax.mail.Transport.send() 이용 
	}
	
}
