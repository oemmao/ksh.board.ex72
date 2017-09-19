<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String memberId = (String)session.getAttribute("userID");
	boolean login = memberId == null ? false : true;
	if (!login) {	
		response.sendRedirect("/ksh.cp.layout.cal.ex70/index.jsp");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to seonhwa's world</title>
<link rel="stylesheet" type="text/css" href="../css/index.css" >
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- <script src="../js/jquery.min.js"></script> -->
<script src="../js/member.js"></script>
</head>
<body>
	<div class="all" >
		<jsp:include page="/layout/top.jsp" flush="false" />
		<div id="container">
			<section class="content" > 
			<article class="blank"> <a id="self"><h2>엘리베이터</h2></a>
			<legend>기본데이터 입력</legend>
				<table class="alignCenter">
					<tr>
						<th><label>TOP_FLOORS</label></th>
						<td><input type="text" placeholder="최고층 입력 ex)10층"
							name="topfloor" id="topfloor" size="20"></td>
					</tr>
					<tr>
						<th><label>BOTTOM_FLOORS</label></th>
						<td><input type="text" placeholder="최하층 입력 ex)1층"
							name="bottomfloor" id="bottomfloor" size="20"></td>
					</tr>
					<tr>
						<th><label>CURRENT_FLOORS</label></th>
						<td><input type="text" placeholder="현재층 입력 ex)1층"
							name="currentfloor" id="currentfloor" size="20"></td>
					</tr>
				</table>
				<br>
			<legend>사용자 데이터 입력</legend>
				<table class="alignCenter">
					<tr>
						<th><label>Desired Floor</label></th>
						<td><input type="text" placeholder="원하는층 입력 ex)5층"
							name="desiredfloor" id="desiredfloor" size="20"></td>
					</tr>
				</table>
				<div class="buttonspace">
					<div>
						<input type="button" class="resultbutton" id="rankResult" value="결과보기">
					</div>
					<input type="button" class="buttonstyle" id="sampleData" value="샘플데이터입력">
					<input type="button" class="buttonstyle" id="clearRank" value="초기화">
				</div>
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
