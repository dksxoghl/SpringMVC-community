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

    #outter {
        display: block;
        width: 30%;
        margin: auto;
    }
</style>
<body>

<h2>게시판</h2>
<div id="outter">
    <table border="1">
        <tr>
            <td> ${board.h_subject }</td>
        </tr>
        <tr>
            <td>
                ${board.h_userName }
                <span><fmt:formatDate value="${board.h_created }" pattern="yyyy.MM.dd hh:ss"/></span>
               view:${board.h_hit}
            </td>
        </tr>
        <tr>
            <td>
                <div style="height: 300px; margin: 10px; display: inline-block">${board.h_content }
                <br/>
                   좋아요:${board.h_like}
                </div>
            </td>
        </tr>
    </table>
    <input type="button" value="글 목록" style="float: right;" onclick="location.href='/'">
    <input type="button" value="글 수정" style="float: right;" onclick="location.href='/'">
    <input type="button" value="글 삭제" style="float: right;" onclick="location.href='/deleteForm?seq=${board.h_id}'">
    <jsp:include page="hyboardList.jsp"/>
</div>
</body>
</html>