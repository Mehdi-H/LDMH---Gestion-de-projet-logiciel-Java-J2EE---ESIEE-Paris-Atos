
USE ldmh;

##########################################################################
## PRODUITS
##########################################################################

INSERT INTO `produits` (nom_produit, date_sortie, stock, prix)
VALUES
    ("Starboy", "2016/01/01", 50, 1.99), # R&B, Soul
    ("Black Beatles", "2016/01/01", 50, 1.99), # Pop, Rap
    ("Closer", "2016/01/01", 50, 1.99), # Dance
    ("I Feel It Coming", "2016/01/01", 50, 1.99), # R&B, Soul
    ("Let Me Love You", "2016/01/01", 50, 1.99), # Dance, Dancehall
    ("Don't Wanna Know", "2016/01/01", 50, 1.99), # Pop
    ("Party Monster", "2016/01/01", 50, 1.99), # R&B, Soul
    ("24K Magic", "2016/01/01", 50, 1.99), # Funk, Pop, R&B
    ("Rockabye (feat. Sean Paul & Anne-Marie)", "2016/01/01", 50, 1.99), # Dance
    ("Say You Won't Let Go", "2016/01/01", 50, 1.99), # Pop
    ("Cold Water (feat. Justin Bieber & MØ)", "2016/01/01", 50, 1.99), # Dance, Dancehall
    ("Side To Side", "2016/01/01", 50, 1.99), # Pop
    ("The Greatest", "2016/01/01", 50, 1.99), # Pop
    ("Fake Love", "2016/01/01", 50, 1.99), # Rap
    ("Rockin'", "2016/01/01", 50, 1.99); # R&B, Soul

##########################################################################
## ARTISTES
##########################################################################

INSERT INTO `artistes` (nom_artiste)
VALUES
    ("The Weeknd"), # 1
    ("Daft Punk"), # 2
    ("Rae Sremmurd"), # 3
    ("Gucci Mane"), # 4
    ("The Chainsmokers"), # 5
    ("Halsey"), # 6
    ("DJ Snake"), # 7
    ("Justin Bieber"), # 8
    ("Maroon 5"), # 9
    ("Kendrick Lamar"), # 10
    ("Bruno Mars"), # 11
    ("Clean Bandit"), # 12
    ("Sean Paul"), # 13
    ("Anne-Marie"), # 14
    ("James Arthur"), # 15
    ("Major Lazer"), # 16
    ("MØ"), # 17
    ("Ariana Grande"), # 18
    ("Nicki Minaj"), # 19
    ("Sia"), # 20
    ("Drake"); # 21

##########################################################################
## PARTICIPATIONS
##########################################################################

INSERT INTO `participations` (id_produit, id_artiste)
VALUES
    (1,1), # Starboy
    (1,2),
    (2,3), # Bleack Beatles
    (2,4),
    (3,5), # Closer
    (3,6),
    (4,1), # I feel it coming
    (4,2),
    (5,7), # Lemme luv u
    (5,8),
    (6,9), # dont wana no
    (6,10),
    (7,1), # Party monsta
    (8,11), # 24 karat magik
    (9,12), # Rockbabye
    (9,13),
    (9,14),
    (10,15), # Say u wont let go
    (11,8), # Apply Cold Water
    (11,16),
    (11,17),
    (12,18), # Side 2 side
    (12,19),
    (13,20), # Da Greatest Eva
    (14,21), # Nofake luv
    (15,1); # Rocky

##########################################################################
## RUBRIQUES
##########################################################################

INSERT INTO `rubriques` (nom_rubrique)
VALUES
    ("Nouveautés"), # 1
    ("R&B"), # 2
    ("Soul"), # 3
    ("Jazz"), # 4
    ("Classique"), # 5
    ("Pop"), # 6
    ("Rap"), # 7
    ("Dance"), # 8
    ("Dancehall"), # 9
    ("Funk"), # 10
    ("Chansons enfantines"); # 11

##########################################################################
## CLASSEMENTS
##########################################################################

INSERT INTO `classements` (id_produit, id_rubrique)
VALUES
    (1,2), # Starboy
    (1,3),
    (1,1),
    (2,6), # Bleack Beatles
    (2,7),
    (3,8), # Closer
    (4,2), # I feel it coming
    (4,3),
    (5,8), # Lemme luv u
    (5,9),
    (6,6), # dont wana no
    (7,2), # Party monsta
    (7,3),
    (8,10), # 24 karat magik
    (8,6),
    (8,2),
    (9,8), # Rockbabye
    (10,6), # Say u wont let go
    (11,8), # Apply Cold Water
    (11,9),
    (12,6), # Side 2 side
    (13,6), # Da Greatest Eva
    (14,7), # Nofake luv
    (15,2), # Rocky
    (15,3);
