###
 # Exécuter après db_setup.sql
###

USE ldmh;

##########################################################################
## ROLES
##########################################################################

INSERT INTO `roles` (label_role)
VALUES
    ("admin"),
    ("professionnel"),
    ("particulier"),
    ("visiteur");
    
##########################################################################
## RUBRIQUES
##########################################################################

INSERT INTO `rubriques` (label_rubrique, place_menu)
VALUES
    ("Nouveautés", 0),
    ("Jazz", 1),
    ("Rock", 2),
    ("Classique", 3),
    ("Chansons enfantines", 4),
    ("NE PAS AFFICHER DANS LE MENU", -1);

##########################################################################
## ETATS
##########################################################################

INSERT INTO `etats` (label_etat)
VALUES
    ("Panier"),
    ("Commandé"),
    ("Livraison en cours"),
    ("Livré");
