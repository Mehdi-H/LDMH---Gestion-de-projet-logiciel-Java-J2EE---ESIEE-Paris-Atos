package daos;

import java.util.List;

import beans.Rubrique;

public interface RubriqueDao 
{
	String create(final String nom_rubrique, final int place_menu);
	void delete(final String nom_rubrique);
	Rubrique find(final String nom_rubrique);
	List<Rubrique> list();
	void setPlaceMenu(final String nom_rubrique, final int position);
	void setLabel(final String ancien_nom_rubrique, final String nouveau_nom_rubrique);
}
