$(document).ready(function () {
    $(document).on('click', '#loginForm', function (e) {
        if (!$('#username').val()) {
            alert('id는 필수값');
            e.preventDefault();
        } else if (!$('#password').val()) {
            alert('password는 필수값');
            e.preventDefault();
        }
    });
    $(document).on('click', '#joinForm', function (e) {
        if (!$('#username').val()) {
            alert('id는 필수값');
            e.preventDefault();
        } else if (!$('#password').val()) {
            alert('password는 필수값');
            e.preventDefault();
        }else if (!$('#password2').val()) {
            alert('password확인은 필수값');
            e.preventDefault();
        }
        else if ($('#password').val()!==$('#password2').val()) {
            alert('password확인이 잘못되었습니다');
            e.preventDefault();
        }
        else if (!$('#adultRadio').is(":checked") && !$('#adultRadio2').is(":checked")) {
            alert('성인체크는 필수값');
            e.preventDefault();
        }
    });
});