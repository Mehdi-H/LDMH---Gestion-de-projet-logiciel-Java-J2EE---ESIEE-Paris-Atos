package beans;

public class Commande 
{
	// ========================================================================
	// == CHAMPS
	// ========================================================================
	
	private int 
		id_commande,
		id_user;
	
	private String date_commande;
	private float frais_port;
	private String label_etat;

	// ========================================================================
	// == GETTERS & SETTERS
	// ========================================================================
	
	public int getId_commande() {
		return id_commande;
	}
	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}
	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	public String getDate_commande() {
		return date_commande;
	}
	public void setDate_commande(String date_commande) {
		this.date_commande = date_commande;
	}
	
	public float getFrais_port() {
		return frais_port;
	}
	public void setFrais_port(float frais_port) {
		this.frais_port = frais_port;
	}

	public String getEtat() {
		return label_etat;
	}
	public void setEtat(String etat) {
		this.label_etat = etat;
	}
}
