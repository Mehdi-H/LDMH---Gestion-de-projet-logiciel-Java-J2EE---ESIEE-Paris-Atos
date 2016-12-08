package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Artiste;
import beans.Produit;
import beans.Rubrique;

public class ProduitDaoMySQL implements ProduitDao 
{// ========================================================================
	// == METHODES BDD
	// ========================================================================
	
	@Override
	public int create(String nom_produit, String date_sortie, int stock, float prix) 
	{
		// === Variables ===

		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("INSERT INTO produits(nom_produit, date_sortie, stock, prix) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			req.setString(1, nom_produit);
			req.setString(2, date_sortie);
			req.setInt(3, stock);
			req.setFloat(4, prix);
			
			req.executeUpdate();
			result = req.getGeneratedKeys();
			
			// --- Récupérer l'ID du produit créé ---
			
			if (result.next()) {
				return result.getInt("id_produit");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public void delete(int id_produit)
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("DELETE FROM produits WHERE id_produit = ?");
			req.setInt(1, id_produit);
			
			req.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------
	// -- FINDERS
	// ------------------------------------------------------------------------
	
	@Override
	public Produit find(int id_produit) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("SELECT * FROM rubriques WHERE label_rubrique = ?");
			req.setInt(1, id_produit);
			
			result = req.executeQuery();
			
			// --- Retourner la rubrique trouvée ---
			
			if (result.next()) {
				return HelpersDaoMySQL.resultToProduit(result);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Produit> listByRubrique(String label_rubrique) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		List<Produit> produits = new ArrayList<Produit>();
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("SELECT * FROM produits NATURAL JOIN classements WHERE classements.label_rubrique = ?");
			req.setString(1, label_rubrique);
			
			result = req.executeQuery();
			
			// --- Stocker la liste des produits de cette rubrique ---
			
			while (result.next()) {
				produits.add(HelpersDaoMySQL.resultToProduit(result));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return produits;
	}

	@Override
	public List<Produit> search(String pattern)
	{
		// === Variables ===

		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		List<Produit> produits = new ArrayList<Produit>();
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement(
				"SELECT * FROM produits "
				+ "NATURAL JOIN (participations NATURAL JOIN artistes) "
				+ "NATURAL JOIN (classements NATURAL JOIN rubriques) "
				+ "WHERE nom_produit LIKE ? OR nom_artiste LIKE ? OR label_rubrique LIKE ? "
				+ "GROUP BY produits.id_produit;"
			);
			req.setString(1, "%" + pattern + "%");
			req.setString(2, "%" + pattern + "%");
			req.setString(3, "%" + pattern + "%");
			
			result = req.executeQuery();
			
			// --- Stocker la liste des produits de cette rubrique ---
			
			while (result.next()) {
				produits.add(HelpersDaoMySQL.resultToProduit(result));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return produits;
	}

	// ------------------------------------------------------------------------
	// -- SETTERS
	// ------------------------------------------------------------------------
	
	@Override
	public void setStock(int id_produit, int stock) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("UPDATE produits SET stock = ? WHERE id_produit = ?");
			req.setInt(1, stock);
			req.setInt(2, id_produit);
			
			req.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setPrix(int id_produit, float prix) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("UPDATE produits SET prix = ? WHERE id_produit = ?");
			req.setFloat(1, prix);
			req.setInt(2, id_produit);
			
			req.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------
	// -- RUBRIQUES (CLASSEMENTS)
	// ------------------------------------------------------------------------
	
	@Override
	public void addRubrique(int id_produit, String label_rubrique) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("INSERT INTO classements(id_produit, label_rubrique) VALUES (?,?)");
			req.setInt(1, id_produit);
			req.setString(2, label_rubrique);
			
			req.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeRubrique(int id_produit, String label_rubrique)
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("DELETE FROM classements WHERE id_produit = ? AND label_rubrique = ?");
			req.setInt(1, id_produit);
			req.setString(2, label_rubrique);
			
			req.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Rubrique> listRubriques(int id_produit)
	{
		// === Variables ===

		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		List<Rubrique> rubriques = new ArrayList<Rubrique>();
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("SELECT * FROM classements NATURAL JOIN rubriques WHERE id_produit = ?");
			req.setInt(1, id_produit);
			
			result = req.executeQuery();
			
			// --- Stocker la liste des rubriques du Produit ---
			
			while (result.next()) 
			{
				rubriques.add(HelpersDaoMySQL.resultToRubrique(result));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rubriques;
	}


	// ------------------------------------------------------------------------
	// -- ARTISTES (PARTICIPATIONS)
	// ------------------------------------------------------------------------
	
	@Override
	public void addArtiste(int id_produit, int id_artiste) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("INSERT INTO participations(id_produit, id_artiste) VALUES (?,?)");
			req.setInt(1, id_produit);
			req.setInt(2, id_artiste);
			
			req.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeArtiste(int id_produit, int id_artiste)
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("DELETE FROM participations WHERE id_produit = ? AND id_artiste = ?");
			req.setInt(1, id_produit);
			req.setInt(2, id_artiste);
			
			req.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Artiste> listArtistes(int id_produit) 
	{
		// === Variables ===

		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		List<Artiste> artistes = new ArrayList<Artiste>();
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("SELECT * FROM participations NATURAL JOIN artistes WHERE id_produit = ?");
			req.setInt(1, id_produit);
			
			result = req.executeQuery();
			
			// --- Stocker la liste des artistes du Produit ---
			
			while (result.next()) 
			{
				artistes.add(HelpersDaoMySQL.resultToArtiste(result));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return artistes;
	}
}
