<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	session.setAttribute("name", "ji");
	application.setAttribute("address", "서울시 강남구");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름과 주소를 저장합니다. <br>
	<a href="/test/apptest2.jsp">두번쨰 페에지 이동</a>
</body>
</html>