<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="vo.BoardVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
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
<%!
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%
	String pageNum = request.getParameter("pageNum");
	if (pageNum == null) {
		pageNum = "1";
	}

	BoardVO vo = (BoardVO) request.getAttribute("vo");
	int pageSize = vo.getPageSize();
	int currentPage = vo.getCurrentPage();
	int startRow = vo.getStartRow();
	int endRow = vo.getEndRow();
	int count = vo.getCount();
	int number = vo.getNumber();
	List<BoardVO> articleList = vo.getList();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to seonhwa's world</title>
<link rel="stylesheet" type="text/css" href="<%=route%>/css/index.css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!--  <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
-->
<script src="<%=route%>/js/jquery.min.js"></script>
<script src="<%=route%>/js/member.js"></script>
<script src="<%=route%>/js/board.js"></script>
</head>
<body>

	<div class="all">
		<jsp:include page="/layout/top.jsp" flush="false" />
		<div id="container">
			<section class="content">
				<article class="blank">
					<a id="self"> <h2>게시판</h2> </a>
					<b class="boardTitle">글목록(전체 글:<%=count%>)</b>
					<div align="right" id="writing" ><a href="<%=route%>/board/writeForm.jsp">글쓰기</a>
					</div>
					<%
						if (count == 0) {
					%>
					<table width="700" class="boardWriting" class="alignCenter" >
						<tr>
							<td align="center">게시판에 저장된 글이 없습니다.</td>
					</table>

					<%
						} else {
					%>
					<table border="1" class="boardWriting" class="alignCenter" align="center">
						<tr height="30">
							<td align="center" width="55"><b>번 호</b></td>
							<td align="center" width="250"><b>제 목</b></td>
							<td align="center" width="95"><b>작성자</b></td>
							<td align="center" width="150"><b>작성일</b></td>
							<td align="center" width="50"><b>조 회</b></td>
							<td align="center" width="100"><b>IP</b></td>
						</tr>
						<%
							for (int i = 0; i < articleList.size(); i++) {
									BoardVO article = (BoardVO) articleList.get(i);
						%>
						<tr height="30">
							<td align="center" width="50"><%=number--%></td>
							<td width="250" align="left">
								<%
									int wid = 0;
									if (article.getRe_level() > 0) {
										wid = 5 * (article.getRe_level());
								%>		<img src="<%=route%>/board/images/level.gif" width="<%=wid%>" height="16">
										<img src="<%=route%>/board/images/re.gif">
								<%
									} else {
								%> 
										<img src="<%=route%>/board/images/level.gif" width="<%=wid%>" height="16">
								<%
 									}
 								%> 
 								<a href="<%=route%>/BoardController?num=<%=article.getNum()%>&pageNum=<%=currentPage%>&cmd=getBoard">
 								<%=article.getSubject()%></a>
 								<%
 									if (article.getReadcount() >= 20) {
 								%> 
 										<img src="<%=route%>/board/images/hot.gif" border="0" height="16">
								<%
									}
								%>
							</td>
							<td align="center" width="100"><a href="mailto:<%=article.getEmail()%>"><%=article.getWriter()%></a></td>
							<td align="center" width="150"><%=sdf.format(article.getReg_date())%></td>
							<td align="center" width="50"><%=article.getReadcount()%></td>
							<td align="center" width="100"><%=article.getIp()%></td>
						</tr>
						<%
							}
						%>
					</table>
					<%
						}
					%>
					
					<div id="pageNumer" >
					<%
						if (count > 0) {
							int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);

							int startPage = (int) (currentPage / 10) * 10 + 1;
							int pageBlock = 10;
							int endPage = startPage + pageBlock - 1;
							if (endPage > pageCount)
								endPage = pageCount;

							if (startPage > 10) {
					%>
					
					<a href="list.jsp?pageNum=<%=startPage - 10%>">[이전]</a>
					<%
						}
							for (int i = startPage; i <= endPage; i++) {
					%>
					<a href="<%=route%>/BoardController?cmd=getAllBoards&pageNum=<%=i%>">[<%=i%>]</a>
					<%
						}
							if (endPage < pageCount) {
					%>
					<a href="list.jsp?pageNum=<%=startPage + 10%>">[다음]</a>
					
					<%
						}
						}
					%>
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
