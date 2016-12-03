###
 # Exécuter après db_setup.sql
###

USE ldmh;

##########################################################################
## ROLES
##########################################################################

INSERT INTO `roles` (label)
VALUES
    ("admin"),
    ("professionnel"),
    ("particulier"),
    ("visiteur");
    
##########################################################################
## RUBRIQUES
##########################################################################

INSERT INTO `rubriques` (label)
VALUES
    ("Nouveautés"),
    ("Jazz"),
    ("Rock"),
    ("Classique"),
    ("Chansons enfantines");

##########################################################################
## ETATS
##########################################################################

INSERT INTO `etats` (label)
VALUES
    ("Panier"),
    ("Commandé"),
    ("Livraison en cours"),
    ("Livré");
