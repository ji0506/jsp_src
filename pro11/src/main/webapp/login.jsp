<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <%
		request.setCharacterEncoding("utf-8");
    
	%>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="frmLogin" method="post" action="result3.jsp" encType="utf-8">
		아이디: <input type="text" name="userId"><br>
		비밀번호: <input type="password" name="userPw"><br>
		<input type="submit" value="로그인">
		<input type="reset" value="다시입력">
	</form>
	<br><br>
	<a href="${contextPath}/view/eltest/memberForm.jsp">회원가입</a>
	
</body>
</html>