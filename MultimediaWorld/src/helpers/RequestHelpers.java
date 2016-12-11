package helpers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import beans.Commande;
import beans.Role;
import beans.User;
import daos.DaoFactory;

public abstract class RequestHelpers
{
	public static void homePageDoGet(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) throws ServletException, IOException
	{
		// === GENERATION DE LA JSP ===
		
		RequestHelpers.setUsualAttributes(request, "Présentation de Multimedia World");
		servlet.getServletContext().getRequestDispatcher("/WEB-INF/presentation.jsp").forward(request, response);
	}
	
	public static void setUsualAttributes(HttpServletRequest request, final String title)
	{
		// === Titre de la page ===
		
		request.setAttribute("_title", title);
		
		// === Rubriques du menu ===
		
		request.setAttribute("_rubriques_menu", DaoFactory.getRubriqueDao().list());
		
		// === User connecté ===
		
		User user = getCurrentUser(request);
		if (user != null)
		{
			request.setAttribute("user", user);
			
			// --- Admin ? ---
			
			if (user.getRole().equals(Role.Label.ADMIN.toString())) {
				request.setAttribute("admin", true);				
			}
			
			// --- Nombre d'articles au panier ---
			
			Commande panier = DaoFactory.getCommandeDao().findUserPanier(user.getUsername());
			if (panier != null) 
			{
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
		
		if (cookies == null) {
			return null;
		}
		
		for (Cookie cookie : cookies)
		{
			if (cookie.getName().equals("username")) {
				return DaoFactory.getUserDao().find(cookie.getValue());
			}
		}
		
		return null;
	}
	
	/* public static String getNomFichier(Part part)
	{
		for (String contentDisposition : part.getHeader("content-disposition").split(";"))
		{
			if (contentDisposition.trim().startsWith("filename")) {
				return contentDisposition.substring(contentDisposition.indexOf("=") + 1);
			}
		}
		
		return null;
	} */
}
