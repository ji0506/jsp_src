<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=UTF-8");
/*	String id = (String) request.getAttribute("id");
	String pwd = (String) request.getAttribute("pwd");
	String name = (String) request.getAttribute("name");
	String email = (String) request.getAttribute("email");*/
%>


<%
		List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("world");
		list.add("안녕하세요");

	%>
<c:set var="list" value="<%=list %>" />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach var="i" begin="1" end="10" step="1" varStatus="loop">
		i= ${i} &nbsp;&nbsp;&nbsp;&nbsp;  반복횟수:${loop.count}<br>
	</c:forEach>
	<br>
	<c:forEach var="i" begin="1" end="10" step="2">
		5 * ${i} = ${5*i}<br>
	</c:forEach>
	<c:forEach var="data" items="${list}">
		${data}<br>
	</c:forEach>
	<c:set var="fruits" value="사과, 파인애플, 바나나,망고,귤" />
	<c:forTokens var="token" items="${fruits}" delims=",">
		${token}<br>
	</c:forTokens>


</body>
</html>