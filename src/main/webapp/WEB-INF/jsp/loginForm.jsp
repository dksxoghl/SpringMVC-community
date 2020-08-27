<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width" initial-scale="1">
    <link rel="stylesheet" href="/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css" type="text/css">
    <title>해연갤</title>
</head>
<style>
    .join {
        padding: 10px;
    }
</style>
<body>
<jsp:include page="include/header.jsp"/>
<div class="container" style=" background-color:white;padding-bottom: 50px">
    <div class="card" style="width: 30%; text-align: center;margin:auto;">
        <div class="card-body">
            <form method="post" action="/loginForm">
                <div class="form-group">
                    <input type="text" class="form-control" name="username" id="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <input type="submit" class="btn" style="width: 100%; background-color:#537599;color: white" value="로그인">
                <div class="form-group form-check col-6" style="margin-top: 20px">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="remember-me">  <%--RememberMe 파라메터 기본값이 remember-me--%>
                    <label class="form-check-label" for="exampleCheck1" style="font-size: 15px;text-align: center">로그인 유지</label>
                </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <c:if test="${not empty param.error}">
                    Reason: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </c:if>
            </form>
        </div>
    </div>

</div>
<jsp:include page="include/footer.jsp"/>

</body>
</html>
