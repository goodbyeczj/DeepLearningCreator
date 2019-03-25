jQuery(function ($) {
    $('.play.button').on('click',function () {
        var $change = $(this).children('.icon');
        // batch = parseInt($('input[name="batch_size"]').val());
        // epoch = $('input[name="epochs"]').val();
        // var batchTemp = batch*epoch;
        if ($change.hasClass('play')){
            if (!$('#option').hasClass('active'))
                $('#option').addClass('active').siblings().removeClass('active');
                $change.removeClass('play').addClass('power off');
            $('#Training,.train').addClass('active').siblings().removeClass('active');
            // layer.msg("程序执行中，请稍等",{time:2000})
            t1 =setInterval(function () {
                train("loss","loss");
                train("acc","acc");
                // for (var i = 0; i <dataList.length ; i++) {
                //     xData.push(batch);
                //     batch+=batchTemp;
                // }
            }, 1000);//8秒自动刷新
                $.ajax({
                    url:'/model/train', async:true,
                    type:'post',
                    // dataType:'json',
                    beforeSend:function(jqXHR, settings){
                        layer.msg("开始训练,请等待程序运行",{icon:1,time:4000});
                    },
                    success:function(data, textStatus){
                        if(data=="success"){
                            layer.alert("训练完毕！",{icon:1});
                            $change.removeClass('power off').addClass('play');
                            window.clearTimeout(t1);
                        }
                        else if (data=="interrupt"){
                            layer.alert("训练中断",{icon:0});
                            $change.removeClass('power off').addClass('play');
                            window.clearTimeout(t1);
                        }
                        else {
                            layer.alert("代码运行出错！请检查代码",{icon:0});
                            $change.removeClass('power off').addClass('play');
                            window.clearTimeout(t1);
                        }
                        train("loss","loss");
                        train("acc","acc");
                    },
                    error:function(jqXHR,textStatus,errorThrown){
                        layer.msg("服务器繁忙");
                    }
                });
            }
            else if ($change.hasClass('power')){
                $change.removeClass('power off').addClass('play');
                $.ajax({
                    url:'/model/stop', async:true,
                    type:'post',
                    // dataType:'json',
                    beforeSend:function(jqXHR, settings){
                    },
                    success:function(data, textStatus){
                        if(textStatus=="success"){
                            window.clearTimeout(t1);
                            train("loss","loss");
                            train("acc","acc");
                            dataList = [];
                            layer.msg("停止训练!",{icon: 1,time:'1000'});
                        }
                    },
                    error:function(jqXHR,textStatus,errorThrown){
                        layer.msg("服务器繁忙");
                    }
                });
            }
        }
    );
    $(window).on('beforeunload unload', function() {
        $.ajax({
            url:'/model/stop', async:true,
            type:'post',
            // dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                if(textStatus=="success"){
                    window.clearTimeout(t1);
                    train("loss","loss");
                    train("acc","acc");
                    layer.msg("停止训练!",{icon: 1,time:'1000'});
                }
            },
            error:function(jqXHR,textStatus,errorThrown){
                layer.msg("服务器繁忙");
            }
        });
        // window.clearTimeout(t1);
        // layer.m("停止训练!",{icon: 1,time:'1000'});
    });
});//end
var dataList =[], t1,ID;
var xData = [];
var batch;
function train(file_name,name){
    $.ajax({
        url:'/train/'+file_name+'',
        type:'get',
        // data:{id:id},
        // dataType:'json',
        beforeSend:function(){
        },
        success:function (data) {
            if (data!=""){
                dataList = data.split(",");
                console.log(name+"/"+dataList.length);
            }
            draw(dataList,name);
        },
        error:function () {
            layer.msg("加载失败");
        }
    });
}
function draw(data,name){
    var myChart = echarts.init(document.getElementById(name));
    var title = name;
    var option;
    var fit = graph_main.nodes.filter(function (v) {
        return v.conventional.current=='fit';
    });
    var epochs = fit[0].conventional.epochs;
    var xData = new Array();
    for (var i = 1; i <=epochs; i++) {
        xData.push(i);
    }
        option = {
            title: {
                text: title
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                type: 'category',
                // boundaryGap: false
                // min: 1,
                // max: dataList.length,
                data:xData
            },
            yAxis: {
                boundaryGap: [0, '50%'],
                type: 'value'
                // min:0,
                // max:1
            },
            dataZoom: [
                // {
                //     type: 'slider',
                //     show: true,
                //     xAxisIndex: [0],
                //     start: 1,
                //     end: 35
                // },
                {
                    type: 'slider',
                    show: true,
                    yAxisIndex: [0],
                    left: '93%',
                    start: 0,
                    end: 100
                },
                // {
                //     type: 'inside',
                //     xAxisIndex: [0],
                //     start: 1,
                //     end: 35
                // },
                {
                    type: 'inside',
                    yAxisIndex: [0]
                }
            ],
            series: [
                {
                    name:title,
                    type:'line',
                    // smooth:true,
                    // symbol: 'none',
                    // stack: 'a',
                    // areaStyle: {
                    //     normal: {}
                    // },
                    data: data
                    // itemStyle : { normal: {label : {show: true}}}
                }
            ]
        };
    myChart.setOption(option);
}