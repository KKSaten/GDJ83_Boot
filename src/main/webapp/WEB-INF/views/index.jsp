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
</head>
<body>
	<h1>Index Page</h1>
	<img alt="" src="/images/yay_420.gif">
	
	
	<sec:authorize access="!isAuthenticated()">
		<h1>Login 하기 전</h1>
		<a href="/member/login">로그인 페이지 이동</a>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<h1>Login 성공</h1>
		<sec:authentication property="principal" var="member"/>
		<spring:message code="member.login.message" arguments="${member.username},${member.email}" argumentSeparator=","></spring:message><br><br>
		<a href="/member/mypage">마이 페이지</a><br><br>
		<a href="/member/logout">로그아웃</a>
		<c:forEach items="${member.vos}" var="r">
			<h3>${r.roleName}</h3>
		</c:forEach>
	</sec:authorize>
	
	
	<sec:authorize access="hasRole('ADMIN')">
		<h1>관리자 전용</h1>
	</sec:authorize>
	
	
	
	
	
	
	
</body>
</html>