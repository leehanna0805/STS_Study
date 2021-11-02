<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page errorPage="error.jsp" %>
<%@ page import="com.company.annotation.board.BoardDO" %>
<%@ page import="com.company.annotation.board.BoardDAO" %>
<%@ page import="java.util.List" %>

<%
	//서블릿의 login.do 에서 인증성공하면 세션에?? 아닌디.
	List<BoardDO> boardList = (List)session.getAttribute("boardList");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	int totalList = boardList.size();
	request.setAttribute("totalList", totalList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getBoardList.jsp</title>
</head>
<body>
	<center>
		<h3>${IdKey}님 환영합니다.&nbsp;&nbsp;&nbsp;<a href="logout.do">로그아웃</a></h3>
		<form name="myForm" method="POST" action="getBoardList.do">
			<p>총 게시글: ${totalList}건</p>
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
						<select name="searchCondition">
							<option value="TITLE">제목</option>
							<option value="WRITER">작성자</option>
						</select>
						<input type="text" name="searchKeyword"/>
						<input type="submit" value="검색"/>
					</td>
				</tr>
			</table>
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<th bgcolor="orange" width="100">번호</th>
					<th bgcolor="orange" width="200">제목</th>
					<th bgcolor="orange" width="150">작성자</th>
					<th bgcolor="orange" width="150">등록일</th>
					<th bgcolor="orange" width="100">조회수</th>
				</tr>
				
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td align="center">${board.seq}</td>
						<!-- model2에선 getBoard.do?seq=...이렇게 한다! -->
						<td align="left"><a href="getBoard.do?seq=${board.seq}">${board.title}</a></td>
						<td align="center">${board.writer}</td>
						<td align="center">${board.regDate}</td>
						<td align="center">${board.cnt}</td>
					</tr>
				</c:forEach>
			</table>
			<br><br>
			<a href="insertBoard.jsp">새 게시글 등록</a>&nbsp;&nbsp;&nbsp;
			<a href="getBoardList.do">전체 게시글 목록보기</a> <!-- 여기도 do! -->
		</form>
	</center>
</body>
</html>