package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory 
{
	// ========================================================================
	// == ATTRIBUTS
	// ========================================================================

	private String 
		url, 
		username, 
		password;

	private final static String 
		DB_URL = "jdbc:mysql://localhost:3306/ldmh",
		DB_USERNAME = "user",
		DB_PASSWORD = "user";
	
	// ========================================================================
	// == CONSTRUCTEUR
	// ========================================================================
	
	DaoFactory(final String url, final String username, final String password)
	{
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	// ========================================================================
	// == CONNEXION & INSTANCIATION
	// ========================================================================
	
	public static DaoFactory getInstance()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			System.err.println("JDBC introuvable");
			return null;
		}
		
		return new DaoFactory(DB_URL, DB_USERNAME, DB_PASSWORD);
	}
	
	public Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url, username, password);
	}
	
	// ========================================================================
	// == DAOs
	// ========================================================================
	
	public UserDao getUserDao()
	{
		return new UserDaoMySQL(this);
	}
}
