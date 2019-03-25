jQuery(function ($) {
    $('#subMenu').find(".item").click(function () {
                $(this)
                    .addClass('active')
                    .closest('.ui.menu')
                    .find('.item')
                    .not($(this))
                    .removeClass('active');
        var name = $(this).text().NoSpace();
        var $page = $('.'+name);
        if (!$page.hasClass('active'))
            $page.addClass('active').siblings().removeClass('active');
            switch (name) {
                case "DataSetInference":
                    break;
                case "FromInference":
                    break;
                case "Download":
                    break;
                case "Deploy":
                    $.ajax({
                        url:'/model/findTrainedModel.do',
                        type:'get',
                        // data:{id:id},
                        dataType:'json',
                        beforeSend:function(){
                        },
                        success:function (data) {
                            var json = data.trainedModels;
                            for (var obj in json){
                                var name = json[obj].modelPath;
                                $('#TrainingRun').append("<option data-id="+json[obj].id+" value="+name+">"+name+"</option>");
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
String.prototype.NoSpace = function() {
    return this.replace(/\s+/g, "");
};