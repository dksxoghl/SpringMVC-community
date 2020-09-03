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
    <script type="text/javascript" src="<c:url value="/js/jquery-3.5.1.js"/>"></script>
    <script type="text/javascript" src="/js/lib/bootstrap.bundle.js"></script> <%--min 링크시 dropdown안됨,jquery보다밑에--%>
    <script src="https://kit.fontawesome.com/c3f6082297.js" crossorigin="anonymous"></script>
    <title>해연갤</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="user"/>
<%--    <sec:authentication property="name"/>--%>
</sec:authorize>
<c:forEach var="list" items="${categoryList}">
    <c:if test="${list.categoryUrl eq url}">
        <style type="text/css">
            .fab-btn {
                background-color: ${list.categoryColor};
                border: white;
                color: white;
                margin-bottom: 1px;
            }
        </style>
    </c:if>
</c:forEach>
<input type="hidden" id="user" value='<c:out value="${user.username}"/>'>
<input type="hidden" id="url" value='<c:out value="${url}"/>'>
<div class="fab">
    <a href="#">
        <button class="fab-btn">▲</button>
    </a>
    <c:if test="${detail !=null}">
        <a href="#rep_refresh">
            <button class="fab-btn " style="width: 27.84px">
                <i class="far fa-comment"></i>
            </button>
        </a>
    </c:if>
    <a href="/${url}/writeForm" id="writeLink">
        <button class="fab-btn " style="width: 27.84px">
            <%--                <img &lt;%&ndash;width='10px' height='10px'&ndash;%&gt; src='/resources/img/pen-solid.svg'>--%>
            <i class="fas fa-pen"></i>
        </button>
    </a>
    <a href="#bottom">
        <button class="fab-btn">▼</button>
    </a>
</div>
<c:forEach var="list" items="${categoryList}">
<c:if test="${list.categoryUrl eq url}">
<div class="container" id="header" style="width:70%; background-color: ${list.categoryColor}">
    </c:if>
    </c:forEach>
    <div class="head">
        <div class="row justify-content-between" style=" margin-left: 10px; text-align: center;">
            <c:forEach var="list" items="${categoryList}">
                <c:if test="${list.categoryUrl eq url}">
                <div class="col col-lg-1 align-self-start"><a style="color: white;" href="/${list.categoryUrl}">
                        ${list.categoryName}</a>
                </div>
                </c:if>
            </c:forEach>
            <c:if test="${url!='nt'}">
            <div class="col col-lg-1 align-self-start dropdown">
                <a style="color: white;" href="/${url}?best=1" id="dropdownMenuLink" <%--data-toggle="dropdown"--%> aria-haspopup="true" aria-expanded="false">
                개념글</a>
<%--    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--        Small button--%>
<%--    </button>--%>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink" >
                    <a class="dropdown-item" href="/${url}?best=1">1</a>
                    <a class="dropdown-item" href="/${url}?best=2">2</a>
                    <a class="dropdown-item" href="/${url}?best=3">3</a>
                </div>
            </div>
            </c:if>
            <c:forEach var="list" items="${categoryList}">
                <c:if test="${list.categoryUrl ne url}">
                <div class="col col-lg-1 align-self-start"><a style="color: white;" href="/${list.categoryUrl}">
                        ${list.categoryName}</a>
                </div>
                </c:if>
            </c:forEach>
            <div class="col col-lg-1 offset-md-1"><a style="color: white;" href="#">문의</a></div>
            <sec:authorize access="isAnonymous()">
                <div class="col col-lg-1 align-self-end"><a style="color: white;" href="/joinForm?url=${url}">회원가입</a>
                </div>
                <div class="col col-lg-1 align-self-end"><a style="color: white;" href="/loginForm?url=${url}">로그인</a>
                </div>
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
    <div class="row">
    <c:if test="${deleteNav==null}">
        <c:forEach var="list" items="${categoryList}">
            <c:if test="${list.categoryUrl eq url}">
             <h3 style="padding-top: 10px;margin-left:1%"><a style="color: #868686;font-weight: bold; " href="/${list.categoryUrl}">${list.categoryName}</a></h3>
                <c:if test="${best!=0 and not empty best}">     <%--best null일경우도 체크--%>
                  <h5 style="padding-top: 10px;margin-left:1%;margin-top:6px; "><a style="color: ${list.categoryColor};font-weight: bold;  " href="/${list.categoryUrl}?best=${best}">개념글</a></h5>
                </c:if>
            </c:if>
        </c:forEach>
    </c:if>

    </div>
</div>
<script type="text/javascript" src="<c:url value="/js/userCheck.js"/>"></script>
</body>
</html>
