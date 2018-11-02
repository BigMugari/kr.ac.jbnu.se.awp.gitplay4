<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
    <jsp:useBean id="db" class="usebean.dbBean" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		Connection Conn = db.getCon();
	PreparedStatement pstmt = null;

		String sql = "insert into jdbc_test values(?, ?)";
		pstmt = Conn.prepareStatement(sql);
		pstmt.setString(1, request.getParameter("username"));
		pstmt.setString(2, request.getParameter("email"));
		pstmt.executeUpdate();

		try{
			sql = "select username, email from jdbc_test";
			pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			int i =1;
			while(rs.next()) {
				out.println(i+" : " + rs.getString(1) + " , " + rs.getString("email")+"<BR>");
				i++;
			
			}
			 rs.close();
			  pstmt.close();
			  Conn.close();
			 }
			 catch(Exception e) {
			  System.out.println(e);
			 }
	%>

</body>
</html>