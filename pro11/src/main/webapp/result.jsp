<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과출력창</title>
</head>
<body>
	<h1>결과 출력</h1>
	<%
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPw");
	%>
	<h1>아이디: <%=id %></h1>
	<h1>비밀번호:<%=pwd %></h1>	

</body>
</html>