<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	=======toString ===========
	<h1><%=exception.toString() %></h1>
	<h1><%=exception.getMessage() %></h1>
	<h1><%exception.printStackTrace(); %></h1>

	<h3>
		숫자만 입력 가능 합니다. 다시 시도해주세요
		<a href="add.html">다시하기</a>
	</h3>
</body>
</html>