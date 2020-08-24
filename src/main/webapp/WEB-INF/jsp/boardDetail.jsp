<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<c:url value="/js/jquery-3.5.1.js"/>"></script>
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
        <div style="font-size: 1.4em; font-weight: bold; margin-left: 10px">●${board.hySubject }</div>
        <div class="col align-self-end">
            <div style="float: right;">${board.userId}</div>
        </div>
    </div>
    <div class="row" style="border-bottom: 1px inset  #bcbcbc; ">
        <div style="margin-left: 10px;color:#777;">${board.hyUrl }</div>
        <div class="col align-self-end">
            <div style="float: right;">
                <span style="font-weight: bold; color:#777;"> <fmt:formatDate value="${board.hyCreatedDate }"
                                                                              pattern="yyyy.MM.dd"/></span>
                <fmt:formatDate value="${board.hyCreatedDate }" pattern="hh:mm"/>
                &nbsp <span style="font-weight: bold; color:#777;">view  &nbsp</span>${board.hyHit}
            </div>
        </div>
    </div>
    <div style="margin-top:30px">${board.hyContent }
    </div>
    <div class="row justify-content-around" style="margin-top:30px">
        <div class="col-md-3">
            <button type="button" class="btn btn-outline-secondary">북마크</button>
        </div>
        <div class="col-md-3 offset-md-2">
            <button type="button" class="btn btn-outline-secondary">추천 ${board.hyLike}</button>
        </div>
        <div class="col-md-2 offset-md-2">
            <button type="button" class="btn btn-outline-secondary">신고</button>
        </div>
    </div>
    <div style="color:#777; border-bottom: 1px inset  #bcbcbc;">${board.hyUrl}</div>
    <div class="row" style="margin-left:5px">
        <div><a id="toggle">댓글</a><span id="re_length">[0]</span></div>
        <div class="col align-self-end">
            <input class="b_rud btn btn-dark" type="button" value="글 목록" style="float: right; font-size: 6px"
                   onclick="location.href='/${url}'">
            <form id="writeForm" name="writeForm" method="post" action="/${url}/writeForm">
                <input type='hidden' id='hyId' name='hyId' value='${board.hyId }'/> <%--id get으로--%>
                <input type='hidden' id='categoryId' name='categoryId' value='${board.categoryId }'/>
                <input type='hidden' id='hySubject' name='hySubject' value='${board.hySubject }'/>
                <input type='hidden' id='userId' name='userId' value='${board.userId }'/>
                <%--            <input type='hidden' id='h_created_date' name='h_created_date' value= <fmt:formatDate value="${board.h_created_date}" pattern="yyyy.MM.dd hh:ss" /> />--%>
                <input type='hidden' id='hyHit' name='hyHit' value='${board.hyHit }'/>
                <input type='hidden' id='hyContent' name='hyContent' value='${board.hyContent }'/>
                <input type='hidden' id='hyLike' name='hyLike' value='${board.hyLike }'/>
                <input class="b_rud btn btn-dark" type="submit" value="글 수정" style="float: right;font-size:6px">
            </form>
            <%--        <input type="button" value="글 수정" style="float: right;" onclick="location.href=--%>
            <%--                '/${url}/writeForm?h_subject=${board.h_subject}&h_content=${board.h_content}&user_id=${board.user_id}&h_id=${board.h_id}&category_id=${board.category_id}'">--%>
            <input class="b_rud btn btn-dark" type="button" value="글 삭제" style="float: right;font-size: 6px"
                   onclick="location.href='/${url}/deleteForm/${board.hyId}'">
        </div>
    </div>
    <div id="reply">
        <div class="rep_page"
             style="text-align: center;background-color: #f5f5f5; width: 99%; padding-right: 10px"></div>
        <div id="rep_info" style="background-color: #f5f5f5;width: 99%;padding: 20px 20px 20px 30px; ">
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
        <div class="rep_page"
             style="text-align: center;background-color: #f5f5f5; margin-bottom: 10px;width: 99%; padding-right: 10px">
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
            <button style="float: right;" class="btn-dark" id="refresh">댓로고침</button>
        </div>
        <div id="rep_edit" style=" height:180px">
            <textarea id="re_content" style="width: 99%; height: 80px"></textarea>
            <div>
                <button style="float: right;" class="btn-dark" id="re_insert">댓글등록</button>
            </div>
        </div>
    </div>

</div>
</div>
<jsp:include page="hyboardList.jsp"/>
<jsp:include page="include/footer.jsp"/>

