jQuery(function ($) {
    $("#DTM").click(function () {
        var value = $("#TrainingRun").dropdown('get value');
        var url = "/model/downloadModel.do?fileName="+value;
        downloadFile(url);
    });

});//end
String.prototype.NoSpace = function() {
    return this.replace(/\s+/g, "");
};