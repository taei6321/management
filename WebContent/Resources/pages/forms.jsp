<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.namowebiz.retouch.Result"%>
<%@page import="com.namowebiz.retouch.ResultDAO"%>
<%@page import="com.namowebiz.retouch.Retouch"%>
<%@page import="com.namowebiz.retouch.RetouchDAO"%>
<%@page import="com.namowebiz.save.Save"%>
<%@page import="com.namowebiz.save.SaveDAO"%>
<%@page import="com.namowebiz.user.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.namowebiz.user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.namowebiz.servlet.*"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
            
    <title>알림기능</title>

    <!-- Bootstrap Core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.css" rel="stylesheet"/>   

    <!-- MetisMenu CSS -->
    <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet"/>

    <!-- Data CSS -->
    <link href="../vendor/data-plugins/data.bootstrap.css" rel="stylesheet"/>

    <!-- Data Responsive CSS -->
    <link href="../vendor/data-responsive/data.responsive.css" rel="stylesheet"/>

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet"/>

    <!-- Custom Fonts -->
    <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    
    <link href="../css/datepicker3.css" rel="stylesheet"/>
	<script src="../js/jquery-1.11.0.min.js"></script>
	<script src="../js/bootstrap-datepicker.js"></script>
		
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
	<link rel="stylesheet" href="/resources/demos/style.css"/>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>  
    
