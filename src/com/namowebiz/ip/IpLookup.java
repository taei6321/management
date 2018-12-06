package com.namowebiz.ip;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.namowebiz.ip.IpInsert;

public class IpLookup extends HttpServlet{	

	private static String ip = null;
	public static boolean IP_CK = true;
	
	// Process of importing visitor IP
	// 방문자 IP를 가져오기위한 과정
	public String ipFind(HttpServletRequest request) {
		request.getRemoteAddr() ;
		ip = request.getHeader("X-FORWARDED-FOR"); 
	     
	     if (ip == null || ip.length() == 0) {
	         ip = request.getHeader("Proxy-Client-IP");
	     }

	     if (ip == null || ip.length() == 0) {
	         ip = request.getHeader("WL-Proxy-Client-IP");  // 웹로직
	     }

	     if (ip == null || ip.length() == 0) {
	         ip = request.getRemoteAddr() ;
	     }
	     System.out.println(ip);
	     return ip;
	}

	// External or internal accessor comparison
	// 외부 또는 내부 접근자 비교
	public boolean compare() {

		IpInsert ipinsert = new IpInsert();

		String threeIp = ip.substring(0, 3);
        String date[] = ip.split(".");
     
		// If internal ip
		// 내부 아이피인 경우
		if( threeIp.equals("127") || date[2].equals("0")) {
			IP_CK = true;
			ipinsert.insertIP(ip, true);
			return IP_CK;
		}

		// If external ip
		// 외부 아이피인경우
		else {
			IP_CK = false;
			ipinsert.insertIP(ip, false);
			return IP_CK;

		}
	}

}
