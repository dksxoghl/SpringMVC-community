<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>해연갤</title>
</head>
<body>
<div>
        <div>
            <h2>삭제할거임?</h2>
                <div>
                    <input type="submit" value="삭제" onclick="location.href='/delete?seq=${seq}'">
                    <input type="submit" value="취소" onclick="location.href='/detail?seq=${seq}'">
                </div>
        </div>

</div>
</body>
</html>
