package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import beans.Commande;
import beans.Commandite;
import beans.Etat;

public class CommandeDaoMySQL implements CommandeDao 
{
	// ========================================================================
	// == ATTRIBUTS
	// ========================================================================
	
	private DaoFactory factory;
	
	// ========================================================================
	// == CONSTRUCTEUR
	// ========================================================================
	
	CommandeDaoMySQL(final DaoFactory factory)
	{
		this.factory = factory;
	}
	
	// ========================================================================
	// == METHODES BDD
	// ========================================================================
	
	@Override
	public int create(int id_user) 
	{
		// === Variables ===

		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		// === Requ�te ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("INSERT INTO commandes(id_user, label_etat) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
			req.setInt(1, id_user);
			req.setString(2, Etat.Label.CART.toString());
			
			req.executeUpdate();
			result = req.getGeneratedKeys();
			
			// --- R�cup�rer l'ID de la commande cr��e ---
			
			result.next();
			return result.getInt("id_commande");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	// ------------------------------------------------------------------------
	// -- FINDERS
	// ------------------------------------------------------------------------
	
	@Override
	public Commande find(int id) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		// === Requ�te ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("SELECT * FROM commandes WHERE id_commande = ?");
			req.setInt(1, id);
			
			result = req.executeQuery();
			
			// --- Retourner la commande trouv�e ---
			
			if (result.next()) 
			{
				return HelpersDaoMySQL.resultToCommande(result);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<Commande> listCommandesUser(int id_user) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		List<Commande> commandes = new ArrayList<Commande>();
		
		// === Requ�te ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("SELECT * FROM commandes WHERE id_user = ?");
			req.setInt(1, id_user);
			
			result = req.executeQuery();
			
			// --- Stocker la liste des commandes du User ---
			
			while (result.next()) 
			{
				commandes.add(HelpersDaoMySQL.resultToCommande(result));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commandes;
	}

	// ------------------------------------------------------------------------
	// -- SETTERS
	// ------------------------------------------------------------------------
	
	@Override
	public void setEtat(int id, String label_etat) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requ�te ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("UPDATE commandes SET label_etat = ? WHERE id_commande = ?");
			req.setString(1, label_etat);
			req.setInt(2, id);
			
			req.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setDate(int id, String date) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requ�te ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("UPDATE commandes SET date_commande = ? WHERE id_commande = ?");
			req.setString(1, date);
			req.setInt(2, id);
			
			req.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setFraisPort(int id, float frais)
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requ�te ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("UPDATE commandes SET frais_port = ? WHERE id_commande = ?");
			req.setFloat(1, frais);
			req.setInt(2, id);
			
			req.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// ------------------------------------------------------------------------
	// -- CALCULS
	// ------------------------------------------------------------------------
	
	@Override
	public float computeTotal(int id) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		float total = 0;
		
		// === Requ�te ===
		
		try {
			conn = factory.getConnection();
			
			// --- Calculer le total de la commande ---
			
			req = conn.prepareStatement("SELECT * FROM commandites WHERE id_commande = ?");
			req.setInt(1, id);
			
			result = req.executeQuery();
			
			while (result.next()) {
				total += (result.getFloat("prix_unitaire") * result.getInt("quantit�"));
			}
			
			// --- R�cup�rer les frais de port de la commande ---
			
			total += this.find(id).getFrais_port();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return total;
	}
	
	// ------------------------------------------------------------------------
	// -- COMMANDITES
	// ------------------------------------------------------------------------
	
	@Override
	public void addCommandite(int id_commande, int id_produit, int quantite, float prix_unitaire) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requ�te ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("INSERT INTO commandites(id_commande, id_produit, quantite, prix_unitaire) VALUES (?,?,?,?)");
			req.setInt(1, id_commande);
			req.setInt(2, id_produit);
			req.setInt(3, quantite);
			req.setFloat(4, prix_unitaire);
			
			req.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeCommandite(int id_commande, int id_produit) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requ�te ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("DELETE FROM commandites WHERE id_commande = ? AND id_produit = ?");
			req.setInt(1, id_commande);
			req.setInt(2, id_produit);
			
			req.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setCommanditeQuantite(int id_commande, int id_produit, int quantite) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requ�te ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("UPDATE commandites SET quantite = ? WHERE id_commande = ? AND id_produit = ?");
			req.setInt(1, quantite);
			req.setInt(2, id_commande);
			req.setInt(3, id_produit);
			
			req.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Commandite> listCommandites(int id_commande) 
	{
		// === Variables ===

		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		List<Commandite> commandites = new ArrayList<Commandite>();
		
		// === Requ�te ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("SELECT * FROM commandites WHERE id_commande = ?");
			req.setInt(1, id_commande);
			
			result = req.executeQuery();
			
			// --- Stocker la liste des commandites de la Commande ---
			
			while (result.next()) 
			{
				commandites.add(HelpersDaoMySQL.resultToCommandite(result));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commandites;
	}
}
