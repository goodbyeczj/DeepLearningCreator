jQuery(function ($) {
    //横标题
    $('#manage_menu').find(".item").click(function () {
        var name = $(this).text().NoSpace();
        $('.ui.tab.segment.'+name).addClass('active').siblings().removeClass('active');
        });
    $('#Manager').on('click','#MyProject,#SimpleProject,#MyDataSets,#PublicDataSets',function () {
        var id = $(this).attr('id');
        // $("#"+id).addClass('active').siblings().removeClass('active');
        switch (id) {
            case "MyProject":
                if (!$('#Manage').hasClass('active'))
                    $('#Manage').addClass('active').siblings().removeClass('active');
                $('.MyProject').addClass('active').siblings().removeClass('active');
                if (!$('#MyItem').hasClass('active'))
                    $('#MyItem').addClass('active').siblings().removeClass('active');
                break;
            case "SimpleProject":
                if (!$('#Manage').hasClass('active'))
                    $('#Manage').addClass('active').siblings().removeClass('active');
                $('.SimpleProject').addClass('active').siblings().removeClass('active');
                if (!$('#SimpleItem').hasClass('active'))
                    $('#SimpleItem').addClass('active').siblings().removeClass('active');
                break;
            case "MyDataSets":
                if (!$('#dataManage').hasClass('active'))
                    $('#dataManage').addClass('active').siblings().removeClass('active');
                $('.MyDataSets').addClass('active').siblings().removeClass('active');
                if (!$('#MyDataItem').hasClass('active'))
                    $('#MyDataItem').addClass('active').siblings().removeClass('active');
                break;
            case "PublicDataSets":
                if (!$('#dataManage').hasClass('active'))
                    $('#dataManage').addClass('active').siblings().removeClass('active');
                $('.PublicDataSets').addClass('active').siblings().removeClass('active');
                if (!$('#SimpleDataItem').hasClass('active'))
                    $('#SimpleDataItem').addClass('active').siblings().removeClass('active');
                break;
            default:
                break;
        }
    });
    $('#dataManage').find('.item').click(function () {
        var name = $(this).text().NoSpace();
        $('.ui.tab.segment.'+name).addClass('active').siblings().removeClass('active');
    });
    // $('#OpenManager').on('click','#OpenMyProject,#OpenSimpleProject,#MyDataItem,#SimpleDataItem',function () {
    //     // $(this).addClass('active').siblings().removeClass('active');
    //     var id = $(this).attr('id');
    //     switch (id) {
    //         case "OpenMyProject":
    //             if (!$('#Manage').hasClass('active'))
    //                 $('#Manage').addClass('active').siblings().removeClass('active');
    //             $('.MyProject').addClass('active').siblings().removeClass('active');
    //             if (!$('#MyItem').hasClass('active'))
    //                 $('#MyItem').addClass('active').siblings().removeClass('active');
    //             break;
    //         case "OpenSimpleProject":
    //             if (!$('#Manage').hasClass('active'))
    //                 $('#Manage').addClass('active').siblings().removeClass('active');
    //             $('.SimpleProject').addClass('active').siblings().removeClass('active');
    //             if (!$('#SimpleItem').hasClass('active'))
    //                 $('#SimpleItem').addClass('active').siblings().removeClass('active');
    //             break;
    //         case "MyDataItem":
    //             if (!$('#dataManage').hasClass('active'))
    //                 $('#dataManage').addClass('active').siblings().removeClass('active');
    //             $('.MyDataSets').addClass('active').siblings().removeClass('active');
    //             if (!$('#MyDataItem').hasClass('active'))
    //                 $('#MyDataItem').addClass('active').siblings().removeClass('active');
    //             break;
    //         case "SimpleDataItem":
    //             if (!$('#dataManage').hasClass('active'))
    //                 $('#dataManage').addClass('active').siblings().removeClass('active');
    //             $('.PublicDataSets').addClass('active').siblings().removeClass('active');
    //             if (!$('#SimpleDataItem').hasClass('active'))
    //                 $('#SimpleDataItem').addClass('active').siblings().removeClass('active');
    //             break;
    //         default:
    //             break;
    //     }
    // });
    // $('.mainWrap.navslide').on('click','#option,#Manage,#dataManage',function () {
    //     if (!$(this).hasClass('active'))
    //         $(this).addClass('active').siblings().removeClass('active');
    // });
    var list = ['MNIST','CIFAR-10','IMDB','Titanic','Reuters'];
    var $add = $('#addProject');
    $('#copyProject').on('click','button',function () {
        var id = $(this).attr("name");
        var name = $(this).parent().text().NoSpace();
        $.ajax({
            url:'/project/copyCore.do',
            type:'get',
            data:{publicId:id},
            dataType:'json',
            beforeSend:function(){
            },
            success:function (data) {
                layer.msg("success",{icon:1});
                $add.append("                    <div class=\"ui red raised horizontal segments\">\n" +
                    "                        <div class=\"ui segment\">"+name+"</div>\n" +
                    "                        <button class=\"ui right attached icon button external\" name="+data+">\n" +
                    "                            <i class=\"external alternate icon\"></i>\n" +
                    "                        </button>\n" +
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
        $('#MyItem').addClass('active');
        // if (!$('#Manage').hasClass('active'))
        //     $('#Manage').addClass('active').siblings().removeClass('active');
        $('.MyProject').addClass('active').siblings().removeClass('active');
    });
    //面对jquery append 动态添加的元素事件on 不起作用我们该如何解决呢？on方法中要先找到原选择器（如例.ui.segments),再找到动态添加的选择器（如列.x.button)。
    $('.ui.segments').on('click','.x.button',function () {
        var id = $(this).attr("name");
        var $button = $(this);
            $.ajax({
                url:'/model/delete/'+id+'.do',
                type:'get',
                // data:{id:id},
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
    $('.ui.column').on('click','.add.button',function () {
        $('.ui.modal.add')
            .modal({
                closable  : false,
                onApprove : function() {
                    var name = $('.field input[name="name"]').val();
                    var id = $('.field input[name="id"]').val();
                    var description = $('.field textarea').val();
                    console.log(description);
                    var data = {
                        nodes: graph_main.nodes,
                        edges: graph_main.edges
                    };
                    var model = {name:name,describe_:description,nodeCore:data};
                    $.ajax({
                        url:'/project/add.do', async:false,
                        type:'post',
                        data: model,
                        dataType:'json',
                        beforeSend:function(jqXHR, settings){
                        },
                        success:function(data, textStatus){
                            layer.msg("success",{icon:1});
                            $add.append("                    <div class=\"ui red raised horizontal segments\">\n" +
                                "                        <div class=\"ui segment\">"+name+"</div>\n" +
                                "                        <button class=\"ui right attached icon button external\" name="+data+">\n" +
                                "                            <i class=\"external alternate icon\"></i>\n" +
                                "                        </button>\n" +
                                "                        <button class=\"ui right attached icon button x\" name="+data+">\n" +
                                "                            <i class=\"x icon\"></i>\n" +
                                "                        </button>\n" +
                                "                    </div>\n");
                        },
                        error:function(jqXHR,textStatus,errorThrown){
                            //充当catch(e)的角色
                            alert("刷新页面重试");
                        }
                    });
                }
            })
            .modal('show');
    });
    $('.ui.segments').on('click','.external.button',function () {
        var id = $(this).attr("name");
        var url = "/model/core/"+id+".do";
        var $it = $(this);
        if (!$('#option').hasClass('active'))
            $('#option').addClass('active').siblings().removeClass('active');
        if (!$('.data').hasClass('active'))
            $('.data').addClass('active').siblings().removeClass('active');
        if (!$('#Data').hasClass('active'))
            $('#Data').addClass('active').siblings().removeClass('active');
        $(this).attr('disabled',true);
        $.ajax({
            url:'/model/projectName.do', async:false,
            type:'post',
            data:{id:id},
            // dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                $('#projectName').html('').append(data);
            },
            error:function(jqXHR,textStatus,errorThrown){
                //充当catch(e)的角色
                alert("服务器繁忙");
            }
        });
        $.ajax({
            url:'/data/findDataSetByUserId.do', async:false,
            type:'get',
            dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                var json = data.dataSets;
                $('#path').find('option').remove();
                for (var obj in json){
                    $('#path').append("<option data-id="+json[obj].id+" value="+json[obj].path+">"+json[obj].name+"</option>");
                    // $('#dataSets').append("<option data-id="+json[obj].id+" value="+json[obj].path+">"+json[obj].name+"</option>");
                }
            },
            error:function(jqXHR,textStatus,errorThrown){
                //充当catch(e)的角色
                alert("服务器繁忙");
            }
        });
        $.ajax({
            url:url, async:true,
            type:'post',
            dataType:'json',
            beforeSend:function(jqXHR, settings){
                var pools = graphPool.pools;
                for (var i = 0; i < pools.length; i++) {
                    pools[i].deleteGraph();
                }
            },
            success:function(data, textStatus){
                // $('.pointing.menu').find(".item").each(function () {
                //    if ($(this).text().trim()=="Data"){
                //        $(this).addClass('active').siblings().removeClass('active');
                //    }
                // });
                // $('.data').addClass('active').siblings().removeClass('active');
                $('#Data').addClass('active').siblings().removeClass('active');

                var jsonObj = edgeAssociateNode(data);
                graph_main.nodes = graph_main.nodes.concat(jsonObj.nodes);
                graph_main.edges = graph_main.edges.concat(jsonObj.edges);
                graph_main.updateGraph();
            },
            error:function(jqXHR,textStatus,errorThrown){
                //充当catch(e)的角色
                alert("刷新页面重试");
            }
        });
        var node = {
            'nodes': graph_main.nodes
        };
        var str = JSON.stringify(node);
        $.ajax({
            url:'/model/show_core.do', async:true,
            type:'post',
            // dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                // $('#data_core pre').append(data.toString());
            },
            error:function(jqXHR,textStatus,errorThrown){
                //充当catch(e)的角色
                alert("服务器繁忙");
            }
        });
        $.ajax({
            url:'/model/showPredict_core.do', async:true,
            type:'post',
            // dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                // $('#predict_core pre').append(data.toString());
            },
            error:function(jqXHR,textStatus,errorThrown){
                //充当catch(e)的角色
                alert("服务器繁忙");
            }
        });
        $.ajax({
            url:'/model/showRealCore.do', async:true,
            type:'post',
            data: {str:str},
            // dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                if(textStatus=="success"){
                    //将得到的代码直接代码直接先alert出来
                    // layer.msg("success",{icon:1});
                }
            },
            error:function(jqXHR,textStatus,errorThrown){
                //充当catch(e)的角色
                layer.msg("刷新页面重试");
            }
        });
        $.ajax({
            url:'/model/showData.do', async:false,
            type:'post',
            // data: {str:str},
            dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                if(textStatus=="success"){
                    //将得到的代码直接代码直接先alert出来
                    // layer.msg("success",{icon:1});
                    var data = JSON.parse(data);
                    // console.log(data.pn);
                $('#path').dropdown('set selected', data.path);
                $('#size').val(data.size);
                $('#change').dropdown('set selected', data.change);
                // $('#pn').val(data.pn.toString());
                //     $("#pn").find("option[value = '"+data.pn+"']").attr("selected","selected");
                    $('#pn').dropdown('set selected', data.pn);
                }
            },
            error:function(jqXHR,textStatus,errorThrown){
                //充当catch(e)的角色
                layer.msg("刷新页面重试");
            }
        });
        var $play = $('.play.button');
        if ($play.hasClass('disabled')){
            $play.removeClass('disabled');
        }
        $it.attr('disabled',false);
        return false;
    });
});//end
String.prototype.NoSpace = function() {
    return this.replace(/\s+/g, "");
};
$.ajax({
    url:'/project/showCore.do',
    type:'get',
    // data:{id:id},
    dataType:'json',
    beforeSend:function(){
    },
    success:function (data) {
        var json = data.PublicModels;
        for (var i in json) {
            $('#copyProject').append("<div class=\"ui red raised horizontal segments\">\n" +
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