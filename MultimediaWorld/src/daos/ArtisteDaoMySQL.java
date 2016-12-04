package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArtisteDaoMySQL implements ArtisteDao 
{
	// ========================================================================
	// == ATTRIBUTS
	// ========================================================================
	
	private DaoFactory factory;
	
	// ========================================================================
	// == CONSTRUCTEUR
	// ========================================================================
	
	ArtisteDaoMySQL(final DaoFactory factory)
	{
		this.factory = factory;
	}
	
	// ========================================================================
	// == METHODES BDD
	// ========================================================================
	
	@Override
	public int create(String nom_artiste) 
	{
		// === Variables ===

		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		// === Requête ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("INSERT INTO artistes(nom_artiste) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			req.setString(1, nom_artiste);
			
			req.executeUpdate();
			result = req.getGeneratedKeys();
			
			// --- Récupérer le label de la rubrique créée ---
			
			if (result.next()) {
				return result.getInt("id_artiste");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public void delete(int id_artiste) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requête ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("DELETE FROM artistes WHERE id_artiste = ?");
			req.setInt(1, id_artiste);
			
			req.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
