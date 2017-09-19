<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vo.BoardVO"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	String route = request.getContextPath();
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
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	BoardVO vo = (BoardVO) request.getAttribute("vo");

	int num = vo.getNum();
	String pageNum = vo.getPageNum();
	int ref = vo.getRef();
	int re_step = vo.getRe_step();
	int re_level = vo.getRe_level();
%>
<html>
<head>
<title>게시판</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to seonhwa's world</title>
<link rel="stylesheet" type="text/css" href="<%=route%>/css/index.css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!--  <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
-->
<script src="<%=route %>/js/jquery.min.js"></script>
<script src="<%=route %>/js/member.js"></script>
<script src="<%=route %>/js/board.js"></script>
</head>

<body>
	<div class="all">
		<jsp:include page="/layout/top.jsp" flush="false" />
		<div id="container">
			<section class="content">
				<article class="blank">
					<a id="self"><h2>게시판</h2></a> <b class="boardTitle">글내용 보기</b>
						<form>
							<table class="boardWriting" align="center">
								<tr height="30">
									<td align="center" width="125">글번호</td>
									<td align="center" width="125" align="center"><%=vo.getNum()%></td>
									<td align="center" width="125">조회수</td>
									<td align="center" width="125" align="center"><%=vo.getReadcount()%></td>
								</tr>
								<tr height="30">
									<td align="center" width="125">작성자</td>
									<td align="center" width="125" align="center"><%=vo.getWriter()%></td>
									<td align="center" width="125">작성일</td>
									<td align="center" width="125" align="center"><%=sdf.format(vo.getReg_date())%></td>
								</tr>
								<tr height="30">
									<td align="center" width="125">글제목</td>
									<td align="center" width="375" align="center" colspan="3"> <%=vo.getSubject()%></td>
								</tr>
								<tr>
									<td align="center" width="125">글내용</td>
									<td align="left" width="375" colspan="3"><pre><%=vo.getContent()%></pre></td>
								</tr>
								<tr height="30">
									<td colspan="4" align="center">
										<input type="button" value="글수정"
										onclick="document.location.href='<%=route%>/BoardController?cmd=updateBoard&num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
										 
										<input type="button" value="글삭제"
										onclick="document.location.href='<%=route%>/board/deleteForm.jsp?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
										
										<input type="button" value="답글쓰기"
										onclick="document.location.href='<%=route%>/board/writeForm.jsp?num=<%=num%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>'">
										
										<input type="button" value="글목록"
										onclick="document.location.href='BoardController?pageNum=<%=pageNum%>&cmd=getAllBoards'">
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