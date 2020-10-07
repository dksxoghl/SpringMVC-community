<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width" initial-scale="1">
    <link rel="stylesheet" href="/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css" type="text/css">
    <title>해연갤</title>
</head>
<style>


</style>
<body>
<jsp:include page="include/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-1">
            <button>회원정보</button>
        </div>
    </div>
    <sec:authentication property="principal" var="user"/>
    ${user.username}
    과연:<sec:authentication property="name"/>
</div>
<jsp:include page="include/footer.jsp"/>

</body>
</html>
