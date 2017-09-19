<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String route = request.getContextPath(); //프로젝트 Path만 얻어옴 return /WebContent
%>
<%
	String memberId = (String)session.getAttribute("userID");
	boolean login = memberId == null ? false : true;
	if (!login) {	
		response.sendRedirect("/ksh.board.ex72/index.jsp");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to seonhwa's world</title>
<link rel="stylesheet" type="text/css" href="<%=route %>/css/index.css" >
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!--  <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
-->
<script src="../js/jquery.min.js"></script>
<script src="../js/member.js"></script>
<script src="../js/cal.js"></script>
</head>
<body>
	<div class="all" >
		<jsp:include page="/layout/top.jsp" flush="false" />
		<div id="container">
			<section class="content" >
				<article class="blank" > 
				<a id="self"><h2>계산기</h2></a>
				<table class="caltable" >
					<tr>
						<td colspan="3"><input type="text" id="number" style="width:95%; height:90%; text-align:right;" ></td>
						<td><input type=button class="clearCal" value="C" ></td>
					</tr>
					<tr>
						<td><input type=button class="num" value="7" id="cal7" name="cal7" ></td>
						<td><input type=button class="num" value="8" id="cal8" name="cal8" ></td>
						<td><input type=button class="num" value="9" id="cal9" name="cal9" ></td>
						<td><input type=button class="ops" value="+" id="operator1" name="operator1" ></td>
					</tr>	
					<tr>
						<td><input type=button class="num" value="4" id="cal4" name="cal4" ></td>
						<td><input type=button class="num" value="5" id="cal5" name="cal5" ></td>
						<td><input type=button class="num" value="6" id="cal6" name="cal6" ></td>
						<td><input type=button class="ops" value="-" id="operator2" name="operator2" ></td>
					</tr>	
					<tr>
						<td><input type=button class="num" value="1" id="cal1" name="cal1" ></td>
						<td><input type=button class="num" value="2" id="cal2" name="cal2" ></td>
						<td><input type=button class="num" value="3" id="cal3" name="cal3" ></td>
						<td><input type=button class="ops" value="*" id="operator3" name="operator3" ></td>
					</tr>	
					<tr>
						<td colspan="2"><input type=button class="num" value="0" id="cal0" name="cal0" style="width:100%;" ></td>
						<td><input type=button class="calresult" value="=" ></td>
						<td><input type=button class="ops" value="/" id="operator4" name="operator4" ></td>
					</tr>
			</table>
				</article>
			</section>
			<%
				if (login) {
			%>
				<jsp:include page="/layout/rightLogout.jsp" flush="false" />
			<%
				} else {
			%>
				<jsp:include page="/layout/right.jsp" flush="false" />
			<%
				}
			%>
		</div>
		<jsp:include page="/layout/bottom.jsp" flush="false" />
	</div>
</body>
</html>
