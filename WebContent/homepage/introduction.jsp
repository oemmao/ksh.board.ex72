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
			<a id="self"><h2>자기소개</h2></a>
			<table class="table">
				<caption>이력서</caption>
				<tr>
					<th rowspan="5"><img src="../image/ex1.jpg" width="130" height="150" alt="고양이이미지"></th>
					<th>성명</th>
					<td colspan="5"><strong>김선화</strong></td>	
				</tr>
				<tr>
					<th width="80">주민번호</th>
					<td colspan="5">87021-XXXXXXX</td>
				</tr>	
				<tr>
					<th>생년월일</th>
					<td colspan="5">1987/01/21</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td colspan="2" width="150">010-XXXX-XXXX</td>
					<th>E-mail</th>
					<td>seonhwa121@naver.com</td>
				</tr>
				<tr>
					<th>주소</th>
					<td colspan="5" >대한민국 경기도 부천</td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<th colspan="3">학&nbsp력&nbsp사&nbsp항</th>
				</tr>
				<tr>	
					<th width="150">기&nbsp&nbsp간</th>
					<th width="300">학&nbsp교&nbsp명</th>
					<th>학&nbsp&nbsp과</th>
				</tr>
				<tr>
					<td>00.00~00.00</td>
					<td>OO대학교</td>
					<td>OOOO학과</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>	
			</table>
			<table class="table">
				<tr>
					<th colspan="3">경&nbsp력&nbsp사&nbsp항</th>
				</tr>
				<tr>	
					<th width="150">기&nbsp&nbsp간</th>
					<th width="200">근&nbsp무&nbsp처</th>
					<th>담&nbsp당&nbsp업&nbsp무</th>
				</tr>
				<tr>
					<td>15.03~16.04</td>
					<td>넥슨가고싶다</td>
					<td>게임운영</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>	
			</table>
			<table class="table">
				<tr>
					<th colspan="2">취미 및 특기</th>
				</tr>
				<tr>	
					<th width="150">취&nbsp&nbsp미</th>
					<td>게임, 영화</td>
				</tr>
				<tr>
					<th>특&nbsp&nbsp기</th>
					<td>잠자기</td>
				</tr>	
			</table>
	
			<p class="link" ><a href="#top" >[메인으로]</a></p>
			</article>
			<article class="blank" > 
			<a id="food"><h2>음식좋아</h2></a>
				<ol class="list" >
					<li>좋아하는 음식은? 만두! 고기! 라면! 밀가루음식! (못먹는거 빼고 다!)</li>
					<li>최근에 먹은 음식은? 뼈해장국! 샐러드! </li>
					<li>맛있게 먹은 음식은? 샤오롱빠오!</li>
					<li>제일 맛이 없던 음식은? 없음! (기억나지 않는다..)</li>
					<li>추천 음식은? 마크정식 드셔보세요!</li>
				</ol>
			<p class="link" ><a href="#top" >[메인으로]</a></p>
			</article>
			<article class="blank" > 
			<a id="movie"><h2>영화좋아</h2></a>
				<ol class="list" >
					<li>좋아하는 영화는? SF! 판타지! 등등 (공포영화 빼고 다!)</li>
					<li>최근에 본 영화는? 라라랜드</li>
					<li>재미있게 본 영화는? 인터스텔라! 마션! 인셉션! 어바웃타임! 겨울왕국!<br> 마블시리즈 모두!</li>
					<li>최악의 영화는? 닌자 어쌔신! (보고 기절하는줄..)</li>
					<li>추천 영화는? 의형제! (강동원님 나옴..)</li>
					<li>보고싶은 영화는? 캐리비안의 해적: 죽은 자는 말이 없다</li>
				</ol>
			<p class="link" ><a href="#top" >[메인으로]</a></p>
			</article>
			<article class="blank" > 
			<a id="trip"><h2>여행좋아</h2></a>
				<ol class="list" >
					<li>어디 가봤어? 제주도! 중국! 일본! 유럽!</li>
					<li>최근에 다녀온 여행지는? 후쿠오카</li>
					<li>제일 좋았던 여행지는? 이탈리아, 프라하</li>
					<li>최악의 여행지는? 상해<br>(택시 사기도 당하고.. 황사때문에 앞도 잘 안보이고.. 너무 힘들었음.)</li>
					<li>추천 여행지는? 제주도! 일본! 유럽!</li>
					<li>가고싶은 여행지는? 프랑스! 싱가폴! 대만!</li>
				</ol>
			<p class="link" ><a href="#top" >[메인으로]</a></p>
			</article>
			<article class="blank" > 
			<a id="question"><h2>설문조사</h2>
				<fieldset class="fields">
				<legend>설문조사</legend>
				<div class="space">
					<label>닉네임: <input type="text" placeholder="닉네임만입력" size="20" ></label>
					<input type="submit" value="중복확인">
				</div>
				<div class="space">
					<label>성별: <input type="radio" value="man" name="ckecked" >남자
					<input type="radio" value="women" name="ckecked" >여자</label>
				</div>
				<div class="space">
					<label>나이: 
					<select> 
						<option value="ten">10대</option>
						<option value="twenty">20대</option>
						<option value="thirty">30대</option>
						<option value="forty">40대</option>
						<option value="fifty">50대</option>
					</select>
					</label>
				</div>	
				</fieldset>
			<button type="submit" class="button">제출하기</button>
			<p class="link" ><a href="#top" >[메인으로]</a></p>
			</article>
			<article class="blank" > 
			<a id="guestbook"></a><h2>방명록</h2>
			<p class="indent" >남기고 싶은 말을 작성해 주세요.</p>	
			<textarea rows="5" cols="85" class="textarea"></textarea>
			<button type="submit" class="button" >등록하기</button>
			<p class="link" ><a href="#top" >[메인으로]</a></p>
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