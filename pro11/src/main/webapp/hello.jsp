<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String name = "듀크";

	public String gatName()
	{
		return name;
	}
%>
<% String age =request.getParameter("age"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫번째 JSP 페이지</title>
</head>
<body>
<!-- 	<img alt="1" src="./image/topleft.jpg" />-->
<h1>안녕하세요 <%=name %></h1>
 <h1>나이는 <%=age %> 입니다.</h1>
 
 </body>
</html>	