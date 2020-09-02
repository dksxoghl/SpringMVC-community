const userDouble = $('#userDouble').val();
$(document).ready(function () {
    $(document).on('click', '#writeForm2', function (e) {
        console.log(userDouble,'????')
        if (!userDouble) {
            alert('로그인이 필요합니다.');
            e.preventDefault();
        }
    });
});

