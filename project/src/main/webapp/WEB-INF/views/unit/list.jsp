<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유닛 리스트</title>
<style type="text/css">
/* datarow class(.) 안에 있는 이미지 태그의 사이즈 조정 */
/* 은 밑에 스타일에서 설정함 */
.dataRow:hover {
	cursor: pointer;
	background: #eee;
}
</style>
<script type="text/javascript">
//perPageNum 데이터의 변경 이벤트 처리(jQuery에 대한 이벤트)
$(function() {
$("#perPageNumSelect").change(function(){
	/* alert("값 변경"); */
	$("#perPageNumForm").submit();
		});
});
</script>
</head>
<body style="background-color: #0000FF">
	<div class="container" class="p-3 mb-2 bg-primary text-white">
		<h2>유닛</h2>
		<div class="row">
		<!-- 검색에 대한 div -->
			<div class="col-md-8">
				<form class="form-inline">
				<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
					<div class="input-group">
						<select name="key" class="form-control">
							<option value="c" ${(pageObject.key =="c")?"selected":"" }>이름</option>
							<option value="i" ${(pageObject.key =="i")?"selected":"" }>파일명</option>
						</select>
					</div>
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Search"
							name="word" value="${pageObject.word }">
						<div class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
			<!-- 한 페이지당 보여주는 데이터 갯수 -->
			<div class="col-md-4 text-right">
				<form action="list.do" class="form-inline" id ="perPageNumForm">
					<input type="hidden" name="page" value="${pageObject.page }"> <input
						type="hidden" name="key" value="${pageObject.key }"> <input
						type="hidden" name="word" value="${pageObject.word }">
					<div class="form-group">
						<label> 1페이지당 갯수 <select name="perPageNum"
							class="form-control" id="perPageNumSelect">				
								<option ${(pageObject.perPageNum == 4)?"selected":"" }>4</option>
								<option ${(pageObject.perPageNum == 8)?"selected":"" }>8</option>
								<option ${(pageObject.perPageNum == 12)?"selected":"" }>12</option>
								<option ${(pageObject.perPageNum == 16)?"selected":"" }>16</option>
						</select>
						</label>
					</div>
				</form>
			</div>
			<!-- col md 3 : 한 줄에 사진 네 장을 표시 3*(3+1)=12 -->
			<c:forEach items="${list }" var="vo" varStatus="vs">
				<div class="col-md-3">
					<div class="thumbnail dataRow"
						onclick="location='view.do?no=${vo.no }&page=${pageObject.page }&perPageNum=${pageObject.perPageNum }&key=${pageObject.key }&word=${pageObject.word }'"
						style="background-color:#BE8C63">
						<img src="${vo.image }" alt="Photo Lists"
							style="width: 100%; height: 300px">
						<div class="caption">
							<p align="center" style = "font-weight:bolder; font-size:1.4em;  color: blue;">${vo.character }</p>
						</div>
					</div>
				</div>
			<!-- // 이미지 4개를 출력하면 새로운 줄을 만든다.
			// 만약에 출력된 이미지가 총 데이터의 갯수와 같으면 줄을 더 이상 만들지 않는다
		 -->
				<c:if test="${vs.count % 4 == 0 && vs.count != list.size()}">
		${"</div>" }
		${"<div class='row'>" }
			</c:if>
			</c:forEach>
		</div>
		<div>
			<pageNav:pageNav listURI="list.do" pageObject="${pageObject }" query="&key=${pageObject.key }&word=${pageObject.word }"/>
		</div>
		<c:if test="${!empty login }">
		<a href="write.do?perPageNum=${pageObject.perPageNum }" class="btn btn-default">등록</a>
		</c:if>
		<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum }&key=${pageObject.key }&word=${pageObject.word }" class="btn btn-default">새로고침</a>
	</div>
	<!-- 몇 페이지에 있는지 나타내줍니다 -->
	<%-- <%= request.getQueryString() %>
	${queryString } --%>
</body>
</html>