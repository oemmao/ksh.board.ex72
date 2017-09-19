$(function() {
	var domainPath = "/ksh.board.ex72";
	var globalPath = domainPath + "/memberController?cmd=";
	
	$("#duCheckID").click(function(){
		event.preventDefault();
		var userID = $("#userID").val();
		if (userID == "") { //아이디 입력 여부 확인
			alert("Please input ID");
		} else {
			$.ajax({
				url: globalPath + 'checkID',
				type: 'POST',
				data: { 
					userID : userID
				},
				success:function(obj) {
					var result = JSON.parse(obj).result;
					if (result == "1") {
						alert("success");
						$("#checkid").val(1);
					} else {
						alert("duplication");
						$("#checkid").val(0);
					}
				}
			})
		}	
	});

	$("#submit").click(function(){
		event.preventDefault();
		if ($("#checkid").val() == 1) {
			var inputdata = $('input').serialize();
			$.ajax({
				url: globalPath + 'memberInsert',
				type: 'POST',
				data: inputdata,
				success:function(obj) {
					var result = JSON.parse(obj).result;
//					var result = $.trim(result);
//					alert(result);
					if (result == "1") { //문자 형태로 받아옴
						alert("Sign up for a membership.");
						document.location.href = domainPath + "/index.jsp";
					} else {
						alert("Please input Data.");
					}
				}
			});
		} else {
			alert("Please check ID");
		}
	});

	$("#loginsubmit").click(function(){	
		event.preventDefault();
		var inputdata = $('input').serialize();
		$.ajax({
			url: globalPath + 'login',
			type: 'POST',
			data: inputdata,
			success:function(obj) {				
				var userID = JSON.parse(obj).userID;
				var result = JSON.parse(obj).result;
//				alert(result);
				if (result == "0") {
					alert("Welcome to " + userID);
					location.reload();
				} else if (result == "1") {
					alert("Passwords are not the same.");
				} else {
					alert("ID could not be found.");
				}
			}
		});
	});

	$("#logoutsubmit").click(function(){	
		console.log("come in");
		event.preventDefault();
		var inputdata = $('input').serialize();
		$.ajax({
			url: globalPath + 'logout',
			type: 'POST',
			data: inputdata,
			success:function(result) {
				var result = $.trim(result);
//				alert(result);
				alert("Logout");
				document.location.href = domainPath +"/index.jsp";
			}
		});
	});
	
	$("#selectAllMemberForm").click(function(){
		$(".selectAll").hide();
		$("#selectAllMember").show();
		$("#selectAllList").click(function(){	
			$.ajax({
				url: globalPath + "selectAllMember",
				type: "POST",
				dataType: "json",
				cache: false,
				success:function(data) {
	//				alert(data);
					$("#result").html(""); //#result를 공백으로 초기화		
					$("<table/>").appendTo("#result"); //result에 table을 생성
					var key = Object.keys(data["memberlist"][0]); // id , name , address , phone 의 키값을 가져옴
	//				alert(key);
					$("<tr>" , {
						html : "<td>" + key[2] + "</td>"+  //column명
						"<td>" + key[3] + "</td>"+
						"<td>" + key[0] + "</td>"+
						"<td>" + key[1] + "</td>"
					}).appendTo("#result table"); //column명을 #result table에 첨부
					$.each(data.memberlist, function(index, memberlist) { //each함수
						var items = [];	
						items.push("<td>" + memberlist.ID + "</td>"); //id, name, address, phone의 값을 배열에 add함
						items.push("<td>" + memberlist.NAME + "</td>");
						items.push("<td>" + memberlist.PHONE + "</td>");
						items.push("<td>" + memberlist.ADDRESS + "</td>");
						$("<tr/>", {
							html : items
						}).appendTo("#result table"); // #result table에 첨부
					});//each끝
					$("#result").fadeToggle("slow");
				}
			});	
		});
	});	

	$("#selectMemberForm").click(function(){
		$(".selectAll").hide();
		$("#selectMember").show();
		$("#selectList").click(function(){	
			event.preventDefault();
			var inputdata = $('input').serialize();
			if ($("#userID2").val() == "") {
				alert("Please input ID");
			} else {	
				$.ajax({
					url: globalPath + 'selectMember',
					type: 'POST',
					data: inputdata,
					success:function(obj) {	
						var result1 = JSON.parse(obj).result1;
						if (result1 == "1") {
							alert("The ID is unknown.");
						} else if (result1 == "0") {
							var result = [];
							result[0] = JSON.parse(obj).ID;
							result[1] = JSON.parse(obj).NAME;
							result[2] = JSON.parse(obj).PHONE;
							result[3] = JSON.parse(obj).ADDRESS;
							result[4] = JSON.parse(obj).userIDValue;
							result[5] = JSON.parse(obj).nameValue;
							result[6] = JSON.parse(obj).phoneValue;
							result[7] = JSON.parse(obj).addressValue;
	//						alert(result);
	//						alert("Login");
	//						var info = result.split(" ");
							for (var i=0; i<result.length; i++) {
								$("#result"+(i+1)).html(result[i]);
							}
						}
					}
				});
			}			
		});	
	});
	
	$("#updateMemberForm").click(function(){
		$(".selectAll").hide();
		$("#updateMember").show();
		$("#dataUpdate").click(function(){
			event.preventDefault();
			var inputdata = $('input').serialize();
			if ($("#pwd").val() == "" || $("#name").val() == "" || $("#phone").val() == "" || $("#address").val() == "") {
				alert("Please input Data");
			} else {
				$.ajax({
					url: globalPath + 'updateMember',
					type: 'POST',
					data: inputdata,
					success:function(obj) {
						var result = JSON.parse(obj).result;
	//					alert(result);
						if (result == "1") {
							alert("Complete a modification");
							document.location.href = "memberPage.jsp";
						} else {
							alert("Fail.. please check again");
						}		
					}
				});
			}
		});
	});	

	$("#deleteMemberForm").click(function(){
		$(".selectAll").hide();
		$("#deleteMember").show();
		$("#dataDelete").click(function(){	
			event.preventDefault();
			var inputdata = $('input').serialize();
			$.ajax({
				url: globalPath + 'deleteMember',
				type: 'POST',
				data: inputdata,
				success:function(obj) {
					var result = JSON.parse(obj).result;
					if (result == "1") {
						alert("Withdraw from a website");
						document.location.href = domainPath + "/index.jsp";
					} else {
						alert("ID or Passwords are not the same.");				
					}
				}
			});
		});
	});	

	$(".isLogin").click(function(event){
		var url = $(this).attr("href");
		event.preventDefault();	
		$.ajax({
			url: globalPath + 'isLogin',
			type: 'POST',
			success:function(result) {
				console.log(result, result==1, url);
				if (result == 1) {
					document.location.href = url;
				} else {
					alert("Please login");
				}	
			}
		});			
	});	

});

