
$(function() {
	var inputData = [];
	$("#rankResult").click(function() {
		inputData.push({ //JSON
			name : input("student", 1, 1),
			score : input("score", 1, 3)
		});
		inputData.push({
			name : input("student", 2, 2),
			score : input("score", 4, 6)
		});
		inputData.push({
			name : input("student", 3, 3),
			score : input("score", 7, 9)
		});
		inputData.push({
			name : input("student", 4, 4),
			score : input("score", 10, 12)
		});
		inputData.push({
			name : input("student", 5, 5),
			score : input("score", 13, 15)
		});
		
		if (check(inputData)) { //데이터 입력여부 확인
			rankTotal();
		} else {
			alert("데이터가 입력되지 않았습니다.");
		}
		inputData = [];
	});
	var check = function(data) { //if(check(inputData) -> 값의 유무를 확인하기 위한 함수 //js 함수 호출 패턴
		for (i in data) {
			for (j in data[i].score) {
				if (data[i].score[j] == "") {
					return false;
				}
			}
		}
		return true;
	}

	var input = function(title, num1, num2) { //입력받은 정보를 배열로 inputData에 저장
		var arrData = [];
		for (var i = num1; i <= num2; i++) {
			arrData.push(document.getElementById(title + i).value);
		}
		return arrData;
	}

	var rankTotal = function() { // 각 학생별 점수 합
		var inputData = $('input').serialize();
		console.log(inputData);
		$.ajax({
			url:"../rankController?cmd=doService",
			type:"POST",
			data:inputData,
			success:function(obj) {
//				alert(obj);
				var obj = JSON.parse(obj);	

				for (var i=0; i<obj.rankList.length; i++) {
					$('#sum'+(i+1)).text(obj.rankList[i].sum);
					$('#avg'+(i+1)).text(obj.rankList[i].avg);
					$('#rank'+(i+1)).text(obj.rankList[i].rank);
					$('#bigo'+(i+1)).text(obj.rankList[i].bigo);
				}
				
				$('#subtotal1').text(obj.subTotal1);
				$('#subtotal2').text(obj.subTotal2);
				$('#subtotal3').text(obj.subTotal3);
			}
		});
	}

	$("#clearRank").click(function() { //입력값 초기화
		window.location.reload();
	});

	$("#sampleData").click(function() { //샘플데이터 입력 함수
		$("#student1").val("홍길동");
		$("#student2").val("강감찬");
		$("#student3").val("유관순");
		$("#student4").val("이순신");
		$("#student5").val("김갑순");

		for (var i = 1; i < 16; i++) {
			$("#score" + i).val(Math.floor(Math.random() * 101));
		}
	});
});