<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=UTF-8");
	String id = (String) request.getAttribute("id");
	String pwd = (String) request.getAttribute("pwd");
	String name = (String) request.getAttribute("name");
	String email = (String) request.getAttribute("email");


%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <table border=1 align="center">
		<tr style="text-align: center; background-color: #99ccff;">
			<td style="width: 20%;"><b>아이디</b></td>
			<td style="width: 20%;"><b>비밀번호</b></td>
			<td style="width: 20%;"><b>이름</b></td>
			<td style="width: 20%;"><b>이메일</b></td>
			<td style="width: 20%;"><b>주소</b></td>
		</tr>
		<tr style="text-align: center;">
			<td><%=id%></td>
			<td><%=pwd%></td>
			<td><%=name%></td>
			<td><%=email%></td>
			<td></td>

		</tr>
		<tr style="text-align: center;">
			<td>${id}1</td>
			<td>${pwd}</td>
			<td>${name}</td>
			<td>${email}</td>
			<td>${address}</td>
			
		</tr>
    </table>
</body>
</html>