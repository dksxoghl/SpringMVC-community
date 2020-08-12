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

</style>
<body>

<div class="container" style="background-color: #537599;">
    <div class="head">
    <div class="row" style=" margin-left: 10px;">
            <c:forEach begin="1" end="5" var="p">
                <div style="width:9%;"><span>해외연예${p}</span></div>
            </c:forEach>
            <div style="width:10%;">문의</div>
            <div style="width:10%;">회원가입</div>
            <div style="width:10%;">로그인</div>
        </div>
</div>
</div>
</body>
</html>
