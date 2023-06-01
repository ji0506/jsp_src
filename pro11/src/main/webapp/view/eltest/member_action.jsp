<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,pro11.sec02.*"
    %>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=UTF-8");
%>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean id="m" class="pro11.sec02.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="m"/>
<title>Insert title here</title>
</head>
<body>
	<%
		MemberDAO dao=new MemberDAO();
		if(m.getId() != null)
			dao.addMember(m);
		List<MemberVO> list = dao.listMembers();
		request.setAttribute("memList", list);
	%>
	<jsp:forward page="member.jsp"></jsp:forward>
</body>
</html>