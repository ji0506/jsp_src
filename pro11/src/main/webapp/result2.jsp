<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPw");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(id==null || id.length() == 0){
%>
아이디를 입력하세요 <br>
<a href="/login.jsp">로그인하기</a>
<%
	}else{
%>
	<h1>환영합니다. <%=id %> 님</h1>
<%
	}
%>

</body>
</html>