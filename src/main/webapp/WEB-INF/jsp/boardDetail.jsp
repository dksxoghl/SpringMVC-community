<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<c:url value="/js/jquery-3.5.1.js"/>"></script>
    <%--    <script type="text/javascript" src="/resources/js/jquery-3.5.1.js"></script>--%>

    <title>해연갤</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<style>
    .btn-dark {
        margin: 3px;
    }
    .re_b {
        font-size: 5px;
    }
</style>
<%--<script>--%>
<%--    window.boardHyId='${board.hyId}';--%>
<%--    let boardHyId2='<c:out value="${board.hyId}"/>';--%>
<%--</script>--%>
<body>
<jsp:include page="include/header.jsp"/>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="user"/>
</sec:authorize>
<input type="hidden"  id="boardHyId"  value='<c:out value="${board.hyId}"/>'> <%--js변수공유--%>
<input type="hidden"  id="username"  value='<c:out value="${user.username}"/>'>
<div class="container" style="width:70%">
    <div class="row" style=" border-bottom: 3px inset  #bcbcbc; ">
        <div style="font-size: 1.4em; font-weight: bold; margin-left: 10px">●${board.hySubject }</div>
        <div class="col align-self-end">
            <div style="float: right;">${board.userId}</div>
        </div>
    </div>
    <div class="row" style="border-bottom: 1px inset  #bcbcbc; ">
<%--        <div style="margin-left: 10px;color:#777;">${board.hyUrl }</div>--%>
        <div style="margin-left: 10px;color:#777;">http://localhost:8080/${url}/detail/${board.hyId}</div>
        <div class="col align-self-end">
            <div style="float: right;">
                <span style="font-weight: bold; color:#777;"> <fmt:formatDate value="${board.hyCreatedDate }"
                                                                              pattern="yyyy.MM.dd"/></span>
                <fmt:formatDate value="${board.hyCreatedDate }" pattern="hh:mm"/>
                &nbsp <span style="font-weight: bold; color:#777;">view  &nbsp</span>${board.hyHit}
            </div>
        </div>
    </div>
    <div style="margin-top:30px">${board.hyContent }
    </div>
    <div class="row justify-content-around" style="margin-top:30px">
        <div class="col-md-3">
            <button type="button" class="btn btn-outline-secondary">북마크</button>
        </div>
        <div class="col-md-3 offset-md-2">
            <button type="button" id="like-up" class="btn btn-outline-secondary"><img width='13px' height='13px' src='/resources/img/thumbs-up-regular.svg'>&nbsp추천 <span style="color: orangered">0</span></button>
            <button type="button" id="like-already" class="btn btn-outline-secondary" style=" display: none"><img width='13px' height='13px' src='/resources/img/thumbs-up-solid.svg'>&nbsp추천 <span style="color: orangered">1</span></button>
        </div>
        <div class="col-md-2 offset-md-2">
            <button type="button" class="btn btn-outline-secondary">신고</button>
        </div>
    </div>
    <div style="color:#777; border-bottom: 1px inset  #bcbcbc;">http://localhost:8080/${url}/detail/${board.hyId}</div>
    <div class="row" style="margin-left:5px">
        <div><a id="toggle">댓글</a><span id="re_length">[0]</span></div>
        <div class="col align-self-end">
            <input class="b_rud btn btn-dark" type="button" value="글 목록" style="float: right; font-size: 6px"
                   onclick="location.href='/${url}'">
            <form id="writeForm" name="writeForm" method="post" action="/${url}/writeForm">
                <input type='hidden' id='hyId' name='hyId' value='${board.hyId }'/> <%--id get으로--%>
                <input type='hidden' id='categoryId' name='categoryId' value='${board.categoryId }'/>
                <input type='hidden' id='hySubject' name='hySubject' value='${board.hySubject }'/>
                <input type='hidden' id='userId' name='userId' value='${board.userId }'/>
                <%--            <input type='hidden' id='h_created_date' name='h_created_date' value= <fmt:formatDate value="${board.h_created_date}" pattern="yyyy.MM.dd hh:ss" /> />--%>
                <input type='hidden' id='hyHit' name='hyHit' value='${board.hyHit }'/>
                <input type='hidden' id='hyContent' name='hyContent' value='${board.hyContent }'/>
                <input type='hidden' id='hyLike' name='hyLike' value='${board.hyLike }'/>
                <c:if test="${user.username==board.userId ||user.username=='admin'}">
                <input class="b_rud btn btn-dark" type="submit" value="글 수정" style="float: right;font-size:6px">
                </c:if>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            </form>
            <c:if test="${user.username==board.userId ||user.username=='admin'}">
            <input class="b_rud btn btn-dark" type="button" value="글 삭제" style="float: right;font-size: 6px"
                   onclick="location.href='/${url}/deleteForm/${board.hyId}'">
            </c:if>
        </div>
    </div>
    <div id="reply">
        <div class="rep_page"
             style="text-align: center;background-color: #f5f5f5; width: 99%; padding-right: 10px"></div>
        <div id="rep_info" style="background-color: #f5f5f5;width: 99%;padding: 20px 20px 20px 30px; ">
            <%--            <div class="row">--%>
            <%--                <div class="col-1">user</div>--%>
            <%--                <div class="col-3 offset-md-6">2020.~~~(~~~~~~)</div>--%>
            <%--                <div class="col-2 ">--%>
            <%--                    <button>신고</button>--%>
            <%--                    <button>x</button>--%>
            <%--                    <button>댓</button>--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <%--            <div >content</div>--%>
            <%--            <div style="border-top:1px dashed #ccc; height: 20px"></div>--%>
        </div>
        <div class="rep_page"
             style="text-align: center;background-color: #f5f5f5; margin-bottom: 10px;width: 99%; padding-right: 10px">
            <%--            <c:if test="${replyPage.total>10}">--%>
            <%--            <a href="/reply/all/1">&lt;첫 페이지</a>--%>
            <%--            <c:forEach begin="${replyPage.startPage }" end="${replyPage.endPage }" var="p">--%>
            <%--                <c:choose>--%>
            <%--                    <c:when test="${p == replyPage.nowPage }">--%>
            <%--                        <b>${p }</b>--%>
            <%--                    </c:when>--%>
            <%--                    <c:when test="${p != replyPage.nowPage }">--%>
            <%--                        <a href="/reply/all/" +${p}>${p }</a>--%>
            <%--                    </c:when>--%>
            <%--                </c:choose>--%>
            <%--            </c:forEach>--%>
            <%--            <a href="/reply/all/" +${replyPage.lastPage}>끝 페이지</a>--%>
            <%--            </c:if>--%>
        </div>
        <div id="rep_refresh" style="width: 99%;height:38px; border:1px inset #F6F6F6;margin-top: 3px">
            <button style="float: right;" class="btn-dark" id="refresh">댓로고침</button>
        </div>
        <div id="rep_ban" style= "height:180px;">
            <textarea style="width: 99%; height: 80px" readonly="readonly">로그인이 필요한 서비스입니다.</textarea></div>
        <div id="rep_edit" style=" height:180px">
            <textarea id="re_content" style="width: 99%; height: 80px"></textarea>
            <div>
                <button style="float: right;" class="btn-dark" id="re_insert">댓글등록</button>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="hyboardList.jsp"/>
<jsp:include page="include/footer.jsp"/>

<script type="text/javascript" src="<c:url value="/js/boardDetail.js"/>"></script>
</body>
</html>