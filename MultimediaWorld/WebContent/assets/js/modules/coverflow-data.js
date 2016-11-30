$(document).on('ready', function (){
    var $work = $('#work'),
        $imgs = $work.find('img'),
        labelLine;
    window.labels = {
        "well-ce": "2014 | Start up Well-CE <br/> Stage technicien Développeur Web",
        "MH": "2015 | Autoentrepreneur Web",
        "blu-e": "2016 | ENGIE Blu.e <br/> Développeur visualisation de données Web",
        "my-esiee": "2015 | ESIEE Paris <br/> App. Web et Android MyESIEE de suivi temps réel",
        "kaggle": "2016 | Compétitions Kaggle d'analyse de données <br/> Expedia, NY Times",
        "dsg": "2016 | Compétition international Data Science Game"
    };

    $imgs.on('mouseenter', function () {
        labelLine = $(this).siblings('label').html();
        var label = $(this).data('label');
        console.log(labels[label]);
        $(this).siblings('label').html(labels[label]);
    }).on('mouseleave', function() {
        $(this).siblings('label').html(labelLine);
    });
});
