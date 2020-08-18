<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    Date nowTime = new Date();
    SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");
%>
<html>
<head>
    <%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <!-- 뷰포트 -->
    <meta name="viewport" content="width=device-width" initial-scale="1">
    <%--    <link href="<c:url value="/css/lib/bootstrap.min.css" />" rel="stylesheet">--%>
    <%--    <link rel="stylesheet" href="/css/lib/bootstrap.min.css?after">--%>

    <%--    <script src="/js/lib/jquery.min.js"></script>--%>
    <%--    <script src="/js/lib/bootstrap.min.js"></script>--%>
    <title>해연갤</title>
</head>

<body>
<div class="container" style="width:70%">
    <div class="row">
        <table class="table">
            <%--            <tr>--%>
            <%--                <th style="background-color: #eeeeee; text-align: center;">번호</th>--%>
            <%--                <th style="background-color: #eeeeee; text-align: center;">제목</th>--%>
            <%--                <th style="background-color: #eeeeee; text-align: center;">작성자</th>--%>
            <%--                <th style="background-color: #eeeeee; text-align: center;">작성일</th>--%>
            <%--                <th style="background-color: #eeeeee; text-align: center;">조회수</th>--%>
            <%--                <th style="background-color: #eeeeee; text-align: center;">좋아요</th>--%>
            <%--            </tr>--%>
            <tbody>
            <tr class="board-notice">
                <td style="width:9%; ">notice</td>
                <td style="width:66%; text-align: start; color: #0f74a8">
                    [필독] 해연갤입니다.
                </td>
                <td style="width:8%;"><c:out value="공지"/></td>
                <td style="width:8%;">06-03</td>
                <td style="width:9%;"><c:out value="0"/></td>
                <td style="width:8%;"><c:out value="0"/></td>
            </tr>
            <c:forEach var="list" items="${list}" varStatus="status">
                <tr class="board-list">
                    <c:choose>
                        <c:when test="${seeingNow==list.h_id}">
                            <td style="width:9%; color: #537599">▶▶</td>
                        </c:when>
                        <c:otherwise>
                            <td style="width:9%;"><c:out value="${list.h_no }"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td style="width:66%; text-align: start;">
                        <a href='/${url}/detail/${list.h_id}?nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}'>
                            <c:out value="${list.h_subject }"/></a></td>
                    <td style="width:8%;"><c:out value="${list.user_id }"/></td>
                    <c:set var="sysdate" value="<%= sf.format(nowTime)%>" scope="session"/>
                    <c:set var="h_created_date" value="${list.h_created_date}" scope="session"/>
                    <c:choose>
                        <c:when test="${sysdate==fn:substring(h_created_date,0,10)}">
                            <td style="width:8%;"><fmt:formatDate value="${list.h_created_date}"
                                                                  pattern="HH:mm"/></td>
                        </c:when>
                        <c:otherwise>
                            <td style="width:8%;"><fmt:formatDate value="${list.h_created_date}"
                                                                  pattern="MM-dd"/></td>
                        </c:otherwise>
                    </c:choose>
                        <%--                    <td><c:out value="${list.h_created }"/></td>--%>
                    <td style="width:9%;"><c:out value="${list.h_hit }"/></td>
                    <td style="width:8%;"><c:out value="${list.h_like }"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div style="text-align: center; background-color: white;">
        <%--        <c:if test="${paging.startPage != 1 }">--%>
        <a href="/${url}?nowPage=1&cntPerPage=${paging.cntPerPage}">&lt;첫 페이지</a>
        <%--        </c:if>--%>
        <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
            <c:choose>
                <c:when test="${p == paging.nowPage }">
                    <b>${p }</b>
                </c:when>
                <c:when test="${p != paging.nowPage }">
                    <a href="/${url}?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
                </c:when>
            </c:choose>
        </c:forEach>
        <c:if test="${paging.endPage != paging.lastPage}">
            <a href="/${url}?nowPage=${paging.nowPage+1}&cntPerPage=${paging.cntPerPage}">&gt;다음 페이지</a>
        </c:if>
    </div>
    <div style="background-color: white; height: 50px">
        <input class="btn btn-dark" style="float: right" type="button"  value="글쓰기"
               onclick="location.href='/${url}/writeForm'">
    </div>
</div>
</body>
</html>
