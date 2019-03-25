/* 与 node edge 无关的js */
var package_id = '${param.processid}';
package_id = package_id.indexOf('Package_') !== -1 ? package_id : 'Package_'+randomWord(false, 8);
var workflow_name = '详细';
var workflow_id = package_id+'_Wor1';
var create_time = new Date().pattern('yyyy-MM-dd HH:mm:ss');// 需要从xpdl读取
var create_type = '${param.kind}';
create_type = create_type.indexOf('param.kind') !== -1 ? create_type : 'create';// html防止报错
// 定义生成 node id 序列
var seqer_nodeID = serial_marker();
seqer_nodeID.set_prefix(workflow_id + '_Act');

// 定义生成 edge id 序列
var seqer_edgeID = serial_marker();
seqer_edgeID.set_prefix(workflow_id + '_Tra');

// 定义生成 参与者id 序列
var seqer_participantID = serial_marker();
seqer_participantID.set_prefix(workflow_id + '_Par');

// 定义生成 块活动blockId 序列
var seqer_blockId = serial_marker();
seqer_blockId.set_prefix(workflow_id + '_Ase');

$('.full-right>.menu').on('click', '.item', function() {
    var activitysetid = $(this).attr('activitysetid'); // 编辑快活动id
    if (activitysetid) {
        seqer_nodeID.set_prefix(activitysetid + '_Act');
    } else {
        seqer_nodeID.set_prefix(workflow_id + '_Act');
    }

    var containerId = $(this).attr('data-tab'); // graph.containerId
    graphPool.updateGraphActiveById(containerId);
});

