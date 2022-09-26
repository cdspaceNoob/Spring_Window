<%@page import="java.util.Date"%>
<%@page import="gntp.lesson.guestbook.util.DateTimeService"%>
<%@page import="gntp.lesson.guestbook.vo.GuestbookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Update Book</title>
</head>
<body>
<h1>Update Book</h1>
<body>

<form action="update.do" method="post">

<input type="hidden" name="seq" value="${book.seq }">
<table>
	<tr><td>title :</td><td><input type="text" name="title" value="${book.title }"></td><td></td></tr>
	<tr><td>content :</td><td><input type="text" name="content" size="80" value="${book.content }"></td><td></td></tr>
	<tr><td>readCount :</td><td><input type="text" name="readCount" value="${book.readCount }" readonly="readonly"></td><td></td></tr>
	<tr><td>date :</td><td><input type="text" name="date" value="<fmt:formatDate value='${book.regDate }' pattern='YYYY-MM-DD'/>" readonly="readonly"></td><td></td></tr>
	<tr><td>userId :</td><td> <input type="text" name="userId" value="${book.userId }" readonly="readonly"></td><td></td></tr>
	<tr><td colspan="3"><input type="submit" value="글 수정"></td></tr>
</table>	
</form>
</body>
</html>