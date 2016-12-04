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
	// == ATTRIBUTS
	// ========================================================================
	
	private DaoFactory factory;
	
	// ========================================================================
	// == CONSTRUCTEUR
	// ========================================================================
	
	UserDaoMySQL(final DaoFactory factory)
	{
		this.factory = factory;
	}
	
	// ========================================================================
	// == METHODES BDD
	// ========================================================================

	@Override
	public int create(String nom, String prenom, String adresse, String mdp_hash) 
	{
		// === Variables ===
		
		Connection conn = null;
		PreparedStatement req = null;
		
		// === Requête ===
		
		try {
			conn = factory.getConnection();
			
			req = conn.prepareStatement("INSERT INTO users(nom, prenom, adresse, mdp_hash) VALUES (?,?,?,?)");
			req.setString(1, nom);
			req.setString(2, prenom);
			req.setString(3, adresse);
			req.setString(4, mdp_hash);
			
			req.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public User find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRole(String label_role) {
		// TODO Auto-generated method stub
		
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
			conn = factory.getConnection();
			
			req = conn.createStatement();
			result = req.executeQuery("SELECT * FROM users");
			
			// --- Stocker la liste d'Users ---
			
			while (result.next()) 
			{
				User user = new User();
				user.setId(result.getInt("id_user"));
				user.setNom(result.getString("nom"));
				user.setPrenom(result.getString("prenom"));
				user.setAdresse(result.getString("adresse"));
				user.setMdp_hash(result.getString("mdp_hash"));
				
				users.add(user);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
}
