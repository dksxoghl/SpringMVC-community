<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>해연갤</title>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="container">
<%--        <div>--%>
<%--            <h2>삭제할거임?</h2>--%>
<%--                <div>--%>
<%--                    <input type="submit" value="삭제" onclick="location.href='/${url}/delete?seq=${seq}'">--%>
<%--                    <input type="submit" value="취소" onclick="location.href='/${url}/detail?seq=${seq}'">--%>
<%--                </div>--%>
<%--        </div>--%>
<div class="card" style="width: 18rem;  text-align: center;margin: 0 auto; ">
    <div class="card-body">
        <h5 class="card-title">삭제할거임?</h5>
        <button type="button" class="btn btn-primary" onclick="location.href='/${url}/delete?seq=${seq}'">삭제</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="location.href='/${url}/detail?seq=${seq}'">취소</button>
    </div>
</div>
<%--    <div class="modal" tabindex="-1" role="dialog">--%>
<%--        <div class="modal-dialog" role="document">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <h5 class="modal-title">삭제할거임?</h5>--%>
<%--                </div>--%>
<%--                <div class="modal-body">--%>
<%--                    <p>Modal body text goes here.</p>--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button type="button" class="btn btn-primary" onclick="location.href='/${url}/delete?seq=${seq}'">삭제</button>--%>
<%--                    <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="location.href='/${url}/detail?seq=${seq}'">취소</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
