<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <!-- 뷰포트 -->
    <meta name="viewport" content="width=device-width" initial-scale="1">
    <title>Welcome</title>
</head>

<body>
<h2>Welcome to Court Reservation System</h2>
Today is <fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>.
<div class="container">
    <div class = "row">

        <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
            <thead>
            <tr>
                <th style="background-color: #eeeeee; text-align: center;">번호</th>
                <th style="background-color: #eeeeee; text-align: center;">제목</th>
                <th style="background-color: #eeeeee; text-align: center;">작성자</th>
                <th style="background-color: #eeeeee; text-align: center;">작성일</th>
                <th style="background-color: #eeeeee; text-align: center;">조회수</th>
                <th style="background-color: #eeeeee; text-align: center;">좋아요</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="list" items="${list}" varStatus="status">
                <tr>
                    <td><c:out value="${list.id }"/></td>
                    <td><c:out value="${list.subject }"/></td>
                    <td><c:out value="${list.userName }"/></td>
                    <td><c:out value="${list.created }"/></td>
                    <td><c:out value="${list.hit }"/></td>
                    <td><c:out value="${list.like }"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

<%--        <a href = "write.jsp" class="btn btn-primary pull-right">글쓰기</a>--%>

    </div>

</div>



</body>
</html>
