###
 # Exécuter en premier
###

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
drop table if exists `etats`;

##########################################################################
## CREATE TABLES
##########################################################################

# === ROLES ===

CREATE TABLE `roles` (
    label_role varchar(255) NOT NULL UNIQUE,

    PRIMARY KEY (label_role)
);

# === USERS ===

CREATE TABLE `users` (
    username varchar(255) NOT NULL,
    label_role varchar(255) NOT NULL DEFAULT "visiteur",
    nom varchar(255),
    prenom varchar(255),
    adresse varchar(255),
    mdp_hash varchar(255),

    PRIMARY KEY (username),
    FOREIGN KEY (label_role) REFERENCES roles (label_role)
);

# === PRODUITS ===

CREATE TABLE `produits` (
    id_produit int NOT NULL AUTO_INCREMENT,
    nom_produit varchar(255) NOT NULL,
    date_sortie date,
    stock int DEFAULT 0,
    prix float NOT NULL,

    PRIMARY KEY (id_produit)
);

# === ÉTATS ===
# Panier, Commandé, Livraison en cours, Livré

CREATE TABLE `etats` (
	label_etat varchar(255) NOT NULL UNIQUE,
	
	PRIMARY KEY (label_etat)
);

# === COMMANDES ===

CREATE TABLE `commandes` (
    id_commande int NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    date_commande date,
    frais_port float,
    label_etat varchar(255) NOT NULL DEFAULT "Panier",

    PRIMARY KEY (id_commande),
    FOREIGN KEY (username) REFERENCES users (username),
    FOREIGN KEY (label_etat) REFERENCES etats (label_etat)
);

# === COMMANDITES ===

CREATE TABLE `commandites` (
    id_commande int NOT NULL,
    id_produit int NOT NULL,
    quantite int DEFAULT 1,
    prix_unitaire float NOT NULL,

    PRIMARY KEY (id_commande, id_produit),
    FOREIGN KEY (id_commande) REFERENCES commandes (id_commande),
    FOREIGN KEY (id_produit) REFERENCES produits (id_produit)
);

# === RUBRIQUES ===

CREATE TABLE `rubriques` (
    label_rubrique varchar(255) UNIQUE NOT NULL,
    place_menu int DEFAULT -1,

    PRIMARY KEY (label_rubrique)
);

# === CLASSEMENTS ===

CREATE TABLE `classements` (
    id_produit int NOT NULL,
    label_rubrique varchar(255) NOT NULL,

    PRIMARY KEY (id_produit, label_rubrique),
    FOREIGN KEY (id_produit) REFERENCES produits (id_produit),
    FOREIGN KEY (label_rubrique) REFERENCES rubriques (label_rubrique)
);

# === ARTISTES ===

CREATE TABLE `artistes` (
    id_artiste int NOT NULL AUTO_INCREMENT,
    nom_artiste varchar(255),

    PRIMARY KEY (id_artiste)
);

# === PARTICIPATIONS ===

CREATE TABLE `participations` (
    id_artiste int NOT NULL,
    id_produit int NOT NULL,

    PRIMARY KEY (id_artiste, id_produit),
    FOREIGN KEY (id_artiste) REFERENCES artistes (id_artiste),
    FOREIGN KEY (id_produit) REFERENCES produits (id_produit)
);
