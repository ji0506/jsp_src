<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	   function readURL(input) {
		      if (input.files && input.files[0]) {
			      var reader = new FileReader();
			      reader.onload = function (e) {
			        $('#preview').attr('src', e.target.result);
		          }
		         reader.readAsDataURL(input.files[0]);
		      }
		  }  
		function backtoList(obj){
			obj.action = "/board/list.do"
			obj.submit();
		}
	</script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <h1 style="text-align: center">새 글 쓰기</h1>
	<form name="aricleform" method="post" action="/board/add.do" enctype="multipart/form-data">
	    <table align="center">
	        <tr>
	            <td align="right">글제목: </td>
				<td colspan="2"><input type="text" size="67" maxlength="500" name="title"></td>
	        </tr>
	        <tr>
	            <td align="right" valign="top">글내용: </td>
				<td colspan="2"><textarea name="content" rows="10" cols="65" maxlength="4000" ></textarea></td>
	        </tr>
	        <tr>
	            <td align="right">이미지파일 첨부: </td>
				<td><input type="file" name="imageFileName" onchange="readURL(this);"></td>
				<td><img id="preview" src="#" width=200 height=200></td>
	        </tr>
	        <tr>
	            <td width="200">
					<p>&nbsp;</p>
				</td>
				<td style="width: 400px;">
					<input type="submit" value="가입하기">
					<input type="button" value="목록보기" onClick="backtoList(this.form)">
				</td>
	        </tr>
	    </table>
	</form>
</body>
</html>