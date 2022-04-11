<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>ORDUnit</title>
<meta name="referrer" content="no-referrer" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>

	<div class="container" align="center">
			<!-- 검색에 대한 div -->
			<div class="col-md-15">
        <a href="/"><img src="https://www.dogdrip.net/dvs/d/22/03/31/502d4e1f0b0d1faf69425a4ee4a78438.png" style=""></a>
			</div>
	<hr class="mt-5">
	<div class="btn-group btn-group-justified">
		<a href="/notice/list.do" class="btn btn-primary">공지사항</a> <a
			href="/unit/list.do" class="btn btn-primary">유닛</a> <a
			href="/combination/view.do" class="btn btn-primary">조합식</a> <a
			href="/board/list.do" class="btn btn-primary">공략</a>
	</div>
	<div class="row">
		<hr class="mt-5">
		<div class="col-sm-3">
			<div class="well well-lg">
				<a href="/member/login.do"
					class="btn btn-block btn-primary font-weight-bold"> 로그인 </a>
				<!-- 					<div class="row mt-3 mb-3"> <a class="col text-right" href="/member/write.do"> <i
							class="fas fa-user"></i> 회원가입
						</a>
					</div> -->
			</div>
		</div>
		<div class="col-sm-7" align="center">
			<img
				src="https://img.hani.co.kr/imgdb/resize/2013/1101/138320842490_20131101.JPG"
				width="500" height="400" class="img-rounded" />
		</div>
		<div class="col-sm-2.5" align="center">
			<a class="btn btn-primary btn-lg"
				href="https://cafe.naver.com/clanopcr?iframe_url=/ArticleList.nhn%3Fsearch.clubid=27675647%26search.menuid=14%26search.boardtype=L"
				target="_blank"> 유즈맵 다운로드 <i class="fas fa-external-link-alt"></i>
			</a>
		</div>
	</div>
	<hr class="mt-5">
	<div class="mb-5 text-right" style="width: 1130px;">
		<div>
			<a href="/">홈으로</a> <span class="ml-1 mr-1" style="color: darkgray">|</span>
			<a href="//cafe.naver.com/clanopcr" target="_blank">원랜디카페</a> <span
				class="ml-1 mr-1" style="color: darkgray">|</span> <a
				href="mailto:rlatlghks96@naver.com">메일문의</a>
		</div>
		<div class="mt-3">
			<b><i class="fas fa-skull"></i> 김시환</b>
		</div>
	</div>
	<hr class="mt-5">
	<P>현재시각 : ${serverTime }</P>
	</div>
</body>
</html>
