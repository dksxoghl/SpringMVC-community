// let boardHyId = document.getElementById('boardHyId').value;
const boardHyId = $('#boardHyId').val();
const username = $('#username').val();
const likeUp = $("#like-up");
const likeAlready = $("#like-already");
let nowpage = 1;
const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");

if (!username) {
    $('#rep_edit').hide();
    $('#rep_ban').show();
} else {
    $('#rep_edit').show();
    $('#rep_ban').hide();
}
if(username){
    getLike();
}

function getLike() {
    $.getJSON("/like/" + username + "/" + boardHyId, function (data) {
        if (data === 0) {
            likeUp.show();
            likeAlready.hide();
        } else {
            likeUp.hide();
            likeAlready.show();
        }
    });
}


//첫페이지 끝페이지로 설정.
getLastPage(boardHyId);

function getLastPage(hyId) {
    $.getJSON("/reply/lastPage/" + hyId, function (data) {
        if (data == 0) nowpage = 1;
        else nowpage = data;
        getReply(boardHyId, nowpage);
    });
}

function getReply(hyId, nowpage, cur_edit) {         //세번째 가변인자로 대댓글란 컨트롤.
    $.getJSON("/reply/all/" + hyId + "/" + nowpage, function (data) {
        console.log(data);
        let str = "";
        $(data.replyVO).each(function () {
            let indent = "";
            let color = "";
            let deleteColor = "";
            for (let i = 0; i < this.reIndent; i++) {
                indent += "&nbsp&nbsp&nbsp&nbsp";
                color = "#fafafa";
                if (i === this.reIndent - 1) indent += "<img width='10px' height='10px' src='/resources/img/right-arrow.png'>&nbsp&nbsp";
            }
            if (this.reContent === "[작성자가 삭제한 댓글입니다.]") {
                console.log(this.reContent);
                deleteColor += "#acacac";
            }

            str += "<div style='background-color:" + color + "'><div style='padding-top: 5px' class='row' data-replyNo='" + this.reId + "'>" +
                "<div class='col-2'>" + indent + this.userId + "</div>" +
                "<div class='col-3 offset-md-5'><span style='font-weight: bold;color:#777;'>" + this.reRegdate.substr(0, 10) + "</span>";
            // console.log(new Date(this.reRegdate).toString().substr(16, 8))
            str += "<span>(" + new Date(this.reRegdate).toString().substr(16, 8) + ")</span></div>";
            // str+= "<span>(" + this.reRegdate.substr(11, 8) + ")</span></div>" +
            if (username) {
                str+="<div class='col-2'>" +
                "<button class='re_b btn-dark'>신고</button>";
                if (username === this.userId)
                    str += "<button class='re_b' id='re_del' style='border:0;outline: 0'>x</button>";
                if (this.reIndent < 4) {
                    str += "<button class='re_b' id='re_reply' style='border-color:#ccc'>" +
                        "<img width='9px' height='9px' src='/resources/img/right-arrow.png'>" +
                        "</button></div>";
                }
            }
            str += "</div>" +
                "<div style='margin-top:10px; margin-left:" + this.reIndent * 25 + "px; color:" + deleteColor + "'>" + this.reContent + "</div>" +
                "<div id=\"rerep_edit\">";
            if (this.reId == cur_edit) {
                str += "<textarea id=\"rerep_content\" style=\"width: 95%; \"></textarea>\n" +
                    "<div>\n" +
                    "<button style=\"float: right; margin-right: 50px; font-size:5px;\" " +
                    "class=\"btn-dark\" data-indent='" + this.reIndent + "' id=\"rerep_insert\" data-order='" + this.reOrder + "' data-reGroup='" + this.reGroup + "'>댓글등록</button>\n" +
                    "</div>"
            }
            str += "</div>" +
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

// window.onload = function(){
//     console.log(username);
// }


$(document).ready(function () {     //dom생성시 reday메소드 실행,  모든요소(이미지등) 로드완료시는 window.load  ,ready 해당 셀렉터에이벤트를 직접바인딩 on은 이벤트를위임
    $(document).ajaxSend(function (e, xhr, options) {         //ajax통신에 csrf토큰포함
        xhr.setRequestHeader(header, token);
    });
    $(document).on("click", "#toggle", function () {
        $("#reply").toggle();
    });

    $(document).on("click", ".rep_page a", function (event) {
        event.preventDefault();
        nowpage = $(this).attr("class");
        getReply(boardHyId, nowpage);
    });

    $(document).on('click', '#refresh', function () {
        getReply(boardHyId, nowpage);
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
                getReply(boardHyId, nowpage); // 댓글 목록 출력 함수 호출
            }
        });
    });
    $(document).on('click', '#re_reply', function () {
        let parent = $(this).parent().parent();
        let reId = parent.attr("data-replyNo");
        getReply(boardHyId, nowpage, reId);
    });
    $(document).on("click", "#rerep_insert", function (e, xmlHttpRequest) {
        let reContent = $("#rerep_content");
        let reContentVal = reContent.val();
        let reIndent = $(this).attr("data-indent");
        let reOrder = $(this).attr("data-order");
        let reGroup = $(this).attr("data-reGroup");
        let reId = $(this).parent().parent().parent().children().attr("data-replyNo");
        $.ajax({
            type: "post",
            url: "/reply",
            headers: {
                "Content-type": "application/json",
                "X-HTTP-Method-Override": "POST",
            },
            dataType: "text",
            data: JSON.stringify({
                hyId: boardHyId,
                userId: username,
                reContent: reContentVal,
                reOrder: reOrder,
                reGroup: reGroup,
                reIndent: parseInt(reIndent) + 1,
                reParent: reId
            }),
            success: function (result) {
                if (result == "regSuccess") {
                    alert("댓글 등록 완료!");
                }
                getReply(boardHyId, nowpage); // 댓글 목록 출력 함수 호출
                reContent.val(""); // 댓글 내용 초기화
            }
        });
    });
    $(document).on("click", "#re_insert", function () {
        let reContent = $("#re_content");
        let reContentVal = reContent.val();
        $.ajax({
            type: "post",
            url: "/reply",
            headers: {
                "Content-type": "application/json",
                "X-HTTP-Method-Override": "POST"
            },
            dataType: "text",
            data: JSON.stringify({
                hyId: boardHyId,
                userId: username,
                reContent: reContentVal,
                reGroup: 0
            }),
            success: function (result) {
                if (result == "regSuccess") {
                    alert("댓글 등록 완료!");
                }
                getReply(boardHyId, nowpage); // 댓글 목록 출력 함수 호출
                reContent.val(""); // 댓글 내용 초기화
            }
        });
    });
    //  post 삽입,  자원의 부분교체 >patch
    $(document).on("click", "#like-up", function (e) {
        if(!username){
            alert('로그인이 필요한 서비스입니다.');
            e.preventDefault();
        }else {
            $.ajax({
                type: "POST",
                url: "/like",
                headers: {
                    "Content-type": "application/json",
                    "X-HTTP-Method-Override": "POST"
                },
                dataType: "text",
                data: JSON.stringify({
                    hyId: boardHyId,
                    userId: username,
                }),
                success: function (result) {
                    likeUp.hide();
                    likeAlready.show();
                }
            });
        }
    });
    $(document).on("click", "#like-already", function (e) {
            alert('이미 추천을 하였습니다.');
            e.preventDefault();
    });
});