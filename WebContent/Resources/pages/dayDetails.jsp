<%@page import="java.util.Calendar"%>
<%@page import="com.namowebiz.retouch.Result"%>
<%@page import="com.namowebiz.retouch.ResultDAO"%>
<%@page import="com.namowebiz.retouch.RetouchDAO"%>
<%@page import="com.namowebiz.retouch.Retouch"%>
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
    
<script type="text/javascript">
	$(function(){
	    //전체선택 체크박스 클릭
		$("#all").click(function(){
			//만약 전체 선택 체크박스가 체크된상태일경우
			if($("#all").prop("checked")) {
				//해당화면에 전체 checkbox들을 체크해준다
				$("input[type=checkbox]").prop("checked",true);
			// 전체선택 체크박스가 해제된 경우
			} else {
				//해당화면에 모든 checkbox들의 체크를해제시킨다.
				$("input[type=checkbox]").prop("checked",false);
			}
		})
	})
</script>

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
					
						<h1 class="page-header">개인 통계 관리</h1>
						<div style="float: left;">
							<input type="text" id="startDay" name="startDay" size="30%" placeholder="찾을 시작날짜를 입력하세요." value="<%=InfoServlet.startDate%>"></input>
							~
							<input type="text" id="endDay" name="endDay" size="30%" placeholder="찾을 종료날짜를 입력하세요." value="<%=InfoServlet.endDate%>"></input>&nbsp;
						</div>
						
					      <script src="../js/jquery-1.11.0.min.js"></script>	
						  <script src="../js/bootstrap-datepicker.js"></script>
						  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
						  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
						  <script>
						  $(function() {
				               $("#startDay").datepicker({ dateFormat: "yy-mm-dd"}).val()
				               $("#endDay").datepicker({ dateFormat: "yy-mm-dd" }).val()
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
						<div class="customButton" style="float: left;">
							<input type="button" class="btn btn-info" value="통계" onclick="info_list();"/>
						</div><br></br>
						
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<%=session.getAttribute("user_id")%>님의 일별 세부사항
									</div>
									<!-- /.panel-heading -->
									<div class="panel-body">
										<table style="width: 100%"
											class="table table-striped table-bordered table-hover"
											id="data-example">
											<thead>
												<tr>
													<th><input type="checkbox" id="all" name="all"
														style="width: 20px; height: 20px" /></th>
													<th><p class="text-center">날짜</p></th>
													<th><p class="text-center">출근시간</p></th>
													<th><p class="text-center">퇴근시간</p></th>
													<th><p class="text-center">외근시간</p></th>
													<th><p class="text-center">총 근무 시간</p></th>
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
													String start = InfoServlet.startDate;
													String end = InfoServlet.endDate;
													SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
													SimpleDateFormat sDf = new SimpleDateFormat("HH:mm");
													Calendar cal = Calendar.getInstance();
													int max = saveDAO.dayDiff(start, end)+1;
		                                         	int year = cal.get(Calendar.YEAR);
		                                            int month = cal.get(Calendar.MONTH);
		                                            int num = 0;
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
		                                         	Date dStart = df.parse(start);
	                                                cal.setTime(dStart);
	                                                
		                                            for(int j=1; j<=max; j++){
		                                            	num = 0;
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
		                                                
		                                            	ArrayList<Save> list = saveDAO.dayDetailSelect(id, df.format(cal.getTime()));
		                                            	float hours = saveDAO.timeSet(id, df.format(cal.getTime()));
		                                            	if(list != null){
		                                            		for(Save i : list){
		                                            			if(i.getSave_number() != 0){
		                                            				num = i.getSave_number();
		                                            			}
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
		                                            			if(i.getSave_working_hours() != 0){
		                                            				time = hours;
		                                            				total+= time;
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
		                                                     	
		                                            		}
		                                            	}
		                                               		%>                                            		
		   	                                                <tr>
		   	                                                	<td><input type="checkbox" id="check" name="check" value="<%=num%>" style="width: 20px; height: 20px" /></td>
		   	                                                    <td><p class="text-center"><%= df.format(cal.getTime()) %></p></td>
		   	                                                    <td><p class="text-center"><input name="date" type="hidden" value=<%=sDate%>></input><%= sDate %></p></td>
		   	                                                    <td><p class="text-center"><%= eDate %></p></td>
		   	                                                    <td><p class="text-center"><%= oDate %></p></td>
		   	                                                    <td><p class="text-center"><input name="wHours" type="hidden" value=<%= time %>></input><%= time %>시간</p></td>
		   	                                                    <td><p class="text-center"><%= dWork %></p></td>
		   	                                                    <td><p class="text-center"><%= dOut %></p></td>
		   	                                                    <td><p class="text-center"><%= holi %></p></td>
		   	                                                    <td><p class="text-center"><%= half %></p></td>
		   	                                                    <td><p class="text-center"><%= sick %></p></td>
		   	                                                    <td><p class="text-center"><%= busi %></p></td>
		   	                                                </tr>
		                                               		<%
		                                               	cal.add(Calendar.DATE, 1);
		                                            }
		                                            %>
		                                            
		                                            </tbody>
		                                            <tfoot>
														<tr class="odd gradeX">
															<td class="text-center" colspan="3"><strong> 총 합계 </strong></td>
															<td class="text-center"></td>
															<td class="text-center"></td>
															<td class="text-center"><strong><%= total %>시간</strong></td>
															<td class="text-center"><strong><%= idWork %></strong></td>
															<td class="text-center"><strong><%= idOut%></strong></td>
															<td class="text-center"><strong><%= iholi %></strong></td>
															<td class="text-center"><strong><%= ihalf%></strong></td>
															<td class="text-center"><strong><%= isick%></strong></td>
															<td class="text-center"><strong><%= ibusi%></strong></td>
														</tr>
											</tfoot>
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
		
			<div class="row">
           		<div class="col-lg-12">
		          		<div class="col-lg-4"><input type="button" class="btn btn-default btn-lg btn-block" value="정정요청" onclick="Modal();"></input></div>
		          	 	<div class="col-lg-4"><% FindReDateServlet dateServlet = new FindReDateServlet(); %><a class="btn btn-default btn-lg btn-block" href="requestItem.jsp">요청 건 보기</a></div>
		          	 	
       				<form name="frm" id="frm" method="post">
		           		<input type="hidden" name="excel_data"></input> 
                        <div class="col-lg-4"><input type="button" class="btn btn-default btn-lg btn-block" value="엑셀 저장" onclick="excel();"></input></div>
            		</form>
            	</div>
        	</div>
    </div>
  	
 
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
    $(document).ready(function() {
    	
    	var sum = 0;
    	$('input[name="wHours"]').each(function() {
    		sum+= parseFloat($(this).val());
    	})
    	$("#tr_sum").text((sum).toFixed(2)+"시간");
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
    	function info_list(){
    		var f=document.info;
    		f.action="/namowebiz/InfoServlet.do";
    		f.submit();
    	}
    	function retouch(){
    		var f=document.info;
    		f.action="./dayDetailsModal.jsp";
    		f.submit();
    	}
    	function Modal(){
    		if($('input:checkbox[id="check"]:checked').length == 0){
    			alert("선택을 먼저 해주세요.");
    		}/* else if($('input[name="date"]').val() == null){
       			alert("정정 보낼 데이터가 없습니다. 다시 선택해주세요.");
    		} */else{
    			retouch();
    		}
    	}
    </script>
    <script>

	function expireSession()
	{
	  window.location = "/namowebiz/index.html";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 600 %>);
	</script>
   
</div>
</body>
</html>