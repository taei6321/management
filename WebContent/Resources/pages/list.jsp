<%@page import="java.sql.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.namowebiz.retouch.Result"%>
<%@page import="com.namowebiz.retouch.ResultDAO"%>
<%@page import="com.namowebiz.retouch.Retouch"%>
<%@page import="com.namowebiz.retouch.RetouchDAO"%>
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
            
    <title>직원리스트</title>

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
                                <li role="presentation" class="active">
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
                <div class="col-lg-12">
                    <h1 class="page-header"></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Namowebiz 직원리스트 
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table style="width:100%; margin:auto; text-align:center" class="table table-striped table-bordered table-hover" id="data-example">
                                <thead>
                                    <tr>
                                    	<th style="text-align:center">관리자</th>
                                        <th style="text-align:center">사번</th>
                                        <th style="text-align:center">이름</th>
                                        <th style="text-align:center">연락처</th>
                                        <th style="text-align:center">부서</th>
                                        <th style="text-align:center">직급</th>
                                        <th style="text-align:center">입사일</th>
                                        <th style="text-align:center">퇴사일</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                UserDAO userDAO = new UserDAO();
                                
                                ArrayList<User> list = userDAO.userSelect();
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                
                                if(list != null){
                                	for(User i : list){
                                		
                            			java.util.Date produce = df.parse(i.getUser_produce_date());
                            			java.util.Date update = null;
                            			String sUpdate = "";
                            			if(i.getUser_resignation_date() != null){
                                			update = df.parse(i.getUser_resignation_date());
                                			sUpdate = df.format(update);
                            			}
                            			
                                		if(i.getUser_manager() == true){
                                		%>
                                		<tr class="odd gradeX">
	                                        <td><i class="fa fa-maxcdn fa-fw"></i><strong>관리자</strong></td>
	                                        <td><a href=detailed.jsp?user_id=<%= i.getUser_id() %>><%= i.getUser_id() %></a></td>
	                                        <td><%= i.getUser_name() %></td>
	                                        <td><%= i.getUser_phone() %></td>
	                                        <td><%= i.getUser_part() %></td>
	                                        <td><%= i.getUser_rank() %></td>
	                                        <td><%= df.format(produce) %></td>
	                                        <td><%= sUpdate %></td>
                                   		</tr>	
                                		<%
                                		}
                                		else{
                                			if(i.getUser_resignation()){
                                				%>
                                        		<tr class="odd gradeX danger">
                                        			<td></td>
        	                                        <td><a href=detailed.jsp?user_id=<%= i.getUser_id() %>><%= i.getUser_id() %></a></td>
        	                                        <td><%= i.getUser_name() %></td>
        	                                        <td><%= i.getUser_phone() %></td>
        	                                        <td><%= i.getUser_part() %></td>
        	                                        <td><%= i.getUser_rank() %></td>
        	                                        <td><%= df.format(produce) %></td>
        	                                        <td><%= sUpdate %></td>
                                           		</tr>
                                        		<%
                                			}else{
                                				%>
                                        		<tr class="odd gradeX">
                                        			<td></td>
        	                                        <td><a href=detailed.jsp?user_id=<%= i.getUser_id() %>><%= i.getUser_id() %></a></td>
        	                                        <td><%= i.getUser_name() %></td>
        	                                        <td><%= i.getUser_phone() %></td>
        	                                        <td><%= i.getUser_part() %></td>
        	                                        <td><%= i.getUser_rank() %></td>
        	                                        <td><%= df.format(produce) %></td>
        	                                        <td><%= sUpdate %></td>
                                           		</tr>
                                        		<%
                                			}
                                		}
                                	}
                                }
                                %>                                    
                                </tbody>
                            </table><br></br>
                            <!-- /.table-responsive -->
                            <% session.setAttribute("id", ""); %>
                            <div class="col-lg-4"><a class="btn btn-default btn-lg btn-block" href="./userInsert.jsp">인원 추가</a></div>
                            <div class="col-lg-4"><a class="btn btn-default btn-lg btn-block" data-target="#mail" data-toggle="modal" >메일</a></div>
        					<form name="frm" id="frm" method="post">
        						<input type="hidden" name="excel_data"></input> 
                            	<div class="col-lg-4"><input type="button" class="btn btn-default btn-lg btn-block" value="엑셀 저장" onclick="excel();"></input></div>
            				</form>
           				</div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
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
 		
	<!-- 메일 전송 모달창 -->
    <div class="row">
    	<div class="modal" id="mail" tabindex="-1">
    		<div class="modal-dialog">
    			<div class="modal-content">
    				<div class="modal-header">
    					메일 전송
    					<button class="close" data-dismiss="modal">&times;</button>
					</div><br/>
					<div class="modal-body">
						<form method="post" action="/namowebiz/MailSend.do">
							
							<div class="form-group">
								<div class="col-lg-12">
									<select class="form-control" name="uId" style="text-align-last:center; float:left">
										<option value="">이름(사번)</option>
										<% 	userDAO = new UserDAO();
											ArrayList<User> u_list = userDAO.userSelect();
											if(u_list != null){
												for(User i : u_list){
												%>
										<option value="<%=i.getUser_id()%>"><%= i.getUser_name()%>(<%=i.getUser_id()%>)</option>
										<%}} %>
									</select>
								
								</div>
								<br></br><br></br>

								<p class="text-center">
									<input class="btn btn-outline btn-primary btn-lg btn-block"
										type="submit" value="전송" />
								</p>
							</div>
						
						</form>
				</div>
    			</div>
    		</div>
   		 </div>
    </div>

    <!-- Page-Level Demo Scripts -  - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#data-example').DataTable({
            responsive: true
        });
    });
    </script>
     <script>
    	 function excel(){
    	 document.frm.action = "./saveExcel.jsp";
    	 document.frm.excel_data.value = document.getElementById("data-example").outerHTML;
    	 document.frm.submit();
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