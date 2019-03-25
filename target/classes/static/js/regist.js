$(function () {
    $('#sbm').click(function (e) {
        var pw1 = $('#pw1').val();
        var pw2 = $('#pw2').val();
        if (pw1!=pw2){
            e.preventDefault();
            alert("两次密码不同");
        }
    });
});