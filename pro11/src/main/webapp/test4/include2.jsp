<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
		request.setCharacterEncoding("utf-8");

	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	안녕하세요. 쇼핑물 JSP 시작입니다.
	<br>
	<jsp:include page="duke.jsp">
		<jsp:param value="듀크2" name="name"/>
		<jsp:param value="duke2.png" name="imgName"/>
	</jsp:include>
</body>
</html>