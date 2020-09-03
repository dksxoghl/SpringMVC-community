<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width" initial-scale="1">
    <link rel="stylesheet" href="/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css" type="text/css">
    <script type="text/javascript" src="<c:url value="/js/jquery-3.5.1.js"/>"></script>
    <title>해연갤</title>
</head>
<style>
    .join {
        padding: 10px;
    }
</style>
<body>
<jsp:include page="include/header.jsp"/>
<div class="container" style="padding: 0px; ">
    <p style="padding-top: 20px">◎ 회원 가입</p>
    <div style="width: 100%; text-align: center">
        기본정보
        <%--    <form>--%>
        <%--        <div class="form-group row">--%>
        <%--            <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>--%>
        <%--            <div class="col-sm-10">--%>
        <%--                <input type="text"  id="staticEmail">--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--        <div class="form-group row">--%>
        <%--            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>--%>
        <%--            <div class="col-sm-10">--%>
        <%--                <input type="password" class="form-control" id="inputPassword">--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--    </form>--%>
    </div>
    <form name="joinForm" method="post" action="/user/join">
        <div class="row join">
            <div class="col-2">아이디 *</div>
            <div class="col-4">
                <input type="text" id="username" name="userId">
                <div>아이디는 3~20자로 되어야 합니다.</div>
            </div>
        </div>
        <div class="row join">
            <div class="col-2">비밀번호 *</div>
            <div class="col-4">
                <input type="password" id="password" name="password">
                <div>비밀번호는 6~20자로 되어야 합니다.</div>
            </div>
        </div>
        <div class="row join">
            <div class="col-2">비밀번호 확인 *</div>
            <div class="col-4">
                <input type="password" id="password2">
            </div>
        </div>
        <div class="row join">
            <div class="col-2">이메일</div>
            <div class="col-4">
                <input type="text" id="email" name="email">
            </div>
        </div>
        <div class="row join">
            <div class="col-2">성인입니까? *</div>
            <div class="col-4">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="adult" id="adultRadio" value=true>
                    <label class="form-check-label" for="adultRadio">Yes</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="adult" id="adultRadio2" value=false>
                    <label class="form-check-label" for="adultRadio2">No</label>
                </div>
                <%--            <label>--%>
                <%--                <input type="checkbox" id="adult" name="adult"> Yes--%>
                <%--            </label>--%>
                <%--            <label>--%>
                <%--                <input type="checkbox" id="adult" name="adult"> No--%>
                <%--            </label>--%>
            </div>
        </div>
        <div class="row" style="background-color:#EAEAEA;width: 100%; margin:0px">
            <div class="offset-10 join">
                <input type="submit" class="btn-dark" value="회원가입" id="joinButton">
                <a class="btn-info" onclick="location.href='javascript:history.back();'">취소</a>
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</div>
<jsp:include page="include/footer.jsp"/>
<script type="text/javascript" src="<c:url value="/js/validation.js"/>"></script>
</body>
</html>
