package helpers;

import java.util.ArrayList;
import java.util.List;

import beans.Commande;
import beans.Commandite;
import beans.Produit;
import daos.DaoFactory;

public abstract class DataHelpers 
{
	// ========================================================================
	// == PRODUITS
	// ========================================================================

	/**
	 * Dans une liste de produits, remplir pour chacun la liste de ses artistes.
	 */
	public static void fillArtistesList(List<Produit> produits)
	{
		for (Produit pdt : produits) {
			pdt.setArtistes(DaoFactory.getProduitDao().listArtistes(pdt.getId()));
		}
	}
	
	/**
	 * Dans une commande, retourner une liste des produits, 
	 * avec chacun leurs artistes et la quantité commmandée.
	 */
	public static List<Produit> getProduitsInCommande(final int id_commande)
	{
		// === Commandites ===
			
		List<Commandite> commandites = DaoFactory.getCommandeDao().listCommandites(id_commande);
		if (commandites == null || commandites.size() < 1) {
			return null;
		}
		
		// === Produits du panier et quantités ===
		
		List<Produit> produits = new ArrayList<Produit>();
		for (Commandite commandite : commandites) 
		{
			Produit pdt = DaoFactory.getProduitDao().find(commandite.getId_produit());
			pdt.setQuantity(commandite.getQuantite());
			produits.add(pdt);
		}
		
		// === Artistes des produits ===
		
		fillArtistesList(produits);
		
		return produits;
	}
	
	
	// ========================================================================
	// == COMMANDES
	// ========================================================================
	
	/**
	 * Ajouter un produit à une commande :
	 * créer une commandite ou incrémenté sa quantité si elle existe.
	 */
	public static void addProduitToCommande(final int id_commande, final Produit produit)
	{
		Commandite commandite = DaoFactory.getCommandeDao().findCommandite(id_commande, produit.getId());
		if (commandite == null) {
			// Ajouter l'article au panier :
			DaoFactory.getCommandeDao().addCommandite(id_commande, produit.getId(), 1, produit.getPrix());
		}
		else {
			// Incrémenter quantité :
			DaoFactory.getCommandeDao().setCommanditeQuantite(id_commande, produit.getId(), commandite.getQuantite() + 1);
		}
	}
}
