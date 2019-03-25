/**
 * 工具栏-导入/导出功能
 */
function handleImportOrExport(e) {
    var isImport = e.target.className.indexOf('in'),
        textarea = $('.json_data textarea');
    $('.ui.modal.json_data').modal({
        onApprove: function() {
            if (isImport !== -1) { // 导入
                var jsonStr = textarea.val();
                if (jsonStr) {
                    var jsonObj = JSON.parse(jsonStr);
                    jsonObj = edgeAssociateNode(jsonObj);
                    graph_main.nodes = graph_main.nodes.concat(jsonObj.nodes);
                    graph_main.edges = graph_main.edges.concat(jsonObj.edges);
                    graph_main.updateGraph();
                }
            }
        },
        onHidden: function() {
            textarea.val('');
        }
    })
        .modal('setting', 'transition', 'scale')
        .modal('show');

    var element_header = $('div.json_data .header');
    if (isImport !== -1) {
        element_header.text('导入数据');
    } else {
        element_header.text('导出数据');
        var data = {
            nodes: graph_main.nodes,
            edges: graph_main.edges
        };
        textarea.val(JSON.stringify(data));
    }
}
// 寻找入度为0,与出度为0的节点,为model添加inputs与outputs
function findInAndOut() {
    var inputs = "";
    var outputs = "";
    var graph_active = graphPool.getGraphByActiveEdit();
    graph_main.nodes.forEach(function (value) {
        var con = value.conventional;
        if (con.in==""){
            inputs+=","+con.current;
        }
        if (con.out==""&&con.current!="evaluate"||con.out=="Model"){
            outputs+=","+con.current;
                con.out='Model';
        }
    });
    if (inputs.charAt(0)==","){
        inputs = inputs.substring(1,inputs.length);
    }
    if (outputs.charAt(0)==","){
        outputs = outputs.substring(1,outputs.length);
    }
    var f = graph_main.nodes.filter(function (v) {
        return v.conventional.current=='Model';
    });
    var d = {
        id: seqer_nodeID.gensym(),
        title: 'model',
        component: 'Activity',
        type: 'Hy',
        x: '-10000',
        y: '-10000',
        conventional: {
            in :outputs,
            out : 'compile',
            current: 'Model',
            inputs : inputs,
            outputs : outputs
        },
        frontCondition: {},
        postCondition: {},
        extendAttr: [],
        highLevel: {},
        timeoutLimit: {},
        monitorinf: {isResponsibleTem: true},
        eventTypeId: null
    };
    if (f.length==0){
        graph_active.nodes.push(d);
    }
    else{
        f[0].conventional.inputs=inputs;
        f[0].conventional.outputs=outputs;
    }
    graph_active.updateGraph();
}
//创建Compil_Node
function createOrUpdateComplie() {
    var graph_active = graphPool.getGraphByActiveEdit();
    var f = graph_main.nodes.filter(function (v) {
        return v.conventional.current=='compile';
    });
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
    if (f.length==0){
        graph_active.nodes.push(node);
    }
    graph_active.updateGraph();
}
//创建Fit_Node
function createOrUpdateFit() {
    var graph_active = graphPool.getGraphByActiveEdit();
    var f = graph_main.nodes.filter(function (v) {
        return v.conventional.current=='fit';
    });
    var node = {
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
    if (f.length==0){
        graph_active.nodes.push(node);
    }
    graph_active.updateGraph();
}
//创建Evalute_Node
function createOrUpdateEvaluate() {
    var graph_active = graphPool.getGraphByActiveEdit();
    var f = graph_main.nodes.filter(function (v) {
        return v.conventional.current=='evaluate';
    });
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
    if (f.length==0){
        graph_active.nodes.push(node);
        graph_active.updateGraph()
    }
    else{
        f[0].conventional=node.conventional;
        graph_active.updateGraph();
    }
}
/**
 * 工具栏-清空
 */
function clearGraph() {
    layer.confirm('确认清空？', {
        icon: 0,
        btn: ['确定','取消'],
        offset: '180px'
    }, function() {
        var pools = graphPool.pools;
        for (var i = 0; i < pools.length; i++) {
            var id = pools[i].containerId;
            switch (id) {
                case 'tab_main':
                    pools[i].deleteGraph();
                    break;
                default:
                    $('.full-right [data-tab='+id+']').remove();
                    pools.splice(i, 1);
                    break;
            }
        }
        layer.msg('删除成功', {icon: 1, offset: '180px', time: 600});
    }, function() {

    });

}

/**
 * 工具栏-删除节点
 */
function handleDeleteNode() {
    var graph_active = graphPool.getGraphByActiveEdit();
    var selectedNode = graph_active.state.selectedNode,
        selectedEdge = graph_active.state.selectedEdge;
    if (!selectedNode && !selectedEdge) {
        layer.msg('请选中元素！', {time: 2000, icon: 0, offset: '180px'});
        return;
    } else {
        layer.confirm('确定要删除选择元素吗？', {
            icon: 0,
            btn: ['确定','取消'],
            offset: '180px'
        }, function() {
            if (selectedNode) {
                var nodes = graph_active.nodes;
                nodes.splice(nodes.indexOf(selectedNode), 1);
                graph_active.spliceLinksForNode(selectedNode);
                if (selectedNode.component === 'blockActivity') {
                    var containerId = 'tab_'+selectedNode.id;
                    $('.full-right [data-tab='+containerId+']').remove();
                    graphPool.removeGraphFromPools(containerId);
                }
                selectedNode = null;
                graph_active.updateGraph();
            } else if (selectedEdge) {
                var edges = graph_active.edges;
	              var nodes = graph_active.nodes;
                var source = selectedEdge.source.conventional.current;
                var target = selectedEdge.target.conventional.current;
                nodes.forEach(function (value) {
                    var conventional = value.conventional;
                    if (conventional.current==source){
	                    var output = conventional.out.split(",");
	                    output.splice(output.indexOf(target),1);
	                    output.join(",");
	                    value.conventional.out = output.toString();
                    }
                    else if (conventional.current==target){
	                    var input = conventional.in.split(",");
	                    input.splice(input.indexOf(source),1);
	                    input.join(",");
	                    value.conventional.in = input.toString();
                    }
                });
	              edges.splice(edges.indexOf(selectedEdge), 1);
                selectedEdge = null;
                graph_active.updateGraph();
            }
            layer.msg('删除成功', {icon: 1, offset: '180px', time: 600});
        }, function() {

        });
    }
}

/**
 * 工具栏-放大/缩小按钮 scale(0.3-2)
 */
function handleClickZoom() {
    var graph_active = graphPool.getGraphByActiveEdit();
    var translate = graph_active.dragSvg.translate(),
        scale = graph_active.dragSvg.scale(),
        extent = graph_active.dragSvg.scaleExtent(),
        direction = 1,
        factor = 0.1;
    direction = (this.id === 'zoom-enlarge') ? 1 : -1;
    if ((scale <= extent[0] && direction < 0) || (scale >= extent[1] && direction > 0)) {
        return;
    } else {
        scale = parseFloat(scale) + factor * direction;
    }
    graph_active.dragSvg.scale(scale)
        .translate(translate);
    graph_active.zoomed();
}

/**
 * 工具栏-还原缩放及归位
 */
function resetZoom() {
    var graph_active = graphPool.getGraphByActiveEdit();
    graph_active.svgG.transition() // start a transition
        .duration(1000) // make it last 1 second
        .attr('transform', 'translate(0,0) scale(1)');
    graph_active.dragSvg.scale(1);
    graph_active.dragSvg.translate([0,0]);
}

/**
 * 工具栏-帮助
 */
function handleHelp() {
    if ($('.layer_notice').length) return;
    layer.open({
        type: 1,
        shade: false,
        title: false, // 不显示标题
        offset: ['130px', '450px'],
        content: '<ul class="layer_notice">'+
            '  <li><a href="javascript:;">1. 将左侧活动拖至编辑区</a></li>'+
            '  <li><a href="javascript:;">2. 左上角第三个按钮输入数据</a></li>'+
            '  <li><a href="javascript:;">3. 左上角第一、二个按钮显示代码</a></li>'+
            '</ul>',
        cancel: function() {
            // console.log('helper closed!');
        }
    });
}

/**
 * 左侧组件
 */
function handleComponentsBtn() {
    $(this).siblings().removeClass('active').end().addClass('active');
    var graph_active = graphPool.getGraphByActiveEdit(),
        state = graph_active.state,
        nodeName = $(this).attr('name'),
        container = $('.svg-container');
    if (nodeName === 'NOROUTING' || nodeName === 'SIMPLEROUTING') {
        state.drawLine = nodeName;
        container.on('mouseover mouseout', '.conceptG', function(e) {
            if (e.type === 'mouseover') {
                this.style.cursor = 'crosshair';
            } else if (e.type == 'mouseout') {
                this.style.cursor = 'default';
            }
        });
    } else {
        container.off('mouseover mouseout', '.conceptG');
        state.drawLine = null;
    }
}

/**
 * 自动插入开始结束节点
 */
function handleAddStartEnd() {
    var graph_active = graphPool.getGraphByActiveEdit();
    var edges = graph_active.edges;
    var nodes = graph_active.filterActivities();
    nodes.forEach(function(node) {
        if (!graph_active.hasLinked(node, false, -1)) {
            var start = {
                id: generateUUID(),
                title: 'S',
                component: 'startComponent',
                type: 'start',
                x: node.x - 120,
                y: node.y
            };
            graph_active.nodes.push(start);
            var edge_start = {
                edgeId: generateUUID(),
                drawLine: 'NOROUTING',
                source: start,
                target: node
            };
            graph_active.edges.push(edge_start);
            graph_active.updateGraph();
        }
        if (!graph_active.hasLinked(node, false, 1)) {
            var end = {
                id: generateUUID(),
                title: 'E',
                component: 'endComponent',
                type: 'end',
                x: node.x + 120,
                y: node.y
            };
            graph_active.nodes.push(end);
            var edge_end = {
                edgeId: generateUUID(),
                drawLine: 'NOROUTING',
                source: node,
                target: end
            };
            graph_active.edges.push(edge_end);
            graph_active.updateGraph();
        }
    });
}

/**
 * 视图显示Tab（图标视图、Xpdl视图、Xml视图）
 */
function handleViews() {
    var dataTab = $(this).attr('data-tab');
    var element = $('.full-right>.tab.active .content-div');
    var activitysetid = $('.full-right>.menu>.item.active').attr('activitysetid');
    switch (dataTab) {
        case 'second':
            var xpdlContent = graph_main.emergeAllxpdlContent();
            $('#xpdlContainer xmp').empty().text(xpdlContent);
            element.mCustomScrollbar("update");
            break;
        case 'third':
            var XmlContent = graph_main.emergeAllXmlContent();
            $('#xmlContainer xmp').empty().text(XmlContent);
            break;
    }
    var isSubGraphXpdlView = /Package_(.+)_second/.test(dataTab);
    if (isSubGraphXpdlView) {
        var blockActivity = graph_main.findActByActSetId(activitysetid);
        var subGraph = blockActivity.activitySet.graphCreator;
        var activitySet = graph_main.emergeActivitySet.call(subGraph, activitysetid);
        activitySet = vkbeautify.xml('<ActivitySet>' + activitySet + '</ActivitySet>');
        element.find('xmp').empty().text(activitySet);
        element.mCustomScrollbar("update");
    }
}

/*function handleSave() {
    // var dataTab = $('.full-right-btn .item.active').attr('data-tab');
    // $('.tab[data-tab="tab_main"] .item').not($('.full-right-btn .item.active')).trigger('click'); // 触发点击事件获取xpdl和xml
    // $('.full-right-btn .item[data-tab="' + dataTab + '"]').trigger('click');
    // var xpdl = $('#xpdlContainer xmp').text();
    // var xml = $('#xmlContainer xmp').text();
    // var xpdl_top = /!*下面这一块应该可以从WfDSystemConfig.xml中获取，发现Applet与xml中有差别*!/
    //   '<?xml version="1.0" encoding="UTF-8" standalone="no"?>' +
    //   '   <Package xmlns="http://www.wfmc.org/2002/XPDL1.0" xmlns:xpdl="http://www.wfmc.org/2002/XPDL1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" Id="' + package_id + '" Name="新包" xsi:schemaLocation="http://www.wfmc.org/2002/XPDL1.0 http://wfmc.org/standards/dtd/TC-1025_schema_10_xpdl.xsd">' +
    //   '       <PackageHeader>' +
    //   '           <XPDLVersion>1.0</XPDLVersion>' +
    //   '           <Vendor>GENTLESOFT</Vendor>' +
    //   '           <Created>' + create_time + '</Created>' +
    //   '       </PackageHeader>' +
    //   '       <RedefinableHeader PublicationStatus="UNDER_TEST">' +
    //   '           <Author>管理员</Author>' +
    //   '           <Version>1.0</Version>' +
    //   '       </RedefinableHeader>' +
    //   '       <ConformanceClass GraphConformance="NON_BLOCKED"/>' +
    //   '       <Script Type="text/javascript"/>' +
    //   '       <DataFields>' +
    //   '           <DataField Id="sourceReferenceId" IsArray="FALSE" Name="sourceReferenceId">' +
    //   '               <DataType>' +
    //   '                   <BasicType Type="STRING"/>' +
    //   '               </DataType>' +
    //   '               <InitialValue>null</InitialValue>' +
    //   '           </DataField>' +
    //   '           <DataField Id="formId" IsArray="FALSE" Name="formId">' +
    //   '               <DataType>' +
    //   '                   <BasicType Type="STRING"/>' +
    //   '               </DataType>' +
    //   '               <InitialValue>null</InitialValue>' +
    //   '           </DataField>' +
    //   '           <DataField Id="nextActivityInfo" IsArray="FALSE" Name="nextActivityInfo">' +
    //   '               <DataType>' +
    //   '                   <ExternalReference location="org.gentlesoft.wf.NextActivitiesParty"/>' +
    //   '               </DataType>' +
    //   '               <InitialValue>null</InitialValue>' +
    //   '           </DataField>' +
    //   '           <DataField Id="nextActivityName" IsArray="FALSE" Name="nextActivityName">' +
    //   '               <DataType>' +
    //   '                   <ExternalReference location="java.util.ArrayList"/>' +
    //   '               </DataType>' +
    //   '               <InitialValue>null</InitialValue>' +
    //   '           </DataField>' +
    //   '           <DataField Id="formType" IsArray="FALSE" Name="formType">' +
    //   '               <DataType>' +
    //   '                   <BasicType Type="STRING"/>' +
    //   '               </DataType>' +
    //   '               <InitialValue>null</InitialValue>' +
    //   '           </DataField>' +
    //   '       </DataFields>';
    // var xpdl_end =
    //   '       <ExtendedAttributes>'+
    //   '           <ExtendedAttribute Name="MadeBy" Value="com.gentlesoft.tools.wfd"/>' +
    //   '           <ExtendedAttribute Name="Version" Value="1.4.2"/>' +
    //   '       </ExtendedAttributes>' +
    //   '   </Package>';
    // var xml_top = '<?xml version="1.0" encoding="UTF-8"?><pkg-config xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" Id="' + package_id + '" Name="新包" Version="" xsi:noNamespaceSchemaLocation="../dtd/flowactconfig.xsd">';
    // var xml_end = '</pkg-config>';
    // xpdl = vkbeautify.xml(xpdl_top + xpdl + xpdl_end);
    // xml = vkbeautify.xml(xml_top + xml + xml_end);
    // $('input[name=xpdlcontent]').val(xpdl);
    // $('input[name=xmlcontent]').val(xpdl);
    // $('#containerForm').submit();
}*/

/**
 * 右击菜单编辑
 */
function handleMenuEdit() {
    var graph_active = graphPool.getGraphByActiveEdit();
    var selectedNode = graph_active.state.selectedNode;
    var data = {
        id: selectedNode.id,
        activitySetId: selectedNode.activitySet.activitySetId
    };
    var full_right = $('.full-right'),
        menu = full_right.children('.menu'),
        tab = full_right.find('.tab.active'),
        curr_tab = menu.find('[data-tab="tab_'+ data.id + '"]');
    if (curr_tab.length) {
        full_right.find('[data-tab=tab_' + data.id + ']').show();
        curr_tab.trigger('click');
    } else { // 创建标签页及graph对象
        menu.append(juicer($('#blockActiEdi_item_tpl').html(), data));
        full_right.append(juicer($('#blockActiEdi_tab_tpl').html(), data));
        menu.find('.item').tab();
        menu.find('[data-tab="tab_' + data.id + '"]').trigger('click');
        tab.find('.full-right-btn .item').tab();
        tab.find('.content-div').mCustomScrollbar();
        graph_active.createSubGraph();
    }
}

function handleNodeMenuProp() {
    var graph_active = graphPool.getGraphByActiveEdit();
    var selectedNode = graph_active.state.selectedNode;
    var title = selectedNode.title;
    $('.prop_node>.menu a[data-tab="'+title+'"]').removeClass('hideitem').addClass('active');
    $('.prop_node>.segment[data-tab="'+title+'"]').addClass('active');
    var conventional = {};
    $('.ui.modal.prop_node').modal({
        autofocus: false,
        closable: false,
        onApprove: function() {
            //更新-扩展属性
            //更新-常规
            $('.prop_node .conventional.'+title).find('input[name], textarea, select').each(function() {
                        conventional[$(this).attr('name')] = $(this).val();
            });
            selectedNode.conventional = conventional;
            graph_active.updateGraph();
            updateCore();
        },
        onShow: function() {
            var node = selectedNode;
            //展示-监控信息
            $('.monitorinf select[name="isResponsibleTem"]').dropdown('set selected', node.monitorinf.isResponsibleTem);
            //展示-常规
            conventional = node.conventional;
            $('.active .conventional').find('input[name], textarea').each(function() {
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
            // $(this).find('input, textarea').val('');
            // $(this).find('.ui.dropdown').dropdown('clear');
            // $(this).find('.ui.checkbox').checkbox('uncheck');
            // $('.post_condition .targetActivity').html('');
            $('.conventional select[name="definition_role"]').siblings('.text').removeAttr('definition_id');
        }
    }).modal('show');
}

function handleEdgeMenuProp() {
    var graph_active = graphPool.getGraphByActiveEdit();
    var selectedEdge = graph_active.state.selectedEdge;
    $('.prop_edge .targetActivity').html($('#transition_tpl').html());
    $('.prop_edge .targetActivity .menu .item').tab();
    $(".targetActivity .transferInf_extended_attr .postCondi_extendedAttr").mCustomScrollbar();
    $('.targetActivity .conditionList,.conditionList2').mCustomScrollbar();
    $('.ui.modal.prop_edge').modal({
        autofocus: false,
        closable: false,
        onApprove: function() {
            //更新-转移属性
            graph_active.updatePostCondi('.prop_edge');
        },
        onShow: function() {
            var edge = graph_active.state.selectedEdge;
            //展示-后置条件
            graph_active.showTransition('.prop_edge', edge);
        },
        onHidden: function() {
            $('.prop_edge .targetActivity').html('');
        }
    }).modal('show');
}

function handleRightMenu() {
    var graph_active = graphPool.getGraphByActiveEdit();
    var item = $(this).attr('name');
    var selectedNode = graph_active.state.selectedNode,
        selectedEdge = graph_active.state.selectedEdge,
        nodes = graph_active.nodes;
    switch (item) {
        case 'removeMenu':
            handleDeleteNode();
            break;
        case 'toFront':
            alert('前置');
            break;
        case 'editMenu':
            handleMenuEdit();
            break;
        case 'propMenu':
            if (selectedNode) {
                handleNodeMenuProp();
            } else if (selectedEdge) {
                handleEdgeMenuProp();
            }
            break;
    }
    $('#rMenu').hide();
}

/**
 * edge关联连接的node对象
 * @param  {Object} jsonObj 数据对象
 * @return {Object}         关联node以后的数据对象
 */
function edgeAssociateNode(jsonObj) {
    jsonObj.edges.map(function(edge) { // 根据edge.source.id重新关联node对象
        var source = jsonObj.nodes.find(function(node) {
            return node.id === edge.source.id;
        });
        var target = jsonObj.nodes.find(function(node) {
            return node.id === edge.target.id;
        });
        edge.source = source;
        edge.target = target;
        return edge;
    });
    return jsonObj;
}
window.onbeforeunload = function () {
    var pools = graphPool.pools;
    for (var i = 0; i < pools.length; i++) {
        var id = pools[i].containerId;
        switch (id) {
            case 'tab_main':
                pools[i].deleteGraph();
                break;
            default:
                $('.full-right [data-tab='+id+']').remove();
                pools.splice(i, 1);
                break;
        }
    }
};

