<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width" initial-scale="1">
<%--    <link rel="stylesheet" href="/resources/css/lib/bootstrap.min.css">--%>
<%--    <link rel="stylesheet" href="/resources/css/main.css" type="text/css">--%>
    <link rel="stylesheet" href="/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css" type="text/css">
    <title>해연갤</title>
</head>


<body>
    <div class="fab">
        <%--    <button type="button" class="btn btn-primary">▲</button>--%>
            <a href="#" > <button class="fab-btn">▲</button></a>
            <a href="/${url}/writeForm"> <button class="fab-btn " style="width: 27.84px">글</button></a>
            <a href="#bottom"> <button class="fab-btn">▼</button></a>
    </div>
<div class="container" id="header" style="background-color: #537599;width:70%">
    <div class="head">
        <div class="row justify-content-between" style=" margin-left: 10px; text-align: center;">
            <c:forEach var="list" items="${categoryList}">
                <div class="col col-lg-1 align-self-start"><a style="color: white;" href="/${list.categoryUrl}">${list.categoryName}</a>
                </div>
            </c:forEach>
            <%--            <div class="col-md-auto">--%>
            <%--            </div>--%>
            <div class="col col-lg-1 offset-md-1"><a style="color: white;" href="#">문의</a></div>
            <sec:authorize access="isAnonymous()">
                <div class="col col-lg-1 align-self-end"><a style="color: white;" href="/${url}/joinForm">회원가입</a></div>
                <div class="col col-lg-1 align-self-end"><a style="color: white;" href="/${url}/loginForm">로그인</a></div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <div class="col col-lg-1 align-self-end"><a style="color: white;" href="/${url}/myPage">My</a></div>
                <div class="col col-lg-1 align-self-end">
<%--                    <form id="logOut" action="/${url}/logOut" method="POST">--%>
<%--                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--                        <input  type="submit" value="로그아웃"/>--%>
<%--                    </form>--%>
                    <a style="color: white;" href="/${url}/logOut">로그아웃</a>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>
<div class="container" style="background-color: white; width:70%;  height: 10%;">
    <c:if test="${myPage==null}">
    <c:forEach var="list" items="${categoryList}">
        <c:if test="${list.categoryUrl eq url}">
            <h3 style="padding-top: 10px;margin:0px"><a style="color: #868686" href="/${list.categoryUrl}">${list.categoryName}</a></h3>
        </c:if>
    </c:forEach>
    </c:if>
</div>
</body>
</html>
