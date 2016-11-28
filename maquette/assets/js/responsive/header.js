// Responsive
$(document).on('ready', function () {
    var $header = $('header');
    $header.responsiveHeader();
});

/**
 * Modifie la classe de l'icône hamburger pour (dé)plier la navigation.
 */
(function( $ ){
    $.fn.responsiveHeader = function() {
        $(this).find('.mobile-nav-hamburger').on('click touch', function () {
            $(this).toggleClass('is-open is-close');
        });
        return this;
    };
})( jQuery );