<script type="text/javascript">
    let nowpage = 1;

    //첫페이지 끝페이지로 설정.
    getLastPage(${board.hyId});

    function getLastPage(hyId) {
        $.getJSON("/reply/lastPage/" + hyId, function (data) {
            nowpage = data;
            getReply(${board.hyId}, nowpage);
        });
    }

    function getReply(hyId, nowpage,cur_edit) {         //세번째 가변인자로 대댓글란 컨트롤.
        $.getJSON("/reply/all/" + hyId + "/" + nowpage, function (data) {
            console.log(data);
            let str = "";
            $(data.replyVO).each(function () {
                let indent="";
                let color="";
                for (let i=0;i<this.reIndent;i++){
                    indent+="→&nbsp";
                    color="#fafafa";
                }
                str += "<div style='background-color:"+color+"'><div style='padding-top: 5px' class='row' data-replyNo='" + this.reId + "'>" +
                    "<div class='col-2'>" +indent+ this.userId + "</div>" +
                    "<div class='col-3 offset-md-5'><span style='font-weight: bold;color:#777;'>" + this.reRegdate.substr(0, 10) + "</span>" +
                    "<span >(" + this.reRegdate.substr(11, 8) + ")</span></div>" +
                    "<div class='col-2'>" +
                    "<button class='re_b btn-dark'>신고</button><button class='re_b' id='re_del'>x</button><button class='re_b' id='re_reply'>댓</button>" +
                    "</div></div>" +
                    "<div style='margin-top: 10px;margin-left:"+this.reIndent*15+"px'>" + this.reContent + "</div>" +
                    "<div id=\"rerep_edit\">";
                        if(this.reId==cur_edit) {
                            str+="<textarea id=\"rerep_content\" style=\"width: 95%; \"></textarea>\n" +
                            "<div>\n" +
                            "<button style=\"float: right; margin-right: 50px; font-size:5px;\" class=\"btn-dark\" data-indent='"+this.reIndent+"' id=\"rerep_insert\">댓글등록</button>\n" +
                            "</div>"
                        };
                    str+="</div>" +
                    "<div style='border-bottom:1px dashed #ccc;margin-top: 10px; height: 20px'></div>" +
                    "</div>";
            });
            $('#rep_info').html(str);
            $('#re_length').html("[" + data.replyPage.total + "]");
            printPage(data.replyPage);
            // $(".rerep_edit").hide();
        });
    }

    function printPage(replyPage) {
        let str = "";
        if (replyPage.total > 10) {
            str += "<a class=1 href='#'>&lt;첫 페이지</a>\n";
            for (let i = replyPage.startPage; replyPage.endPage >= i; i++) {
                if (i == replyPage.nowPage) {
                    str += "<b>" + i + "</b>\n";
                } else {
                    str += "<a class=\"" + i + "\" href=\"#\">" + i + "</a>&nbsp"
                }
            }
            str += "<a href='#' class='" + replyPage.lastPage + "'>끝 페이지></a>";
        }
        $('.rep_page').html(str);
    }

    $(document).ready(function () {     //dom생성시 reday메소드 실행,  모든요소(이미지등) 로드완료시는 window.load  ,ready 해당 셀렉터에이벤트를 직접바인딩 on은 이벤트를위임
        $(document).on("click", "#toggle", function () {
            $("#reply").toggle();
        });

        $(document).on("click", ".rep_page a", function (event) {
            event.preventDefault();
            nowpage = $(this).attr("class");
            getReply(${board.hyId}, nowpage);
        });

        $(document).on('click', '#refresh', function () {
            getReply(${board.hyId}, nowpage);
            alert('댓로고침 완료')
        });
        $(document).on('click', '#re_del', function () {
            let parent = $(this).parent().parent();
            let reId = parent.attr("data-replyNo");
            console.log(reId);
            $.ajax({
                type: "Delete",
                url: "/reply/" + reId,
                headers: {
                    "Content-type": "application/json",
                    "X-HTTP-Method-Override": "DELETE"
                },
                dataType: "text",
                success: function (result) {
                    if (result == "delSuccess") {
                        alert("댓글 삭제 완료!");
                    }
                    getReply(${board.hyId}, nowpage); // 댓글 목록 출력 함수 호출
                }
            });
        });
//패런트가진애 찾고 그 넘버에 패런트인에 찾고  그애의 오더+1 그리고 같은그룹 그오더 업데이트
        $(document).on('click', '#re_reply', function () {
            let parent = $(this).parent().parent();
            let reId = parent.attr("data-replyNo");
            getReply(${board.hyId}, nowpage,reId);
        });
        $(document).on("click", "#rerep_insert", function () {
            let reContent = $("#rerep_content");
            let reContentVal = reContent.val();
            let reIndent= $(this).attr("data-indent");
            let reId = $(this).parent().parent().parent().children().attr("data-replyNo");
            console.log(parseInt(reIndent)+1,reId);
            $.ajax({
                type: "post",
                url: "/reply/insert/"+reId,
                headers: {
                    "Content-type": "application/json",
                    "X-HTTP-Method-Override": "POST"
                },
                dataType: "text",
                data: JSON.stringify({
                    hyId: ${board.hyId},
                    userId: 'ㅌㅎ',
                    reContent: reContentVal,
                    reIndent: reIndent+1,
                    reParent: reId
                }),
                success: function (result) {
                    if (result == "regSuccess") {
                        alert("댓글 등록 완료!");
                    }
                    getReply(${board.hyId}, nowpage); // 댓글 목록 출력 함수 호출
                    reContent.val(""); // 댓글 내용 초기화
                }
            });
        });
        $(document).on("click", "#re_insert", function () {
            let reContent = $("#re_content");
            let reContentVal = reContent.val();
            $.ajax({
                type: "post",
                url: "/reply/insert/0",
                headers: {
                    "Content-type": "application/json",
                    "X-HTTP-Method-Override": "POST"
                },
                dataType: "text",
                data: JSON.stringify({
                    hyId: ${board.hyId},
                    userId: 'ㅌㅎ',
                    reContent: reContentVal
                }),
                success: function (result) {
                    if (result == "regSuccess") {
                        alert("댓글 등록 완료!");
                    }
                    getReply(${board.hyId}, nowpage); // 댓글 목록 출력 함수 호출
                    reContent.val(""); // 댓글 내용 초기화
                }
            });
        });

    });
</script>
</body>
</html>