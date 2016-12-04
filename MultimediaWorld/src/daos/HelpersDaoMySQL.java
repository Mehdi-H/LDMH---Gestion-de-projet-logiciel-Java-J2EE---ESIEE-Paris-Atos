package daos;

import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Artiste;
import beans.Commande;
import beans.Commandite;
import beans.Produit;
import beans.Rubrique;

public class HelpersDaoMySQL 
{
	// ========================================================================
	// == COMMANDES & COMMANDITES
	// ========================================================================
	
	public static Commande resultToCommande(ResultSet result) throws SQLException
	{
		Commande comm = new Commande();
		
		comm.setId_commande(result.getInt("id_commande"));
		comm.setId_user(result.getInt("id_user"));
		comm.setDate_commande(result.getString("date_commande"));
		comm.setEtat(result.getString("date_commande"));
		comm.setFrais_port(result.getFloat("frais_port"));
		
		return comm;
	}
	
	public static Commandite resultToCommandite(ResultSet result) throws SQLException
	{
		Commandite comm = new Commandite();
		
		comm.setId_commande(result.getInt("id_commande"));
		comm.setId_produit(result.getInt("id_produit"));
		comm.setQuantite(result.getInt("quantite"));
		comm.setPrix_unitaire(result.getFloat("prix_unitaire"));
		
		return comm;
	}
	
	// ========================================================================
	// == RUBRIQUES
	// ========================================================================
	
	public static Rubrique resultToRubrique(ResultSet result) throws SQLException
	{
		Rubrique rub = new Rubrique();
		rub.setLabel(result.getString("label_rubrique"));
		rub.setPlace_menu(result.getInt("place_menu"));
		return rub;
	}
	
	// ========================================================================
	// == PRODUITS 
	// ========================================================================
	
	public static Produit resultToProduit(ResultSet result) throws SQLException 
	{
		Produit pdt = new Produit();
		
		pdt.setId_produit(result.getInt("id_produit"));
		pdt.setNom_produit(result.getString("nom_produit"));
		pdt.setDate_sortie(result.getString("date_sortie"));
		pdt.setStock(result.getInt("stock"));
		pdt.setPrix(result.getFloat("prix"));
		
		return pdt;
	}

	public static Artiste resultToArtiste(ResultSet result) throws SQLException
	{
		Artiste art = new Artiste();
		
		art.setId(result.getInt("id_artiste"));
		art.setNom(result.getString("nom_artiste"));
		
		return art;
	}
}