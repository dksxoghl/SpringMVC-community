<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width" initial-scale="1">
    <link rel="stylesheet" href="/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css" type="text/css">
    <script type="text/javascript" src="<c:url value="/js/jquery-3.5.1.js"/>"></script>
    <title>해연갤</title>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="container" style=" background-color:white;padding-bottom: 50px;padding-top: 20px">
    <div class="card" style="width: 30%; text-align: center;margin:auto;">
        <div class="card-body">
            <form method="post" action="/loginForm">
                <div class="form-group">
                    <input type="text" placeholder="아이디" class="form-control" name="username" id="username">
                </div>
                <div class="form-group">
                    <input type="password"  placeholder="비밀번호" class="form-control" id="password" name="password">
                </div>
                <input type="submit" id="loginForm" class="btn" style="width: 100%; background-color:#537599;color: white" value="로그인" >
                <div class="form-group form-check col-6" style="margin-top: 20px">
                    <input type="checkbox" class="form-check-input" id="remem-check"
                           name="remember-me"> <%--RememberMe 파라메터 기본값이 remember-me--%>
                    <label class="form-check-label" for="remem-check" style="font-size: 15px;text-align: center">로그인
                        유지</label>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <c:if test="${not empty param.error}">
                    <script type="text/javascript">
                        alert('${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}');
                    </script>
<%--                    Reason: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}--%>
                </c:if>
                <c:if test="${not empty errorMessage}">

<%--                        <p style="color:red">${errorMessage }</p>--%>
                </c:if>
            <sec:authentication property="authorities" var="authorities"/>
<%--            <c:forEach items="${authorities}" var="authority">--%>
<%--                <a>${authority.authority}</a>--%>
<%--            </c:forEach>--%>
            </form>
        </div>
    </div>

</div>
<jsp:include page="include/footer.jsp"/>
<script type="text/javascript" src="<c:url value="/js/validation.js"/>"></script>
</body>
</html>
