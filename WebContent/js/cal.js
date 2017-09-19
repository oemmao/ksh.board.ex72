$(function(){
	var op = "";
	var op1 = 0;
	var op2 = 0;
	var str1 = ""; //식 출력을 위해 사용 (op1, op)
	var str2 = ""; //식 출력을 위해 사용 (op2)
	
	$(".num").click(function(){
 		if(op == "") {
			str1 += $(this).val();
			$("#number").val(str1);//식 출력
			op1 = Number(str1);  //값 계산을 위해 op1에 값을 할당
		} else { 
			str2 += $(this).val();
			$("#number").val(str1 + str2);//식 출력
			op2 = Number(str2); //값 계산을 위해 op2에 값을 할당
		} 
	});
	
	$(".ops").click(function(){
		op = $(this).val(); //연산자를 op에 할당
		str1 += op;
		$("#number").val(str1);//식 출력
	});
	
	$(".calresult").click(function(){
		$.ajax({
			url:"../calController?cmd=calInput",
			type: 'POST',
			data: {
				op1 : op1,
				op : op,
				op2 : op2
			},
			success:function(obj) {
				var result = JSON.parse(obj).result;
				var errResult = JSON.parse(obj).errResult;
				
				if (result != null) {
					printResult(result);
				} else if (errResult != null) {
					errMsg(errResult);
				}		
			}
		})
	});
	
	var printResult = function(result){ //js 함수호출 패턴
		$("#number").val(result); //inner tag에는 값(value)을 넣어줘야한당.	
		op1 = result; //연산을 계속하기 위해 op1에 result를 할당 => 값 계산을 위한 것
		str1 = result; //연산을 계속하기 위해 str1에 result를 할당 => 식 출력을 위한 것
		str2 = ""; //연산을 계속하기 위해 초기화
	}
	
	var errMsg = function(errResult){ //js 함수호출 패턴
		$("#number").val(errResult); //inner tag에는 값(value)을 넣어줘야한당.	
		op = "";
		op1 = 0;
		op2 = 0;
		str1 = "";
		str2 = "";
	}
	
	$(".clearCal").click(function(){
		$("#number").val("");
		op = "";
		op1 = 0;
		op2 = 0;
		str1 = "";
		str2 = "";
	});
});