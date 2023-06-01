<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=UTF-8");
/*	String id = (String) request.getAttribute("id");
	String pwd = (String) request.getAttribute("pwd");
	String name = (String) request.getAttribute("name");
	String email = (String) request.getAttribute("email");*/
%>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="id" value="hong" scope="page"/>
	<c:set var="pwd" value="1234" scope="page"/>
	<c:set var="name" value="${'홍길동'}" scope="page"/>
	<c:set var="age" value="${22}" scope="page"/>
	<c:set var="height" value="${177}" scope="page"/>


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${true}">
		<h1>참입니다.</h1>
	</c:if>
	<c:if test="${11==11}">
		<h1>두값은 같습니다.</h1>	
	</c:if>
	<c:if test="${11!=31}">
		<h1>두값은 같지 않습니다.</h1>	
	
	</c:if>
	<c:if test="${(id=='hong') && (name=='홍길동')}">
		<h1>아이디는${id}이고 이름은 ${name} 입니다.</h1>	
	
	</c:if>
	<c:if test="${age == 22}">
		<h1>이름은 ${name}이고 나이는 ${age} 입니다.</h1>	
	
	</c:if>
	<c:if test="${height > 160}">
		<h1>160보다 큽니다.</h1>
	
	</c:if>

</body>
</html>