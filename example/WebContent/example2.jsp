<%@ page language="java" contentType="text/html; charset=UTF-8" import = "example.UserInfo,example.SignUpClass"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("utf-8"); %>

<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String dob = request.getParameter("dob");
	String username = request.getParameter("username");

	if(id != null || password != null || 
	dob != null || username != null){
		SignUpClass signUp = new SignUpClass();
		UserInfo userInfo = new UserInfo(id, password, dob, username);
		signUp.signUp(userInfo);
	}
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<H2>회원가입 성공</H2>
</body>
</html>