<%@page import="java.util.Date"%>
<%@page import="gntp.lesson.guestbook.util.DateTimeService"%>
<%@page import="gntp.lesson.guestbook.vo.GuestbookVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guestbook list</title>
</head>
<body>
<h1>Guestbook List</h1>
<a href="viewWriteBook.do">방명록 작성</a>
<table>
<%-- <% 
	ArrayList<GuestbookVO> list = (ArrayList<GuestbookVO>)request.getAttribute("list");
	for(GuestbookVO book : list){
%> --%> 
<c:forEach var="book" items="${list}">
	<tr>
		<td>${book.seq }</td>
		<td>${book.userId}</td>
		<td><a href="read.do?seq=${book.seq}&token=on">${book.title }</a></td>
		<td>${book.content }</td>
		<td><fmt:formatDate value="${book.regDate }" pattern="YYYY-MM-dd"/></td>
		<td>${book.readCount }</td>
		<td><a href="delete.do?seq=${book.seq }">삭제</a></td>
	</tr>
<%--<% } %> --%>	
</c:forEach>
</table>
</body>
</html>