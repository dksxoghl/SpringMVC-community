<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page="include/header.jsp"/>
<div class="container">
    <form id="writeForm" name="writeForm" method="post" action="/${url}/write">
        <div class="row" >
            <div class="col-sm-9" style="padding-top: 0px; padding-left: 0px">
                <div class="input-group input-group-sm mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-sm">제목</span>
                    </div>
                    <input class="form-control form-control-sm" type="text" id="h_subject" name="h_subject"
                           value="${board.h_subject}">
                </div>
            </div>
        </div>
        <div class="row " style="margin-top: 0">
            <div class="input-group">
                <textarea class="form-control" id="h_content" name="h_content">${board.h_content}</textarea>
                <script type="text/javascript">
                    CKEDITOR.replace('h_content'
                        , {
                            height: 300, width: 1536
                        });
                </script>
            </div>
        </div>
        <input type='hidden' id='h_id' name='h_id' value='${board.h_id }'/>
        <input type='hidden' id='category_id' name='category_id' value='${board.category_id }'/>
        <hr/>
        <div>
            작성자
            <input type="text" id="user_id" name="user_id" value="${board.user_id}"/>
            <input style="float: right" type="submit" class="btn btn-primary" value="글 등록">
            <%--                    onclick="location.href='/{url}/write 젠장~~~--%>
        </div>
        <%--        <input type='hidden' id='seq' name='seq' value='${board.h_id }' />--%>
    </form>

</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
