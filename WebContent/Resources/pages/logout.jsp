<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.namowebiz.servlet.*" %>
<?xml version="1.0" encoding="UTF-8" ?>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>로그아웃</title>
</head>
<body>
		<%
			LoginServlet logServlet = new LoginServlet();
			logServlet.logoutSession(request, response);
		%>
</body>
</html>