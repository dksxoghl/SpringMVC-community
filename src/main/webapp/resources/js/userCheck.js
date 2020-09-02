const user = $('#user').val();
const url = $('#url').val();
$(document).ready(function () {
    $(document).on('click', '#writeForm', function (e) {
        console.log(user,'??')
        if (!user) {
            alert('로그인이 필요합니다.');
            e.preventDefault();
        }
        else if(url==='nt' && user!=='admin'){     //공지게시글일때 admin아니면
            alert('권한이 없습니다.');
            e.preventDefault();
        }
    });
    $('.dropdown').hover(function () {
        $('.dropdown-menu').show();
    },function () {
    });
    $('.dropdown-menu').hover(function () {
        $('.dropdown-menu').show();
    },function () {
        $('.dropdown-menu').hide();
    });

});

