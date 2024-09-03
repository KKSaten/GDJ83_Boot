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

	<h1>Qna Add</h1>
	
	<form method="post">
		<input type="text" name="boardTitle">
		<input type="text" name="boardWriter">
		<textarea name="boardContents"></textarea>
		<button type="submit">Add</button>
	</form>
	
</body>
</html>