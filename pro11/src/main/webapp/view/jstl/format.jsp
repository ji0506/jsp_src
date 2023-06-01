<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Date"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="price" value="1000000"></c:set>
	<fmt:formatNumber value="${price}" type="number" var="pricNumber"></fmt:formatNumber>
	통화로 표현 시 : 
	<fmt:formatNumber type="currency" value="${price}" currencySymbol="￦"  ></fmt:formatNumber><br>
	퍼센트로 표현시 :
	<fmt:formatNumber type="percent" value="${price}" groupingUsed="false" ></fmt:formatNumber><br>
	일반 숫자로 표현시 : ${pricNumber}<br>
	
	<h2>fromatData 예제</h2>
	<c:set var="now" value="<%=new Date() %>"></c:set>
	<fmt:formatDate value="${now}" type="date" dateStyle="full"/><br>
	<fmt:formatDate value="${now}" type="date" dateStyle="short"/><br>
	<fmt:formatDate value="${now}" type="time"/><br>
	<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/><br>
	<fmt:formatDate value="${now}" pattern="YYYY-MM-DD hh:mm:ss" /><br>
	<br><br>
	한국 현재 시간:
	<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/><br>
	뉴욕 현재 시간
	<fmt:timeZone value="America/New York" >
	<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/><br>
	</fmt:timeZone>
	
</body>
</html>