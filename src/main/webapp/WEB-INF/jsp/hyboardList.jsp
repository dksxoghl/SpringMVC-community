<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page import="java.util.Date" %>--%>
<%--<%@ page import="java.text.SimpleDateFormat" %>--%>
<%--<%--%>
<%--    Date nowTime = new Date();--%>
<%--    SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");--%>
<%--%>--%>
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
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="user"/>
</sec:authorize>
<input type="hidden" id="userDouble" value='<c:out value="${user.username}"/>'> <%--헤더와충돌?글쓰기체크--%>
<div class="container" style="width:70%">
    <div class="row">
        <table class="table">
            <tbody>
            <c:forEach items="${ntList}" var="ntList" varStatus="status">
                <tr class="board-notice">
                    <td style="width:9%; "><img class="notice-img" width='55px' height='26px'
                                                src='/resources/img/notice-images.png'></td>
                    <td style="width:66%; text-align: start; color: #0f74a8">
                        <a href='/${url}/detail/${ntList.hyId}?nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}&best=${best}'>
                            <span style="font-weight: bold; color: #2266EE;">${ntList.hySubject}</span>
                        </a>
                        <span>
                        <c:if test="${ntList.rep!=null}">
                            [${ntList.rep}]
                        </c:if>
                        </span>
                    </td>
                    <td style="width:8%;"><c:out value="${ntList.userId}"/></td>
                    <td style="width:8%;">${ntList.hyCreatedDate}</td>
                    <td style="width:9%;"><c:out value="${ntList.hyHit}"/></td>
                    <td style="width:8%;"><c:out value="${ntList.hyLike}"/></td>
                </tr>
            </c:forEach>
            <c:forEach items="${list}" var="list" varStatus="status">
                <tr class="board-list">
                    <c:choose>
                        <c:when test="${seeingNow==list.hyId}">
                            <td style="width:9%; color: #537599">▶▶</td>
                        </c:when>
                        <c:otherwise>
                            <td style="width:9%;"><c:out value="${list.hyNo}"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td style="width:66%; text-align: start;">
                        <a href='/${url}/detail/${list.hyId}?nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}&best=${best}'>
                            <c:out value="${list.hySubject}"/></a>
                        <span>
                        <c:if test="${list.rep!=null}">
                            [${list.rep}]
                        </c:if>
                        </span>
                    </td>
                    <td style="width:8%;"><c:out value="${list.userId}"/></td>
                        <%--                    <jsp:useBean id="toDay" class="java.util.Date" />--%>
                        <%--                    <fmt:formatDate value='${toDay}' pattern='YYYY-MM-dd' var="nowDate"/>--%>
                        <%--                    <c:choose>--%>
                        <%--                        <c:when test="${nowDate==fn:substring(list.hyCreatedDate,0,10)}">--%>
                        <%--                            <td style="width:8%;"><fmt:formatDate value="${list.hyCreatedDate}"--%>
                        <%--                                                                  pattern="HH:mm"/></td>--%>
                        <%--                        </c:when>--%>
                        <%--                        <c:otherwise>--%>
                        <%--                            <td style="width:8%;"><fmt:formatDate value="${list.hyCreatedDate}"--%>
                        <%--                                                                  pattern="MM-dd"/></td>--%>
                        <%--                        </c:otherwise>--%>
                        <%--                    </c:choose>--%>
                    <td style="width:8%;"><c:out value="${list.hyCreatedDate}"/></td>
                        <%--<td style="width:8%;"><c:out value="${date[status.index]}"/></td>--%>
                    <td style="width:9%;"><c:out value="${list.hyHit }"/></td>
                    <td style="width:8%;"><c:out value="${list.hyLike }"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <c:if test="${searchTarget == null }">
        <div style="text-align: center; background-color: white;">
                <%--        <c:if test="${paging.startPage != 1 }">--%>
            <a href="/${url}?nowPage=1&cntPerPage=${paging.cntPerPage}&best=${best}">&lt;첫 페이지</a>
                <%--        </c:if>--%>
            <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
                <c:choose>
                    <c:when test="${p == paging.nowPage }">
                        <b>${p }</b>
                    </c:when>
                    <c:when test="${p != paging.nowPage }">
                        <a href="/${url}?nowPage=${p }&cntPerPage=${paging.cntPerPage}&best=${best}">${p }</a>
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:if test="${paging.endPage != paging.lastPage}">
                <a href="/${url}?nowPage=${paging.nowPage+1}&cntPerPage=${paging.cntPerPage}&best=${best}">&gt;다음
                    페이지</a>
            </c:if>
        </div>
    </c:if>
    <c:if test="${searchTarget != null }">
        <div style="text-align: center; background-color: white;">
                <%--        <c:if test="${paging.startPage != 1 }">--%>
            <a href="/${url}?nowPage=1&cntPerPage=${paging.cntPerPage}&best=${best}&searchTarget=${searchTarget}&searchKeyword=${searchKeyword}">&lt;첫
                페이지</a>
                <%--        </c:if>--%>
            <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
                <c:choose>
                    <c:when test="${p == paging.nowPage }">
                        <b>${p }</b>
                    </c:when>
                    <c:when test="${p != paging.nowPage }">
                        <a href="/${url}?nowPage=${p}&cntPerPage=${paging.cntPerPage}&best=${best}&searchTarget=${searchTarget}&searchKeyword=${searchKeyword}">${p }</a>
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:if test="${paging.endPage != paging.lastPage}">
                <a href="/${url}?nowPage=${paging.nowPage+1}&cntPerPage=${paging.cntPerPage}&best=${best}&searchTarget=${searchTarget}&searchKeyword=${searchKeyword}">&gt;다음
                    페이지</a>
            </c:if>
        </div>
    </c:if>
    <%--    <c:if test="${url!='nt' or (url=='nt'and user.username=='admin')}">--%>
    <%--    </c:if>--%>
    <div style=" height: 50px">
        <c:choose>
            <c:when test="${url!='nt'}">
                <a class="btn btn-dark" style="float: right;color: whitesmoke"<%-- type="submit" value="글쓰기"--%>
                   id="writeForm2"
                   <%--onclick="location.href='/${url}/writeForm'--%>href="/${url}/writeForm">글쓰기</a>  <%--버튼,input은왜? 리다이렉트되지..--%>
            </c:when>
            <c:when test="${url=='nt'and user.username=='admin'}">
                <a class="btn btn-dark" style="float: right;color: whitesmoke"<%-- type="submit" value="글쓰기"--%>
                   id="writeForm2"
                   <%--onclick="location.href='/${url}/writeForm'--%>href="/${url}/writeForm">글쓰기</a>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>

    </div>
    <form action="/${url}" method="get" style="padding-bottom: 30px">
        <select name="searchTarget" style="border-color: #ccc #aaa #aaa #ccc; height: 30px">
            <option value="hyAll" selected="selected">제목+내용</option>
            <option value="hy_subject">제목</option>
            <option value="hy_content">내용</option>
        </select>
        <input type="text" maxlength="40" style="border-color: #ccc #aaa #aaa #ccc;" name="searchKeyword">
        <input type="checkbox" name="best" value="1">
        개념글만
        <span class="button"><input type="submit" value="검색"></span>
    </form>
</div>
<script type="text/javascript" src="<c:url value="/js/userCheck2.js"/>"></script>
</body>
</html>
