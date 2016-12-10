package daos;

import java.util.List;

import beans.Artiste;
import beans.Produit;
import beans.Rubrique;

public interface ProduitDao 
{
	int create(final String nom_produit, final String date_sortie, final int stock, final float prix);
	void delete(final int id_produit);
	
	// === FINDERS ===
	
	Produit find(final int id_produit);
	List<Produit> listByRubrique(final String label_rubrique);
	List<Produit> list();
	List<Produit> listNotNew();
	
	List<Produit> search(final String pattern);
	
	// === SETTERS ===
	
	void setStock(final int id_produit, final int stock);
	void setPrix(final int id_produit, final float prix);
	
	// === RUBRIQUES ===
	
	void addRubrique(final int id_produit, final String label_rubrique);
	void removeRubrique(final int id_produit, final String label_rubrique);
	List<Rubrique> listRubriques(final int id_produit);
	
	// === ARTISTES ===
	
	void addArtiste(final int id_produit, final int id_artiste);
	void removeArtiste(final int id_produit, final int id_artiste);
	List<Artiste> listArtistes(final int id_produit);
}
