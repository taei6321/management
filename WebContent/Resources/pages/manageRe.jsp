<%@page import="java.util.Date"%>
<%@page import="com.namowebiz.retouch.Result"%>
<%@page import="com.namowebiz.retouch.ResultDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<title>정정요청 관리</title>
	<meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>


    <!-- Bootstrap Core CSS -->
  	<link href="../vendor/bootstrap/css/bootstrap.css" rel="stylesheet"/>

    <!-- MetisMenu CSS -->
    <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet"/>

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
                                              	
                      	 <li>
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
                                <li role="presentation" class="active">
                                 	<%RetouchDAO badgeDAO = new RetouchDAO();
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
            <form  name="manage" method="post" action="/namowebiz/ManageAcceptServlet.do">
			<div class="row">
				<div class="col-lg-12" style="margin: auto">
					
						<h1 class="page-header">정정요청 관리</h1>
						
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										수락 & 반려 관리창
									</div>
									<!-- /.panel-heading -->
									<div class="panel-body">
										<table style="width: 100%"
											class="table table-striped table-bordered table-hover"
											id="data-example">
											<thead>
												<tr>
													<th class="text-center">요청날짜</th>
													<th class="text-center">요청자 ID</th>
													<th class="text-center">요청자 이름</th>
													<th class="text-center">수정 날짜 및 시간</th>
													<th class="text-center">요청건</th>
													<th class="text-center">요청사유</th>
													<th class="text-center">수락</th>
													<th class="text-center">반려</th>
												</tr>
											</thead>
											<tbody>
												<%
												RetouchDAO retouchDAO = new RetouchDAO();
												SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
												SimpleDateFormat sDf = new SimpleDateFormat("HH:mm");
												String date = "";
												String time = "";
												ArrayList<Retouch> list = retouchDAO.retouchSelect();
												boolean re_result = false;
												String re_reason = null;
												int num = 1;
												if (list != null) {
													for (Retouch i : list) {
															String note = i.getRetouch_specialNote();
															Date rDate = df.parse(i.getRetouch_date());
															if(i.getRetouch_intime() != null){
																Date t = sDf.parse(i.getRetouch_intime());
																time = sDf.format(t);
															}
															if(note.equals("출근시간")||note.equals("퇴근시간")||note.equals("외근시간")){
																%>
																<tr class="odd gradeX">
																	<td class="text-center"><input type="hidden" id="tr_reDate_<%=num %>" name="reDate" value="<%= i.getRetouch_date() %>"></input><%= df.format(rDate) %></td>
																	<td class="text-center"><input type="hidden" id="tr_reID_<%= num %>" name="reID" value="<%= i.getRetouch_id() %>"></input><%= i.getRetouch_id() %></td>
																	<td class="text-center"><input type="hidden" id="tr_reName_<%=num %>" name="reName" value="<%= i.getRetouch_name() %>"></input><%= i.getRetouch_name() %></td>
																	<td class="text-center"><input type="hidden" id="tr_rePK_<%=num %>" name="rePK" value="<%= i.getRetouch_PK() %>"></input><%= i.getRetouch_indate() %> <%= time %></td>
																	<td class="text-center"><input type="hidden" id="tr_reNote_<%=num %>" name="reNote" value="<%= i.getRetouch_specialNote() %>"></input><%= i.getRetouch_specialNote()%></td>
																	<td class="text-center"><input type="hidden" id="tr_reason_<%=num%>" name="reason" value="<%= i.getRetouch_reason() %>"></input><%= i.getRetouch_reason() %></td>
																	<td class="text-center"><input type="hidden" id="tr_inS_<%=num%>" name="inS" value="<%= i.getRetouch_indate() %>"></input><a data-target="#accept" data-toggle="modal" class="btn" onclick="tr_modal(<%= num%>);">수락</a></td>
																	<td class="text-center"><input type="hidden" id="tr_inE_<%=num%>" name="inE" value="<%= i.getRetouch_intime() %>"></input><a data-target="#down" data-toggle="modal" class="btn" onclick="tr_modalf(<%= num%>);">반려</a></td>
																</tr>
																<%
															}else{
																%>
																<tr class="odd gradeX">
																	<td class="text-center"><input type="hidden" id="tr_reDate_<%=num %>" name="reDate" value="<%= i.getRetouch_date() %>"></input><%= df.format(rDate) %></td>
																	<td class="text-center"><input type="hidden" id="tr_reID_<%= num %>" name="reID" value="<%= i.getRetouch_id() %>"></input><%= i.getRetouch_id() %></td>
																	<td class="text-center"><input type="hidden" id="tr_reName_<%=num %>" name="reName" value="<%= i.getRetouch_name() %>"></input><%= i.getRetouch_name() %></td>
																	<td class="text-center"><input type="hidden" id="tr_rePK_<%=num %>" name="rePK" value="<%= i.getRetouch_PK() %>"></input><%= i.getRetouch_start() %>~<%= i.getRetouch_end() %></td>
																	<td class="text-center"><input type="hidden" id="tr_reNote_<%=num %>" name="reNote" value="<%= i.getRetouch_specialNote() %>"></input><%= i.getRetouch_specialNote() %></td>
																	<td class="text-center"><input type="hidden" id="tr_reason_<%=num%>" name="reason" value="<%= i.getRetouch_reason() %>"></input><%= i.getRetouch_reason() %></td>
																	<td class="text-center"><input type="hidden" id="tr_inS_<%=num%>" name="inS" value="<%= i.getRetouch_start() %>"></input><input type="submit" value="수락" class="btn" onmouseout="change2(this)" onmouseover="change1(this)" style="background-color:transparent;"></input></td>
																	<td class="text-center"><input type="hidden" id="tr_inE_<%=num%>" name="inE" value="<%= i.getRetouch_end() %>"></input><a data-target="#down" data-toggle="modal" class="btn" onclick="tr_modalf(<%= num%>);">반려</a></td>
																</tr>
																<%
															}
														num++;
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
	</form>
	<form  method="post" action="/namowebiz/ManageReDate.do">
		<div style="float: left;">
			<input type="text" id="findStart" name="findStart" size="30%" placeholder="찾을 시작날짜를 입력하세요." value="<%= ManageReDate.FINDSTART%>"></input>
			~
			<input type="text" id="findEnd" name="findEnd" size="30%" placeholder="찾을 종료날짜를 입력하세요." value="<%= ManageReDate.FINDEND%>"></input>&nbsp;
							
				</div>	
				<script src="../js/jquery-1.11.0.min.js"></script>
				<script src="../js/bootstrap-datepicker.js"></script>
				<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
			  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
			  <script>
			  $(function() {
	               $("#findStart").datepicker({ dateFormat: "yy-mm-dd"}).val()
	               $("#findEnd").datepicker({ dateFormat: "yy-mm-dd" }).val()
	       	  });
			  $( function() {
			    var dateFormat = "yy-mm-dd",
			   		 findStart = $( "#findStart" )
			        .datepicker({
			          defaultDate: "+1w",
			          changeMonth: true,
			          numberOfMonths: 3
			        })
			        .on( "change", function() {
			        	findEnd.datepicker( "option", "minDate", getDate( this ) );
			        }),
			        findEnd = $( "#findEnd" ).datepicker({
			        defaultDate: "+1w",
			        changeMonth: true,
			        numberOfMonths: 3
			      })
			      .on( "change", function() {
			    	  findStart.datepicker( "option", "maxDate", getDate( this ) );
			      });
			 
			    function getDate( element ) {
			      var date;
			      try {
			        date = $.datepicker.parseDate( dateFormat, element.value );
			      } catch( error ) {
			        date = null;
			      }
			 
			      return date;
			    }
			  } );
			  </script>
				
				<div class="customButton" style="float: left;">
					<input type="submit" class="btn btn-info" value="찾기"></input>
				</div><br></br>
           
            <div class="row">
                <div class="col-lg-12"  style="margin: auto">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                          	정정요청건 수락/반려건 찾기
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table style="width:100%" class="table table-striped table-bordered table-hover" id="data-example">
                                <thead>
                                    <tr>
                                        <th class="text-center">요청날짜</th>
                                        <th class="text-center">요청자</th>
                                        <th class="text-center">요청건</th>
                                        <th class="text-center">수정 날짜 및 시간</th>
                                        <th class="text-center">요청사유</th>
                                        <th class="text-center">요청결과</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                               
                                String sDate = "";
                                String sTime = "";
                                
                                retouchDAO = new RetouchDAO();
                                resultDAO = new ResultDAO();
                                String start = ManageReDate.FINDSTART;
                                String end = ManageReDate.FINDEND;
                                ArrayList<Retouch> f_list = retouchDAO.retouchSelect();
                                ArrayList<Result> r_list = resultDAO.findSelect();
                                if(f_list != null){
                                	for(Retouch i : f_list){
                                		if(i.getRetouch_date() != null){
                                			Date d = df.parse(i.getRetouch_date());
                                			sDate = df.format(d);
                                		}
                                		if(i.getRetouch_intime() != null){
                            				Date t = sDf.parse(i.getRetouch_intime());
                                			sTime = sDf.format(t);
                            			}
                                		if(i.getRetouch_specialNote().equals("휴가")){
                                			%>
                                    		<tr class="odd gradeX">
    	                                        <td class="text-center"><%= sDate %></td>
    	                                        <td class="text-center"><%= i.getRetouch_id() %>(<%= i.getRetouch_name() %>)</td>
    	                                        <td class="text-center"><%= i.getRetouch_specialNote() %></td>
    	                                        <td class="text-center"><%= i.getRetouch_start() %>~<%= i.getRetouch_end() %></td>
    	                                        <td class="text-center"><%= i.getRetouch_reason() %></td>
    	                                        <td class="text-center">대기중</td>
                                       		</tr>
                                    		<% 	
                                		}else{
                                			%>
                                    		<tr class="odd gradeX">
    	                                        <td class="text-center"><%= sDate %></td>
    	                                        <td class="text-center"><%= i.getRetouch_id() %>(<%= i.getRetouch_name() %>)</td>
    	                                        <td class="text-center"><%= i.getRetouch_specialNote() %></td>
    	                                        <td class="text-center"><%= i.getRetouch_indate()%> <%= sTime %></td>
    	                                        <td class="text-center"><%= i.getRetouch_reason() %></td>
    	                                        <td class="text-center">대기중</td>
                                       		</tr>
                                    		<% 	
                                		}	
                                	}
                                }
                                
                                int fnum = 1;
                                if(r_list != null){
                        			for(Result j : r_list){
                        				if(j.getResult_date() != null){
                        					Date d = df.parse(j.getResult_date());
                        					sDate = df.format(d);
                        				}
                        				if(j.getRetouch_intime() != null){
                    						Date t = sDf.parse(j.getRetouch_intime());
                                			sTime = sDf.format(t);
                    					}
                        				if(j.getResult_specialNote().equals("휴가")){
                        					if(j.getResult_or()== true){
                                    			%>
                                        		<tr class="odd gradeX success">
        	                                        <td class="text-center"><%= sDate %></td>
        	                                        <td class="text-center"><%= j.getResult_id() %>(<%= j.getResult_name() %>)</td>
        	                                        <td class="text-center"><%= j.getResult_specialNote()%></td>
        	                                        <td class="text-center"><%= j.getRetouch_holidaystart()%>~<%= j.getRetouch_holidayend() %></td>
        	                                        <td class="text-center"><%= j.getResult_reason() %></td>
        	                                        <td class="text-center">수락</td>
                                           		</tr>
                                        		<% 
                                    		}else {
                                    			%>
                                        		<tr class="odd gradeX danger">
        	                                        <td class="text-center"><%= sDate %></td>
        	                                        <td class="text-center"><%= j.getResult_id() %>(<%= j.getResult_name() %>)</td>
        	                                        <td class="text-center"><%= j.getResult_specialNote() %></td>
        	                                        <td class="text-center"><%= j.getRetouch_holidaystart()%>~<%= j.getRetouch_holidayend() %></td>
        	                                        <td class="text-center"><%= j.getResult_reason() %></td>
        	                                        <td class="text-center"><input type="hidden" id="tr_falseModal_<%=fnum %>" value="<%=j.getResult_false_reason()%>"></input><a data-target="#refusal" data-toggle="modal" class="btn" onclick="tr_modalFalse(<%= fnum%>);">반려</a></td>
                                           		</tr>
                                        		<% 
                                    		}
                        				}else{
                        					if(j.getResult_or()== true){
                                    			%>
                                        		<tr class="odd gradeX success">
        	                                        <td class="text-center"><%= sDate %></td>
        	                                        <td class="text-center"><%= j.getResult_id() %>(<%= j.getResult_name() %>)</td>
        	                                        <td class="text-center"><%= j.getResult_specialNote()%></td>
        	                                        <td class="text-center"><%= j.getRetouch_indate()%> <%= sTime %></td>
        	                                        <td class="text-center"><%= j.getResult_reason() %></td>
        	                                        <td class="text-center">수락</td>
                                           		</tr>
                                        		<% 
                                    		}else {
                                    			%>
                                        		<tr class="odd gradeX danger">
        	                                        <td class="text-center"><%= sDate %></td>
        	                                        <td class="text-center"><%= j.getResult_id() %>(<%= j.getResult_name() %>)</td>
        	                                        <td class="text-center"><%= j.getResult_specialNote() %></td>
        	                                        <td class="text-center"><%= j.getRetouch_indate()%> <%= sTime %></td>
        	                                        <td class="text-center"><%= j.getResult_reason() %></td>
        	                                        <td class="text-center"><input type="hidden" id="tr_falseModal_<%=fnum %>" value="<%=j.getResult_false_reason()%>"></input><a data-target="#refusal" data-toggle="modal" class="btn" onclick="tr_modalFalse(<%= fnum%>);">반려</a></td>
                                           		</tr>
                                        		<% 
                                    		}
                        				}
                        				fnum++;
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
                <!-- /.col-lg-12 -->
            </div>
    	</form>
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
	function change1(obj){
		style="background-color:transparent;"
        obj.style.background = 'transparent';
        obj.style.color = '#333333';
    }
	function change2(obj){
        obj.style.background = 'transparent';
        obj.style.color = '#337ab7';
    }
	</script>
	
	<script>
	function tr_modalFalse(str){
		$("#falseModal").val($("#tr_falseModal_"+str).val());
	}
	function tr_modal(str){
		$("#reDate").val($("#tr_reDate_"+str).val());
		$("#rePK").val($("#tr_rePK_"+str).val());
		$("#reID").val($("#tr_reID_"+str).val());
		$("#reName").val($("#tr_reName_"+str).val());
		$("#reNote").val($("#tr_reNote_"+str).val());
		$("#reason").val($("#tr_reason_"+str).val());
		$("#inS").val($("#tr_inS_"+str).val());
		$("#inE").val($("#tr_inE_"+str).val());
		$("#id").val($("#tr_reID_"+str).val());
		$("#name").val($("#tr_reName_"+str).val());
		$("#dateTime").val($("#tr_inS_"+str).val()+" "+$("#tr_inE_"+str).val());
		$("#note").val($("#tr_reNote_"+str).val());
		$("#re").val($("#tr_reason_"+str).val());
	}
	
	function tr_modalf(str){
		$("#refDate").val($("#tr_reDate_"+str).val());
		$("#refPK").val($("#tr_rePK_"+str).val());
		$("#refID").val($("#tr_reID_"+str).val());
		$("#refName").val($("#tr_reName_"+str).val());
		$("#refNote").val($("#tr_reNote_"+str).val());
		$("#reasonf").val($("#tr_reason_"+str).val());
		$("#infS").val($("#tr_inS_"+str).val());
		$("#infE").val($("#tr_inE_"+str).val());
		$("#fid").val($("#tr_reID_"+str).val());
		$("#fname").val($("#tr_reName_"+str).val());
		$("#fdateTime").val($("#tr_inS_"+str).val()+" "+$("#tr_inE_"+str).val());
		$("#fnote").val($("#tr_reNote_"+str).val());
		$("#fre").val($("#tr_reason_"+str).val());
	}
	</script>
	  <script>

	function expireSession()
	{
	  window.location = "/namowebiz/index.html";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 600 %>);
	</script>
    
<!-- 수락건 모달 -->
	 <div class="row" >
    	<div class="modal" id="accept" tabindex="-1">
    		<div class="modal-dialog">
    			<div class="modal-content">
    				<div class="modal-header">
    					수락 건
    					<button class="close" data-dismiss="modal">&times;</button>
					</div><br/>
					<div class="modal-body" style="width:100%; height:50%; margin:auto">
					<form method="post" action="/namowebiz/ManageAcceptServlet.do" class="form-inline" role="form">
							
							<div class="col-1g-12" style="text-align:left">
							
								<div class="col-lg-12"><h6 class="col-lg-3">요청자 ID : </h6><strong class="col-lg-9"><input type="text" id="id" name="id" value="" style="border:0px; background-color:transparent;" disabled></input></strong></div>
								<div class="col-lg-12"><h6 class="col-lg-3">요청자 이름 : </h6><strong class="col-lg-9"><input type="text" id="name" name="name" value="" style="border:0px; background-color:transparent;" disabled></input></strong></div>
								<div class="col-lg-12"><h6 class="col-lg-3">수정 날짜 및 시간 : </h6><strong class="col-lg-9"><input type="text" id="dateTime" name="dateTime" value="" style="border:0px; background-color:transparent;" disabled></input></strong></div>
								<div class="col-lg-12"><h6 class="col-lg-3">요청건 : </h6><strong class="col-lg-9"><input type="text" id="note" name="note" value="" style="border:0px; background-color:transparent;" disabled></input></strong></div>
								<div class="col-lg-12"><h6 class="col-lg-3">요청사유</h6></div>
								<div class="col-lg-12"><strong><input type="text" id="re" name="re" value="" style="text-align:center; width:100%; border:0px; background-color:transparent;" disabled></input></strong><br></br></div>
								
								<div class="col-lg-12">
									<div class="col-lg-6">
										<div class="inputBox">
											<input class="form-control" type="text" id="inDate" name="inDate" placeholder="수정할 날짜를 입력하세요." style="text-align:center;width:100%;"></input>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="inputBox">
											<input class="form-control" type="time" id="inTime" name="inTime" style="text-align:center;width:100%;"></input>
										</div>
									</div>
									
									<script src="../js/jquery-1.11.0.min.js"></script>
									  <script src="../js/bootstrap-datepicker.js"></script>
									  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
									  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
									  <script>
									  $(function() {
							               $("#inDate").datepicker({ dateFormat: "yy-mm-dd" }).val()
							       	  });
									  $( function() {
									    var dateFormat = "yy-mm-dd",
									   		inDate = $( "#inDate" ).datepicker({
									        defaultDate: "+1w",
									        changeMonth: true,
									        numberOfMonths: 1
									        });
									  } );
									  </script>
										<div class="col-lg-2">
										<div class="form-group">				
											<p class="text-center">
												<input type="hidden" id="reDate" name="reDate" value=""></input>
												<input type="hidden" id="rePK" name="rePK" value=""></input>
												<input type="hidden" id="reID" name="reID" value=""></input>
												<input type="hidden" id="reName" name="reName" value=""></input>
												<input type="hidden" id="reNote" name="reNote" value=""></input>
												<input type="hidden" id="reason" name="reason" value=""></input>
												<input type="hidden" id="inS" name="inS" value=""></input>
												<input type="hidden" id="inE" name="inE" value=""></input>
	 											<input class="btn btn-outline btn-primary btn-sm btn-block" type="submit" value="수락"></input>
											</p>				
										</div>							
									</div>
								
								</div>
							</div>
														
							</form>
						</div>	
					</div>
    			</div>
    		</div>
   		 </div>
    </div>
    
    <!-- 반려건 모달 -->
	 <div class="row">
    	<div class="modal" id="down" tabindex="-1">
    		<div class="modal-dialog">
    			<div class="modal-content">
    				<div class="modal-header">
    					반려 건
    					<button class="close" data-dismiss="modal">&times;</button>
					</div><br/>
					<div class="modal-body">
					<form method="post" action="/namowebiz/ManageReasonServlet.do">
						<div class="col-lg-12" style="text-align:left">
							<div class="col-lg-12"><h6 class="col-lg-3">요청자 ID : </h6><strong class="col-lg-9"><input type="text" id="fid" name="fid" value="" style="width:100%;border:0px; background-color:transparent;" disabled></input></strong></div>
							<div class="col-lg-12"><h6 class="col-lg-3">요청자 이름 : </h6><strong class="col-lg-9"><input type="text" id="fname" name="fname" value="" style="width:100%;border:0px; background-color:transparent;" disabled></input></strong></div>
							<div class="col-lg-12"><h6 class="col-lg-3">수정 날짜, 시간 : </h6><strong class="col-lg-9"><input type="text" id="fdateTime" name="fdateTime" value="" style="width:100%;border:0px; background-color:transparent;" disabled></input></strong></div>
							<div class="col-lg-12"><h6 class="col-lg-3">요청건 : </h6><strong class="col-lg-9"><input type="text" id="fnote" name="fnote" value="" style="width:100%;border:0px; background-color:transparent; " disabled></input></strong></div>
							<div class="col-lg-12"><h6 class="col-lg-3">요청사유</h6></div>
							<div class="col-lg-12"><strong><input type="text" id="fre" name="fre" value="" style="text-align:center; width:100%; border:0px; background-color:transparent;" disabled></input></strong><br></br></div>
						</div>
						<div class="inputBox">
							<input class="form-control" type="text" name="re_reason" placeholder="반려사유를 작성하세요." style="text-align:center;width:100%;"></input><br></br>
						
						</div>
						<div class="form-group">				
							<p class="text-center">
								<input type="hidden" id="refDate" name="reDate" value=""></input>
								<input type="hidden" id="refPK" name="rePK" value=""></input>
								<input type="hidden" id="refID" name="reID" value=""></input>
								<input type="hidden" id="refName" name="reName" value=""></input>
								<input type="hidden" id="refNote" name="reNote" value=""></input>
								<input type="hidden" id="reasonf" name="reason" value=""></input>
								<input type="hidden" id="infS" name="inS" value=""></input>
								<input type="hidden" id="infE" name="inE" value=""></input>
								<input class="btn btn-outline btn-primary btn-lg btn-block" type="submit" value="반려"></input>
							</p>				
						</div>
					</form>
				</div>
    			</div>
    		</div>
   		 </div>
    </div>
     <!-- 반려 사유 모달창 -->
    <div class="row">
    	<div class="modal" id="refusal" tabindex="-1">
    		<div class="modal-dialog modal-sm">
    			<div class="modal-content">
    				<div class="modal-header">
    					반려사유
    					<button class="close" data-dismiss="modal">&times;</button>
					</div><br/>
					<div class="modal-body">
						<div class="form-group">
						
						<p class="text-default" style="text-align:center; font-size:12pt">
							<strong>
								<input type="text" id="falseModal" value="" style="background-color:transparent;border:0 solid black;text-align:center;" disabled></input>
							</strong>
						</p>
						
						</div>
					</div>
					<div class="modal-footer"> 
		                <div class="btn-group fr">
		                   	<button class="btn btn-outline btn-primary btn-sm btn-block" data-dismiss="modal" aria-hidden="true">닫기</button>              
		               </div>                  
		           </div><!-- end of modal footer --> 
    			</div>
    		</div>
   		 </div>
    </div>
</body>
</html>