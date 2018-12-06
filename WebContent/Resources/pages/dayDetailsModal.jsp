<%@page import="com.namowebiz.retouch.Result"%>
<%@page import="com.namowebiz.retouch.ResultDAO"%>
<%@page import="com.namowebiz.retouch.Retouch"%>
<%@page import="com.namowebiz.retouch.RetouchDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="jxl.write.DateTime"%>
<%@page import="com.namowebiz.save.Save"%>
<%@page import="com.namowebiz.save.SaveDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8;" pageEncoding="UTF-8"%>
<%@ page import="com.namowebiz.user.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.namowebiz.user.UserDAO"%>
<%@ page import="com.namowebiz.servlet.*"%> 

<?xml version="1.0" encoding="UTF-8" ?>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="UTF-8"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
            
    <title>개인 통계 관리</title>

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
                                              	
                      	 <li>
                            <a href="forms.jsp"><i class="fa fa-comments fa-fw"></i> 알림</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 통계<span class="fa arrow"></span></a>
                            <ul class="nav nav-pills nav-stacked nav-second-level">
                                <li role="presentation" class="active">
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
			<div class="row">
				<div class="col-lg-12" style="margin: auto">
					
						<h1 class="page-header">정정 요청</h1>
						
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<%=session.getAttribute("user_id")%>님의 정정요청 건
									</div>
									<!-- /.panel-heading -->
									<div class="panel-body">
										<table style="width: 100%"
											class="table table-striped table-bordered table-hover"
											id="data-example">
											<thead>
												<tr>
													<th class="text-center">날짜</th>
													<th class="text-center">출근시간</th>
													<th class="text-center">퇴근시간</th>
													<th class="text-center">외근시간</th>
													<th class="text-center">총 근무 시간</th>
													<th><p class="text-center">직출</p></th>
                                                    <th><p class="text-center">직퇴</p></th>
                                                    <th><p class="text-center">휴가</p></th>
                                                    <th><p class="text-center">반차</p></th>
                                                    <th><p class="text-center">병가</p></th>
                                                    <th><p class="text-center">출장</p></th>
												</tr>
											</thead>
											<tbody>
												<%
													SaveDAO saveDAO = new SaveDAO();
													String id = (String) session.getAttribute("user_id");
													String[] values = request.getParameterValues("check");
													String[] dates = request.getParameterValues("date");
													SimpleDateFormat sDf = new SimpleDateFormat("HH:mm");
													ArrayList<Save> list;
													String sDate = "";
		                                         	String eDate = "";
		                                         	String oDate = "";
													float time = 0.0f;
		                                         	float total = 0.0f;
		                                         	String dWork = "";
		                                         	String dOut = "";
		                                         	String holi = "";
		                                         	String half = "";
		                                         	String sick = "";
		                                         	String busi = "";
		                                         	int idWork = 0;
		                                         	int idOut = 0;
		                                         	int iholi = 0;
		                                         	int ihalf = 0;
		                                         	int isick = 0;
		                                         	int ibusi = 0;
		                                         	
			                                         	
													for(int j=0; j<values.length; j++){
														sDate = "";
			                                         	eDate = "";
			                                         	oDate = "";
														time = 0.0f;
			                                         	dWork = "";
			                                         	dOut = "";
			                                         	holi = "";
			                                         	half = "";
			                                         	sick = "";
			                                         	busi = "";
			                                         	idWork = 0;
			                                         	idOut = 0;
			                                         	iholi = 0;
			                                         	ihalf = 0;
			                                         	isick = 0;
			                                         	ibusi = 0;
														list = saveDAO.saveSelect(id, values[j]);
													
														if (list != null) {
															for (Save i : list) {
		                                            			if(i.getSave_start_time() != null){
		                                            				Date startDate = sDf.parse(i.getSave_start_time());
		                                            				sDate = sDf.format(startDate);
		                                            			}
		                                            			if(i.getSave_end_time() != null){
		                                            				Date endDate = sDf.parse(i.getSave_end_time());
		                                            				eDate = sDf.format(endDate);
		                                            			}
		                                            			if(i.getSave_out_time() != null){
		                                            				Date outDate = sDf.parse(i.getSave_out_time());
		                                            				oDate = sDf.format(outDate);
		                                            			}
		                                            			if(i.isSave_direct_working()){
		                                                			dWork = "직출";
		                                                			idWork++;
		                                            			}
		                                            			if(i.isSave_direct_offwork()){
		                                                         	dOut = "직퇴";
		                                                         	idOut++;
		                                            			}
		                                            			if(i.isSave_holiday()){
		                                            				holi = "휴가";
		                                            				iholi++;
		                                            			}
		                                                     	if(i.isSave_half()){
		                                                     		half = "반차";
		                                                     		ihalf++;
		                                                     	}
		                                                     	if(i.isSave_sick_leave()){
		                                                     		sick = "병가";
		                                                     		isick++;
		                                                     	}
		                                                     	if(i.isSave_business()){
		                                                     		busi = "출장";
		                                                     		ibusi++;
		                                                     	}
												%>
												<tr class="odd gradeX">
													<td class="text-center"><%=i.getSave_date()%></td>
													<td><p class="text-center"><%= sDate %></p></td>
                                                   <td><p class="text-center"><%= eDate %></p></td>
                                                   <td><p class="text-center"><%= oDate %></p></td>
                                                   <td><p class="text-center"><%= time %>시간</p></td>
                                                   <td><p class="text-center"><%= dWork %></p></td>
                                                   <td><p class="text-center"><%= dOut %></p></td>
                                                   <td><p class="text-center"><%= holi %></p></td>
                                                   <td><p class="text-center"><%= half %></p></td>
                                                   <td><p class="text-center"><%= sick %></p></td>
                                                   <td><p class="text-center"><%= busi %></p></td>
												</tr>
												<%
														}
													}
												}
												%>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					<div class="row">
		           		<div class="col-lg-12">
				           <div class="col-lg-6"><a data-target="#modify" data-toggle="modal" class="btn btn-default btn-lg btn-block">작성창 가기</a></div>
				           <div class="col-lg-6"><a class="btn btn-default btn-lg btn-block" href="dayDetails.jsp">돌아가기</a></div>
		            	</div>
		        	</div>
				</div>
			</div>
    </div>
  	
    <!-- jQuery -->
    <script src="../vendor/jquery/jquery.min.js"></script>
	
    <!-- Bootstrap Core JavaScript -->
    <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    
    <!-- Metis Menu Plugin JavaScript -->
    <script src="../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Data JavaScript -->
    <script src="../vendor/data/js/jquery.data.min.js"></script>
    <script src="../vendor/data-plugins/data.bootstrap.min.js"></script>
    <script src="../vendor/data-responsive/data.responsive.js"></script>
    
    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts -  - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#data-example').DataTable({
            responsive: true
        });
    });
    </script>
    <script>
    $(document).ready(function(){
    	$("#modify").modal();
    });
    </script>
   <script>

	function expireSession()
	{
	  window.location = "/namowebiz/index.html";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 600 %>);
	</script>
		
    <div class="row">
    	<div class="modal" id="modify" tabindex="-1">
    		<div class="modal-dialog">
    			<div class="modal-content">
    				<div class="modal-header">
    					정정요청건
    					<button class="close" data-dismiss="modal">&times;</button>
					</div><br/>
					<div class="modal-body">
					<form method="post" action="/namowebiz/ModifyServlet.do">
					<%
					String save_id = (String) session.getAttribute("user_id");
					
					String[] value = request.getParameterValues("check");
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					String mTime = df.format(date);
					
					%>
					<div class="form-group">
					<p class="text-primary" style="text-align:center; font-size:18pt"><strong>신청 날짜  : <%= mTime%></strong></p><br/>
						<%
						String stDate = "";
						String enDate = "";
						for(int i = 0; i < value.length; i++){
							list = saveDAO.saveSelect(id, values[i]);

							if(list != null){
								for (Save j : list) {
									if(j.getSave_start_time() != null){
                        				Date startDate = sDf.parse(j.getSave_start_time());
                        				stDate = sDf.format(startDate);
                        			}
                        			if(j.getSave_end_time() != null){
                        				Date endDate = sDf.parse(j.getSave_end_time());
                        				enDate = sDf.format(endDate);
                        			}
							%>
							<div class="col-1g-12" style="text-align:center; vertical-align:middle">
							<div class="col-lg-6">
								<p class="text-info" style="text-align:center">요청건 날짜 : <strong><%= j.getSave_date() %></strong></p>
								<p class="text-info" style="text-align:center">출근 시간 : <strong><%= stDate %></strong></p>
								<p class="text-info" style="text-align:center">퇴근 시간 : <strong><%= enDate %></strong></p>
							</div>
							
							<input type="hidden" name="date" value="<%= j.getSave_date() %>"/>
							<div class="col-lg-6">
							
							<select class="form-control" id="retouch" name="retouch" style="text-align-last:center">
								<option value="">요청 건</option>
								<option value="출근시간">출근시간</option>
								<option value="퇴근시간">퇴근시간</option>
								<option value="외근시간">외근시간</option>
								<option value="직출">직출</option>
								<option value="직퇴">직퇴</option>
								<option value="외근">외근</option>
								<option value="복귀">복귀</option>
								<option value="연차">연차</option>
								<option value="병가">병가</option>
								<option value="반차">반차</option>
								<option value="출장">출장</option>
							</select>
							</div>
							</div><br/><br/><br/>
							<div class="col-lg-12">
								<div class="col-lg-6">
									<div class="inputBox">
										<input class="form-control" type="text" id="inDate" name="inDate" placeholder="수정할 날짜를 입력하세요." style="text-align:center;width:100%;height:5%;"></input><br/>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="inputBox">
										<input class="form-control" type="time" id="inTime" name="inTime" placeholder="수정할 시간,분을 입력하세요." style="text-align:center;width:100%;height:5%;"></input><br/>
									</div>
								</div>
								
							</div>
							<script src="../js/jquery-1.11.0.min.js"></script>
							  <script src="../js/bootstrap-datepicker.js"></script>
							  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
							  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
							  <script>
							  $(function() {
//					               $("#inDate").datepicker({ dateFormat: "yy-mm-dd", minDate : 0 }).val()
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
							  <div class="col-lg-12">
								  <div class="inputBox">
									<input class="form-control" type="text" name="reason" placeholder="정정 사유를 작성하시오."style="text-align:center;width:100%;height:5%;"></input>	
								  </div><br/>
							  </div>
							
							<input type="hidden" name="save_id" value= <%= id%>></input>
							<input type="hidden" name="save_number" value=<%= j.getSave_number()%>></input><hr/>
							
						<%}}}%>	
							<p class="text-center">
								<input class="btn btn-outline btn-primary btn-lg btn-block"
									type="submit" value="신청" />
							</p>				
						</div>
					</form>
				</div>
    			</div>
    		</div>
   		 </div>
    </div>
</div>
<script>
$(document).ready(function()
		{
		$("#inTime").hide();
		    $("#retouch").change(function() {
		        if($(this).val() == "출근시간") {
		            $("#inTime").show();
		        }else if($(this).val() == "퇴근시간"){
		        	 $("#inTime").show();
		        }else if($(this).val() == "외근시간"){
		        	 $("#inTime").show();
		        }
		        else {
		            $("#inTime").hide();
		        }
		    });
		});
</script>
</body>
</html>