<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.servlet.RequestDispatcher"%>
<!DOCTYPE html>
<%
	request.setAttribute("name", "ji");
	request.setAttribute("address", "서울시 강남구");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름과 주소를 저장합니다. <br>
	<%
		RequestDispatcher dispatch =request.getRequestDispatcher("request2.jsp");
		dispatch.forward(request, response);
	%>
	
</body>
</html>