$(function() {
    // 数据表格添加点击选中行
    $('.toolgrid,.extended_attr,.post_condition,.prop_edge,.role_manage').on('click', 'tbody tr', function() {
        $(this).addClass('active').siblings().removeClass('active');
        $(this).find('input:radio').prop('checked','checked');
    });

    $('.monitorinf,.conventional_definition,.timeout_limit,.monitorinfAddDefinition tbody').on('click', 'tr', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    $('.front_condition .dropdown.convergeType').on('change', function(e) {
        var convergeType = $(this).val();
        if (convergeType == 0) { // 空
            $('.otherOpt>div').addClass('hideDiv');
        }
        if (convergeType == 'AND') { // And
            $('.otherOpt>div').eq(0).removeClass('hideDiv');
            $('.otherOpt>div').eq(1).addClass('hideDiv');
        }
        if (convergeType == 'XOR') { // Xor
            $('.otherOpt>div').eq(0).addClass('hideDiv');
            $('.otherOpt>div').eq(1).removeClass('hideDiv');
        }
    });
});
$(function () {
    $('.ui.accordion')
        .accordion()
    ;
});
// 编辑工具(保存)
$(function() {
    $('.editor-toolbar .icon.save').on('click', function(e) {
        findInAndOut();
        createOrUpdateComplie();
        createOrUpdateFit();
        createOrUpdateEvaluate();
        var node = {
            'nodes': graph_main.nodes
        };
        var str = JSON.stringify(node);
        var nodeCore = {
            nodes: graph_main.nodes,
            edges: graph_main.edges
        };
        nodeCore = JSON.stringify(nodeCore);
        $.ajax({
            url:'/model/showRealCore', async:true,
            type:'post',
            data: {str:str},
            dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                if(textStatus=="success"){
                    //将得到的代码直接代码直接先alert出来
                    $('#fullscreen').modal({
                        allowMultiple: true,
                        autofocus: false,
                        duration: {},
                        onShow: function() {
                            $('#run_core pre').append(data.core);
                        },
                        onHidden: function() {
                            $(this).find('pre').each(function () {
                                $(this).empty();
                            });
                        },
                        onApprove:function () {
                            if (data.pid!=null){
                                $.ajax({
                                    url:'/project/update',
                                    type:"post",
                                    data:{modelId:data.pid,nodeCore:nodeCore},
                                    success:function (data) {
                                        layer.msg("更新成功");
                                    },
                                    error:function () {
                                        layer.msg("更新失败");
                                    }
                                });
                            }
                            else {
                                $('.ui.modal.add')
                                    .modal({
                                        allowMultiple: false,
                                        closable  : false,
                                        onApprove : function() {
                                            var name = $('.field input[name="name"]').val();
                                            var id = $('.field input[name="id"]').val();
                                            var description = $('.field textarea').val();
                                            console.log(description);
                                            console.log(data);
                                            var model = {name:name,describe_:description,nodeCore:nodeCore};
                                            var $add = $('.ui.segments.add');
                                            $.ajax({
                                                url:'/project/add', async:false,
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
                                    .modal('attach events', '#save1');
                            }
                        }
                    }).modal('show');
                }
            },
            error:function(jqXHR,textStatus,errorThrown){
                //充当catch(e)的角色
                layer.msg("刷新页面重试");
            }
        });
        $.ajax({
            url:'/model/show_core', async:true,
            type:'post',
            // dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                $('#data_core pre').append(data.toString());
            },
            error:function(jqXHR,textStatus,errorThrown){
                //充当catch(e)的角色
                alert("服务器繁忙");
            }
        });
        $.ajax({
            url:'/model/showPredict_core', async:true,
            type:'post',
            // dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                $('#predict_core pre').append(data.toString());
            },
            error:function(jqXHR,textStatus,errorThrown){
                //充当catch(e)的角色
                alert("服务器繁忙");
            }
        });
        return false;
    });
});
//生成data.py
$(function() {
    $('.ui.secondary.pointing.menu').on('click', '#run_py,#data_py,#predict_py',function(e) {
        var id = $(this).attr("id");
        if (id=="run_py"){
            if (!$('#run_core').hasClass('active'))
                $('#run_core').addClass('active').siblings().removeClass('active');
        }else if (id=="data_py"){
            if (!$('#data_core').hasClass('active'))
                $('#data_core').addClass('active').siblings().removeClass('active');
        }
        else if (id=="predict_py") {
            if (!$('#predict_core').hasClass('active'))
                $('#predict_core').addClass('active').siblings().removeClass('active');
        }
    });
});

// 编辑工具(保存数据)
var dataSet = {};
$(function() {
    $('.editor-toolbar .icon.paste').on('click', function(e) {
        $('.ui.modal.prop_node').modal({
            allowMultiple: true,
            autofocus: false,
            closable: false,
            onApprove: function() {
                $('.prop_node .conventional.DataIn').find('input[name], select').each(function() {
                    dataSet[$(this).attr('name')] = $(this).val();
                });
                var str = JSON.stringify(dataSet);
                $.ajax({
                    url:'/model/commitData', async:true,
                    type:'post',
                    data: {str:str},
                    // dataType:'json',
                    beforeSend:function(jqXHR, settings){
                    },
                    success:function(data, textStatus){
                        if(textStatus=="success"){
                            //将得到的代码直接代码直接先alert出来
                            $('.prop_node>.menu a[data-tab="Code"]').removeClass('hideitem');
                            $('.prop_node>.menu a[data-tab="Code"]').addClass('active');
                            $('.prop_node>.segment[data-tab="Code"]').addClass('active');
                            $('.ui.modal.prop_node').modal({
                                allowMultiple: true,
                                autofocus: false,
                                duration: {},
                                onShow: function() {
                                    $('.prop_node .conventional.Code').find('pre').each(function () {
                                        $(this).append('<div>'+data.toString()+'</div>');
                                    });
                                },
                                onHidden: function() {
                                    $('.prop_node .menu .item.active').removeClass('active').addClass('hideitem');
                                    $('.prop_node>.segment.active').removeClass('active');
                                    $('.prop_node .conventional.Code').find('pre').each(function () {
                                        $(this).empty();
                                    });
                                }
                            }).modal('show');
                        }
                    },
                    error:function(jqXHR,textStatus,errorThrown){
                        //充当catch(e)的角色
                        // alert("请先输入数据集");
                    }
                });
            },
            onShow:function () {
                $('.prop_node>.menu a[data-tab="DataIn"]').removeClass('hideitem');
                $('.prop_node>.menu a[data-tab="DataIn"]').addClass('active');
                $('.prop_node>.segment[data-tab="DataIn"]').addClass('active');
                $('.conventional').find('input[name]').each(function() {
                    for (var key in dataSet) {
                        if (key == $(this).attr('name')) {
                            $(this).val(dataSet[key]);
                        }
                    }
                });
            },
            onHidden:function () {
                $('.prop_node .menu .item.active').removeClass('active').addClass('hideitem');
                $('.prop_node>.segment.active').removeClass('active');
            }
        }).modal('show');
        return false;
    });
});
//隐藏登录注册
$(function () {
    $('.head .grid .center').hide();
});

//超参数
$(function () {
    $('.components-btn.clearfix[name="Hy"]').click(function (e) {
        var name = $(this).text().trim();
        var node = {};
        var f = graph_main.nodes.filter(function (v) {
            return v.conventional.current==name.toString().toLowerCase();
        });
        var graph_active = graphPool.getGraphByActiveEdit();
        var conventional = {};
        switch (name) {
            case 'Compile':
                $('.prop_node>.menu a[data-tab="Compile"]').removeClass('hideitem');
                $('.prop_node>.menu a[data-tab="Compile"]').addClass('active');
                $('.prop_node>.segment[data-tab="Compile"]').addClass('active');
                var node = {
                    id: seqer_nodeID.gensym(),
                    title: 'compile',
                    component: '',
                    type: '',
                    x: '-10000',
                    y: '-10000',
                    conventional: {
                        in :'Model',
                        out : 'fit',
                        current: 'compile',
                        x : 'X_train',
                        y : 'Y_train',
                        loss : "'mean_squared_error'",
                        optimizer : "'Nadam'",
                        metrics : "'accuracy'"
                    },
                    frontCondition: {},
                    postCondition: {},
                    extendAttr: [],
                    highLevel: {},
                    timeoutLimit: {},
                    monitorinf: {isResponsibleTem: true},
                    eventTypeId: null
                };
                break;
            case 'Fit':
                $('.prop_node>.menu a[data-tab="Fit"]').removeClass('hideitem');
                $('.prop_node>.menu a[data-tab="Fit"]').addClass('active');
                $('.prop_node>.segment[data-tab="Fit"]').addClass('active');
                node = {
                    id: seqer_nodeID.gensym(),
                    title: 'fit',
                    component: '',
                    type: '',
                    x: '-10000',
                    y: '-10000',
                    conventional: {
                        in :'compile',
                        out : 'evaluate',
                        current: 'fit',
                        x : 'X_train',
                        y : 'Y_train',
                        batch_size : '40',
                        epochs : '5',
                        validation_data:'(X_test, Y_test)',
                        callbacks:'[logs_loss]',
                        verbose : '1'
                    },
                    frontCondition: {},
                    postCondition: {},
                    extendAttr: [],
                    highLevel: {},
                    timeoutLimit: {},
                    monitorinf: {isResponsibleTem: true},
                    eventTypeId: null
                };
                break;
            case 'Evaluate':
                $('.prop_node>.menu a[data-tab="Evaluate"]').removeClass('hideitem');
                $('.prop_node>.menu a[data-tab="Evaluate"]').addClass('active');
                $('.prop_node>.segment[data-tab="Evaluate"]').addClass('active');
                var node = {
                    id: seqer_nodeID.gensym(),
                    title: 'evaluate',
                    component: '',
                    type: '',
                    x: '-10000',
                    y: '-10000',
                    conventional: {
                        in :'fit',
                        out : '',
                        current: 'evaluate',
                        x : 'X_train',
                        y : 'Y_train',
                        verbose : '1'
                    },
                    frontCondition: {},
                    postCondition: {},
                    extendAttr: [],
                    highLevel: {},
                    timeoutLimit: {},
                    monitorinf: {isResponsibleTem: true},
                    eventTypeId: null
                };
                break;
            default:
                break;
        }
        if (f.length==0){
            f[0] = node;
            graph_active.nodes.push(node);
            graph_active.updateGraph();
        }
        $('.ui.modal.prop_node').modal({
            autofocus: false,
            closable: false,
            onApprove: function() {
                //更新-常规
                switch (name) {
                    case 'Fit':
                        $('.prop_node .conventional.Fit').find('input[name], textarea, select').each(function() {
                            conventional[$(this).attr('name')] = $(this).val();
                        });
                        break;
                    case 'Compile':
                        $('.prop_node .conventional.Compile').find('input[name], textarea, select').each(function() {
                            conventional[$(this).attr('name')] = $(this).val();
                        });
                        break;
                    case 'Evaluate':
                        $('.prop_node .conventional.Evaluate').find('input[name], textarea, select').each(function() {
                            conventional[$(this).attr('name')] = $(this).val();
                        });
                        break;
                    default:
                        break;
                }
                f[0].conventional=conventional;
                graph_active.updateGraph();
                updateCore();
            },
            onShow: function() {
                var conventional = f[0].conventional;
                $('.active .conventional').find('input[name]').each(function() {
                    for (var key in conventional) {
                        if (key == $(this).attr('name')) {
                            $(this).val(conventional[key]);
                        }
                    }
                });
                $('.active .conventional').find('select').each(function() {
                    for (var key in conventional) {
                        if (key == $(this).attr('name')) {
                            $(this).dropdown('set selected', conventional[key]);
                        }
                    }
                });
            },
            onHidden: function() {
                $('.prop_node>.secondary.menu>.item.active').removeClass('active').addClass('hideitem');
                $('.prop_node>.segment.active').removeClass('active');
                $('.monitorinf select[name="isResponsibleTem"]').off('change'); // 弹窗关闭，避免清空表单时触发事件
            }
        }).modal('show');
    });
});
//跳转经典网络
$(function () {
    $('.editor-toolbar .icon.repeat').click(function () {
        window.location.href="/model/to_classic_net";
    });
});
$(function () {
    $('#download').click(function () {
        downloadFile("/model/download");
        downloadFile("/model/downloadData");
        downloadFile("/model/downloadPredict");
    });
});
//下载文件
// 定义到jQuery全局变量下-文件下载
// jQuerywnload = function (url, method, filedir) {
//     jQuery('<form action="' + url + '" method="' + (method || 'post') + '">' +  // action请求路径及推送方法
//         '</form>')
//         .appendTo('body').submit().remove();
// };
// $(function () {
//     $('#download').click(function () {
//         // $.ajax({
//         //     type: "post",
//         //     url: "/FileExport/Export",
//         //     dataType: "json",
//         //     data: { address: addr},
//         //     success: function (data) {
//         //         $wnload('/FileExport/DownLoadFile', 'post', data.value); // 下载文件
//         //     },
//         //     error: function (data) {
//         //         alert("对不起，出现错误，请稍后重试或联系管理员");
//         //     }
//         // });
//     });
//
// });
//点击左侧按钮显示属性菜单
$(function () {
    $('select').on("click",function (e) {
        this.parentNode.nextSibling.value=this.value;
    });
});
/*  */
$(function() {
    var initProp = '<div>'+
        '	<div name="id" class="prop-value"><span>id:</span><span>'+workflow_id+'</span></div>'+
        '	<div name="name" class="prop-value"><span>名称:</span><span>'+workflow_name+'</span></div>'+
        '</div>'+
        '<div class="clearfix"></div>';
    $('.component-prop').append(initProp);
    $('.component-name span').text(workflow_name);
    $('.menu .item').tab();// 标签显示必须放updateScrollbar之前

    $('.menu .item').on('click', updateScrollbar);

    // 控制属性显示
    $('.full-right').on('click', '.component-name', function() {
        var parent = $(this).parents('div[data-tab]');
        var ishidden = parent.find('.component-prop').is(':hidden');
        if (ishidden) {
            parent.find('.full-right-bottom ').css({height: '26%'});
            parent.find('.component-name').css({height: '28%'});
            parent.find('.view').css({height: '68%'});
            parent.find('.component-name .icon').removeClass('up').addClass('down');
            parent.find('.component-prop').toggle('slow');
        } else {
            parent.find('.full-right-bottom ').css({height: '8%'});
            parent.find('.component-name').css({height: '100%'});
            parent.find('.view').css({height: '86%'});
            parent.find('.component-name .icon').removeClass('down').addClass('up');
            parent.find('.component-prop').toggle();
        }
    });
});

$(function() {
    $('#rMenu').on('mouseleave', function() {
        $('#rMenu').hide();
        // $('#rMenu .item').off('click');
    });
    // semantic初始化
    $('.pop-btn').popup();
    // jquery插件滚动条
    $(".content-div, .postCondi_extendedAttr, .conditionList, .conditionList2").mCustomScrollbar();

    // 活动属性semantic初始化
    $('.prop_node .menu .item').tab();
    /* IE11下 Semantic UI 存在问题
    $('.prop_node>.menu .item').on('click', function() {
        var item = $(this).attr('data-tab');
        $('.prop_node [data-tab="'+item+'"]').find('.content-div').mCustomScrollbar("update");
        if (item == 'four') {
            $('.post_condition .postCondi_extendedAttr').mCustomScrollbar("update");
            $('.prop_node.modal').css({
                height: '745px'
            });
            $('.prop_node>.tab').css({
                height: '608px'
            });
        } else {
            $('.prop_node.modal').css({
                height: '577px'
            });
            $('.prop_node>.tab').css({
                height: '447px'
            });
        }
    });*/

    // 后置条件下的tab
    $('.targetActivity .menu .item').on('click', function() {// 防止切换切换标签时滚动条消失
        var item = $(this).attr('data-tab');
        if (item == 'four/b') {
            $('.conditionDiv .conditionList').mCustomScrollbar("update");
        }
        if (item == 'four/a') {
            $('.targetActivity .postCondi_extendedAttr').mCustomScrollbar("update");
        }
    });

    $('select.dropdown').dropdown({
        allowAdditions: true,
        maxSelections: 1
    });

    $('.ui.checkbox').checkbox({
        onChecked: function() {
            $(this).parents('.checkbox').find('input[name]').val(true);
        },
        onUnchecked: function() {
            $(this).parents('.checkbox').find('input[name]').val(false);
        }
    });

    // checkbox 多人处理是否顺序执行
    $('.checkbox.assignmentsOrderDiv').checkbox({
        onChecked: function() {
            $(this).parents('.checkbox').find('input[name]').val(true);
            $('.checkbox.completeAllAssignmentsDiv').checkbox('check');
        },
        onUnchecked: function() {
            $(this).parents('.checkbox').find('input[name]').val(false);
        }
    });

    // checkbox 是否多人处理
    $('.checkbox.completeAllAssignmentsDiv').checkbox({
        onChecked: function() {
            $(this).parents('.checkbox').find('input[name]').val(true);
        },
        onUnchecked: function() {
            $('.checkbox.assignmentsOrderDiv').checkbox('uncheck');
            $(this).parents('.checkbox').find('input[name]').val(false);
        }
    });

    $('.front_condition .radio.checkbox').checkbox({
        onChecked: function() {
            $('.front_condition').find('input[name=isCreateNew]').val($(this).attr('tabindex'));
        }
    });

    /* 活动属性-常规-定义 */
    $(document).on('click', '.conventional .definitionBtn', function() {
        $('.conventional_definition.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.prop_node').dimmer('hide');
                clearModal(this);
                $(this).find('tbody').empty();
                $('.conventional_definition .menu .item[data-tab="definition_1"]').trigger('click');
                $('.conventional_definition').find('.menu .item[data-tab="definition_2/processer"]').show();
                $('.conventional_definition').find('.menu .item[data-tab="definition_2/historyactselect"]').hide();
            },
            onShow: function() {
                $('.modal.prop_node').dimmer({closable: false}).dimmer('show');
            }
        }).modal('show');
    });

    /* 活动属性-工具-添加 */
    $(document).on('click', '.modal.prop_node .toolAddBtn', function() {
        $('.tool_add.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.prop_node').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.prop_node').dimmer({closable: false}).dimmer('show');
            }
        }).modal('show');
    });

    /* 活动属性-工具-添加-扩展属性添加 */
    $(document).on('click', '.modal.tool_add .actualParametersAdd2', function() {
        $('.tool_extendAttr_add.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.tool_add').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.tool_add').dimmer({closable: false}).dimmer('show');
            }
        }).modal('show');
    });

    /* 活动属性-后置条件-扩展属性-添加 */
    $(document).on('click', '.post_condition .extendAttrAddBtn', function() {
        $('.postCondition_extendAttr_add.modal').modal({
            allowMultiple: true,
            autofocus: true,
            duration: {},
            onHidden: function() {
                $('.modal.prop_node').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.prop_node').dimmer({closable: false}).dimmer('show');
                $('.postCondition_extendAttr_add .green.button').data('event_source', '.post_condition');
            }
        }).modal('show');
    });

    /* 连线属性-扩展属性-添加 */
    $(document).on('click', '.prop_edge .extendAttrAddBtn', function() {
        $('.postCondition_extendAttr_add.modal').modal({
            allowMultiple: true,
            autofocus: true,
            duration: {},
            onHidden: function() {
                $('.modal.prop_edge').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.prop_edge').dimmer({closable: false}).dimmer('show');
                $('.postCondition_extendAttr_add .green.button').data('event_source', '.prop_edge');
            }
        }).modal('show');
    });

    /* 活动属性-后置条件-条件设置-关系设置 */
    $(document).on('click', '.relationshipPlaBtn', function() {
        var num = $('.workflowbeanDiv tbody tr').length;
        if (num) {
            $('.relationshipPlacement.modal').modal({
                allowMultiple: true,
                autofocus: false,
                duration: {},
                onHidden: function() {
                    $('.modal.prop_node, .modal.prop_edge').dimmer('hide');
                    clearModal(this);
                },
                onShow: function() {
                    $('.modal.prop_node, .modal.prop_edge').dimmer({closable: false}).dimmer('show');
                    $('.relationshipPlacement .condition_no').empty();
                    for (var i = 0; i < num; i++) {
                        $('.relationshipPlacement .condition_no').append(
                            '<div class="inline field">'+
                            '  	<div class="ui checkbox">'+
                            '  	  	<input type="checkbox" tabindex="0" class="hidden">'+
                            '  	  	<label>${'+(i+1)+'}</label>'+
                            '  	  	<input type="hidden" name="${'+(i+1)+'}" value="">'+
                            '  	</div>'+
                            '</div>');
                    }
                    $('.relationshipPlacement .condition_no').find('.ui.checkbox').checkbox({
                        onChecked: function() {
                            $(this).parents('.checkbox').find('input[name]').val(true);
                        },
                        onUnchecked: function() {
                            $(this).parents('.checkbox').find('input[name]').val(false);
                        }
                    });
                }
            }).modal('show');
        } else {
            layer.msg('请添加条件!', {time: 2000, icon:2});
        }
    });

    /* 活动属性-扩展属性-添加 */
    $(document).on('click', '.extended_attr .extendAttrAddBtn', function() {
        $('.extendAttr_add.modal').modal({
            allowMultiple: true,
            autofocus: true,
            duration: {},
            onHidden: function() {
                $('.modal.prop_node').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.prop_node').dimmer({closable: false}).dimmer('show');
            }
        }).modal('show');
    });

    /* 超时限制-增加 */
    $(document).on('click', '.modal.prop_node .timeoutLimitAddBtn', function() {
        $('.timeoutLimit_add.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.prop_node').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.prop_node').dimmer({closable: false}).dimmer('show');
            }
        }).modal('show');
    });

    /* 监控信息-增加 */
    /*$('.monitorinf_add.modal').modal({
        allowMultiple: true,
        autofocus: false,
        duration: {},
        onHidden: function() {
            $('.modal.prop_node').dimmer('hide');
            clearModal(this);
        },
        onShow: function() {
            $('.modal.prop_node').dimmer({closable: false}).dimmer('show');
        }
    }).modal('attach events', '.modal.prop_node .monitorinfAddBtn'); //attach events 在firefox下存在问题*/

    $(document).on('click', '.modal.prop_node .monitorinfAddBtn', function() {
        $('.monitorinf_add.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.prop_node').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.prop_node').dimmer({closable: false}).dimmer('show');
            }
        }).modal('show');
    });

    /* 监控信息-增加-定义 */
    $(document).on('click', '.modal.monitorinf_add .definitionBtn', function() {
        $('.monitorinfAddDefinition.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.monitorinf_add').dimmer('hide');
                clearModal(this);
                $(this).find('tbody').empty();
            },
            onShow: function() {
                $('.modal.monitorinf_add').dimmer({closable: false}).dimmer('show');
                $('.monitorinfAddDefinition select[name="definition_role"]').dropdown('set selected', 'responsible');
            }
        }).modal('show');
    });

    /* 工具-添加-定义 */
    $(document).on('click', '.modal.tool_add .definitionBtn', function() {
        $('.tooldefinition.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.tool_add').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.tool_add').dimmer({closable: false}).dimmer('show');
            }
        }).modal('show');
    });

    /* 工具-添加-定义-添加 */
    $(document).on('click', '.modal.tooldefinition .tooldefinitionAddBtn', function() {
        $('.tooldefinition_add.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.tooldefinition').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.tooldefinition').dimmer({closable: false}).dimmer('show');
            }
        }).modal('show');
    });

    /* 工具-添加-定义-添加-添加 */
    $(document).on('click', '.modal.tooldefinition_add .tooldefinitionAddAddBtn', function() {
        $('.tooldefinition_add_add.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.tooldefinition_add').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.tooldefinition_add').dimmer({closable: false}).dimmer('show');
            }
        }).modal('show');
    });

    /* 工具-添加-添加 */
    $(document).on('click', '.modal.tool_add .actualParametersAdd1', function() {
        $('.actualParametersDiv.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.tool_add').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.tool_add').dimmer({closable: false}).dimmer('show');
                //ajax请求角色数据
            }
        }).modal('show');
    });

    /* 工具-添加-添加-定义 */
    $(document).on('click', '.modal.actualParametersDiv .definitionBtn', function() {
        $('.toolAddDefinition.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.actualParametersDiv').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.actualParametersDiv').dimmer({closable: false}).dimmer('show');
            }
        }).modal('show');
    });

    /* 工具-添加-添加-定义-添加 */
    $(document).on('click', '.modal.toolAddDefinition .toolAddDefinitionAddBtn', function() {
        $('.toolAddDefinitionAdd.modal').modal({
            allowMultiple: true,
            autofocus: false,
            duration: {},
            onHidden: function() {
                $('.modal.toolAddDefinition').dimmer('hide');
                clearModal(this);
            },
            onShow: function() {
                $('.modal.toolAddDefinition').dimmer({closable: false}).dimmer('show');
            }
        }).modal('show');
    });

    /* 常规-定义-高级-一般 类型 onChange 事件 */
    $('.def_common [data-tab$="/commonly"] select[name=definition_type]').on('change', function() {
        $(this).parents('[data-tab$="/commonly"]').find('input').val('');
    });

    /* 点击显示树形结构div */
    $('input[name="def_name_show"]').on('click', function() {
        var def_type = $(this).parents('.modal').find('.tab[data-tab$="/commonly"] select[name=definition_type]').val();
        if (!def_type) {
            layer.msg('先选择类型！', {time: 2000, icon:0, offset: '180px'});
            return;
        }
        if (def_type.indexOf('role') != -1) {// 类型-角色
            $('.role_manage.modal').modal({
                allowMultiple: true,
                autofocus: false,
                duration: {},
                onHidden: function() {
                    $('.modal.conventional_definition,.modal.monitorinfAddDefinition').dimmer('hide');
                    clearModal(this);
                },
                onShow: function() {
                    $('.modal.conventional_definition,.modal.monitorinfAddDefinition').dimmer({closable: false}).dimmer('show');
                    selectInfo(1, 15);
                }
            }).modal('show');
            return;
        }
        // 根据类型 定义url
        var url = '';
        if (def_type == 'human|人') {
            url = 'platform/person/userTree?state=';
        } else if (def_type.indexOf('org') != -1) {
            url = 'platform/organise/organiseTree?state=';
        } else if (def_type == 'allParty|所有人【人】') {
            layer.msg('不需要填写！', {time: 2000, icon:4});
            return;
        }

        var setting = {
            view: {
                dblClickExpand: false
            },
            check: {
                enable: true,
                chkStyle: "radio",
                radioType: "all"
            },
            callback: {
                onClick: OnClick,
                onAsyncSuccess: onsuccess
            },
            async: {
                autoParam: ["id=node", "value=parentId"],
                otherParam: {},
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                enable: true,
                type: "post",
                url: url,
                dataFilter: ajaxDataFilter
            },
            data:{
                simpleData: {
                    enable: true,
                    rootPId: "xnode-40"
                }
            }
        };

        treelayer = layer.open({
            type: 1,
            title: 'PersonTree',
            skin: 'layui-layer-rim', // 边框
            area: ['300px', '350px'], // 宽高
            offset: ['167px', '587px'],
            content: '<div id="treeDemo_div" style="height:98%">'+
                '<ul id="treeDemo" class="ztree">'+
                '</ul>'+
                '<div class="buttons_bottom">'+
                '<div class="ui horizontal divider">'+
                '<button class="ui primary mini button">确定</button>'+
                '<button class="ui cancel mini button">取消</button>'+
                '</div>'+
                '</div>'+
                '</div>'
        });
        /* ztree */
        $.fn.zTree.init($("#treeDemo"), setting);
        zTree = $.fn.zTree.getZTreeObj("treeDemo");
    });
    // 树形结构下 取消
    $(document).on('click', '#treeDemo_div .cancel', function() {
        layer.close(treelayer);
    });
    // 树形结构下 确定
    $(document).on('click', '#treeDemo_div .primary', function() {
        var nodes = zTree.getCheckedNodes(true);
        if (nodes.length) {
            if ($('.conventional_definition').is(':visible')) {
                $('.conventional_definition input[name=def_name_show]').val(nodes[0].name);
                $('.conventional_definition input[name=definition_name]').val(nodes[0].value + '|' + nodes[0].name);
            } else {
                $('.monitorinfAddDefinition input[name=def_name_show]').val(nodes[0].name);
                $('.monitorinfAddDefinition input[name=definition_name]').val(nodes[0].value + '|' + nodes[0].name);
            }
        } else {
            layer.msg('请选中元素！', {time: 2000, icon: 0, offset: ['270px','650px']});
            return;
        }
        layer.close(treelayer);
    });
    // 角色管理-搜索
    $('.role_manage .search.icon').on('click', function() {
        selectInfo(1, 15);
    });
    // 角色管理-重置
    $('.role_manage .resetBtn').on('click', function() {
        $(this).siblings('.role-search').find('input').val('');
        $('.role_manage .search.icon').trigger('click');
    });
    // 角色管理-确定
    $('.role_manage .okBtn').on('click', function() {
        var jsonstr = $(this).parents('#role-center').find('tr.active').attr('jsonstr');
        if (jsonstr) {
            var jsonobj = JSON.parse(jsonstr);
            if ($('.conventional_definition').is(':visible')) {
                $('.conventional_definition input[name=def_name_show]').val(jsonobj.ROLENAME);
                $('.conventional_definition input[name=definition_name]').val(jsonobj.ROLEID + '|' + jsonobj.ROLENAME);
            } else {
                $('.monitorinfAddDefinition input[name=def_name_show]').val(jsonobj.ROLENAME);
                $('.monitorinfAddDefinition input[name=definition_name]').val(jsonobj.ROLEID + '|' + jsonobj.ROLENAME);
            }
            $(this).parents('.modal').modal('hide');
        } else {
            layer.msg('请选中元素！', {time: 2000, icon: 0, offset: '180px'});
            return;
        }
    });


});

