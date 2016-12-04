package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Rubrique;

public class RubriqueDaoMySQL implements RubriqueDao
{
	// ========================================================================
	// == ATTRIBUTS
	// ========================================================================
	
	private DaoFactory factory;
	
	// ========================================================================
	// == CONSTRUCTEUR
	// ========================================================================
	
	RubriqueDaoMySQL(final DaoFactory factory)
	{
		this.factory = factory;
	}
	
	// ========================================================================
	// == METHODES BDD
	// ========================================================================
	
	@Override
	public String create(String nom_rubrique, int place_menu) 
	{
		// === Variables ===

		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		// === Requête ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("INSERT INTO rubriques(label_rubrique, place_menu) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
			req.setString(1, nom_rubrique);
			req.setInt(2, place_menu);
			
			req.executeUpdate();
			result = req.getGeneratedKeys();
			
			// --- Récupérer le label de la rubrique créée ---
			
			if (result.next()) {
				return result.getString("label_rubrique");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void delete(String nom_rubrique) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requête ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("DELETE FROM rubriques WHERE label_rubrique = ?");
			req.setString(1, nom_rubrique);
			
			req.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Rubrique find(String nom_rubrique) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		// === Requête ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("SELECT * FROM rubriques WHERE label_rubrique = ?");
			req.setString(1, nom_rubrique);
			
			result = req.executeQuery();
			
			// --- Retourner la rubrique trouvée ---
			
			if (result.next()) {
				return HelpersDaoMySQL.resultToRubrique(result);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Rubrique> list() 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		List<Rubrique> rubriques = new ArrayList<Rubrique>();
		
		// === Requête ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("SELECT * FROM rubriques");
			result = req.executeQuery();
			
			// --- Stocker la liste des rubriques ---
			
			while (result.next()) {
				rubriques.add(HelpersDaoMySQL.resultToRubrique(result));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rubriques;
	}
}
