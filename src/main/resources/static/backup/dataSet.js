jQuery(function ($) {
    var $add = $('#addDataSet');
    $('#allPublicDataSet').on('click','button',function () {
        var id = $(this).attr("name");
        var name = $(this).parent().text().NoSpace();
        $.ajax({
            url:'/data/copyPublicDataSets.do',
            type:'get',
            data:{publicId:id},
            dataType:'json',
            beforeSend:function(){
            },
            success:function (data) {
                layer.msg("success",{icon:1});
                $add.append("                    <div class=\"ui red raised horizontal segments\">\n" +
                    "                        <div class=\"ui segment\">"+name+"</div>\n" +
                    "                        <button class=\"ui right attached icon button x\" name="+data+">\n" +
                    "                            <i class=\"x icon\"></i>\n" +
                    "                        </button>\n" +
                    "                    </div>\n");
            },
            error:function () {
                layer.msg("加载失败");
            }
        });
        $('.pointing.menu>.item.active').removeClass('active');
        $('#MyDataItem').addClass('active');
        if (!$('#allMyDataSets').hasClass('active'))
            $('#allMyDataSets').addClass('active').siblings().removeClass('active');
    });
    //面对jquery append 动态添加的元素事件on 不起作用我们该如何解决呢？on方法中要先找到原选择器（如例.ui.segments),再找到动态添加的选择器（如列.x.button)。
    $('.ui.segments').on('click','#deleteDataSet',function () {
        var id = $(this).attr("name");
        var $button = $(this);
            $.ajax({
                url:'/data/delete.do',
                type:'post',
                data:{id:id},
                beforeSend:function(){
                    layer.msg("正在删除");
                },
                success:function () {
                    $button.parent().hide();
                    layer.msg("删除成功");
                },
                error:function () {
                    layer.msg("删除失败");
                }
            });
        }
    );
    $('.circular.icon.upload.button').click(function () {
        $('.ui.modal.upload').modal('show');
    });
    // $('.button.upload').click(function () {
    //     $(this).submit();
    // });
    $('.fluid.primary.button.upload').click(function () {
        var form = $('#tf')[0];
        var file = new FormData(form);
        var ipt = form[0].files;
        var name = ipt[0].name;
        // var reg = /(.zip)$/;
        // var xhr = new XMLHttpRequest();
        // xhr.open("post", '/data/add.do', true);
        // xhr.onload = function () {
        //     layer.msg("上传成功");
        // };
        // xhr.onerror=function(){
        //   layer.msg("上传失败");
        // };
        // xhr.upload.addEventListener("progress", progressFunction, false);
        // xhr.send(file);
        // var lastName = name.match(reg)[0];
        var lastName = name.split(".")[1];
        if (lastName=="zip"){
            $.ajax({
                url:'/data/add.do',
                type:'post',
                data: file,
                cache: false,
                /**
                 *必须false才会自动加上正确的Content-Type
                 */
                contentType: false,
                /**
                 * 必须false才会避开jQuery对 formdata 的默认处理
                 * XMLHttpRequest会对 formdata 进行正确的处理
                 */
                processData: false,
                beforeSend :function () {
                    console.log('发送ajax前')
                },
                // 因为jQuery默认使用的XMLHttpRequest对象是内部生成的无法直接给jq的xhr绑定onprogress方法
                //
                // 所以只要给jQuery重新生成一个绑定了onprogress的XMLHttpRequest对象即可实现
                // 首先封装一个方法 传入一个监听函数 返回一个绑定了监听函数的XMLHttpRequest对象
                xhr :xhrOnProgress(function(evt){
                    if (evt.lengthComputable) {
                        $('.ui.progress').progress({
                            percent: Math.round(evt.loaded / evt.total * 100)
                        });
                        if (evt.loaded == evt.total) {
                            // layer.alert("success");
                        }
                    }
                }),
                success:function (data) {
                    if (data=="error")
                        layer.alert("该文件已存在",{icon:2});
                    else {
                        layer.msg("上传成功",{icon:1});
                        var json = JSON.parse(data);
                        $add.append("                    <div class=\"ui red raised horizontal segments\">\n" +
                            "                        <div class=\"ui segment\">"+json.name+"</div>\n" +
                            "                        <button class=\"ui right attached icon button x\" name="+json.id+">\n" +
                            "                            <i class=\"x icon\"></i>\n" +
                            "                        </button>\n" +
                            "                    </div>\n");
                    }

                },
                error:function (jqXHR, textStatus, errorThrown) {
                    layer.alert(jqXHR.responseText,{icon:2});
                }
            });
        }
        else {
            layer.msg("请上传zip文件",{icon:2});
        }
    });
});//end
String.prototype.NoSpace = function() {
    return this.replace(/\s+/g, "");
};
$.ajax({
    url:'/data/showPublicDataSets.do',
    type:'get',
    // data:{id:id},
    dataType:'json',
    beforeSend:function(){
    },
    success:function (data) {
        var json = data.publicDataSetsList;
        for (var i in json) {
            $('#allPublicDataSet').append("<div class=\"ui red raised horizontal segments\">\n" +
                "<div class=\"ui segment\">"+json[i].name+"</div>\n" +
                "<button class=\"ui right attached icon button MNIST copy\" name="+json[i].id+">\n" +
                "<i class=\"copy icon\"></i>\n" +
                "</button>\n" +
                "</div>");
        }
    },
    error:function () {
        layer.msg("加载失败");
    }
});
var xhrOnProgress = function (fun) {
    xhrOnProgress.onprogress = fun;
//绑定监听
// 使用闭包实现监听绑
    return function () {
        //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
        var xhr = $.ajaxSettings.xhr();
//判断监听函数是否为函数
        if (typeof xhrOnProgress.onprogress !== 'function')
            return xhr;
//如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
        if (xhrOnProgress.onprogress && xhr.upload) {
            xhr.upload.onprogress = xhrOnProgress.onprogress;
        }
        return xhr;
    }
};