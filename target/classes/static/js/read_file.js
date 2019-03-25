$(document).ready(function () {
    $('#find').on('click', '.item', function() {
        var id = $(this).prop("data-id");
        $.ajax({
            url:'./data/ct_data', async:false,
            type:'post',
            data: {id:id},
            // dataType:'json',
            beforeSend:function(jqXHR, settings){
            },
            success:function(data, textStatus){
                data.each(function (item) {
                    $('#show').append('<img src="./ct_data/picture/"'+id+"/"+item+'>');
                });
            },
            error:function(jqXHR,textStatus,errorThrown){
                //充当catch(e)的角色
                alert("exception:"+jqXHR+"-"+textStatus+"-"+errorThrown);
            }
        });
    });
});
