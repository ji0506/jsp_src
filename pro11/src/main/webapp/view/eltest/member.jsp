<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,pro11.sec02.*"
    %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=UTF-8");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <table border=1 align="center">
		<tr style="text-align: center; background-color: #99ccff;">
			<td style="width: 30%;"><b>아이디</b></td>
			<td style="width: 20%;"><b>비밀번호</b></td>
			<td style="width: 40%;"><b>이름</b></td>
			<td style="width: 20%;"><b>이메일</b></td>
			<td style="width: 60%;"><b>가입일</b></td>
		</tr>
		<c:choose>
			<c:when test="${empty memList}">
				<tr>
					<td colspan=5><b>등록된 회원이 없습니다.</b></td>
				</tr>
			</c:when>
			<c:when test="${memList != null}">
				<c:forEach var="mem" items="${memList}">
					<tr style="text-align: center;">
						<td>${mem.id}</td>
						<td>${mem.pwd}</td>
						<td>${mem.name}</td>
						<td>${mem.email}</td>
						<td>${mem.joinData}</td>					
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
    </table>
</body>
</html>