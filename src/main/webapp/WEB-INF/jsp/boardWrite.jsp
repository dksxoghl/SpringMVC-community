<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>--%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>해연갤</title>
</head>

<body>
<div>
    <form id="writeForm" name="writeForm" method="post" action="/write">
        <div>
            <h2>글쓰기</h2>
            <div>
                <table>
                    <tr>
                        <th>제목</th>
                        <td><input style="width: 500px" type="text" id="h_subject" name="h_subject" value="${board.h_subject}" /></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td><input style="width: 500px" type="text" id="h_content" name="h_content" value="${board.h_content}"/></td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td><input style="width: 500px" type="text" id="h_userName" name="h_userName" value="${board.h_userName}"/></td>
                    </tr>
                    <input type='hidden' id='h_id' name='h_id' value='${board.h_id }' />
                </table>
                <div>
                    <input type="submit" value="글 등록" onclick="location.href='/write'">
                </div>
            </div>
        </div>
<%--        <input type='hidden' id='seq' name='seq' value='${board.h_id }' />--%>
    </form>

</div>
</body>
</html>