// 清空表单
function clearModal(selector) {
    $(selector).find('input, textarea').val('');
    $(selector).find('.ui.dropdown').dropdown('clear');
}

// 刷新显示滚动条
function updateScrollbar() {
    var dataTab = $(this).attr('data-tab');
    if (dataTab == 'third') { //xml视图
        $("#xmlContainer").mCustomScrollbar("update");
    }
    if (dataTab == 'second') { //xpdl视图
        $("#xpdlContainer").mCustomScrollbar("update");
    }
}

// zTree 数据过滤
function ajaxDataFilter(treeId, parentNode, responseData) {
    if (responseData) {
        for (var i = 0; i < responseData.length; i++) {
            responseData[i].name = responseData[i].text;
            responseData[i].open = responseData[i].expanded;
            responseData[i].isParent = !responseData[i].leaf;
            responseData[i].nocheck = responseData[i].checked == false ? false : true;
        }
    }
    return responseData;
}

// zTree 点击节点
function OnClick(event, treeId, treeNode) {
    zTree.checkNode(treeNode, true, false);
}

// zTree onsuccess
function onsuccess(event, treeId, treeNode, msg) {
    try {
        var dataJson = JSON.parse(msg);
        if (!dataJson.length) return;
        if (dataJson[0].id == 'root-root' && dataJson[0].expanded) {
            var node = zTree.getNodeByParam("id", dataJson[0].id);
            zTree.expandNode(node, true);
        }
    } catch (e) {
        layer.msg('platform 未登录！', {time: 2000, icon:2});
        console.error('platform 未登录！');
        //window.location.href = "https://segmentfault.com/search?q=[js]+" + e.message;
    }
}

