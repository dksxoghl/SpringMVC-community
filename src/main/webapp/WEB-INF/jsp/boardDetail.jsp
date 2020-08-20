<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.5.1.js"/>"></script>
    <%--    <script type="text/javascript" src="/resources/js/jquery-3.5.1.js"></script>--%>
    <title>해연갤</title>

</head>
<style>
    .btn-dark {
        margin: 3px;
    }

    .re_b {
        font-size: 5px;

    }

</style>

<body>
<jsp:include page="include/header.jsp"/>
<div class="container" style="width:70%">
    <div class="row" style=" border-bottom: 3px inset  #bcbcbc; ">
        <div style="font-size: 1.4em; font-weight: bold; margin-left: 10px">●${board.h_subject }</div>
        <div class="col align-self-end">
            <div style="float: right;">${board.user_id}</div>
        </div>
    </div>
    <div class="row" style="border-bottom: 1px inset  #bcbcbc; ">
        <div style="margin-left: 10px;color:#777;">${board.h_url }</div>
        <div class="col align-self-end">
            <div style="float: right;">
                <span style="font-weight: bold; color:#777;"> <fmt:formatDate value="${board.h_created_date }"
                                                                              pattern="yyyy.MM.dd"/></span>
                <fmt:formatDate value="${board.h_created_date }" pattern="hh:mm"/>
                &nbsp <span style="font-weight: bold; color:#777;">view  &nbsp</span>${board.h_hit}
            </div>
        </div>
    </div>
    <div style="margin-top:30px">${board.h_content }
    </div>
    <div class="row justify-content-around" style="margin-top:30px">
        <div class="col-md-3">
            <button type="button" class="btn btn-outline-secondary">북마크</button>
        </div>
        <div class="col-md-3 offset-md-2">
            <button type="button" class="btn btn-outline-secondary">추천 ${board.h_like}</button>
        </div>
        <div class="col-md-2 offset-md-2">
            <button type="button" class="btn btn-outline-secondary">신고</button>
        </div>
    </div>
    <div style="color:#777; border-bottom: 1px inset  #bcbcbc;">${board.h_url}</div>
    <div class="row" style="margin-left:5px">
        <div><a class="toggle">댓글</a><span class="re_length">[0]</span></div>
        <div class="col align-self-end">
            <input class="b_rud btn btn-dark" type="button" value="글 목록" style="float: right; font-size: 6px"
                   onclick="location.href='/'">
            <form id="writeForm" name="writeForm" method="post" action="/${url}/writeForm">
                <input type='hidden' id='h_id' name='h_id' value='${board.h_id }'/>
                <input type='hidden' id='category_id' name='category_id' value='${board.category_id }'/>
                <input type='hidden' id='h_subject' name='h_subject' value='${board.h_subject }'/>
                <input type='hidden' id='user_id' name='user_id' value='${board.user_id }'/>
                <%--            <input type='hidden' id='h_created_date' name='h_created_date' value= <fmt:formatDate value="${board.h_created_date}" pattern="yyyy.MM.dd hh:ss" /> />--%>
                <input type='hidden' id='h_hit' name='h_hit' value='${board.h_hit }'/>
                <input type='hidden' id='h_content' name='h_content' value='${board.h_content }'/>
                <input type='hidden' id='h_like' name='h_like' value='${board.h_like }'/>
                <input class="b_rud btn btn-dark" type="submit" value="글 수정" style="float: right;font-size:6px">
            </form>
            <%--        <input type="button" value="글 수정" style="float: right;" onclick="location.href=--%>
            <%--                '/${url}/writeForm?h_subject=${board.h_subject}&h_content=${board.h_content}&user_id=${board.user_id}&h_id=${board.h_id}&category_id=${board.category_id}'">--%>
            <input class="b_rud btn btn-dark" type="button" value="글 삭제" style="float: right;font-size: 6px"
                   onclick="location.href='/${url}/deleteForm?seq=${board.h_id}'">
        </div>
    </div>
    <div class="reply">
        <div class="rep_info" style="background-color: #f5f5f5;width: 99%;padding: 20px 20px 20px 30px; ">
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
                <div class="rep_page" style="text-align: center;background-color: #f5f5f5; margin-bottom: 10px;width: 99%; padding-right: 10px">
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
        <div style="width: 99%;height:38px; border:1px inset #F6F6F6;margin-top: 3px">
            <button style="float: right;" class="refresh btn-dark">댓로고침</button>
        </div>
        <div class="rep_edit" style=" height:180px">
            <%--            <form method="post">--%>
            <textarea class="re_content" style="width: 99%; height: 80px"></textarea>
            <div>
                <button style="float: right;" class="re_insert btn-dark">댓글등록</button>
            </div>
            <%--            </form>--%>
        </div>
    </div>

