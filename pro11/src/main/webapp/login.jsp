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
	<form name="frmLogin" method="post" action="result3.jsp" encType="utf-8">
		아이디: <input type="text" name="userId"><br>
		비밀번호: <input type="password" name="userPw"><br>
		<input type="submit" value="로그인">
		<input type="reset" value="다시입력">
	</form>
	<br><br>
	<a href="${pageContext.request.contextPath}/view/eltest/memberForm.jsp">회원가입</a>
	
</body>
</html>