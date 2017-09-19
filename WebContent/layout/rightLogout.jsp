<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String route = request.getContextPath(); //프로젝트 Path만 얻어옴 return /WebContent
	String memberId = (String)session.getAttribute("userID");
	boolean login = memberId == null ? false : true;
%>

	<aside class="ad">
	<table class="alignCenter">
		<tr>
			<td><%= session.getAttribute("userName")%>님,<br>로그인하였습니다.</td>
		</tr>
	</table>
	<div class="buttonspace">
		<input type="button" id="logoutsubmit" name="logoutsubmit" value="로그아웃">
		<a href="<%=route %>/index.jsp"><input type="button" value="메인으로"></a>
	</div>
	<p class="line">Myhome banner</p>
	<a href="index.jsp" target="_blank"><img
		src="<%=route %>/image/shworld.jpg" width="180" alt="홈페이지로이동"></a> </aside>
