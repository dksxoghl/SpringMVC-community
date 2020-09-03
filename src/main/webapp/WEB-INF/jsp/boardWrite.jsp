<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>--%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>해연갤</title>
</head>
<body>
<script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-3.5.1.js"/>"></script>
<jsp:include page="include/header.jsp"/>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="user"/>
</sec:authorize>
<div class="container" style="width:70%">
    <form id="writeForm" name="writeForm" method="post" action="/${url}/write">
        <div class="row">
            <div class="col-sm-9" style="padding-top: 0px; padding-left: 0px">
                <div class="input-group input-group-sm mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-sm">제목</span>
                    </div>
                    <input class="form-control form-control-sm" type="text" id="hySubject" name="hySubject"
                           value="${board.hySubject}">
                </div>
            </div>
        </div>
        <div class="row " style="margin-top: 0">
            <div class="input-group">
                <textarea class="form-control" id="hyContent" name="hyContent">${board.hyContent}</textarea>
                <script type="text/javascript">
                    CKEDITOR.replace('hyContent'
                        , {
                            height: 300, width: 1536
                        });
                </script>
            </div>
        </div>

        <input type='hidden' id='hyId' name='hyId' value='${board.hyId}'/>
        <input type='hidden' id='categoryId' name='categoryId' value='${board.categoryId }'/>
        <hr/>
        <input type="hidden" id="userId" name="userId" value="${user.username}"/>
        <c:if test="${url!=nt and user.username=='admin'}">
        <input type="hidden" id="isAdmin" name="isAdmin" value="true"/>
        </c:if>
        <div style="background-color: white; height: 50px">
            <input style="float: right" type="submit" class="btn btn-dark" id="check" value="글 등록"/>
        </div>
        <%--        <input type='hidden' id='seq' name='seq' value='${board.h_id }' />--%>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    <script type="text/javascript">
        $(document).ready(function () {
            $(document).on("click", "#check", function (event) {
                var userId = $("input[name=userId]");
                console.log(userId.val());
                if ( userId.val() == null || userId.val()=="") {
                    alert('id 필수');
                    event.preventDefault();
                }
            });
        });
    </script>
</div>
<jsp:include page="include/footer.jsp"/>

</body>
</html>
