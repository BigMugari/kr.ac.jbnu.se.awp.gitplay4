<%@ page language="java" contentType="text/html; charset=UTF-8" import = "example.SignInClass"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	SignInClass signIn = new SignInClass();
	signIn.SignIn(request.getParameter("id"),request.getParameter("password"));
%>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<%  out.println( signIn.getId() + "님 안녕하세요");  %>
</head>
<body>
</body>
</html>