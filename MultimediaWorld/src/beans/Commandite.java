package beans;

public class Commandite 
{
	// ========================================================================
	// == CHAMPS
	// ========================================================================
	
	private int 
		id_commande, 
		id_produit,
		quantite;
	
	private float prix_unitaire;
	
	// ========================================================================
	// == GETTERS & SETTERS
	// ========================================================================
	
	public int getId_commande() {
		return id_commande;
	}
	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}

	public int getId_produit() {
		return id_produit;
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public float getPrix_unitaire() {
		return prix_unitaire;
	}
	public void setPrix_unitaire(float prix_unitaire) {
		this.prix_unitaire = prix_unitaire;
	}
}
