<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String route = request.getContextPath(); //프로젝트 Path만 얻어옴 return /WebContent
%>
	<header class="head" id="top"> <a href="<%=route %>/index.jsp" target="_self">
		<img src="<%=route %>/image/back1.jpg" class="img" alt="상단이미지"></a>
		<span class="name"> SEONHWA'S WORLD </span>
		<span class="domain"> http://www.seonhwa.com/</span> </header>
	
	<nav class="menu">
	<ul class="array">
		<li><a href="<%=route %>/member/memberInsert.jsp"><b>회원가입</b></a></li>
		<li><a href="<%=route %>/homepage/introduction.jsp" class="isLogin" ><b>자기소개</b></a></li>
		<li><a href="<%=route %>/member/memberPage.jsp" class="isLogin"><b>회원정보관리</b></a></li>
		<li><a href="<%=route %>/cal/cal.jsp" class="isLogin"><b>계산기</b></a></li>
		<li><a href="<%=route %>/rank/rank.jsp" class="isLogin"><b>성적처리</b></a></li>
		<li><a href="<%=route %>/elevator/elevator.jsp" class="isLogin"><b>엘리베이터</b></a></li>
		<li><a href="<%=route %>/BoardController?cmd=getAllBoards" class="isLogin"><b>게시판</b></a></li>
	</ul>
	</nav>