// 角色管理分页
function selectInfo(offset, size) {
    if (offset) {
        $(".role_manage #offset").val(offset);
    }
    if (size) {
        $(".role_manage #size").val(size);
    }
    $("#searchForm").trimForm().ajaxSubmit({
        url: "platform/role/query?hyper=false",
        type: "post",
        data: {
            "start": (offset-1)*size,
            "limit": size //后台用的默认参数15，传值无效
        },
        dataType: "json",
        success: function(result) {
            $('.role_manage .content-tab').empty();
            if (result.totalCount) {
                $.pages({
                    count: result.totalCount, //总数量
                    curr: offset, //初始化当前页
                }, selectInfo, size);
                //显示数据
                $('.role_manage .content-tab').html(juicer($('#role_manage_tpl').html(), result));
                $('.role_manage .content-tab').find('.ui.checkbox').checkbox();
            } else {
                $('.role_manage .content-tab').html('<div class="no-info">暂无信息...</div>');
            }
        },
        error: function(data) {
            alert("服务器繁忙,请稍后再试...");
        }
    });
}

/*-------------初始化 tab select start-------------*/
initSelect();

function initSelect() {
}
window.downloadFile = function(url){
    var iframe = document.createElement("iframe");
    iframe.style.display = "none"; // 防止影响页面
    iframe.style.height = 0; // 防止影响页面
    iframe.src = url;
    document.body.appendChild(iframe); // 这一行必须，iframe挂在到dom树上才会发请求
    // 5分钟之后删除（onload方法对于下载链接不起作用，就先抠脚一下吧）
    setTimeout(function(){
        iframe.remove();
    }, 1000);
};
window.updateCore = function() {
    findInAndOut();
    createOrUpdateComplie();
    createOrUpdateFit();
    createOrUpdateEvaluate();
    var node = {
        'nodes': graph_main.nodes
    };
    var str = JSON.stringify(node);
    var nodeCore = {
        nodes: graph_main.nodes,
        edges: graph_main.edges
    };
    nodeCore = JSON.stringify(nodeCore);
    $.ajax({
        url:'/model/showRealCore', async:true,
        type:'post',
        data: {str:str},
        dataType:'json',
        beforeSend:function(jqXHR, settings){
        },
        success:function(data, textStatus){
            if(textStatus=="success"){
                //将得到的代码直接代码直接先alert出来
                if (data.pid!=null){
                    $.ajax({
                        url:'/project/update',
                        type:"post",
                        data:{modelId:data.pid,nodeCore:nodeCore},
                        success:function (data) {
                            layer.msg("更新成功");
                        },
                        error:function () {
                            layer.msg("更新失败");
                        }
                    });
                }
            }
        },
        error:function(jqXHR,textStatus,errorThrown){
            //充当catch(e)的角色
            layer.msg("刷新页面重试");
        }
    });
    $.ajax({
        url:'/model/show_core', async:true,
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
        url:'/model/showPredict_core', async:true,
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
    return false;
};
/*-------------初始化 tab select end-------------*/
