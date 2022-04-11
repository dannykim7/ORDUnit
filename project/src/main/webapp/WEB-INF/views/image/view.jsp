<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 보기</title>
<style type="text/css">
/* 수정 버튼 메시지는 처음에는 안보이게 한다. */
#updateMsgDiv {
	display :none;
}


</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){

// 이미지 바꾸기 이벤트
$("#changeImageBtn").click(function(){
	$("#changeImageDiv").slideToggle();
});

// 삭제 버튼 경고 이벤트
$("#deleteBtn").click(function(){
	// confirm() -> 사용자에게 확인이나 취소를 누르게 하는 함수
	// 확인 - true / 취소 - false
	if(confirm("보고 계신 이미지를 삭제하시겠습니까?"))
		return true;
});
// 이미지 좌우넓이 설정
/* var imageWidth = $("#image").width();
var imageDivWidth = $("#imageDiv").innerWidth();

if(imageDivWidth < imageWidth) $("#image").width("100%"); */

//// 수정 버튼에 메시지를 처음에 안보이게 하고 마우스가 올라가면 보이게 한다.
$("#updateBtn")
.mouseover(function(){
	$("#updateMsgDiv").show;
})
.mouseout(function(){
	$("#updateMsgDiv").hide;
});
$(document).ready
});
</script>
<style type="text/css"> 
#viewImg {
   width: 400px;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

#changeImageDiv {
   display: none; 
}

#updateMsgDiv {
   display: none;
}
</style>


</head>
<body>
	<div class="container">
		<div class="well row">
			<div class="col-sm-3">번호</div>
			<div class="col-sm-9">${vo.no }</div>
		</div>
		<div class="well row">
			<div class="col-sm-3">제목</div>
			<div class="col-sm-9">${vo.title }</div>
		</div>
		<div class="well row">
			<div class="col-sm-3">
				<div>
					이미지
					<button class="btn btn-warning btn-sm" id="changeImageBtn">바꾸기</button>
				</div>
				<div id="changeImageDiv">
					<form action="changeImage.do" method="post"
						enctype="multipart/form-data">
						<input name="no" type="hidden" value="${vo.no }">
						<input name="deleteImage" value="${vo.fileName }" type="hidden">
						<input type="file" name="image" class="form-control">
						<button class="btn btn-default">바꾸기</button>
					</form>
				</div>
			</div>
			<!-- <img src=?>없이 el만 넣으면 파일주소만 뜬다 -->
			<div class="col-sm-9" id="imageDiv">
				<img src=${vo.fileName } class="thumbnail"
					style="width: 75%; height: 100%" id="image">
			</div>
		</div>
		<div class="well row">
			<div class="col-sm-3">내용</div>
			<div class="col-sm-9">${vo.content }</div>
		</div>
		<div class="well row">
			<div class="col-sm-3">작성자</div>
			<div class="col-sm-9">${vo.name }(${vo.id })</div>
		</div>
		<div class="well row">
			<div class="col-sm-3">작성일</div>
			<div class="col-sm-9">
				<fmt:formatDate value="${vo.writeDate }" pattern="yyyy-MM-dd" />
			</div>
		</div>
		<div>
			<!-- 툴팁 쓰려면 js에서 $('[data-toggle="tooltip"]').tooltip(); 추가해야함 -->
			<a
				href="update.do?no=${vo.no }&page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
				class="btn btn-default" id="updateBtn"
				data-toggle="tooltip" data-placement="top" title="이미지를 바꾸고 싶으시면 이미지 좌측의 바꾸기 버튼을 클릭">수정</a>
				 <a
				href="delete.do?no=${vo.no }&deleteImage=${vo.fileName }"
				class="btn btn-default" id="deleteBtn">삭제</a> <a
				href="list.do?page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
				class="btn btn-default">목록</a>
				<div class="alert alert-info" id="updateMsgDiv">이미지를 바꾸고 싶으시면 이미지 좌측의 바꾸기 버튼을 클릭</div>
		</div>
		<%-- <h2>${vo.title }</h2>
		<table class="table">
			<tr>
				<th>번호</th>
				<td>${vo.no }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${vo.title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${vo.content }</td>
			</tr>
			<tr>
				<th>이미지</th>
				<td><img alt="${vo.title }" src="${vo.fileName }"
					id="viewImg" class="img-rounded">
					<hr> <a href="${vo.fileName }" download
					class="btn btn-default">다운로드</a>
					<button onclick="$('#changeImageDiv').show()"
						class="btn btn-default">바꾸기</button>
					<hr>
					<div id="changeImageDiv">
						<form action="changeImage.jsp" method="post"
							enctype="multipart/form-data">
							<!-- 사용자는 볼 수 없고 데이터는 넘어감 -->
							<input type="hidden" name="no" value="${vo.no }"> <input
								type="hidden" name="oldImage" value="${vo.fileName }">
							<input type="file" name="image" required="required"
								class="btn btn-default">
							<button class="btn btn-default">바꾸기</button>
							<button type="button" onclick="$('#changeImageDiv').hide()"
								class="btn btn-default">취소</button>
						</form>
					</div></td>
			</tr>
			<tr>
				<th>이름(아이디)</th>
				<td>${vo.name }(${vo.id })</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${vo.writeDate }" pattern="yyyy-MM-dd"/></td>
			</tr>
		</table>
		<a href="updateForm.do?no=${vo.no }" class="btn btn-default">정보수정</a>
		<a href="delete.do?no=${vo.no }&oldImage=${vo.fileName }"
			class="btn btn-default">삭제</a> <a href="list.do"
			class="btn btn-default">목록</a> --%>

	</div>
</body>
</html>