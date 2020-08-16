<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>해연갤</title>
</head>
<style>
    h2 {
        text-align: center;
    }

    table {
        width: 100%;
    }

    textarea {
        width: 100%;
    }

    /*#outter {*/
    /*    display: block;*/
    /*    width: 30%;*/
    /*    margin: auto;*/
    /*}*/
</style>
<body>

<jsp:include page="include/header.jsp"/>
<div class="container">
    <div class="row">
        <div>${board.h_subject }</div>
        <div class="col align-self-end" style="float: right;">
            <div style="float: right;">${board.user_id}</div>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div>${board.h_url }</div>
        <div class="col align-self-end">
            <div style="float: right;">
            <fmt:formatDate value="${board.h_created_date }" pattern="yyyy.MM.dd hh:ss"/>
            &nbsp view:${board.h_hit}
            </div>
        </div>
    </div>
    <hr/>
    <div>${board.h_content }
    </div>
    <div class="row justify-content-around">
        <div class="col-md-3">북마크</div>
        <div class="col-md-3 offset-md-2"> 추천 ${board.h_like}</div>
        <div class="col-md-2 offset-md-2">신고</div>
    </div>
    <div>${board.h_url}</div>
    <hr/>
    <div class="row">
        <div>댓글</div>
        <div class="col align-self-end">
            <input type="button" value="글 목록" style="float: right;" onclick="location.href='/'">
            <form id="writeForm" name="writeForm" method="post" action="/${url}/writeForm">
                <input type='hidden' id='h_id' name='h_id' value='${board.h_id }'/>
                <input type='hidden' id='category_id' name='category_id' value='${board.category_id }'/>
                <input type='hidden' id='h_subject' name='h_subject' value='${board.h_subject }'/>
                <input type='hidden' id='user_id' name='user_id' value='${board.user_id }'/>
                <%--            <input type='hidden' id='h_created_date' name='h_created_date' value= <fmt:formatDate value="${board.h_created_date}" pattern="yyyy.MM.dd hh:ss" /> />--%>
                <input type='hidden' id='h_hit' name='h_hit' value='${board.h_hit }'/>
                <input type='hidden' id='h_content' name='h_content' value='${board.h_content }'/>
                <input type='hidden' id='h_like' name='h_like' value='${board.h_like }'/>
                <input type="submit" value="글 수정" style="float: right;">
            </form>
            <%--        <input type="button" value="글 수정" style="float: right;" onclick="location.href=--%>
            <%--                '/${url}/writeForm?h_subject=${board.h_subject}&h_content=${board.h_content}&user_id=${board.user_id}&h_id=${board.h_id}&category_id=${board.category_id}'">--%>
            <input type="button" value="글 삭제" style="float: right;"
                   onclick="location.href='/${url}/deleteForm?seq=${board.h_id}'">
        </div>
    </div>

</div>
<jsp:include page="hyboardList.jsp"/>

</body>
</html>