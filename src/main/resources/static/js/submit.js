    jQuery(function ($) {
        var msg = $('.red.basic.label').text().trim();
        if (msg ==""){
            $('.red.basic.label').addClass('hidden');
        }
        else
            $('.red.basic.label').removeClass('hidden');
        $('.submit').on('click', function () {
            $(this).submit();
        });
    });
    // $('.forget-password').on('click',function () {
    //    window.location.href="forgot.jsp";
    // });
    $('.ui.checkbox').checkbox();
    $('.ui.form')
        .form({
            fields: {
                email: {
                    identifier: 'email',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Please enter your e-mail'
                        },
                        {
                            type: 'email',
                            prompt: 'Please enter a valid e-mail'
                        }
                    ]
                },
                password: {
                    identifier: 'password',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Please enter your password'
                        },
                        {
                            type: 'length[6]',
                            prompt: 'Your password must be at least 6 characters'
                        }
                    ]
                },
                re_password: {
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Please enter your password'
                        },
                        {
                            type: 'match[password]',
                            prompt: 'Your repeated password must be identical with password'
                        }
                    ]
                }
            },
            inline: true,
            on: 'blur'
        });