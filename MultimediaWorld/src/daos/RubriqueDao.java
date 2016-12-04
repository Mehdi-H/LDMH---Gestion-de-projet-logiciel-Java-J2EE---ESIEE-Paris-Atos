package daos;

import java.util.List;

import beans.Rubrique;

public interface RubriqueDao 
{
	String create(final String nom_rubrique, final int place_menu);
	void delete(final String nom_rubrique);
	Rubrique find(final String nom_rubrique);
	List<Rubrique> list();
}
