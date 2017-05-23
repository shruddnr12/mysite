<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="board">
			<form class="board-form" method="post"
				action="<%=request.getContextPath() %>/board">
				<input type="hidden" name="a" value="modify" />
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글수정</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td><input type="text" name="title" value="${boardVo.title }"></td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td><textarea id="content" name="content">${boardVo.content}</textarea>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="<%=request.getContextPath() %>/board">취소</a> 
					<input type="submit" value="수정"/> 
						<input type="hidden" name="no" value ="${boardVo.no}"/>
				</div>
			</form>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/navigation.jsp" />
	<c:import url="/WEB-INF/views/include/footer.jsp" />

</body>
</html>