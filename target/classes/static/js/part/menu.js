jQuery(function ($) {
    $('.pointing.menu').find(".item").click(function () {
                $(this)
                    .addClass('active')
                    .closest('.ui.menu')
                    .find('.item')
                    .not($(this))
                    .removeClass('active');
        var name = $(this).text().trim();
        switch (name) {
            case "Data":
                $('.data').addClass('active').siblings().removeClass('active');
                break;
            case "Model":
                $('.model_tag').addClass('active').siblings().removeClass('active');
                break;
            case "Training":
                $('.train').addClass('active').siblings().removeClass('active');
                train("loss","loss");
                train("acc","acc");
                break;
            case "Results":
                $('.result').addClass('active').siblings().removeClass('active');
                train("loss","train_loss");
                train("acc","train_acc");
                train("valLoss","valLoss");
                train("valAcc","valAcc");
                break;
            case "Deploy":
                $('.deploy').addClass('active').siblings().removeClass('active');
                $.ajax({
                    url:'/model/findTrainedModel',
                    type:'get',
                    // data:{id:id},
                    dataType:'json',
                    beforeSend:function(){
                    },
                    success:function (data) {
                        var json = data.trainedModels;
                        for (var obj in json){
                            var name = json[obj].modelPath.split("/").pop();
                            $('#TrainingRun').append("<option data-id="+json[obj].id+" value="+json[obj].modelPath+">"+name+"</option>");
                            // $('#dataSets').append("<option data-id="+json[obj].id+" value="+json[obj].path+">"+json[obj].name+"</option>");
                        }
                    },
                    error:function () {
                        layer.msg("加载失败");
                    }
                });
                break;
            default:
                break;
        }
        }
    );
});