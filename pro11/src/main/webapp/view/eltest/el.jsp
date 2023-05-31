<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	\${100} : ${100}<br>
	\${"안녕하세요"} : ${"안녕하세요" }<br> 
	\${10+1 } : ${10+1 }<br>
	\${"10"+1 } : ${"10"+1 }<br>
	\${10 % 9} : ${10 % 9 }<br>
	\${10 mod 9} : ${10 mod 9 }<br>
	\${10 div 9} : ${10 div 9 }<br>
	\${10 / 9} : ${10 / 9 }<br>

	<%-- \${null-10} --%>
	

</body>
</html>