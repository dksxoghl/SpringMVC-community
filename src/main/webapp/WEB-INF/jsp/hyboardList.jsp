<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <!-- 뷰포트 -->
    <meta name="viewport" content="width=device-width" initial-scale="1">
    <title>해연갤</title>
</head>

<body>
<h1>해외연예</h1>
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
                    <td><c:out value="${list.h_id }"/></td>
                   <td> <a href='detail?seq=${list.h_id}'><c:out value="${list.h_subject }"/></a></td>
                    <td><c:out value="${list.h_userName }"/></td>
                    <td><fmt:formatDate value="${list.h_created}" pattern="MM-dd"/></td>
<%--                    <td><c:out value="${list.h_created }"/></td>--%>
                    <td><c:out value="${list.h_hit }"/></td>
                    <td><c:out value="${list.h_like }"/></td>
                </tr>
            </c:forEach>
            </tbody>
            <input type="button" value="글쓰기" style="float: right;" onclick="location.href='/writeForm'">
        </table>

<%--        <a href = "write.jsp" class="btn btn-primary pull-right">글쓰기</a>--%>

    </div>

</div>



</body>
</html>
