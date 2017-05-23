<%@page import="com.jx372.mysite.vo.UserVo"%>
<%@page import="com.jx372.mysite.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<%
	BoardVo boardVo = (BoardVo) request.getAttribute( "boardVo" );
	UserVo authUser = (UserVo)session.getAttribute( "authUser" );
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/board.css"
	rel="stylesheet" type="text/css">

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">${fn:replace(boardVo.content, newLine, "<br>") }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<c:choose>
						<c:when test="${not empty authUser && boardVo.userNo == authUser.no }">
							<a href="<%=request.getContextPath()%>/board">글목록</a>
							<a href="<%=request.getContextPath() %>/board?a=modifyform&no=${boardVo.no }">글수정</a>
							<a href="<%=request.getContextPath()%>/board?a=replyform&no=${boardVo.no }">댓글달기</a>
						</c:when>
						<c:otherwise>
							<a href="<%=request.getContextPath()%>/board">글목록</a>
							<a href="<%=request.getContextPath()%>/board?a=replyform&no=${boardVo.no }">댓글달기</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp" />
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>