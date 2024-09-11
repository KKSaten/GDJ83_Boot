<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
	
	<h1>My Page</h1>
	
	<sec:authentication property="principal" var="vo"/>
	<h3>${vo.username}</h3>
	<h3>${vo.name}</h3>
	<h3> <sec:authentication property="principal.email"/> </h3> <!-- 이렇게 바로 꺼내는 것도 가능 -->
	<h3> <sec:authentication property="name"/> </h3> <!-- authentication 안에 principal말고도 name이라고해서 바로 id가(username) 들어가있음 -->
	
	<a href="./update">회원수정</a>
	
</body>
</html>