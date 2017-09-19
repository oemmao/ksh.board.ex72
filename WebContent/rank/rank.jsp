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
<script src="../js/rank.js"></script>
</head>
<body>
	<div class="all" >
		<jsp:include page="/layout/top.jsp" flush="false" />
		<div id="container">
			<section class="content" >
				<article class="blank" > 
				<a id="self"><h2>성적처리</h2></a>
					<table class="rankarray" class="alignCenter">
						<tr>
							<th class="tharray"><label>이름/과목</label></th>
							<th class="tharray"><label>국어</label></th>
							<th class="tharray"><label>영어</label></th>
							<th class="tharray"><label>수학</label></th>
							<th class="tharray"><label>합계</label></th>
							<th class="tharray"><label>평균</label></th>
							<th class="tharray"><label>석차</label></th>
							<th class="tharray"><label>비고</label></th>
						</tr>
						<tr>
							<td><input type="text" placeholder="학생1" name="student1" id="student1" size="5"></td>
							<td><input type="text" placeholder="점수" name="score1" id="score1" size="3"></td>
							<td><input type="text" placeholder="점수" name="score2" id="score2" size="3"></td>
							<td><input type="text" placeholder="점수" name="score3" id="score3" size="3"></td>
							<td id="sum1"></td>
							<td id="avg1"></td>
							<td id="rank1"></td>
							<td id="bigo1"></td>
						</tr>
						<tr>
							<td><input type="text" placeholder="학생2" name="student2" id="student2" size="5"></td>
							<td><input type="text" placeholder="점수" name="score4" id="score4" size="3"></td>
							<td><input type="text" placeholder="점수" name="score5" id="score5" size="3"></td>
							<td><input type="text" placeholder="점수" name="score6" id="score6" size="3"></td>
							<td id="sum2"></td>
							<td id="avg2"></td>
							<td id="rank2"></td>
							<td id="bigo2"></td>
						</tr>
						<tr>
							<td><input type="text" placeholder="학생3" name=student3 id=student3 size="5"></td>
							<td><input type="text" placeholder="점수" name=score7 id=score7 size="3"></td>
							<td><input type="text" placeholder="점수" name=score8 id=score8 size="3"></td>
							<td><input type="text" placeholder="점수" name=score9 id=score9 size="3"></td>
							<td id="sum3"></td>
							<td id="avg3"></td>
							<td id="rank3"></td>
							<td id="bigo3"></td>
						</tr>
						<tr>
							<td><input type="text" placeholder="학생4" name=student4 id=student4 size="5"></td>
							<td><input type="text" placeholder="점수" name=score10 id=score10 size="3"></td>
							<td><input type="text" placeholder="점수" name=score11 id=score11 size="3"></td>
							<td><input type="text" placeholder="점수" name=score12 id=score12 size="3"></td>
							<td id="sum4"></td>
							<td id="avg4"></td>
							<td id="rank4"></td>
							<td id="bigo4"></td>
						</tr>
						<tr>
							<td><input type="text" placeholder="학생5" name=student5 id=student5 size="5"></td>
							<td><input type="text" placeholder="점수" name=score13 id=score13 size="3"></td>
							<td><input type="text" placeholder="점수" name=score14 id=score14 size="3"></td>
							<td><input type="text" placeholder="점수" name=score15 id=score15 size="3"></td>
							<td id="sum5"></td>
							<td id="avg5"></td>
							<td id="rank5"></td>
							<td id="bigo5"></td>
						</tr>
						<tr>
							<th class="tharray"><label>총점</label></th>
							<td id="subtotal1"></td>
							<td id="subtotal2"></td>
							<td id="subtotal3"></td>
							<td colspan="4"></td>
						</tr>
					</table>
					<div class="buttonspace">
					<div>
						<input type="button" class="resultbutton" id="rankResult" name="rankResult" value="결과보기">
					</div>
					<input type="button" class="buttonstyle" id="sampleData" value="샘플데이터입력">
					<input type="button" class="buttonstyle" id="clearRank" value="초기화">
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
	