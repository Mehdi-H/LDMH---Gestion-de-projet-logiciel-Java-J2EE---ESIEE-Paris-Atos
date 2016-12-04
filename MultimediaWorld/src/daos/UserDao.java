package daos;

import java.util.List;

import beans.User;

public interface UserDao 
{
	int create(final String nom, final String prenom, final String adresse, final String mdp_hash);
	User find(final int id);
	void setRole(final String label_role);
	List<User> list();
}
