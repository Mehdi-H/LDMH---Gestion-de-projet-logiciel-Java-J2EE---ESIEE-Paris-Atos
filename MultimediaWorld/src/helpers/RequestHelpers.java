package helpers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import beans.Commande;
import beans.User;
import daos.DaoFactory;

public abstract class RequestHelpers
{
	public static void setUsualAttributes(HttpServletRequest request, final String title)
	{
		// === Titre de la page ===
		
		request.setAttribute("_title", "Présentation");
		
		// === Rubriques du menu ===
		
		request.setAttribute("_rubriques_menu", DaoFactory.getRubriqueDao().list());
		
		// === User connecté ===
		
		User user = getCurrentUser(request);
		if (user != null) {
			request.setAttribute("user", user);
		}
		
		// === Nombre d'articles au panier ===
		
		// User courant :
		if (user != null) 
		{
			// Son panier :
			Commande panier = DaoFactory.getCommandeDao().findUserPanier(user.getUsername());
			if (panier != null) 
			{
				// Nombre de commandites du panier :
				int nb_produits = DaoFactory.getCommandeDao().listCommandites(panier.getId()).size();
				if (nb_produits > 0) {
					request.setAttribute("panier_amount", nb_produits);
				}
			}
		}
	}

	public static User getCurrentUser(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies)
		{
			if (cookie.getName().equals("username")) {
				return DaoFactory.getUserDao().find(cookie.getValue());
			}
		}
		
		return null;
	}
}
