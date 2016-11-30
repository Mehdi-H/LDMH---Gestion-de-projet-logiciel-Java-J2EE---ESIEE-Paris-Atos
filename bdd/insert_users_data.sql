
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
## USERS
##########################################################################

INSERT INTO `users` (label_role, nom, prenom, adresse, mdp_hash)
VALUES
    ("admin", "Admin", "Armin", "Multimedia World, Inc.", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3"),
    ("professionnel", "De la Fnac", "Alexandre", "Fnac", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3"),
    ("particulier", "Dupont", "Léo", "Cité Descartes, 2 Boulevard Blaise Pascal, 93160 Noisy-le-Grand, FRANCE", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3"),
    ("visiteur", "Houacine", "Mehdi", "Cité Descartes, 2 Boulevard Blaise Pascal, 93160 Noisy-le-Grand, FRANCE", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3");
