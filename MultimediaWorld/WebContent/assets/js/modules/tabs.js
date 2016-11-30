$(document).on('ready', function () {
    var $tabLinks = $('.tabLink');
    var $tabContents = $('.tabContent');

    $tabLinks.on('click', function (e) {
        // PRevent jump scroll
        e.preventDefault();
        // Set active où il faut
        $tabLinks.removeClass('active');
        $(this).addClass('active');
        //
        var tab = $(this).data('tab');
        $tabContents.removeClass('active');
        $('.tabContent#'+tab).addClass('active');
    });
});
