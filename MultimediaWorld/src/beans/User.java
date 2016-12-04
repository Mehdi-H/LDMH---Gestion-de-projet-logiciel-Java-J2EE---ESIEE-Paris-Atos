package beans;

public class User 
{
	// ========================================================================
	// == CHAMPS
	// ========================================================================
	
	private int id;
	private String 
		nom,
		prenom,
		adresse,
		mdp_hash;
	
	// ========================================================================
	// == GETTERS & SETTERS
	// ========================================================================
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String getMdp_hash() {
		return mdp_hash;
	}
	public void setMdp_hash(String mdp_hash) {
		this.mdp_hash = mdp_hash;
	}	
}
