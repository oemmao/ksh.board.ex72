<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String memberId = (String)session.getAttribute("userID");
	boolean login = memberId == null ? false : true;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to seonhwa's world</title>
<link rel="stylesheet" type="text/css" href="../css/index.css" >
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
-->
<script src="../js/jquery.min.js"></script>
<script src="../js/member.js"></script>
</head>
<body>
	<div class="all" >
		<jsp:include page="/layout/top.jsp" flush="false" />
		<div id="container">
			<section class="content" >
				<article class="blank" >
				<a id="self"><h2>회원가입</h2></a>
				<table id="tableInsertMember" class="alignCenter" >
					<tr>
						<th><label>아이디:</label></th>
						<td><input type="text" id="userID" name="userID" size="20">
							<br></td>
						<td><input type="button" id="duCheckID" value="ID중복확인">
							<input type="hidden" id="checkid" name="checkid" value=0></td>
					</tr>
					<tr>
						<th><label>패스워드:</label></th>
						<td><input type="password" id="pwd" name="pwd" size="20">
							<br></td>
					</tr>
					<tr>
						<th><label>이름:</label></th>
						<td><input type="text" id="name" name="name" size="20">
							<br></td>
					</tr>
					<tr>
						<th><label>연락처:</label></th>
						<td><input type="text" id="phone" name="phone" size="20">
							<br></td>
					</tr>
					<tr>
						<th><label>주소:</label></th>
						<td><input type="text" id="address" name="address" size="20">
							<br></td>
					</tr>
				</table>
				<div class="buttonspace">
					<input type="button" id="submit" value="회원가입 완료">
					<a href="../index.jsp"><input type="button" value="메인으로"></a>
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