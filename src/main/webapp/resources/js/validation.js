$(document).ready(function () {
    let errMessage="";
    $(document).on('click', '#loginButton', function (e) {
        if (!$('#username').val()) {
            errMessage='id는 필수값';
        } else if (!$('#password').val()) {
            errMessage='password는 필수값';
        }
        if(errMessage) {
            alert(errMessage);
            e.preventDefault();
        }
    });
    $(document).on('click', '#joinButton', function (e) {
        let errMessage="";
        if (!$('#username').val()) {
            errMessage='id는 필수값';
        } else if (!$('#password').val()) {
            errMessage='password는 필수값';
        }else if (!$('#password2').val()) {
            errMessage='password확인은 필수값';
        }
        else if ($('#password').val()!==$('#password2').val()) {
            errMessage='password확인이 잘못되었습니다';
        }
        else if (!$('#adultRadio').is(":checked") && !$('#adultRadio2').is(":checked")) {
            errMessage='성인체크는 필수값';
        }
        if(errMessage) {
            alert(errMessage);
            e.preventDefault();
        }
    });
    $(document).on("click", "#wirteButton", function (event) {
        let errMessage="";
        if (!$("#hySubject").val()) {
            errMessage='제목 필수';
        } else if (!(CKEDITOR.instances['hyContent'].getData())) {
            errMessage='내용 필수';
        }
        if(errMessage) {
            alert(errMessage);
            event.preventDefault();
        }
    });
    CKEDITOR.replace('hyContent'
        , {
            height: 300, width: 1536
        });
});