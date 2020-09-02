const user = $('#user').val();
$(document).ready(function () {
    $(document).on('click', '#writeForm', function (e) {
        console.log(user,'??')
        if (!user) {
            alert('로그인이 필요합니다.');
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
    })
});

