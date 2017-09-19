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
<html>
<head>
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

<%
	BoardVO vo = (BoardVO) request.getAttribute("vo");	

	int num=0,ref=1,re_step=0,re_level=0;
	try{  
		if (request.getParameter("num")!=null) {
			num=Integer.parseInt(request.getParameter("num"));
			ref=Integer.parseInt(request.getParameter("ref"));
			re_step=Integer.parseInt(request.getParameter("re_step"));
			re_level=Integer.parseInt(request.getParameter("re_level"));
		}
%>

<body>
	<div class="all">
		<jsp:include page="/layout/top.jsp" flush="false" />
		<div id="container">
			<section class="content">
				<article class="blank">
					<a id="self"><h2>게시판</h2></a>
					<b class="boardTitle">글쓰기</b> <br>
					<form method="post" name="writeform"
						action="<%=route %>/BoardController?cmd=insertBoard"
						onsubmit="return writeSave()">
						<input type="hidden" name="num" value="<%=num%>"> 
						<input type="hidden" name="ref" value="<%=ref%>">
						<input type="hidden" name="re_step" value="<%=re_step%>">
						<input type="hidden" name="re_level" value="<%=re_level%>">

						<table class="boardWriting" class="alignCenter" align="center">
							<tr>
								<td align="right" colspan="2">
								<a href="<%=route %>/BoardController?cmd=getAllBoards">글목록</a></td>
							</tr>
							<tr>
								<td width="70" align="center"><b>이 름</b></td>
								<td width="330"><input type="text" maxlength="10" name="writer"></td>
							</tr>
							<tr>
								<td width="70" align="center"><b>제 목</b></td>
								<td width="330">
								<%
									if(request.getParameter("num")==null){
								%> 
								<input type="text" size="40" maxlength="50" name="subject">
								</td>
								<%
									} else {
								%>
								<input type="text" maxlength="50" name="subject" value="[답변]">
								</td>
								<%
									}
								%>
							</tr>
							<tr>
								<td width="70" align="center"><b>Email</b></td>
								<td width="330"><input type="text" maxlength="30" name="email"></td>
							</tr>
							<tr>
								<td width="70" align="center"><b>내 용</b></td>
								<td width="330"><textarea name="content" rows="13" cols="70"></textarea></td>
							</tr>
							<tr>
								<td width="70" align="center"><b>비밀번호</b></td>
								<td width="330"><input type="password" maxlength="12" name="passwd"></td>
							</tr>
							<tr>
								<td colspan=2 align="center"><input type="submit" value="글쓰기"> <input type="reset" value="다시작성">
									<input type="button" value="목록보기" OnClick="window.location='<%=route%>/BoardController?cmd=getAllBoards'">
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
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
</body>
</html>

