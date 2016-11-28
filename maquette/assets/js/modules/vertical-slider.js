// Responsive
$(document).on('ready', function () {
    var $slides = $('#slides');
    var $initPos = $slides.offset();
    var $goalPos = $('img.focus').offset();
    var direction = $(window).width() < 1200 ? 'left' : 'top';
    $slides.slideOn(slides = $slides, initPos = $initPos, goalPos  = $goalPos, direction = direction);
    var formation;
    $.getJSON('assets/js/modules/formation.json', function (data) {
        formation = data.formation;
    });
    $slides.find('img').on('click', function () {
        var imgFormation = $('.focus').attr('src').split('/').pop().split('.').shift();

        $('.formation')
            .find('.lead').html(formation[imgFormation].lead);
        $('.formation')
            .find('.descriptor').html(formation[imgFormation].descriptor);
    });
});

/**
 * Permet de faire défiler le slider 'slides' en cliquant sur une image.
 */
(function( $ ){
    $.fn.slideOn = function(slides, initPos, goalPos, direction) {
        $(window).resize(function(){
            console.log(direction);
            direction = $(window).width() < 1200 ? 'left' : 'top';
        });
        $(this).find('img').on('click touch', function () {

            console.log(direction);
            var aBitMore = direction == 'top' ? -150 : 250;
            var aLotMore =  direction == 'top' ? 0 : 190;

            /**
             * Si on click sur une image chevron,
             * on retire la classe focus,
             * on remet le slider en position initiale,
             * on met la classe focus à l'image qui l'avait initialement
             */
            if( $(this).hasClass('chevron') ){
                slides.find('img').removeClass('focus');
                slides.css(direction, initPos[direction] + aBitMore);
                slides.find('img.second').addClass('focus');
            }
            /**
             * Si l'image a la classe focus, on ne fait rien,
             * Si l'image ne l'a pas,
             * on enlève le focus (whoever has it),
             * on récupère la position de limage clickée,
             * on calcule la distance entre cette image et la position finale,
             * on déplace le slider de cette distance,
             * on met le focus sur cette image
             */
            else if(!$(this).hasClass('focus')){
                slides.find('img').removeClass('focus');
                // variables
                var $clickedPos = $(this).offset();
                var shift = goalPos[direction] - $clickedPos[direction] + aLotMore;
                var newPos = slides.offset()[direction] + shift;
                console.log(newPos);

                slides.css(direction, newPos);
                $(this).addClass('focus');
            }
        });
        return this;
    };
})( jQuery );
