$(function () {
    $('#1').hover(function () {
        $("#main4").show()
            .hover(function () {
                $(this).show();
            }, function () {
                $(this).hide();
            });
    }, function () {
        $('#main4').hide();
        $('#main5').hide();
        $('#main6').hide();
        $('#main7').hide();
        $('#main8').hide();
        $('#main9').hide();
        $('#main10').hide();
    });
    $('#2').hover(function () {
        $("#main5").show()
            .hover(function () {
                $(this).show();
            }, function () {
                $(this).hide();
            });
    }, function () {
        $('#main4').hide();
        $('#main5').hide();
        $('#main6').hide();
        $('#main7').hide();
        $('#main8').hide();
        $('#main9').hide();
        $('#main10').hide();
    });
    $('#3').hover(function () {
        $("#main6").show()
            .hover(function () {
                $(this).show();
            }, function () {
                $(this).hide();
            });
    }, function () {
        $('#main4').hide();
        $('#main5').hide();
        $('#main6').hide();
        $('#main7').hide();
        $('#main8').hide();
        $('#main9').hide();
        $('#main10').hide();
    });
    $('#4').hover(function () {
        $("#main7").show()
            .hover(function () {
                $(this).show();
            }, function () {
                $(this).hide();
            });
    }, function () {
        $('#main4').hide();
        $('#main5').hide();
        $('#main6').hide();
        $('#main7').hide();
        $('#main8').hide();
        $('#main9').hide();
        $('#main10').hide();
    });
    $('#5').hover(function () {
        $("#main8").show()
            .hover(function () {
                $(this).show();
            }, function () {
                $(this).hide();
            });
    }, function () {
        $('#main4').hide();
        $('#main5').hide();
        $('#main6').hide();
        $('#main7').hide();
        $('#main8').hide();
        $('#main9').hide();
        $('#main10').hide();
    });
    $('#6').hover(function () {
        $("#main9").show()
            .hover(function () {
                $(this).show();
            }, function () {
                $(this).hide();
            });
    }, function () {
        $('#main4').hide();
        $('#main5').hide();
        $('#main6').hide();
        $('#main7').hide();
        $('#main8').hide();
        $('#main9').hide();
        $('#main10').hide();
    });
    $('#7').hover(function () {
        $("#main10").show()
            .hover(function () {
                $(this).show();
            }, function () {
                $(this).hide();
            });
    }, function () {
        $('#main4').hide();
        $('#main5').hide();
        $('#main6').hide();
        $('#main7').hide();
        $('#main8').hide();
        $('#main9').hide();
        $('#main10').hide();
    });
    $('.br input').click(function () {
        $(this).addClass('tu');
    });
    $('#main2').anythingSlider({
        buildArrows: false,
        buildStartStop: false,
        autoPlay: true
    });
    $('#dashboard').hover(
        function () {
            $(this).stop().animate(
                {
                    right: '0',
                    backgroundColor: 'rgb(255,255,255)'
                },
                500,
                'easeInSine'
            ); // end animate
        },
        function () {
            $(this).stop().animate(
                {
                    right: '-92px',
                    backgroundColor: 'rgb(110,138,195)'
                },
                1500,
                'easeOutBounce'
            ); // end animate
        }
    ); // end hover
    $('.ry table tr td').hover(
        function () {
            $(this).addClass('ddf');
        },
        function () {
            $(this).removeClass('ddf');
        }
    ); // end hover
    $('#menu2 li').hover(
        function () {
            $(this).addClass('ddf');
        },
        function () {
            $(this).removeClass('ddf');
        }
    ); // end hover
    $('#menu15 li').hover(function () {
        $(this).addClass('ddf');
    }, function () {
        $(this).removeClass('ddf');
    });
    $('#menu16 li').hover(function () {
        $(this).addClass('ddf');
    }, function () {
        $(this).removeClass('ddf');
    });
    $('#menu17 li').hover(function () {
        $(this).addClass('ddf');
    }, function () {
        $(this).removeClass('ddf');
    });
    $('#menu18 li').hover(function () {
        $(this).addClass('ddf');
    }, function () {
        $(this).removeClass('ddf');
    });
    $('#menu19 li').hover(function () {
        $(this).addClass('ddf');
    }, function () {
        $(this).removeClass('ddf');
    });
}); // end ready