<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% pageContext.setAttribute("newline", "\n"); %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title }</h1>
			<ul>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/blog/admin">블로그 관리</a></li>
			</ul>
			
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>Spring Camp 2016 참여기</h4>
					<c:choose>
						<c:when test="${empty postVo }">
							<p> 카테고리와 함께 하단의 원하는 포스트를 선택해주세요 <p>
						</c:when>
						<c:otherwise>
							<p> ${fn:replace(postVo.contents, newline, "<br/>") } <p>
						</c:otherwise>
					</c:choose>
					
			 <!--	<p>
						스프링 캠프에서는 JVM(Java Virtual Machine) 기반 시스템의 백엔드(Back-end) 또는 서버사이드(Server-side)라고 칭하는 영역을 개발하는 애플리케이션 서버 개발에 관한 기술과 정보, 경험을 공유하는 컨퍼런스입니다.<br>
						 핵심주제로 Java와 Spring IO Platform을 다루고 있으며, 그외 Architecture나 JVM Language, Software Development Process 등 애플리케이션 서버 개발에 필요한 다양한 주제를 다루려고 노력하고 있습니다.<br>
						 우리는 같은 일을 하고, 같은 관심사를 가진 개발자들이지만 서로를 모릅니다.<br>
						 스프링 캠프라는 컨퍼런스에 찾아온 낯선 개발자들 사이에서 자신을 소개하고 이야기를 나누고 웃고 즐기며면서 어색함을 떨쳐내고 우리가 같은 분야에서 함께 일하고 있는 친구이자 동료라는 것을 인지하고 새로운 인연의 고리를 연결하고 이어갈 수 있는 순간으로 만들어가려 합니다.
					<p> -->

				</div>
				<h3>포스트 리스트</h3>
				<ul class="blog-list">
					<c:forEach items="${postlist }" var="list">
						<li><a href="${pageContext.request.contextPath}/${authUser.id }/${list.categoryNo }/${list.no }">${list.title }></a> <span>2015/05/02</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/${blogVo.logo}">  
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			
			<ul>
				<c:forEach items="${categorylist }" var="list" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/${authUser.id }/${list.no }">${list.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>${blogVo.title }</strong> is powered by JBlog (c)2022
			</p>
		</div>
	</div>
</body>
</html>