</head>
<body>
<div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                
                <a class="navbar-brand" href="./index.jsp">Namowebiz 나모웹비즈</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
            
                <!-- /.dropdown -->
                <!-- 이 부분을 연차일수가 얼마나 남았는지 보여주는 란이면 좋을듯  -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-tasks">
						<li><a href="#">
								<div>
									<%
										UserDAO userDao = new UserDAO();
										ArrayList<User> holiList = userDao.userSelect(session.getAttribute("user_id").toString());
										float width = 0.0f;
										if (holiList != null) {
											for (User i : holiList) {
												width = (((float)(i.getUser_holidays_total()-i.getUser_holidays_use())/i.getUser_holidays_total())*100);
									%>
									<p>
										<strong>연차일수</strong> <span class="pull-right text-muted">잔여일수 <%= i.getUser_holidays_total()-i.getUser_holidays_use() %>일</span>
									</p>
						
						<%
							}
							}
						%>
																		
						<% if(width == 100){ %>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 100%">
								</div>
							</div>
						<%}else if(width >= 75){ %>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 75%">
								</div>
							</div>	
						<%}else if(width >= 50){ %>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 50%">
								</div>
							</div>
						<%}else if(width >= 30){ %>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-warning"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 30%">
								</div>
							</div>	
						<%}else if(width >= 20){ %>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-warning"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 20%">
								</div>
							</div>
						<%}else if(width >= 10){ %>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-danger"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 10%">
								</div>
							</div>
						<%}else{ %>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-danger"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 1%">
								</div>
							</div>
						<%} %>
						</div>
						</a></li>
					</ul> <!-- /.dropdown-tasks --></li>

				<!-- 정정요청 및 여러가지 내용들을 띄울 알림창 -->
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                    	<%	ResultDAO resultDAO = new ResultDAO();
                    		ArrayList<Result> rList = resultDAO.alramSelect(session.getAttribute("user_id").toString());
                    		
                    		if(rList != null){
                    			for(Result i : rList){
                    				boolean re_B = i.getResult_or();
                    				if(re_B == true){
                    					%>
                        				<li>
    		                                <div>
    		                                    <i class="fa fa-comment fa-fw"></i>	정정건을 수락하였습니다.
    		                                    <span class="pull-right text-muted small"><%= i.getResult_date() %></span>
    		                                </div>
    			                        </li>
    			                        <li class="divider"></li>
                        				<%
                    				}else if(re_B == false){
                    					%>
                        				<li>
    		                                <div>
    		                                    <i class="fa fa-comment fa-fw"></i>	정정건을 반려하였습니다.
    		                                    <span class="pull-right text-muted small"><%= i.getResult_date() %></span>
    		                                </div>
    			                        </li>
    			                        <li class="divider"></li>
                        				<%
                    				}else{
                    					%>
                        				<li>
    		                                <div>
    		                                    <i class="fa fa-comment fa-fw"></i>	알람건이 없습니다.
    		                                    <span class="pull-right text-muted small">no Message</span>
    		                                </div>
    			                        </li>
    			                        <li class="divider"></li>
                        				<%
                        				break;
                    				}
                    			}
                    		}%>
                        
                        <li>
                            <a class="text-center" href="forms.jsp">
                                <strong>모든 알람건 보기</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
               
				<%
					LoginServlet logServlet = new LoginServlet();
					String user_id = logServlet.loginSession(request, response);
					String uID = (String) session.getAttribute("user_id");
					UserDAO userManage = new UserDAO();
					Boolean manage = userManage.manageUser(uID);
					
				%>
				
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
              		<ul class="dropdown-menu dropdown-user">
              			<li><a href="./profile.jsp"><i class="fa fa-user fa-fw"></i> 프로필</a>
                        </li>
                        <li><a href="./pwCheck.jsp"><i class="fa fa-gear fa-fw"></i> 설정</a>
                        </li>
                        <li class="divider"></li>
              				<li><a href="logout.jsp"><i class="fa fa-sign-out fa-fw"></i> 로그아웃</a>
                        </li>
                   	</ul>
                </li>
                                        
              		
            </ul>
            <!-- /.navbar-top-links -->
            
			<!-- 처음 페이지로 이동 시킬 거 -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav nav-pills nav-stacked nav-second-level" id="side-menu">
                            
                       	<li>
                        	<br/>
                        	<p class="text-center"><%=session.getAttribute("name") %>님,</p>
                        	<p class="text-center">반갑습니다.</p>
                        	<br/>
                        </li>
                                              	
                      	<li role="presentation" class="active">
                            <a href="forms.jsp"><i class="fa fa-comments fa-fw"></i> 알림</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 통계<span class="fa arrow"></span></a>
                            <ul class="nav nav-pills nav-stacked nav-second-level">
                                <li>
                                <% InfoServlet info = new InfoServlet(); %>
                                    <a href="dayDetails.jsp">개인 통계</a>
                                </li>
                                <li>
                                    <a href="holiday.jsp">연차</a>
                                </li>
                                <%if(manage == true){ 
                                	FindReDateMngServlet find = new FindReDateMngServlet();%>
                                <li>
                                    <a href="morris.jsp">전체 통계</a>
                                </li>
                                <%} %>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                         <% 
	                        if(manage == true){ 
                        %>
                         <li>
                         <a href="#"><i class="fa fa-sitemap fa-fw"></i> 관리<span class="fa arrow"></span></a>
                            <ul class="nav nav-pills nav-stacked nav-second-level">
                                <li>
                                    <a href="./list.jsp"> 직원리스트</a>
                                </li>
                                <li>
                                 <% ManageReDate manageReDate = new ManageReDate(); 
                              	RetouchDAO badgeDAO = new RetouchDAO();
                             	int badge = badgeDAO.Badge();
                             	%>
                                 <a href="./manageRe.jsp">정정요청&nbsp;<% if(badge != 0){%><span class="label label-danger"><%= badge %></span><%} %></a>
                                </li>
                                <li>
                                <%FindBusiDateServlet findBusiDateServlet = new FindBusiDateServlet(); %>
                                    <a href="./business.jsp">출장</a>
                                </li>
                                <li>
                                    <a href="./holidayMng.jsp">연차</a>
                                </li>
                                <li>
                                    <a href="./publicHoliday.jsp">공휴일</a>
                                </li>
                            </ul>
                            
                        </li>
                        <%} %>
                       
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>


        <div id="page-wrapper">
            <form name="info" method="post">
			<div class="row">
				<div class="col-lg-12" style="margin: auto">
					
						<h1 class="page-header">알림 세부사항</h1>
						
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<%=session.getAttribute("user_id").toString()%>님의 알림 메시지
									</div>
									<!-- /.panel-heading -->
									<div class="panel-body">
									<% 	userDao = new UserDAO();
										boolean manager = userDao.manageSelect(session.getAttribute("user_id").toString());%>
										<table style="width: 100%"
											class="table table-striped table-bordered table-hover"
											id="data-example">
											<thead>
												<tr>
													<%	if(manager){
															%>
															<th class="text-center">날짜</th>
															<th class="text-center">요청자</th>
															<th class="text-center">정정사항</th>
															<th class="text-center">사유</th>
															<th class="text-center">수정 날짜 및 시간</th>
															<th class="text-center">비고</th>
															<%
														}else{
															%>
															<th class="text-center">날짜</th>
															<th class="text-center">알림내용</th>
															<th class="text-center">정정사항</th>
															<th class="text-center">비고</th>
															<%
														}
													%>
												</tr>
											</thead>
											<tbody>
												<%
													String id = (String) session.getAttribute("user_id");
												
													SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
													SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
													
													String date = "";
													String time = "";
													String rdate = "";
													String rtime = "";
													
													RetouchDAO retouchDAO = new RetouchDAO();
													ArrayList<Retouch> retouchList = retouchDAO.retouchSelect();
												
													resultDAO = new ResultDAO();
													ArrayList<Result> list = resultDAO.ReSelect(id);
													ArrayList<Result> mList = resultDAO.findSelect();
													if(manager){
														if(retouchList != null){
															for(Retouch i : retouchList){
																if(i.getRetouch_date() != null){
																	Date d = dateFormat.parse(i.getRetouch_date());
																	date = dateFormat.format(d);
																}
																if(i.getRetouch_intime() != null){
																	Date t = timeFormat.parse(i.getRetouch_intime());
																	time = timeFormat.format(t);
																}else{
																	
																}
																%>
																<tr class="odd gradeX">
																	<td class="text-center"><%= date %></td>
																	<td class="text-center"><%= i.getRetouch_id() %>(<%= i.getRetouch_name() %>)</td>
																	<td class="text-center"><%= i.getRetouch_specialNote() %></td>
																	<td class="text-center"><%= i.getRetouch_reason() %></td>
																	<% if(i.getRetouch_indate() != null){
																		%>
																	<td class="text-center"><%= i.getRetouch_indate() %> <%= time %></td>
																		<%
																	}else{
																		%>
																	<td class="text-center"><%= i.getRetouch_start() %>~<%= i.getRetouch_end() %></td>
																		<%
																	}
																	%>
																	<td class="text-center">대기중</td>
																</tr>
																<%
															}
														}
														if(mList != null){
															for(Result i : mList){
																if(i.getResult_date() != null){
																	Date d = dateFormat.parse(i.getResult_date());
																	rdate = dateFormat.format(d);
																}
																if(i.getRetouch_intime() != null){
																	Date t = timeFormat.parse(i.getRetouch_intime());
																	rtime = timeFormat.format(t);
																}
																if(i.getResult_or() == true){
																	%>
																	<tr class="odd gradeX success">
																	<td class="text-center"><%= rdate %></td>
																	<td class="text-center"><%= i.getResult_id() %>(<%= i.getResult_name() %>)</td>
																	<td class="text-center"><%= i.getResult_specialNote() %></td>
																	<td class="text-center"><%= i.getResult_reason() %></td>
																	<% 	if(i.getRetouch_indate() != null){
																	%>
																	<td class="text-center"><%= i.getRetouch_indate()%> <%= rtime %></td>
																	<%
																	}else{
																	%>
																	<td class="text-center"><%= i.getRetouch_holidaystart()%>~<%= i.getRetouch_holidayend() %></td>
																	<%	
																	}
																	%>
																		<td class="text-center">수락</td>
																	</tr>
																	<%
																}else{
																	%>
																	<tr class="odd gradeX danger">
																	<td class="text-center"><%= rdate %></td>
																	<td class="text-center"><%= i.getResult_id() %>(<%= i.getResult_name() %>)</td>
																	<td class="text-center"><%= i.getResult_specialNote() %></td>
																	<td class="text-center"><%= i.getResult_reason() %></td>
																	<% 	if(i.getRetouch_indate() != null){
																	%>
																	<td class="text-center"><%= i.getRetouch_indate()%> <%= rtime %></td>
																	<%
																	}else{
																	%>
																	<td class="text-center"><%= i.getRetouch_holidaystart()%>~<%= i.getRetouch_holidayend() %></td>
																	<%	
																	}
																	%>
																		<td class="text-center">반려</td>
																	</tr>
																	<%
																}
																%>
																<%
															}
														}
													}else{
														if (list != null) {
															for (Result i : list) {
																
																if(i.getResult_or() == true){
																	%>
																	<tr class="odd gradeX success">
																		<td class="text-center"><%= i.getResult_date() %></td>
																		<td class="text-center">정정건이 수락되었습니다.</td>
																		<td class="text-center"><%= i.getResult_specialNote() %></td>
																		<td class="text-center"></td>
																	</tr>
																	<%
																}else if(i.getResult_or() == false){
																	%>
																	<tr class="odd gradeX danger">
																		<td class="text-center"><%= i.getResult_date() %></td>
																		<td class="text-center">정정건이 반려되었습니다.</td>
																		<td class="text-center"><%= i.getResult_specialNote() %></td>
																		<td class="text-center"></td>
																	</tr>
																	<%
																}
															}
														}	
													}
												%>
											</tbody>
										</table>
										<!-- /.table-responsive -->
									</div>
									<!-- /.panel-body -->
								</div>
								<!-- /.panel -->
							</div>
						</div>
					
				</div>
			</div>
			<!-- <div class="row">
           		<div class="col-lg-12">
		           <div class="col-lg-6"><a class="btn btn-default btn-lg btn-block" href="index.jsp">메인화면</a></div>
            	</div>
        	</div> -->
	</form>
    </div>
    </div>
    <!-- /#wrapper -->


    <!-- jQuery -->
    <script src="../vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Data JavaScript -->
    <script src="../vendor/data/js/jquery.data.min.js"></script>
    <script src="../vendor/data-plugins/data.bootstrap.min.js"></script>
    <script src="../vendor/data-responsive/data.responsive.js"></script>
    
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>
	<script>

	function expireSession()
	{
	  window.location = "/namowebiz/index.html";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 600 %>);
	</script>
</body>
</html>