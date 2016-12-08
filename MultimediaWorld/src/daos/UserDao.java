package daos;

import java.util.List;

import beans.User;

public interface UserDao 
{
	String create(final String username, final String role, final String nom, final String prenom, final String adresse, final String mdp_hash);
	User find(final String username);
	User find(final String username, final String mdp_hash);
	List<User> list();
}
