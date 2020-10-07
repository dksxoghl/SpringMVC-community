const user = $('#user').val();
const url = $('#url').val();
$(document).ready(function () {
    $(document).on('click', '#writeLink', function (e) {
        console.log(user, '??')
        let errorMessage = "";
        if (!user) {
            errorMessage = '로그인이 필요합니다.';
        } else if (url === 'nt' && user !== 'admin') {     //공지게시글일때 admin아니면
            errorMessage = '권한이 없습니다.';
        }
        if (errorMessage) {
            alert(errorMessage);
            e.preventDefault();
        }

    });
    $('.dropdown').hover(function () {
        $('.dropdown-menu').show();
    }, function () {
    });
    $('.dropdown-menu').hover(function () {
        $('.dropdown-menu').show();
    }, function () {
        $('.dropdown-menu').hide();
    });

});

