jQuery(function ($) {
    $('.pointing.menu').find(".item").click(function () {
            $(this)
                .addClass('active')
                .closest('.ui.menu')
                .find('.item')
                .not($(this))
                .removeClass('active');
        }
    );
    $('#submit').click(function () {
        var  dataSet = {};
        var name ={};
        $('.center.top_20').find('input[name], select').each(function() {
            name = $(this).attr('name');
            dataSet[name] = $(this).val();
        });
        var str = JSON.stringify(dataSet);
        $.ajax({
            url:'/model/commitData', async:false,
            type:'post',
            data: {str:str},
            // dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                if(textStatus=="success"){
                    layer.msg("success!",{icon: 1,time:'1000'});
                    $('#Model,.model_tag').addClass('active').siblings().removeClass('active');
                }
            },
            error:function(jqXHR,textStatus,errorThrown){
                layer.msg("服务器繁忙");
            }
        });
    });//end
});


