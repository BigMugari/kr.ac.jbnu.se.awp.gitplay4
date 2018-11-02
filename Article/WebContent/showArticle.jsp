<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page info="db연동" import="java.sql.*"%>
    <% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="db" class="usebean.dbBean" scope="page" />

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
conn=db.getCon();
String num = request.getParameter("num");
db.hit(conn, rs, pstmt, num);
rs = db.detail(conn, rs, pstmt, num);

%>

제목 : <%=rs.getString("SUBJECT") %>
내용 : <%=rs.getString("CONTENTS") %>
<form name="back" action="Article.jsp" method="post">
		<button>뒤로가기</button></form>
<%  %>
</body>
</html>