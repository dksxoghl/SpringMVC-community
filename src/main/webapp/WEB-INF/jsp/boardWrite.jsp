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
<script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
<jsp:include page="include/header.jsp"/>
<div class="container">
    <form id="writeForm" name="writeForm" method="post" action="/${url}/write">
<%--                    <tr>--%>
<%--                        <th>제목</th>--%>
<%--                        <td><input style="width: 500px" type="text" id="h_subject" name="h_subject" value="${board.h_subject}" /></td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <th>내용</th>--%>
<%--                        <td><input style="width: 500px" type="text" id="h_content" name="h_content" value="${board.h_content}"/></td>--%>
<%--                    </tr>--%>
                        작성자
                       <input style="width: 500px" type="text" id="user_id" name="user_id" value="${board.user_id}"/>

                    <div class="row">
                    <div class="col-sm-9">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text">제목</label>
                            </div>
                            <input type="text" class="form-control" id="h_subject" name="h_subject" value="${board.h_subject}" >
                        </div>
                    </div>

                    </div>

                    <div class="row justify-content-md-center">
                            <div class="input-group">
                                <textarea class="form-control" id="h_content" name="h_content">${board.h_content}</textarea>
                                <script type="text/javascript">
                                    CKEDITOR.replace('h_content'
                                        , {height: 300,width:1536
                                        });
                                </script>
                            </div>
                    </div>


                    <input type='hidden' id='h_id' name='h_id' value='${board.h_id }' />
                    <input type='hidden' id='category_id' name='category_id' value='${board.category_id }' />
                <div>
                    <input type="submit" value="글 등록">
<%--                    onclick="location.href='/{url}/write 젠장~~~--%>
                </div>
<%--        <input type='hidden' id='seq' name='seq' value='${board.h_id }' />--%>
    </form>

</div>

</body>
</html>
