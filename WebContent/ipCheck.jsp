<?xml version="1.0" encoding="EUC-KR" ?>
<%@page import="com.namowebiz.date.TotalDateDAO"%>
<%@page import="java.net.InetAddress"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>		
<%@page import="com.namowebiz.ip.IpLookup" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>ip üũ</title>
</head>
<body>
	<%
		
		IpLookup iplookup = new IpLookup();
		String user_ip = iplookup.ipFind(request);
		session.setAttribute("user_ip", user_ip);
		iplookup.compare();
		
	%>
	
<script>
    window.location.href = "/namowebiz/Resources/pages/login.jsp"
</script>

</body>
</html>