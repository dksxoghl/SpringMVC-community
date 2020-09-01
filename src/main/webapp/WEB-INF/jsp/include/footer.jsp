<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width" initial-scale="1">
<%--    <link rel="stylesheet" href="/resources/css/lib/bootstrap.min.css">--%>
    <link rel="stylesheet" href="/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css" type="text/css">
    <title>해연갤</title>
</head>
<body>
<c:forEach var="list" items="${categoryList}">
<c:if test="${list.categoryUrl eq url}">
<div class="container" id="bottom" style="background-color: ${list.categoryColor}; height: 40px; width:70%">
    </c:if>
    </c:forEach>

     <div style=" margin-left: 20px;">
          <a href="/hy" style="color: white; font-size: 30px">HYGALL2.COM</a>
        </div>
</div>
</body>
</html>
