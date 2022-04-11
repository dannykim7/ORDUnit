<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 보기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>공지 보기</h2>
		<table class="table">
			<tr>
				<th>번호</th>
				<td>${vo.no }
			</tr>
			<tr>
				<th>제목</th>
				<td>${vo.title }
			</tr>
			<tr>
				<th>내용</th>
				<td>${vo.content }
			</tr>
			<tr>
				<th>공지작성일</th>
				<td><fmt:formatDate value="${vo.writeDate }"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td colspan="2">
				<a href="update.do?no=${vo.no }&inc=0&page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key }&word=${param.word}" class="btn btn-default">수정</a> 
				<a href="delete.do?no=${vo.no }&perPageNum=${param.perPageNum}" class="btn btn-default">삭제</a> 
				<a href="list.do?page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key }&word=${param.word}" class="btn btn-default">목록</a></td>
			</tr>
		</table>
	</div>
</body>
</html>