<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="pro11.sec02.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
* {
	margin: 0 auto;
	padding: 0;
	list-style: none;
	font-family: "맑은 고딕";
	color: #333333;
}

h1 {
	text-align: center;
}

body {
	width: 1200px;
	height: 650px;
	background-color: #ffffff;
	font-size: 20px;
}
</style>
<body>
	<h1>회원 정보 출력</h1>
	<form align="center" method="post" action="member.jsp">
		이름<input type="text" name="name"> <input type="submit"
			value="조회하기">
	</form>

	<%
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String name = request.getParameter("name");
		MemberVO vo = new MemberVO();
		vo.setName(name);
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = null;
		
		if(name != null)
			list = dao.listMembers(vo);
		else 
			list = dao.listMembers();

	%>
	<table border=1 width="800" align="center">
		<tr align=center bgcolor="#FFFF66">
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일자</td>
		</tr>

		<%
		for(int i=0; i< list.size();i++)
		{
			MemberVO vo2 = (MemberVO)list.get(i);
			String id = vo2.getId();
		
	%>
		<tr align="center">
			<td><%=id %></td>
			<td><%=vo2.getPwd() %></td>
			<td><%=vo2.getName() %></td>
			<td><%=vo2.getEmail() %></td>
			<td><%=vo2.getJoinData() %></td>
		</tr>
		<%
		}
	%>

	</table>

</body>
</html>