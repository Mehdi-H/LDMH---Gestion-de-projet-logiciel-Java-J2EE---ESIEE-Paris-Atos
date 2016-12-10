package daos;

import java.util.List;

import beans.Commande;
import beans.Commandite;

public interface CommandeDao 
{
	int create(final String username, final double frais_port);
	
	// === Finders ===
	
	Commande find(final int id);
	List<Commande> listCommandesUser(final String username);
	Commande findUserPanier(final String username);
	
	// === Setters ===
	
	void setEtat(final int id, final String label_etat);
	void setDate(final int id, final String date);
	void setFraisPort(final int id, final float frais);
	float computeTotal(final int id);
	
	// === Commandites ===
	
	void addCommandite(final int id_commande, final int id_produit, final int quantite, final float prix_unitaire);
	void removeCommandite(final int id_commande, final int id_produit);
	Commandite findCommandite(final int id_commande, final int id_produit);
	void setCommanditeQuantite(final int id_commande, final int id_produit, final int quantite);
	List<Commandite> listCommandites(final int id_commande);
}
