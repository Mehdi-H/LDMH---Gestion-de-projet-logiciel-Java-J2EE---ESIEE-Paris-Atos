package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.User;

public class UserDaoMySQL implements UserDao
{
	// ========================================================================
	// == METHODES BDD
	// ========================================================================

	@Override
	public String create(String username, String role, String nom, String prenom, String adresse, String mdp_hash) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("INSERT INTO users(username, label_role, nom, prenom, adresse, mdp_hash) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			req.setString(1, username);
			req.setString(2, role);
			req.setString(3, nom);
			req.setString(4, prenom);
			req.setString(5, adresse);
			req.setString(6, mdp_hash);
			
			req.executeUpdate();
			result = req.getGeneratedKeys();
			
			// --- Récupérer le username du User créé ---
			
			if (result.next()) {
				return result.getString(1);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public User find(String username) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
			req.setString(1, username);
			
			result = req.executeQuery();
			
			// --- Retourner le User trouvé ---
			
			if (result.next()) {
				return HelpersDaoMySQL.resultToUser(result);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public User find(String username, String mdp_hash) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		ResultSet result = null;
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND mdp_hash = ?");
			req.setString(1, username);
			req.setString(2, mdp_hash);
			
			result = req.executeQuery();
			
			// --- Retourner le User trouvé ---
			
			if (result.next()) {
				return HelpersDaoMySQL.resultToUser(result);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> list() 
	{
		// === Variables ===
		
		Connection conn = null;
		Statement req = null;
		ResultSet result = null;
		
		List<User> users = new ArrayList<User>();
		
		// === Requête ===
		
		try {
			conn = DaoFactory.getConnection();
			
			req = conn.createStatement();
			result = req.executeQuery("SELECT * FROM users");
			
			// --- Stocker la liste d'Users ---
			
			while (result.next()) 
			{
				users.add(HelpersDaoMySQL.resultToUser(result));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
}
