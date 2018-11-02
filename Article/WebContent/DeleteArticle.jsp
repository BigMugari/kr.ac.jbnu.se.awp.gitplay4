<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page info="db연동" import="java.sql.*"%>
    <% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="show" class="usebean.dbBean" scope="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Connection conn = null;
ResultSet rs = null;
PreparedStatement pstmt = null;
%>
<% 
conn=show.getCon();
String num = request.getParameter("dNum");
show.delete(conn, pstmt, num);

%>

삭제되었습니다
<form name="back" action="Article.jsp" method="post">
		<button>뒤로가기</button></form>
<% 

%>
</body>
</html>