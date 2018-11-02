<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*, javax.sql.*, java.io.*"%>
<jsp:useBean id="db" class="usebean.dbBean" scope="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 		
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	String msg = null;
	
	String id = request.getParameter("userid");
	String pw = request.getParameter("passwd");
	
	boolean loginstatus = false;
	conn = db.getCon();
	rs = db.login(conn, rs, pstmt);
	
	
	while(rs.next()) {
		
		if(rs.getString("userid").equals(id) && rs.getString("passwd").equals(pw)) {
			
			session = request.getSession();
			if (session.isNew()) {
				msg = "님이 새로 로그인하였습니다.";
				session.setMaxInactiveInterval(600);
				out.println("<script> alert('세션이 해제되어 다시 설정합니다.') </script>");
				session.setAttribute("login", rs.getString("userid"));
			} else {
				msg = "이미 로그인하였습니다.";
			}
			loginstatus = true;
			out.println(session.getAttribute("login") + msg);
			out.println("세션 유지 시간 : " + session.getMaxInactiveInterval());
			out.println("<form method=\"get\" action=\"Article.jsp\">\r\n" + 
					"<button>들어가기</button>\r\n" + "</form>");
			out.println("<form method=\"get\" action=\"logout.jsp\">\r\n" + 
					"<button>로그아웃</button>\r\n" + "</form>");
			break;
		}
	}
	if(loginstatus == false) {
		out.println("<script> alert('아이디 및 비밀번호가 틀립니다.') </script>");
	
	out.println("<form method=\"get\" action=\"index.html\">\r\n" + 
			"<button>뒤로가기</button>\r\n" + "</form>");	
	}
	
conn.close();
%>
</body>
</html>