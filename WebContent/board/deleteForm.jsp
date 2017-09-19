<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vo.BoardVO"%>

<%
	String route = request.getContextPath(); //프로젝트 Path만 얻어옴 return /WebContent
%>
<%
	String memberId = (String) session.getAttribute("userID");
	boolean login = memberId == null ? false : true;
	if (!login) {
		response.sendRedirect("/ksh.board.ex72/index.jsp");
		return;
	}
%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to seonhwa's world</title>
<link rel="stylesheet" type="text/css" href="<%=route%>/css/index.css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!--  <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
-->
<script src="<%=route %>/js/jquery.min.js"></script>
<script src="<%=route %>/js/board.js"></script>
<script src="<%=route %>/js/member.js"></script>
</head>

<body>
	<div class="all">
		<jsp:include page="/layout/top.jsp" flush="false" />
		<div id="container">
			<section class="content">
				<article class="blank">
					<a id="self"><h2>게시판</h2></a> <b class="boardTitle">글삭제</b> <br>
					<form method="POST" name="delForm" action="#">
						<table align="center" class="boardWriting">
							<tr height="30">
								<td align=center><b>비밀번호를 입력해 주세요.</b></td>
							</tr>
							<tr height="30">
								<td align=center>비밀번호 : <input type="password" name="passwd" id="passwd" size="8" maxlength="12"> 
								<input type="hidden" name="num" value="<%=num%>"></td>
							</tr>
							<tr height="30">
								<td align=center><input type="button" id="textDelete" value="글삭제">
								<input type="button" value="글목록"
									onclick="document.location.href='<%=route%>/BoardController?cmd=getAllBoards&pageNum=<%=pageNum%>'">
								</td>
							</tr>
						</table>
					</form>
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