<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String route = request.getContextPath(); //프로젝트 Path만 얻어옴 return /WebContent
%>

	<aside class="ad">
	<table class="alignCenter">
		<tr>
			<td><input type="text" id="userID1" name="userID1" placeholder="아이디 입력" size="15"></td>
		</tr>
		<tr>
			<td><input type="password" id="pwd1" name="pwd1" placeholder="비밀번호 입력" size="15"></td>
		</tr>
	</table>
	<div class="buttonspace">
		<input type="button" id="loginsubmit" name="loginsubmit" value="로그인">
		<a href="<%=route %>/member/memberInsert.jsp"><input type="button" value="회원가입"></a>
	</div>
	<p class="line">Myhome banner</p>
	<a href="index.jsp" target="_blank"><img
		src="<%=route %>/image/shworld.jpg" width="180" alt="홈페이지로이동"></a> </aside>
