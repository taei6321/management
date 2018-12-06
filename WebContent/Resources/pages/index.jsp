<%@page import="javafx.scene.control.DatePicker"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.namowebiz.retouch.Result"%>
<%@page import="com.namowebiz.retouch.ResultDAO"%>
<%@page import="com.namowebiz.retouch.Retouch"%>
<%@page import="com.namowebiz.retouch.RetouchDAO"%>
<%@page import="com.namowebiz.user.User"%>
<%@page import="com.namowebiz.user.UserDAO"%>
<%@page import="com.namowebiz.save.Save"%>
<%@page import="com.namowebiz.save.SaveDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.namowebiz.servlet.*"%> 
<%@ page import="com.namowebiz.ip.*" %>
<%@ page import="com.namowebiz.date.*" %>
<?xml version="1.0" encoding="EUC-KR" ?>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <title>Namowebiz Home</title>

    <!-- Bootstrap Core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.css" rel="stylesheet"/>

    <!-- MetisMenu CSS -->
    <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet"/>

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet"/>

    <!-- Morris Charts CSS -->
    <link href="../vendor/morrisjs/morris.css" rel="stylesheet"/>

    <!-- Custom Fonts -->
    <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	
	<!-- datepicker -->
	<link href="../css/datepicker3.css" rel="stylesheet"/>
	<script src="../js/jquery-1.11.0.min.js"></script>
	<script src="../js/bootstrap-datepicker.js"></script>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
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
										boolean manager = false;
										if (holiList != null) {
											for (User i : holiList) {
												width = (((float) (i.getUser_holidays_total() - i.getUser_holidays_use())
														/ i.getUser_holidays_total()) * 100);
												manager = i.getUser_manager();
									%>
									<p>
										<strong>연차일수</strong> <span class="pull-right text-muted">잔여일수
											<%=i.getUser_holidays_total() - i.getUser_holidays_use()%>일
										</span>
									</p>

									<%
										}
										}
									%>

									<%
										if (width == 100) {
									%>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 100%"></div>
									</div>
									<%
										} else if (width >= 75) {
									%>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 75%"></div>
									</div>
									<%
										} else if (width >= 50) {
									%>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 50%"></div>
									</div>
									<%
										} else if (width >= 30) {
									%>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-warning"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 30%"></div>
									</div>
									<%
										} else if (width >= 20) {
									%>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-warning"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 20%"></div>
									</div>
									<%
										} else if (width >= 10) {
									%>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-danger"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 10%"></div>
									</div>
									<%
										} else {
									%>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-danger"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 1%"></div>
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
                    		RetouchDAO retouchDAO = new RetouchDAO();
                    		ArrayList<Retouch> mList = retouchDAO.retouchSelect();
                    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    		String date = "";
                    		if(manager){
                    			if(mList != null){
                    				for(Retouch i : mList){
                    					if(i.getRetouch_date() != null){
                    						Date d = sdf.parse(i.getRetouch_date());
                    						date = sdf.format(d);
                    					}
                    					%>
                    					<li>
       		                                <div>
       		                                    <i class="fa fa-comment fa-fw"></i>"<%= i.getRetouch_specialNote() %>" 요청건이 있습니다.
       		                                    <span class="pull-right text-muted small"><%= date %></span>
       		                                </div>
       			                        </li>
       			                        <li class="divider"></li>
                    					<%
                    				}
                    			}
                    		}else{
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
                        		}                    			
                    		}
                    		%>
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
                                <li>
                                <% 	ManageReDate manageReDate = new ManageReDate(); 
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
                <div class="col-lg-12">
                    <h1 class="page-header">출퇴근관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
		<form  method="get" name="clickForm"> 
		<div class="col-lg-12">
            <div class="row">
				<%					
					boolean ck = IpLookup.IP_CK;
					if(ck != true){
						String work = "직출";
						String home = "직퇴";
				%>        
               <div class="col-lg-3 col-md-6">
                <button id="dworkC" onclick="dWorkClick();" style="border:0; background-color:transparent; width:100%">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-sun-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">직출</div>
                                    <div>Attendance!</div>
                                </div>
                            </div>
                    
                        </div>
                        <div class="panel-footer">
                        	<!-- View Details에 출근 시간 기재 -->
                            <span class="pull-left"><%= session.getAttribute("workTime") %></span>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </button> 
               </div>
               
               <div class="col-lg-3 col-md-6">
                	<button id="doffC" onclick="dOffclick();" style="border:0; background-color:transparent; width:100%">
              	 		 <div class="panel panel-green">
	                    
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-moon-o fa-5x"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge">직퇴</div>
	                                    <div>Leave work!</div>
	                                </div>
	                            </div>
	                        </div>
                            <div class="panel-footer">
                                <span class="pull-left"><%= session.getAttribute("offTime") %></span>
                                <div class="clearfix"></div>
                            </div>
                    	</div>
                	</button>
                </div>
                <%
					}
					else{
                %>
                <div class="col-lg-3 col-md-6">
               <button id="workC" onclick="workClick();" style="border:0; background-color:transparent; width:100%">
                   <div class="panel panel-primary">
                       <div class="panel-heading">
                           <div class="row">
                               <div class="col-xs-3">
                                   <i class="fa fa-sun-o fa-5x"></i>
                               </div>
                               <div class="col-xs-9 text-right">
                                   <div class="huge">출근</div>
                                   <div>Attendance!</div>
                               </div>
                           </div>
                   
                       </div>
                       <div class="panel-footer">
                       	<!-- View Details에 출근 시간 기재 -->
                           <span class="pull-left"><%= session.getAttribute("workTime") %> </span>
                           <div class="clearfix"></div>
                       </div>
                   </div>
               </button> 
              </div>
              
              <div class="col-lg-3 col-md-6">
               	<button id="offC" onclick="offClick();" style="border:0; background-color:transparent; width:100%">
             	 		 <div class="panel panel-green">
                    
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-moon-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">퇴근</div>
                                    <div>Leave work!</div>
                                </div>
                            </div>
                        </div>
                           <div class="panel-footer">
                               <span class="pull-left"><%= session.getAttribute("offTime") %></span>
                               <div class="clearfix"></div>
                           </div>
                   	</div>
               	</button>
               </div>
              
                <%
					}
                %>
                
                <div class="col-lg-3 col-md-6">
               		<button id="outC" onclick="outCilck();" style="border:0; background-color:transparent; width:100%">
	                    <div class="panel panel-yellow">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-car fa-5x"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge">외근</div>
	                                    <div>Working outside!</div>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="panel-footer">
	                            <span class="pull-left"><%= session.getAttribute("outTime") %></span>
	                            <div class="clearfix"></div>
	                        </div>
                   		 </div>
                    </button>
                </div>
                
                <div class="col-lg-3 col-md-6">
                	<button id="comC" onclick="comClick();" style="border:0; background-color:transparent; width:100%">
	                    <div class="panel panel-red">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-home fa-5x"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge">복귀</div>
	                                    <div>comeback!</div>
	                                </div>
	                            </div>
	                        </div>
                            <div class="panel-footer">
                                <span class="pull-left"><%= session.getAttribute("comback") %></span>
                                <div class="clearfix"></div>
                            </div>
	                    </div>
	            	</button>
                </div>
            </div>
            </div>
           </form>  
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 주간 근무시간
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<div class="row">
                                <div class="col-lg-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped">
                                            <thead>
                                                <tr>
                                                    <th><p class="text-center">날짜</p></th>
                                                    <th><p class="text-center">출근시간</p></th>
                                                    <th><p class="text-center">퇴근시간</p></th>
                                                    <th><p class="text-center">근무시간</p></th>
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
                                          	int paging = 0;
                                          	/* int total = saveDAO.totalRecord(); */
                                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                            Calendar cal = Calendar.getInstance();
                                         	SimpleDateFormat sDf = new SimpleDateFormat("HH:mm");
                                         	String start = "";
                                         	String end = "";
                                         	float time = 0.0f;
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
                                            
                                            for(int j=1; j<=7; j++){
                                            	start = "";
                                            	end = "";
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
                                            	cal.set(Calendar.DAY_OF_WEEK, j);
                                            	ArrayList<Save> list = saveDAO.mainSelect(session.getAttribute("user_id").toString(), df.format(cal.getTime()));
                                            	float hours = saveDAO.timeSet(session.getAttribute("user_id").toString(), df.format(cal.getTime()));
                                            	if(list != null){
                                            		for(Save i : list){
                                            			if(i.getSave_start_time() != null){
                                            				Date startDate = sDf.parse(i.getSave_start_time());
                                            				start = sDf.format(startDate);
                                            			}
                                            			if(i.getSave_end_time() != null){
                                            				Date endDate = sDf.parse(i.getSave_end_time());
                                                			end = sDf.format(endDate);
                                            			}
                                            			
                                            			time = hours;
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
                                                     	
                                            		}
                                            	}
                                               		%>                                            		
   	                                                <tr>
   	                                                    <td><p class="text-center"><%= df.format(cal.getTime()) %></p></td>
   	                                                    <td><p class="text-center"><%= start %></p></td>
   	                                                    <td><p class="text-center"><%= end %></p></td>
   	                                                    <td><p class="text-center"><input name="wHours" type="hidden" value=<%= time %>></input><%= time %>시간</p></td>
   	                                                    <td><p class="text-center"><%= dWork %></p></td>
   	                                                    <td><p class="text-center"><%= dOut %></p></td>
   	                                                    <td><p class="text-center"><%= holi %></p></td>
   	                                                    <td><p class="text-center"><%= half %></p></td>
   	                                                    <td><p class="text-center"><%= sick %></p></td>
   	                                                    <td><p class="text-center"><%= busi %></p></td>
   	                                                </tr>
                                               		<%
                                            }
                                            %>
                                            
                                            </tbody>
                                            <tfoot>
												<tr class="odd gradeX">
													<td class="text-center" colspan="2"><strong> 총 합계 </strong></td>
													<td class="text-center"></td>
													<td class="text-center"><strong id ="tr_sum"></strong></td>
													<!-- 총 횟수 -->
													<td class="text-center"><strong><%= idWork %></strong></td>
													<td class="text-center"><strong><%= idOut%></strong></td>
													<td class="text-center"><strong><%= iholi %></strong></td>
													<td class="text-center"><strong><%= ihalf%></strong></td>
													<td class="text-center"><strong><%= isick%></strong></td>
													<td class="text-center"><strong><%= ibusi%></strong></td>
												</tr>
											</tfoot>
                                        </table>
                                    </div>
                                    <div class="col-lg-12">
                                    	<div class="col-lg-6"></div>
                                    	<div class="col-lg-3">
						                 <!-- https://www.youtube.com/watch?v=9zRt9-j_Baw 모달 추가 -->
						                 	<a data-target="#modal" data-toggle="modal">
							                    <div class="panel panel-default">
							                        <div class="panel-heading">
							                            <div class="row">
							                                <div class="col-xs-1" >
							                                    <i class="fa fa-music fa-3x"></i>
							                                </div>
							                                <div class="col-xs-9 text-right">
							                                  	  <h4><strong>연차</strong></h4>
							                                </div>
							                            </div>
							                        </div>
							                    </div>
							            	</a>
							            </div>
							                
						                <div class="col-lg-3">
						                	<a data-target="#business" data-toggle="modal">
							                    <div class="panel panel-default">
							                        <div class="panel-heading">
							                            <div class="row">
							                                <div class="col-xs-1">
							                                    <i class="fa fa-credit-card fa-3x"></i>
							                                </div>
							                                <div class="col-xs-9 text-right">
							                                    <h4><strong>출장</strong></h4>
							                                </div>
							                            </div>
							                        </div>
							                    </div>
							            	</a>
						                </div>
					                </div>
                                    <!-- /.table-responsive -->
                                </div>
                                
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                </div> 
           
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../vendor/jquery/jquery.min.js"></script>
    
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="../vendor/raphael/raphael.min.js"></script>
    <script src="../vendor/morrisjs/morris.min.js"></script>
    <script src="../data/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>
    
    <!-- 연차 신청 모달창 -->
    <div class="row">
    	<div class="modal" id="modal" tabindex="-1">
    		<div class="modal-dialog">
    			<div class="modal-content">
    				<div class="modal-header">
    					연차 등록
    					<button class="close" data-dismiss="modal">&times;</button>
					</div><br/>
					<div class="modal-body">
						<form method="post" action="/namowebiz/HolidayServlet.do">
							<%
							float holiday_total = 0.0f;
							float holiday_use = 0.0f;
							UserDAO userDAO = new UserDAO();
							ArrayList<User> holist = userDAO.userSelect(session.getAttribute("user_id").toString());
							Calendar calendar = Calendar.getInstance();
							int year = calendar.get(Calendar.YEAR);
							int month = calendar.get(Calendar.MONTH);
							int day = calendar.get(Calendar.DAY_OF_MONTH);
							if(holist != null) {
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
						               $("#startDay").datepicker({ dateFormat: "yy-mm-dd", minDate : 0}).val()
						               $("#endDay").datepicker({ dateFormat: "yy-mm-dd", minDate : 0}).val()
						       	  });
								  $( function() {
								    var dateFormat = "yy-mm-dd",
								    	startDay = $( "#startDay" ).datepicker({
								        changeMonth: true,
								        numberOfMonths: 1,
								        minDate: new Date(2018, 5, 14)
								        })
								        .on( "change", function() {
								        	endDay.datepicker( "option", "minDate", getDate( this ) );
								        }),
								        endDay = $( "#endDay" ).datepicker({
								        changeMonth: true,
								        numberOfMonths: 1,
								        minDate: new Date(2018, 5, 14)
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
	
	 <!-- 츨장 신청 모달창 -->
    <div class="row">
    	<div class="modal" id="business" tabindex="-1">
    		<div class="modal-dialog">
    			<div class="modal-content">
    				<div class="modal-header">
    					출장 등록
    					<button class="close" data-dismiss="modal">&times;</button>
					</div><br/>
					<div class="modal-body">
						<form method="post" action="/namowebiz/BusinessTripServlet.do">
							
							<div class="form-group">
								<div class="col-lg-12"><p class="text-center">
									<input type="text" id="busyStart" name="busyStart" size="25%" placeholder="시작날짜를 입력하세요."></input>
									~
									<input type="text" id="busyEnd" name="busyEnd" size="25%" placeholder="종료날짜를 입력하세요."></input>

								</p></div>
								  <script src="../js/jquery-1.11.0.min.js"></script>
								  <script src="../js/bootstrap-datepicker.js"></script>
								  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
								  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
								  <script>
								  $(function() {
						               $("#busyStart").datepicker({ dateFormat: "yy-mm-dd", minDate : 0 }).val()
						               $("#busyEnd").datepicker({ dateFormat: "yy-mm-dd", minDate : 0 }).val()
						       	  });
								  $( function() {
								    var dateFormat = "yy-mm-dd",
								    	busyStart = $( "#busyStart" ).datepicker({
								        defaultDate: "+1w",
								        changeMonth: true,
								        numberOfMonths: 1
								        })
								        .on( "change", function() {
								        	busyStart.datepicker("option", "minDate", 0);
								        	busyEnd.datepicker( "option", "minDate", getDate( this ) );
								        }),
								        busyEnd = $( "#busyEnd" ).datepicker({
								        defaultDate: "+1w",
								        changeMonth: true,
								        numberOfMonths: 1
								      })
								      .on( "change", function() {
								    	  busyStart.datepicker( "option", "maxDate", getDate( this ) );
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

								<br /> <br /><br/>
								<div class="inputBox">
									<p class="text-center">
										<input type="text" id="reason" name="reason" placeholder="출장 내용을 상세히 작성하시오."style="text-align:center;width:80%;height:5%;"/>
									</p>
								</div><br/> <br />

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
    
    <script>
    $(document).ready(function() {
    	
    	var sum = 0;
    	$('input[name="wHours"]').each(function() {
    		sum+= parseFloat($(this).val());
    	})
    	$("#tr_sum").text((sum).toFixed(2)+"시간");
    	
   });
    </script> 
      
    <script>
	    function workClick(){
			var f=document.clickForm;
			f.action="/namowebiz/AttendanceClick.do?directW=출근";
			f.submit();
		}
    	function offClick(){
    		var f=document.clickForm;
    		f.action="/namowebiz/LeaveWorkClick.do?directO=퇴근";
    		f.submit();
    	}
    	function dWorkClick(){
    		var f=document.clickForm;
    		f.action="/namowebiz/AttendanceClick.do?directW=직출";
    		f.submit();
    	}
    	function dOffclick(){
    		var f=document.clickForm;
    		f.action="/namowebiz/LeaveWorkClick.do?directO=직퇴";
    		f.submit();
    	}
    	function outCilck(){
    		var f=document.clickForm;
    		f.action="/namowebiz/OutsideClick.do";
    		f.submit();
    	}
    	function comClick(){
    		var f=document.clickForm;
    		f.action="/namowebiz/CombackClick.do";
    		f.submit();
    	}
    </script>
    
    <script>
		var outBtn = "<%=(String)session.getAttribute("offTime")%>"
		if(outBtn != ""){
			$('#workC').attr('disabled',true);
			$('#offC').attr('disabled',true);
			$('#dworkC').attr('disabled',true);
			$('#doffC').attr('disabled',true);
			$('#outC').attr('disabled',true);
			$('#comC').attr('disabled',true);
		}
	</script>
    
    <script>

	function expireSession()
	{
	  window.location = "/namowebiz/index.html";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 600 %>);
	</script>
	  
</body>
</html>