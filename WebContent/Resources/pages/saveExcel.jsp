
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("application/vnd.ms-excel");
	response.setCharacterEncoding("utf-8");
	
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
	String today = formatter.format(new java.util.Date());
	
	response.setHeader("Content-Disposition", "attachment; filename=\'"+today+"_Namowebiz.xls\'");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	out.print("<meta http-equiv=\"Content-Type\" content=\"application/vnd.ms-excel; charset=utf-8\">");
%>
<html xmlns:o="urn:schemas-microsoft-com:office:office"
	xmlns:x="urn:schemas-microsoft-com:office:excel"
	xmlns="http://www.w3.org/TR/REC-html40">
<head>
<style type="text/css">
body {
	font-family: tahoma;
	font-size: 12px
}

table {
	padding: 2px;
	border-spacing: 0px;
	font-family: tahoma;
	font-size: 12px;
	border-collapse: collapse
}

td {
	text-align: center
}
</style>
</head>
<body>
	<%
		out.print(request.getParameter("excel_data"));
	%>
</body>
</html>