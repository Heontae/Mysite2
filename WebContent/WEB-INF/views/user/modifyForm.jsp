<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.UserVo"%>

<%
	UserVo userVo = (UserVo) request.getAttribute("userVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/Mysite2/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/Mysite2/assets/css/user.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<div id="header">
			<h1><a href="http://localhost:8088/Mysite2/main">MySite</a></h1>
			
			<%if(userVo == null) { %>
			<ul>
				<li><a href="http://localhost:8088/Mysite2/user?action=loginForm">로그인</a></li>
				<li><a href="http://localhost:8088/Mysite2/user?action=joinForm">회원가입</a></li>
			</ul>
			
			<%}else{ %>
			<!--  로그인 성공했을때(세션값이 있으면) 
			 -->
			<ul>
				<li><%=userVo.getName() %> 님 안녕하세요^^</li>
				<li><a href="http://localhost:8088/Mysite2/user?action=logout">로그아웃</a></li>
				<li><a href="http://localhost:8088/Mysite2/user?action=modifyFoem">회원정보수정</a></li>
			</ul>
			 <%} %>
		</div>
		<!-- //header -->

		<div id="nav">
			<ul>
				<li><a href="">방명록</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">입사지원서</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<!-- //nav -->

		<div id="aside">
			<h2>회원</h2>
			<ul>
				<li>회원정보</li>
				<li>로그인</li>
				<li>회원가입</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>회원정보</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>회원</li>
						<li class="last">회원정보</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="user">
				<div id="modifyForm">
					<form action="/Mysite2/user" method="get">

						<input type="hidden" name="action" value="modif"> 
				
						<!-- 아이디 -->
						<div class="form-group">
							<label class="form-text" for="input-uid">아이디</label> <span
								class="text-large bold" ><%=userVo.getId() %></span>
						</div>

						<!-- 비밀번호 -->
						<div class="form-group">
							<label class="form-text" for="input-pass">패스워드</label> <input
								type="password" id="input-pass" name="password" value="<%=userVo.getPassword() %>"
								placeholder="비밀번호를 입력하세요">
						</div>

						<!-- 이름 -->
						<div class="form-group">
							<label class="form-text" for="input-name">이름</label> <input
								type="text" id="input-name" name="name" value="<%=userVo.getName() %>"
								placeholder="이름을 입력하세요">
						</div>

						<!-- 성별 -->
						<div class="form-group">
							<span class="form-text">성별</span> 
							<%if("male".equals(userVo.getGender())) { %>
							<label for="rdo-male">남</label>
							<input type="radio" id="rdo-male" name="gender" value="male" checked="checked">
							<label for="rdo-female">여</label> 
							<input type="radio" id="rdo-female" name="gender" value="fmale">
							<%}else{ %>
							<label for="rdo-male">남</label>
							<input type="radio" id="rdo-male" name="gender" value="male">
							<label for="rdo-female">여</label>
							<input type="radio"id="rdo-female" name="gender" value="female" checked="checked">
							<%} %>
						</div>

						<!-- 버튼영역 -->
						<div class="button-area">
							<button type="submit" id="btn-submit">회원정보수정</button>
						</div>

					</form>


				</div>
				<!-- //modifyForm -->
			</div>
			<!-- //user -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<div id="footer">Copyright ⓒ 2020 황일영. All right reserved</div>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>