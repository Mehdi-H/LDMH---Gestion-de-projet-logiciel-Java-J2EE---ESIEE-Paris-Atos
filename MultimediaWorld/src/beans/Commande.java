package beans;

public class Commande 
{
	// ========================================================================
	// == CHAMPS
	// ========================================================================
	
	private int id;
	
	private String username, date_commande;
	private float frais_port;
	private String etat;

	// ========================================================================
	// == GETTERS & SETTERS
	// ========================================================================
	
	public int getId() {
		return id;
	}
	public void setId(int id_commande) {
		this.id = id_commande;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username= username;
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
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
}
