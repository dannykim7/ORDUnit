<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 글쓰기</title>
</head>
<body>
<div class="container">
<h2>이미지 등록 폼</h2>
<form action="write.do" method="post" enctype="multipart/form-data">
<!-- 페이지 유지해야되니까 안보이게 히든으로 몰래 가져옴 -->
<input name="perPageNum" value="${param.perPageNum }" type="hidden">
<div class="form-group">
<label>제목</label>
<input name="title" class="form-control">
</div>
<div class="form-group">
<label>내용</label>
<textarea name="content" class="form-control"></textarea>
</div>
<div class="form-group">
<label>이미지 파일</label>
<input name="image" type="file" class="form-control">
</div>
<button class="btn btn-default">등록</button>
<button type="reset" class="btn btn-default">새로입력</button>
<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
</form>
</div>
</body>
</html>