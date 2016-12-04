package beans;

public class Produit 
{
	// ========================================================================
	// == ATTRIBUTS
	// ========================================================================

	private int 
		id_produit,
		stock;
	
	private String 
		nom_produit,
		date_sortie;
	
	private float prix;

	// ========================================================================
	// == GETTERS & SETTERS
	// ========================================================================
	
	public int getId_produit() {
		return id_produit;
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}

	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getNom_produit() {
		return nom_produit;
	}
	public void setNom_produit(String nom_produit) {
		this.nom_produit = nom_produit;
	}

	public String getDate_sortie() {
		return date_sortie;
	}
	public void setDate_sortie(String date_sortie) {
		this.date_sortie = date_sortie;
	}

	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
}
