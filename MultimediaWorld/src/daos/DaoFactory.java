package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoFactory 
{
	// ========================================================================
	// == ATTRIBUTS
	// ========================================================================

	private final static String 
		DB_URL = "jdbc:mysql://localhost:3306/ldmh",
		DB_USERNAME = "user",
		DB_PASSWORD = "user";
	
	private static Connection CONNECTION;
	
	// ========================================================================
	// == CONNEXION
	// ========================================================================
	
	public static Connection getConnection() throws SQLException
	{
		if (CONNECTION != null) {
			return CONNECTION;
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			System.err.println("JDBC introuvable");
			return null;
		}
		
		return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	}
	
	// ========================================================================
	// == DAOs
	// ========================================================================
	
	public static ArtisteDao getArtisteDao() {
		return new ArtisteDaoMySQL();
	}
	public static CommandeDao getCommandeDao() {
		return new CommandeDaoMySQL();
	}
	public static ProduitDao getProduitDao() {
		return new ProduitDaoMySQL();
	}
	public static RubriqueDao getRubriqueDao() {
		return new RubriqueDaoMySQL();
	}
	public static UserDao getUserDao() {
		return new UserDaoMySQL();
	}
}
