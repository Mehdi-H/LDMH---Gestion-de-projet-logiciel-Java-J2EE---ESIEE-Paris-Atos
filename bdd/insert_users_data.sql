###
 # Exécuter après setup_tables_labels.sql
###

USE ldmh;

##########################################################################
## USERS
##########################################################################

INSERT INTO `users` (username, label_role, nom, prenom, adresse, mdp_hash)
VALUES
    ( "admin", "admin", "Admin", "Armin", "Multimedia World, Inc.", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3"),
    ( "alexaf", "professionnel", "De la Fnac", "Alexandre", "Fnac", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3"),
    ("seeven", "particulier", "Dupont", "Léo", "Cité Descartes, 2 Boulevard Blaise Pascal, 93160 Noisy-le-Grand, FRANCE", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3"),
    ("mehou", "visiteur", "Houacine", "Mehdi", "Cité Descartes, 2 Boulevard Blaise Pascal, 93160 Noisy-le-Grand, FRANCE", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3");
