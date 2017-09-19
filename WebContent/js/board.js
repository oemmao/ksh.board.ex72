function writeSave(){
	var writeform = document.writeform;
	if(writeform.writer.value==""){
		alert("이름을 입력해 주세요.");
		writeform.writer.focus();
		return false;
	}
	if(writeform.subject.value==""){
		alert("제목을 입력해 주세요.");
		writeform.subject.focus();
		return false;
	}

	if(writeform.content.value==""){
		alert("내용을 입력해 주세요.");
		writeform.content.focus();
		return false;
	}

	if(writeform.passwd.value==""){
		alert("비밀번호를 입력해 주세요.");
		writeform.passwd.focus();
		return false;
	}
//	var url = $("form[name=writeform]").attr("action");
//	alert(url);
} 

$(function() {
	var boardDomainPath = "/ksh.board.ex72/BoardController?cmd=";
	
	$("#textModify").click(function(){
		var inputData = $('form').serialize();
//		alert("inputdata:: "+inputData);
		$.ajax({
			url: boardDomainPath + 'updateGetBoard',
			type: "POST",
			data: inputData,
			success:function(obj) {
//				alert(obj);
				var checkResult = JSON.parse(obj).check;
				if (checkResult == "1") {
					alert("수정이 완료 되었습니다.");
					document.location.href = boardDomainPath + 'getAllBoards';
				} else {
					alert("비밀번호가 다릅니다. 다시 입력해 주세요.");
				}
			}
		});
	});	
	
	$("#textDelete").click(function(){
		alert("textDelete:: come in");
		var pw = $('#passwd').val();
		var inputData = $('input').serialize();
//		alert(inputData);
		if (pw == "") {
			alert("비밀번호를 입력해 주세요.");
		} else {
			$.ajax({
				url: boardDomainPath + 'deleteBoard',
				type: "POST",
				data: inputData,
				success:function(obj) {
					alert(obj);
					var result = JSON.parse(obj).result;
					if (result == "1") {
						alert("삭제되었습니다.");
						document.location.href = boardDomainPath + 'getAllBoards';
					} else {
						alert("비밀번호가 다릅니다. 다시 입력해 주세요.");
					}
				}
			});
		}
	});

});
