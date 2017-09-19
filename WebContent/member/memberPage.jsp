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
				<a id="self"><h2>회원정보관리</h2></a>
				<table class="memberPageTable">
					<tr>
						<td><input type="button" id="selectAllMemberForm" value="목록"></td>
						<td><input type="button" id="selectMemberForm" value="조회"></td>
						<td><input type="button" id="updateMemberForm" value="수정"></td>
						<td><input type="button" id="deleteMemberForm" value="삭제"></td>
					</tr>
				</table>	
				</article>
				<article>
					<div class="selectAll" id="selectAllMember" >
						<div class="buttonspace" >
							<input type="button" id="selectAllList" value="검색">
						</div>	
						<div class="slectAllPrint" id="result" ></div>
						<br>
					</div>	
				</article>
				<article>
					<div class="selectAll" id="selectMember">
					<table id="tableInsertMember" class="alignCenter" >
						<tr>
							<th>아이디:</th>
							<td><input type="text" id="userID2" name="userID2" size="20">
								<br></td>
							<td><input type="button" id="selectList" name="selectList"
								value="검색"></td>
						</tr>
					</table>
					<table class="resultTable">
					<tr>
						<td><p id="result1" class="resultTableSize" ></p></td>
						<td><p id="result2" class="resultTableSize" ></p></td>
						<td><p id="result3" class="resultTableSize" ></p></td>
						<td><p id="result4" class="resultTableSize" ></p></td>
					</tr>
					<tr>
						<td><p id="result5" class="resultTableSize" ></p></td>
						<td><p id="result6" class="resultTableSize" ></p></td>
						<td><p id="result7" class="resultTableSize" ></p></td>
						<td><p id="result8" class="resultTableSize" ></p></td>
					</tr>
				</table>
				<br>
				</div>
				</article>
				<article>
					<div class="selectAll" id="updateMember" >
					<table id="tableInsertMember" class="alignCenter" >
						<tr>
							<th>아이디:</th>
							<td><input type="text" id="userID" name="userID"
								value="<%= memberId %>" size="20" readonly> <br></td>
						</tr>
						<tr>
							<th>패스워드:</th>
							<td><input type="password" id="pwd" name="pwd" size="20">
								<br></td>
						</tr>
						<tr>
							<th>이름:</th>
							<td><input type="text" id="name" name="name" size="20">
								<br></td>
						</tr>
						<tr>
							<th>연락처:</th>
							<td><input type="text" id="phone" name="phone" size="20">
								<br></td>
						</tr>
						<tr>
							<th>주소:</th>
							<td><input type="text" id="address" name="address" size="20">
								<br></td>
						</tr>
					</table>
					<div class="buttonspace" >
					<input type="button" id="dataUpdate" value="수정하기">
					</div>
					</div>	
				</article>
				<article>
					<div class="selectAll" id="deleteMember" >
					<table id="tableInsertMember" class="alignCenter" >
						<tr>
							<th>아이디:</th>
							<td><input type="text" id="userID" name="userID" size="20"> <br> </td>
						</tr>
						<tr>
							<th>패스워드:</th>
							<td><input type="password" id="pwd2" name="pwd2" size="20"> <br> </td>
						</tr>
					</table>
					<div class="buttonspace" >
					<input type="button" id="dataDelete" value="탈퇴하기">
					</div>
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