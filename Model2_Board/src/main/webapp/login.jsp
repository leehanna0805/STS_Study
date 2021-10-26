<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp 페이지</title>
</head>
<body>
	<div id="div_box">
		<h1>로그인</h1>
		<form name="loginForm" action="login.do">
			<table border="1" cellpadiing="0" cellspacing="0">
				<tr>
					<td bgcolor="orange">아이디</td>
					<td><input type="text" name="id"/></td>
				</tr>
				<tr>
					<td bgcolor="orange">비밀번호</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<!-- submit클릭하면 login.do 요청을 DispatcherServlet에서 받는다. -->
					<td><input type="submit" value="로그인"/></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>