<%@page import="gntp.lesson.guestbook.vo.ReplyVO"%>
<%@page import="java.util.ArrayList"%>
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
	<script>
		function goPage(type,frm){
			if(type==0){
				frm.action = "viewUpdateBook.do";
			} else {
				frm.action = "writeReply.do";
			}
			frm.submit();
		}
	</script>
	<style>
		a {
			text-decoration: none;
		}
	</style>
	<title>Read Book</title>
	</head>
	<body>
	<h1>Read Book</h1>
	<body>
	
	<form action="#" method="post" name="frm">
	<input type="hidden" name="seq" value="${book.seq}">
	<table>
		<tr><td>title :</td><td><input type="text" name="title" value="${book.title }" readonly="readonly"></td><td></td></tr>
		<tr><td>content :</td><td><input type="text" name="content" size="80" value="${book.content }" readonly="readonly"></td><td></td></tr>
		<tr><td>readCount :</td><td><input type="text" name="readCount" value="${book.readCount }" readonly="readonly"></td><td></td></tr>
		<tr><td>date :</td><td><input type="text" name="date" value='<fmt:formatDate value="${book.regDate }" pattern="YYYY-MM-dd HH:mm:ss" />' readonly="readonly"></td><td></td></tr>
		<tr><td>userId :</td><td> <input type="text" name="userId" value="${book.userId }" readonly="readonly"></td><td></td></tr>
		<tr>
			<td colspan="3">
				<input type="submit" value="수정하기" onclick="goPage(0,document.frm)">
				<input type="button" value="뒤로가기" onclick="javascript:history.back();">
				<button type="button"><a href="list.do">리스트로 가기</a></button>
		 	</td>
		</tr>
	</table><br/>	
	<!-- 댓글 목록 -->
	<table>
	
	<c:if test="${book.replyList != null }">
	<c:forEach var="vo" items="${book.replyList }">
		<tr>
			<td colspan="3">
				${vo.replySeq } 
				${vo.replyContent } 
				<fmt:formatDate value="${vo.replyDate }" pattern="YYYY-MM-DD"/>
			</td>
		</tr>
	</c:forEach>
	</c:if>
		
	</table>
	
	<br/>
	<table>
		<tr><td>댓글</td><td></td><td></td></tr>
		<tr><td colspan="3"><input type="text" name="replyContent" value="" size="100"></td></tr>
		<tr><td colspan="3"><input type="submit" value="댓글쓰기" onclick="goPage(1,document.frm)"></td></tr>
	</table>
	</form>
	</body>
</html>