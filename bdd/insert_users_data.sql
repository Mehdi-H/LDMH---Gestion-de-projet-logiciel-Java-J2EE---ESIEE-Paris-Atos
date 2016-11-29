
USE ldmh;

##########################################################################
## ROLES
##########################################################################

INSERT INTO `roles` (role)
VALUES
    ("admin"),
    ("professionnel"),
    ("particulier"),
    ("visiteur");

##########################################################################
## USERS
##########################################################################

INSERT INTO `users` (id_role, nom, prenom, adresse, mdp_hash)
VALUES
    (1, "Admin", "Armin", "Multimedia World, Inc.", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3"),
    (2, "De la Fnac", "Alexandre", "Fnac", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3"),
    (3, "Dupont", "Léo", "Cité Descartes, 2 Boulevard Blaise Pascal, 93160 Noisy-le-Grand, FRANCE", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3"),
    (3, "Houacine", "Mehdi", "Cité Descartes, 2 Boulevard Blaise Pascal, 93160 Noisy-le-Grand, FRANCE", "31E06F7D89FEB99A0E6C0AFFE198748C3BB5BEF5E3CC92D95CB9E996197D3FC3");
