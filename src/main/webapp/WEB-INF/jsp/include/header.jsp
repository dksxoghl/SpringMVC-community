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
    /*.container {*/
    /*    font: 12px Malgun Gothic;*/
    /*}*/

    body {
        /*background-color: #eeeeee;*/
        margin: 0;
        font-family: Arial, Helvetica, Malgun Gothic, sans-serif;
    }

    .head {
        margin-top: 25px;
        padding: 18px 15px;
        font: 12px Malgun Gothic;
        color: white;
        /*width: 100%;*/
    }
</style>
<body>

<div class="container" style="background-color: #537599;">
    <div class="head">
        <div class="row justify-content-between" style=" margin-left: 10px; text-align: center;">
            <c:forEach var="list" items="${categoryList}">
                <div class="col col-lg-2 align-self-start"><a href="/${list.category_url}">${list.category_name}</a>
                </div>
            </c:forEach>
            <%--            <div class="col-md-auto">--%>

            <%--            </div>--%>
            <div class="col col-lg-1 offset-md-1">문의</div>
            <div class="col col-lg-2 align-self-end">회원가입</div>
            <div class="col col-lg-1 align-self-end">로그인</div>
        </div>

    </div>

</div>
<div class="container" style="background-color: white;">

    <c:forEach var="list" items="${categoryList}">
        <c:if test="${list.category_url eq url}">
            <h3 style="margin-bottom: 1px"><a href="/${list.category_url}">${list.category_name}</a></h3>
        </c:if>
<%--            <a href="/${list.category_url}">${list.category_name}</a>--%>
    </c:forEach>
</div>
</body>
</html>
