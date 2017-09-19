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
	BoardVO vo = (BoardVO) request.getAttribute("vo");
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
					<a id="self"><h2>게시판</h2></a> <b class="boardTitle">글수정</b> <br>
					<form name="writeform" action="#" onsubmit="return deleteSave()">
						<table class="boardWriting" align="center">
							<tr>
								<td width="70" align="center">이 름</td>
								<td align="left" width="330">
									<input type="text" size="10" maxlength="10" name="writer" value="<%=vo.getWriter()%>">
									<input type="hidden" name="num" value="<%=vo.getNum()%>"></td>
							</tr>
							<tr>
								<td width="70" align="center">제 목</td>
								<td align="left" width="330">
									<input type="text" size="40" maxlength="50" name="subject" value="<%=vo.getSubject()%>"></td>
							</tr>
							<tr>
								<td width="70" align="center">Email</td>
								<td align="left" width="330">
									<input type="text" size="40" maxlength="30" name="email" value="<%=vo.getEmail()%>"></td>
							</tr>
							<tr>
								<td width="70" align="center">내 용</td>
								<td align="left" width="330">
									<textarea name="content" rows="13" cols="75"><%=vo.getContent()%></textarea></td>
							</tr>
							<tr>
								<td width="70" align="center">비밀번호</td>
								<td align="left" width="330"><input type="password" size="8" maxlength="12" name="passwd"></td>
							</tr>
							<tr>
								<td colspan=2 align="center">
									<input type="button" id="textModify" value="글수정"> 
									<input type="reset" value="다시작성">
									<input type="button" value="목록보기" onclick="document.location.href='BoardController?cmd=getAllBoards&pageNum=<%=pageNum%>'">
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
