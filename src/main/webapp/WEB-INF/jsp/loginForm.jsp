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
            <form id="loginForm" name="loginForm" method="post" action="/user/login">
                <div class="form-group">
                    <input type="text" class="form-control" name="userId" id="userId">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <button type="submit" class="btn" style="width: 100%; background-color:#537599;color: white">로그인</button>
                <div class="form-group form-check col-6" style="margin-top: 20px">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1" style="font-size: 15px;text-align: center">로그인 유지</label>
                </div>

            </form>
        </div>
    </div>
</div>
<jsp:include page="include/footer.jsp"/>

</body>
</html>
