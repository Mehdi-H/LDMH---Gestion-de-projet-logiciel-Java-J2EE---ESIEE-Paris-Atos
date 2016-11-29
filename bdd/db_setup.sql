
USE ldmh;

##########################################################################
## DROP TABLES
##########################################################################

drop table if exists `participations`;
drop table if exists `commandites`;
drop table if exists `commandes`;
drop table if exists `classements`;
drop table if exists `produits`;
drop table if exists `users`;
drop table if exists `roles`;
drop table if exists `rubriques`;
drop table if exists `artistes`;

##########################################################################
## CREATE TABLES
##########################################################################

# === ROLES ===

CREATE TABLE `roles` (
    id_role INT NOT NULL AUTO_INCREMENT,
    role VARCHAR(255),

    PRIMARY KEY (id_role)
);

# === USERS ===

CREATE TABLE `users` (
    id_user INT NOT NULL AUTO_INCREMENT,
    id_role INT NOT NULL,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    adresse VARCHAR(255),
    mdp_hash VARCHAR(255),

    PRIMARY KEY (id_user),
    FOREIGN KEY (id_role) REFERENCES roles (id_role)
);

# === PRODUITS ===

CREATE TABLE `produits` (
    id_produit INT NOT NULL AUTO_INCREMENT,
    nom_produit VARCHAR(255) NOT NULL,
    date_sortie DATE,
    stock INT DEFAULT 0,
    prix FLOAT NOT NULL,

    PRIMARY KEY (id_produit)
);

# === COMMANDES ===

CREATE TABLE `commandes` (
    id_commande INT NOT NULL AUTO_INCREMENT,
    id_user INT NOT NULL,
    date_commande DATE,
    frais_port FLOAT,
    panier BOOLEAN DEFAULT true,

    PRIMARY KEY (id_commande),
    FOREIGN KEY (id_user) REFERENCES users (id_user)
);

# === COMMANDITES ===

CREATE TABLE `commandites` (
    id_commande INT NOT NULL,
    id_produit INT NOT NULL,
    quantite INT DEFAULT 1,
    prix_unitaire FLOAT NOT NULL,

    PRIMARY KEY (id_commande, id_produit),
    FOREIGN KEY (id_commande) REFERENCES commandes (id_commande),
    FOREIGN KEY (id_produit) REFERENCES produits (id_produit)
);

# === RUBRIQUES ===

CREATE TABLE `rubriques` (
    id_rubrique INT NOT NULL AUTO_INCREMENT,
    nom_rubrique VARCHAR(255) UNIQUE,

    PRIMARY KEY (id_rubrique)
);

# === CLASSEMENTS ===

CREATE TABLE `classements` (
    id_produit INT NOT NULL,
    id_rubrique INT NOT NULL,

    PRIMARY KEY (id_produit, id_rubrique),
    FOREIGN KEY (id_produit) REFERENCES produits (id_produit),
    FOREIGN KEY (id_rubrique) REFERENCES rubriques (id_rubrique)
);

# === ARTISTES ===

CREATE TABLE `artistes` (
    id_artiste INT NOT NULL AUTO_INCREMENT,
    nom_artiste VARCHAR(255),

    PRIMARY KEY (id_artiste)
);

# === PARTICIPATIONS ===

CREATE TABLE `participations` (
    id_artiste INT NOT NULL,
    id_produit INT NOT NULL,

    PRIMARY KEY (id_artiste, id_produit),
    FOREIGN KEY (id_artiste) REFERENCES artistes (id_artiste),
    FOREIGN KEY (id_produit) REFERENCES produits (id_produit)
);
