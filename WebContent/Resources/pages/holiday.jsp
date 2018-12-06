<%@page import="com.namowebiz.retouch.RetouchDAO"%>
<%@page import="com.namowebiz.publicHoliday.PublicHolidayDAO"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.namowebiz.user.User"%>
<%@page import="com.namowebiz.user.UserDAO"%>
<%@page import="com.namowebiz.holiday.Holiday"%>
<%@page import="com.namowebiz.holiday.HolidayDAO"%>
<%@page import="com.namowebiz.retouch.Result"%>
<%@page import="com.namowebiz.retouch.ResultDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8;" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.namowebiz.servlet.*"%> 

<?xml version="1.0" encoding="UTF-8" ?>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
            
    <title>연차 확인</title>

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

	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
	
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
                                <li role="presentation" class="active">
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
        <form name="find" method="post" action="/namowebiz/FindBusiDateServlet.do">
            <div class="row">
               <div class="col-lg-12" style="margin: auto">
					 <h1 class="page-header">연차 사용현황</h1>
					  <% ArrayList<User> hoList = userDao.userSelect(session.getAttribute("user_id").toString());
						float hWidth = 0.0f;
						if (hoList != null) {
							for (User i : hoList) {
								hWidth = (((float)(i.getUser_holidays_total()-i.getUser_holidays_use())/i.getUser_holidays_total())*100);
								if(i.getUser_holidays_total()-i.getUser_holidays_use() < 0){
									%>
						 			<h4 class="text-danger" style="text-align:right">남은 연차 일수 : <strong><%= i.getUser_holidays_total()-i.getUser_holidays_use() %> 일</strong></h4>
									<%
								}else{
									%>
						 			<h4 class="text-primary" style="text-align:right">남은 연차 일수 : <strong><%= i.getUser_holidays_total()-i.getUser_holidays_use() %> 일</strong></h4>
									<%
								}
							}
						}
					%>
					          
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	총 연차 사용 현황	
                        </div><br/>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table style="width:100%" class="table table-striped table-bordered table-hover" id="data-example">
                                <thead>
                                    <tr>
                                        <th class="text-center">No</th>
                                        <th class="text-center">연차 시작일</th>
                                        <th class="text-center">연차 종료일</th>
                                        <th class="text-center">휴가일수</th>
                                        <th class="text-center">비고</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");	
                                	HolidayDAO holidayDAO = new HolidayDAO();
                                	PublicHolidayDAO publicHolidayDAO = new PublicHolidayDAO();
	                                ArrayList<Holiday> list = holidayDAO.holiSelect(session.getAttribute("user_id").toString());
	                                int num = 1;
	                                int weekCnt = 0;
 	                        		int holiCnt = 0;
 	                        		int holidays = 0;
 	                        		int resultHoliday = 0;
	                                 if(list != null){
	                                 	for(Holiday i : list){
	                                 		weekCnt = 0;
	      	                        		holiCnt = 0;
	      	                        		holidays = 0;
	      	                        		resultHoliday = 0;
	                                 		try {
	                							Date sDate = df.parse(i.getHoliday_start());
	                							Date eDate = df.parse(i.getHoliday_end());
	                							Calendar cal = Calendar.getInstance();
	                							Calendar cal2 = Calendar.getInstance();
	                							cal.setTime(sDate);
	                							cal2.setTime(eDate);
	                							while(cal.compareTo(cal2) != 1) {
	                								holidays = i.getHolidays_day();
	                								int week = holidayDAO.weekCheck(df.format(cal.getTime()), df.format(sDate));
	                								int holiResult = publicHolidayDAO.userSelect(df.format(cal.getTime()));
	                								if(holiResult == 1) {
	                									holiCnt++;
	                									cal.add(Calendar.DATE, 1);
	                								}else if(week == 1) {
	                									weekCnt++;
	                									cal.add(Calendar.DATE, 1);
	                								}else {
	                									cal.add(Calendar.DATE, 1);
	                								}
	                							}
	                							resultHoliday = holidays + 1 - holiCnt - weekCnt;

	                						} catch (ParseException e) {
	                							e.printStackTrace();
	                						}
	                                 		if(i.getHoliday_am() == true){
	                                 			%>
                                 			<tr class="odd gradeX">
    	                                        <td class="text-center"><%= num %></td>
    	                                        <td class="text-center"><%=i.getHoliday_start()%></td>
    	                                        <td class="text-center"><%=i.getHoliday_end()%></td>
    	                                        <td class="text-center"><%= resultHoliday * 0.5 %></td>
    	                                        <td class="text-center">오전반차</td>
                                       		</tr>
	                                 			<%
	                                 		}else if(i.getHoliday_pm() == true){
	                                 			%>
                                 			<tr class="odd gradeX">
    	                                        <td class="text-center"><%= num %></td>
    	                                        <td class="text-center"><%=i.getHoliday_start()%></td>
    	                                        <td class="text-center"><%=i.getHoliday_end()%></td>
    	                                        <td class="text-center"><%= resultHoliday * 0.5 %></td>
    	                                        <td class="text-center">오후반차</td>
                                       		</tr>
	                                 			<%
	                                 		}else{
	                                 			%>
                                    		<tr class="odd gradeX">
    	                                        <td class="text-center"><%= num %></td>
    	                                        <td class="text-center"><%=i.getHoliday_start()%></td>
    	                                        <td class="text-center"><%=i.getHoliday_end()%></td>
    	                                        <td class="text-center"><%= resultHoliday * 1%></td>
    	                                        <td class="text-center"></td>
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
            
       		</form>
            <div class="row">
           		<div class="col-lg-12">
		           <div class="col-lg-6"><a data-target="#holiday" data-toggle="modal" class="btn btn-default btn-lg btn-block">연차등록</a></div>
		           <div class="col-lg-6"><a class="btn btn-default btn-lg btn-block" href="index.jsp">메인화면</a></div>
            	</div>
       		</div>
        </div>
        <!-- /#page-wrapper -->
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

    <!-- Page-Level Demo Scripts -  - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#data-example').DataTable({
            responsive: true
        });
    });
    </script>
      <script>

	function expireSession()
	{
	  window.location = "/namowebiz/index.html";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 600 %>);
	</script>
    
    <!-- 연차 신청 모달창 -->
    <div class="row">
    	<div class="modal" id="holiday" tabindex="-1">
    		<div class="modal-dialog">
    			<div class="modal-content">
    				<div class="modal-header">
    					연차 등록란
    					<button class="close" data-dismiss="modal">&times;</button>
					</div><br/>
					<div class="modal-body">
						<form method="post" action="/namowebiz/HolidayServlet.do">
							<%
							float holiday_total = 0.0f;
							float holiday_use = 0.0f;
							UserDAO userDAO = new UserDAO();
							ArrayList<User> holist = userDAO.userSelect(session.getAttribute("user_id").toString());
							if(list != null) {
								for(User i : holist) {
									holiday_total = i.getUser_holidays_total();
									holiday_use = i.getUser_holidays_use();
								}
							} 
							%>
							<div class="form-group" style="width:100%; margin: auto;">
							<%
							if(holiday_total <= holiday_use) {
							%>
							
								<p class="text-danger" style="text-align:center"><strong>사용할 수 있는 연차 일수가 없습니다.</strong></p>
								<p class="text-danger" style="text-align:center"><strong>연차를 추가로 신청하시려면 사유를 작성후 전송 하세요.</strong></p><br/>
								<div class="inputBox"><p class="text-center"><input type="text" id="reason" name="reason" placeholder="연차일수를 초과한 사유를 작성하시오."style="text-align:center;width:80%;height:5%;"/></p></div><br/>
								
							<%	
							}
							%>
								<div class="col-lg-12"><p style="text-align:center">
									
									<input type="text" id="startDay" name="startDay" size="25%" placeholder="시작날짜를 입력하세요." ></input>
									~
									<input type="text" id="endDay" name="endDay" size="25%" placeholder="종료날짜를 입력하세요."></input>

								</p></div>
								 <script src="../js/jquery-1.11.0.min.js"></script>
								  <script src="../js/bootstrap-datepicker.js"></script>
								  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
								  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
								  <script>
								  $(function() {
						               $("#startDay").datepicker({ dateFormat: "yy-mm-dd", minDate : 0 }).val()
						               $("#endDay").datepicker({ dateFormat: "yy-mm-dd", minDate : 0 }).val()
						       	  });
								  $( function() {
								    var dateFormat = "yy-mm-dd",
								    	startDay = $( "#startDay" ).datepicker({
								        defaultDate: "+1w",
								        changeMonth: true,
								        numberOfMonths: 1
								        })
								        .on( "change", function() {
								        	endDay.datepicker( "option", "minDate", getDate( this ) );
								        }),
								        endDay = $( "#endDay" ).datepicker({
								        defaultDate: "+1w",
								        changeMonth: true,
								        numberOfMonths: 1
								      })
								      .on( "change", function() {
								    	  startDay.datepicker( "option", "maxDate", getDate( this ) );
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

								<br /> <br /> <br />

								<p class="text-center">
									<input type="radio" id="half_all" name="holiday" value="allDay" style="width:20px;height:20px"/><label
										for="half_all">연차</label> &nbsp;&nbsp;&nbsp;&nbsp; <input
										type="radio" id="half_am" name="holiday" value="am" style="width:20px;height:20px"/><label
										for="half_am">오전반차</label> &nbsp;&nbsp;&nbsp;&nbsp; <input
										type="radio" id="half_pm" name="holiday" value="pm" style="width:20px;height:20px"/><label
										for="half_pm">오후반차</label> <br /> <br />
								</p>

								<p class="text-center">
									<input class="btn btn-outline btn-primary btn-lg btn-block"
										type="submit" value="등록" />
								</p>
							</div>
							
						</form>
				</div>
    			</div>
    		</div>
   		 </div>
    </div>
    
    
</body>
</html>