package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.DaoFactory;
import daos.ProduitDao;
import daos.RubriqueDao;
import daos.UserDao;

/**
 * Servlet implementation class Deconnexion
 */
@WebServlet("/Deconnexion")
public class Deconnexion extends HttpServlet 
{
	// ========================================================================
	// == ATTRIBUTS
	// ========================================================================
	
	private static final long serialVersionUID = 1L;
	
	// ========================================================================
	// == CONSTRUCTEUR
	// ========================================================================

    public Deconnexion() 
    {
        super();
    }
    
    // ========================================================================
 	// == HTTP
 	// ========================================================================

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// === Lien d'origine / accueil ===
		
		String referrer = request.getHeader("referer");
		if (referrer == null || referrer.isEmpty()) {
			referrer = request.getContextPath();
		}
		
		// === Effaceur de cookie ===
		
		Cookie usernameCookieEraser = new Cookie("username", "");
		usernameCookieEraser.setMaxAge(0);
		response.addCookie(usernameCookieEraser);
		
		// === Redirection à la page d'origine ou à l'accueil ===
		
		response.sendRedirect(referrer);
	}
}
