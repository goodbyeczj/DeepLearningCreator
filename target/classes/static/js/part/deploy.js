jQuery(function ($) {
    $("#DTM").click(function () {
        var value = $("#TrainingRun").dropdown('get value');
        var url = "/model/downloadModel?fileName="+value;
        downloadFile(url);
    });
    $("#dataSets .menu").on('click','#uploaded',function () {
        // alert("123");
        $("#DataSetInference").removeClass('active');
        $("#upload").addClass('active');
    });
    $("#upload .menu").on('click','#validation',function () {
        // alert("123");
        $("#DataSetInference").addClass('active');
        $("#upload").removeClass('active');
    });
});//end
String.prototype.NoSpace = function() {
    return this.replace(/\s+/g, "");
};
