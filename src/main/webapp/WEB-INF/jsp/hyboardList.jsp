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
                   <td> <a href='detail?seq=${list.h_id}&nowPage=${ paging.nowPage}&cntPerPage=${paging.cntPerPage}'><c:out value="${list.h_subject }"/></a></td>
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
    </div>

    <div style="display: block; text-align: center;">
<%--        <c:if test="${paging.startPage != 1 }">--%>
            <a href="/hy?nowPage=1&cntPerPage=${paging.cntPerPage}">&lt;첫 페이지</a>
<%--        </c:if>--%>
        <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
            <c:choose>
                <c:when test="${p == paging.nowPage }">
                    <b>${p }</b>
                </c:when>
                <c:when test="${p != paging.nowPage }">
                    <a href="/hy?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
                </c:when>
            </c:choose>
        </c:forEach>
        <c:if test="${paging.endPage != paging.lastPage}">
            <a href="/hy?nowPage=${paging.nowPage+1}&cntPerPage=${paging.cntPerPage}">&gt;다음 페이지</a>
        </c:if>
    </div>
</div>



</body>
</html>
