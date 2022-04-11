<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".dataRow")
				.click(
						function() {
							var no = $(this).find(".no").text();
							location = "view.do?no="
									+ no
									+ "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}";
						});
		// perPageNum 데이터의 변경 이벤트 처리(jQuery에 대한 이벤트)
		$("#perPageNumSelect").change(function() {
			/* alert("값 변경"); */
			$("#perPageNumForm").submit();
		});
	});
</script>
</head>
<body>
	<div class="text-center h4 font-weight-bold">공지사항</div>
	<div class="row justify-content-center mt-5 container">
		<table class="table col-md-8">
			<thead>
				<tr>
					<th class="text-center">#</th>
					<th class="text-center">제목</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<c:forEach items="${list }" var="vo">
				<tr class="dataRow">
					<td class="no" align="center">${vo.no }</td>
					<td align="center">${vo.title }</td>
				</tr>
			</c:forEach>
				</tr>

				<tr>
					<!-- query 태그 : 검색중에 페이지 이동할 때 검색 내용을 유지시켜줌 -->
					<td colspan="5"><pageNav:pageNav listURI="list.do"
							pageObject="${pageObject }"
							query="&key=${pageObject.key }&word=${pageObject.word }" /></td>
				</tr>
				<tr>
					<td colspan="5"><a
						href="write.do?perPageNum=${pageObject.perPageNum }"
						class="btn btn-default">쓰기</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>