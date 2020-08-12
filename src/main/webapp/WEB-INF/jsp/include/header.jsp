<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width" initial-scale="1">
    <link rel="stylesheet" href="/css/lib/bootstrap.min.css">
    <title>해연갤</title>
</head>
<style>
    .container {
        width: 100%;
        background-color: #0c5460;
    }
</style>
<body>
<div class="container">
    <div class="row">
    <c:forEach begin="1" end="5" var="p">
        <div class="col-md-1">${p}</div>
    </c:forEach>
        <div class="col-md-2 col-md-pull-7" style="text-align: end">회원가입</div>
    </div>
    <h1>해외연예</h1>
    <div class="row">
        <div class="col-md-9 col-md-push-3">.col-md-9 .col-md-push-3</div>
        <div class="col-md-3 col-md-pull-9">.col-md-3 .col-md-pull-9</div>
    </div>
</div>

</body>
</html>
