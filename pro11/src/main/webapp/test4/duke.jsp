<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String imgName = request.getParameter("imgName");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br><br>
	<h1>이름은<%=name %></h1>
	<img alt="" src="../image/<%=imgName%>" style="width: 100px; height: 200px">
</body>
</html>