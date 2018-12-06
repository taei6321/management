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
		
		// �޴� ��� ����
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
			out.println("alert('���������� �����Ͽ����ϴ�.');");
			out.println("location.href='/namowebiz/Resources/pages/list.jsp';");
			out.println("</script>");
		}
		out.println("<script>");
		out.println("alert('������ ���۵Ǿ����ϴ�.');");
		out.println("location.href='/namowebiz/Resources/pages/list.jsp';");
		out.println("</script>");
		
	}

	/** �ڹ� ���� �߼� * @throws MessagingException * @throws AddressException **/ 
	public void mailSender(HttpServletRequest request, String name, String email) throws AddressException, MessagingException { 
		// ���̹��� ��� smtp.naver.com �� �Է��մϴ�. 
		// Google�� ��� smtp.gmail.com �� �Է��մϴ�. 
		String host = "smtp.naver.com"; 
		final String user = "namowebiz0"; // ���̹� ���̵� �Է����ּ���. 
		final String pass = "tlfksh93!"; // ���̹� �̸��� ��й�ȣ�� �Է����ּ���. 
		int port=465; //��Ʈ��ȣ ���̹�:465 ����:587
		
		System.out.println(user);
		System.out.println(pass);
		
		// ���� ���� 
		String recipient = email; //�޴� ����� �����ּҸ� �Է����ּ���. 
		String subject = name+"�� Namowebiz �Ի縦 ���ϵ帳�ϴ�."; //���� ���� �Է����ּ���.
		String body = "<h4>Work Ȩ������ <a href='https://auth.worksmobile.com/'>https://auth.worksmobile.com/</a></h4>"
					+"<br/><br/>�̸��� �߱��� ���������� �Ϸ�Ǿ����ϴ�.<br/><br/><br/><br/><br/><br/>"
					+"<div><IMG src='http://192.168.0.105/namowebiz/Resources/image/namo.png' style='width:200px;'/></div><br/><strong>������</strong> Manager"
					+"<br/>05836 ����� ���ı� ������9�� 26(������, H�����Ͻ���ũ), C�� 11�� 1112ȣ<br/><strong>Tel</strong>02-431-4036&nbsp;<strong>Fax</strong> 070-4275-5130"
					+"<br/><strong>Mobile</strong> 010-8730-9864<br/><strong>Email </strong>baikjhh@namowebiz.com"; //���� ���� �Է����ּ���.
				
		Properties props = System.getProperties(); // ������ ��� ���� ��ü ���� 
		
		// SMTP ���� ���� ����
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", port); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.ssl.enable", "true"); 
		props.put("mail.smtp.ssl.trust", host); 
		
		//Session ���� 
		Session session = Session.getInstance(props, new javax.mail.Authenticator() { 
			String un=user; 
			String pw=pass; 
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() { 
				return new javax.mail.PasswordAuthentication(un, pw);
			} 
		}); 
		
		session.setDebug(true); //for debug 
		Message mimeMessage = new MimeMessage(session); //MimeMessage ���� 
		mimeMessage.setFrom(new InternetAddress("namowebiz0@naver.com")); //�߽��� ���� , ������ ����� �̸����ּҸ� �ѹ� �� �Է��մϴ�. �̶��� �̸��� Ǯ �ּҸ� �� �ۼ����ּ���. 
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); //�����ڼ��� //.TO �ܿ� .CC(����) .BCC(��������) �� ���� 
		
		mimeMessage.setSubject(subject); //������� 
//		mimeMessage.setText(body); //������� 
		mimeMessage.setContent(body, "text/html; charset=utf-8");
		Transport.send(mimeMessage); //javax.mail.Transport.send() �̿� 
	}
	
}
