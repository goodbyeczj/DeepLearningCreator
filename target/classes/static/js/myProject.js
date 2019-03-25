jQuery(function ($) {
    $('.external.alternate.icon').click(function () {
        window.location.href="/chart.jsp";
    });
});
String.prototype.NoSpace = function() {
    return this.replace(/\s+/g, "");
};