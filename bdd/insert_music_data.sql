
USE ldmh;

##########################################################################
## VRAIS PRODUITS
##########################################################################

INSERT INTO `produits` (nom_produit, date_sortie, stock, prix)
VALUES
	("Starboy", "2016/01/01", 50, 1.99),
    ("Black Beatles", "2016/01/01", 50, 1.99),
    ("Closer", "2016/01/01", 50, 1.99),
    ("I Feel It Coming", "2016/01/01", 50, 1.99),
    ("Let Me Love You", "2016/01/01", 50, 1.99),
    ("Don't Wanna Know", "2016/01/01", 50, 1.99),
    ("Party Monster", "2016/01/01", 50, 1.99),
    ("24K Magic", "2016/01/01", 50, 1.99),
    ("Rockabye (feat. Sean Paul & Anne-Marie)", "2016/01/01", 50, 1.99),
    ("Say You Won't Let Go", "2016/01/01", 50, 1.99),
    ("Cold Water (feat. Justin Bieber & MØ)", "2016/01/01", 50, 1.99),
    ("Side To Side", "2016/01/01", 50, 1.99),
    ("The Greatest", "2016/01/01", 50, 1.99),
    ("Fake Love", "2016/01/01", 50, 1.99),
    ("Rockin'", "2016/01/01", 50, 1.99);

##########################################################################
## VRAIS ARTISTES
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
## VRAIS PARTICIPATIONS
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
