<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#cancelBtn").click(function(){
			history.back();
		});
	});
	//JQuery에서 문서가 로딩이 다 되면 처리되는 구조
	$(function() {
		
		// 입력란의 배경색을 #eee(옅은 회색)로 만들고
		// 입력하는 입력란의 배경색을 흰색으로 해보자.
		// JQuery
		$("input, textarea").css("background","#eee");
		
		$("#writeForm").submit(function() {
			// 필수입력 항목 검사 - JS
			// 제목 -> 비어있으면 경고>포커스>이동막기
			if (emptyCheck("#title", "제목"))
				return false;

			// 내용 -> 비어있으면 경고>포커스>이동막기
			if (emptyCheck("#content", "내용"))
				return false;

			// 작성자 -> 비어있으면 경고>포커스>이동막기
			if (emptyCheck("#writer", "작성자"))
				return false;

			// 길이 제한 검사 - JS
			// 제목은 4~100까지 사용가능
			if (!lengthCheck("#title", "제목", 4, 100))
				return false;
			// 제목은 4~666(2000바이트)까지 사용가능
			if (!lengthCheck("#content", "내용", 4, 666))
				return false;
			if (!lengthCheck("#writer", "작성자", 2, 10))
				return false;
			/* var len = $("#title").val().length;
			if (!(len >= 4 && len <= 100)){
				alert("제목은 4~100자 이내로 작성하셔야 합니다.");
				$("#title").focus()
				return false;
			}
			 */
			// 코딩이 아직 안 끝나서 임시적으로 submit 되는것을 막는다
			return true;
		});
	});
</script>

</head>
<body>
<div class="container">
<h2>게시판 글쓰기 폼</h2>
<form action="write.do" method="post">
<input type="hidden" value="${param.perPageNum }" name="perPageNum">
<div class="form-group">
<label>제목</label>
<input name="title" id="title" class="form-control">
</div>
<div class="form-group">
<label>내용</label>
<textarea name="content" id="content" class="form-control" rows="7"></textarea>
</div>
<div class="form-group">
<label>작성자</label>
<input name="writer" id="writer" class="form-control">
</div>
<button>등록</button>
<button type="reset">새로입력</button>
<button type="button" id="cancelBtn">취소</button>
</form>
</div>
</body>
</html>