<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page info="db연동" import="java.sql.*"%>
<jsp:useBean id="db" class="usebean.dbBean" scope="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	font-size: 9pt;
}

p {
	width: 600px;
	text-align: right;
}

table thead tr th {
	background-color: gray;
}
</style>
<script type="text/javascript">
	function goUrl(url) {
		location.href = url;
	}
	// 검색 폼 체크
	function searchCheck() {
		var form = document.searchForm;
		if (form.searchText.value == '') {
			alert('검색어를 입력하세요.');
			form.searchText.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<%
	Connection conn = null;
ResultSet rs = null;
PreparedStatement pstmt = null;
%>


<table border="1" summary="게시판 목록">
		<caption>게시판 목록 : 총 게시물 
		<% conn = db.getCon();
		rs = db.total(conn, rs, pstmt); %>
		<% int totalCount = rs.getInt("TOTAL");  %><%= totalCount %> 개</caption>
		<colgroup>
			<col width="50" />
			<col width="80" />
			<col width="80" />
			<col width="100" />
			<col width="70" />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록 일시</th>
				<th>조회수</th>
			</tr>
		</thead>
	<%

   try{
	   conn=db.getCon();

		rs = db.show(conn, rs, pstmt);
		while (rs.next()) {
	%>

		<tbody>
			<%
			if (totalCount == 0) {
			%>
			<tr>
				<td align="center" colspan="5">등록된 게시물이 없습니다.</td>
			</tr>
			<%
			break;
			} else {
			%>
			<tr>
	<!-- 게시물 목록 출력하는 부분 -->
	<td align="center"><%=rs.getString("NUM") %></td>
	<td><a
		href="showArticle.jsp?num=<%=rs.getInt("NUM")%>"><%=rs.getString("SUBJECT") %></a></td>
		<td align="center"><%=rs.getString("WRITER") %></td>
		<td align="center"><%=rs.getDate("REG_DATE") %></td>
		<td align="center"><%=rs.getInt("HIT") %></td>
			</tr>
			<%
				}
			}
			%>
		</tbody>
	</table>
		<p>
		<form action="DeleteArticle.jsp" method="post"> <!-- post 방식 선언 -->
 <input type="text" name="dNum"><br/>
  <input type="submit" value="삭제">
 </form>
		<input type="button" value="목록" onclick="goUrl('boardList.jsp');" />
		<input type="button" value="글쓰기"
			onclick="goUrl('WriteArticleForm.jsp');" />
	</p>
	

</body>
</html>
<%
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (rs != null) rs.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
%>


