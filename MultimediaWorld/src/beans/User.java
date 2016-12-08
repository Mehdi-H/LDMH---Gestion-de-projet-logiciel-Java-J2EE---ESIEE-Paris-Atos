package beans;

public class User 
{
	// ========================================================================
	// == CHAMPS
	// ========================================================================
	
	private String
		username,
		role,
		nom,
		prenom,
		adresse,
		mdp_hash;
	
	// ========================================================================
	// == GETTERS & SETTERS
	// ========================================================================
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
