<!-- sitemesh 사용을 위한 설정 파일 -->
<!-- 작성자 : 이영환 -->
<!-- 작성일 : 2020-06-30 -->
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@page 
	import="javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%
// getAttribute를 통해 로그인 가져오고 LoginVO 강제캐스팅
LoginVO loginVO = (LoginVO) session.getAttribute("login");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 각 do의 타이틀 앞에 웹짱: 이 붙게함 -->
<title>ORDUnit <decorator:title /></title>
<!-- CDN 방식의 Bootstrap 라이브러리 등록 -> 디자인의 웹표준을 구현한 웹 라이브러리 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- jQuery UI 라이브러리 cdn 방식으로 등록 : datepicker같은거 쓸 수 있어 -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<style type="text/css">
header, footer {
	background: #733C3C;
	   font-weight: 900;
	   font-family: Dovemayo;
}
body{
	background: #8FBDD3;
}

pre {
	background: white;
	border: 0px;
}

/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	/* 테두리의 둥근 정도 */
	border-radius: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: black;
	padding: 25px;
	color: #ddd;
}

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
}

article {
	min-height: 400px;
	margin-top: 120px;
	margin-bottom: 120px;
}

#welcome {
	color: grey;
	margin: 0 auto;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
<script type="text/javascript">
$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
});
</script>
<decorator:head />
</head>
<body>
	<div class="container">
		<header>
			<!-- <div><img href="/upload/image/cat01.jpg" style="width: 100%; height: 150px;"/></div> -->
			<!-- navbar-fixed-top : 휠 돌려도 계속 보이도록 위에 고정 -->
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#myNavbar">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="/"><b><i class="fas fa-skull"></i> ORDunit</b></a>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav">
							<li><a href="/notice/list.do">공지사항</a></li>
							<li><a href="/unit/list.do">유닛</a></li>
							<li><a href="/board/list.do">공략</a></li>
							<li><a href="/combination/view.do">조합식</a></li>
							<%
							if (loginVO != null && loginVO.getGradeNo() == 9) {
							%>
							<li><a href="/member/list.do">회원관리</a></li>
							<%
							}
							%>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<%
							if (loginVO == null) {
							%>
							<li><a href="/member/write.do">회원가입</a></li>
							<li><a href="/member/login.do">로그인</a></li>
							<%
							} else {
							%>
							<!-- 로그인 했을 시, 로그인 버튼 사라지고 사용자 이름(등급이름)과 로그아웃 버튼 생성 -->
							<!-- loginVO가 null 이 안됐기 때문 -->
							<li><a href="/member/logout.do"><%=loginVO.getName()%>(<%=loginVO.getGradeName()%>)</a></li>
							<li><a href="/member/logout.do">로그아웃</a></li>
							<!-- <li class="dropdown"><a
								class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown" style="background-color: #733C3C;">
									MyPage<span class="caret"></span>
							</a> -->
								<%-- <ul class="dropdown-menu">
									<li>${vo.id }</li>
									<li><a href="#">마이페이지</a></li>
									<li><a href="#">작성 리뷰</a></li>
									<li><a href="#">로그아웃</a></li>
									<%
									if (loginVO != null && loginVO.getGradeNo() == 9) {
									%>
									<li><a href="#">관리자 메뉴</a></li>
									<%
									}
									%>
								</ul> --%></li>
<%
							}
							%>
						</ul>
					</div>
				</div>
			</nav>
		</header>
		<!-- header, article, section, footer : HTML 5에서 의미없는 구성 태그인 div 태그를 의미 있는 태그로 
	작성되도록 만든 태그 - 시멘틱 태그(낮은 버전에서는 div로 인식) -->
		<article>
			<decorator:body />
		</article>
	</div>
</body>
</html>