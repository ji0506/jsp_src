<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String name=(String)session.getAttribute("name");
	session.setAttribute("address", "서울시 강남구");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름은<%=name %>입니다 <br>
	<a href="session2.jsp">두번쨰 페에지 이동</a>
</body>
</html>