</div>
</div>
<jsp:include page="hyboardList.jsp"/>
<jsp:include page="include/footer.jsp"/>

<script type="text/javascript">
    var nowpage=1;
    getReply(${board.h_id},nowpage);
    $(document).on('click', '.re_del', function () {
        var parent = $(this).parent().parent();
        var re_id = parent.attr("data-replyNo");
        console.log(re_id);
        $.ajax({
            type: "Delete",
            url: "/reply/" + re_id,
            headers: {
                "Content-type": "application/json",
                "X-HTTP-Method-Override": "DELETE"
            },
            dataType: "text",
            success: function (result) {
                if (result == "delSuccess") {
                    alert("댓글 삭제 완료!");
                }
                getReply(${board.h_id},nowpage); // 댓글 목록 출력 함수 호출
            }
        });
    })

    $(".re_insert").on("click", function () {
        var re_content = $(".re_content");
        var re_contentVal = re_content.val();
        $.ajax({
            type: "post",
            url: "/reply/insert",
            headers: {
                "Content-type": "application/json",
                "X-HTTP-Method-Override": "POST"
            },
            dataType: "text",
            data: JSON.stringify({
                h_id: ${board.h_id},
                user_id: 'ㅌㅎ',
                re_content: re_contentVal
            }),
            success: function (result) {
                if (result == "regSuccess") {
                    alert("댓글 등록 완료!");
                }
                getReply(${board.h_id},nowpage); // 댓글 목록 출력 함수 호출
                re_content.val(""); // 댓글 내용 초기화
            }
        });
    });


    $(document).on('click', '.refresh', function () {
        getReply(${board.h_id},nowpage);
        alert('댓로고침 완료')
    });

    function getReply(h_id,nowpage) {
        $.getJSON("/reply/all/" + h_id + "/"+nowpage, function (data) {
            console.log(data);
            let str = "";
            $(data.replyVO).each(function () {
                str += "<div style='padding-top: 5px' class='row' data-replyNo='" + this.re_id + "'>" +
                    "<div class='col-1'>" + this.user_id + "</div>" +
                    "<div class='col-3 offset-md-6'><span style='font-weight: bold;color:#777;'>" + this.re_regdate.substr(0, 10) + "</span>" +
                    "<span >(" + this.re_regdate.substr(11, 8) + ")</span></div>" +
                    "<div class='col-2'>" +
                    "<button class='re_b btn-dark'>신고</button><button class='re_b re_del'>x</button><button class='re_b'>댓</button>" +
                    "</div></div>" +
                    "<div>" + this.re_content + "</div>" +
                    "<div style='border-bottom:1px dashed #ccc; height: 20px'></div>";
            });
            $('.rep_info').html(str);
            $('.re_length').html("[" + data.replyPage.total + "]");
            printPage(data.replyPage);
        });
    }

    function printPage(replyPage) {
        var str = "";
        // str += "<div style='text-align: center; background-color: white; background-color: #f5f5f5; margin-bottom: 10px;width: 99%; padding-right: 10px'>\n";
        if (replyPage.total > 10) {
            str += "<a href='1'>&lt;첫 페이지</a>\n";
            for (var i = replyPage.startPage; replyPage.endPage >= i; i++) {
                if (i == replyPage.nowPage) {
                    str += "<b>"+i+"</b>\n";
                } else {
                    str += "<a href='"+i+"'>" + i + "</a>"
                }
            }
            str += "<a href='" + replyPage.lastPage + "'>&nbsp끝 페이지></a>";
        }
        // str += "</div>";
        $('.rep_page').html(str);
    }
    $(".rep_page").on("click","a",function (event) {
        event.preventDefault();
        nowpage=$(this).attr("href");
        getReply(${board.h_id},nowpage);
    })

    $(document).ready(function () {
        $(".toggle").click(function () {
            $(".reply").toggle();
        });
    });
</script>
</body>
</html>