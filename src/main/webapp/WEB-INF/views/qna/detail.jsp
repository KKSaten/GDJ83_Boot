<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Qna Detail</h1>
	
	<table>
		<thead>
			<tr>
				<th>Title</th>
				<th>Writer</th>
				<th>Contents</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${vo.boardTitle}</td>
				<td>${vo.boardWriter}</td>
				<td>${vo.boardContents}</td>
			</tr>
			<c:forEach items="${vo.ar}" var="f">
				<img src="/files/qna/${f.fileName}">
			</c:forEach>
				
		</tbody>
	</table>
	
</body>
</html>