$(document).ready(function () {
    $("#show_password_div_button").click(function () {
        if($('.password_div').css('visibility') === 'hidden'){
            $(".password_div").css('visibility', 'visible');
        } else {
            $('.password_div').css('visibility', 'hidden');
        }
    });
});