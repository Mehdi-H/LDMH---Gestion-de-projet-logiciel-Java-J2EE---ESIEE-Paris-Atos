package servlets;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import daos.DaoFactory;
import daos.ProduitDao;
import daos.RubriqueDao;
import daos.UserDao;
import helpers.RequestHelpers;
import helpers.SecurityHelpers;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet
{
	// ========================================================================
	// == ATTRIBUTS
	// ========================================================================
	
	private static final long serialVersionUID = 1L;
	
	// === DAOs ===
	
	private UserDao userDao;
	private RubriqueDao rubriqueDao;
	private ProduitDao produitDao;
	
	// ========================================================================
	// == CONSTRUCTEUR
	// ========================================================================

    public Connexion() 
    {
        super();
    }
    
    // ========================================================================
 	// == INIT
 	// ========================================================================
 	
    public void init() throws ServletException
    {
    	this.userDao = DaoFactory.getUserDao();
    	this.rubriqueDao = DaoFactory.getRubriqueDao();
    	this.produitDao = DaoFactory.getProduitDao();
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
		
		request.setAttribute("referrer", referrer);
		
		// === GENERATION DE LA JSP ===
		
		RequestHelpers.setUsualAttributes(request, "Connexion");
		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// === Lien d'origine / accueil ===
		
		String referrer = (String) request.getAttribute("referrer");
		if (referrer == null || referrer.isEmpty()) {
			referrer = request.getContextPath();
		}
		
		String formName = (String) request.getParameter("formName");
		if (formName.equals("connexion")) 
		{
			// === Paramètres de la requête de connexion ===
			
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			
			String mdp_hash = SecurityHelpers.sha256(password);
			
			// === Trouver l'utilisateur ===
			
			User currentUser = userDao.find(username, mdp_hash);
			
			RequestHelpers.setUsualAttributes(request, "Connexion");
			if (currentUser == null) 
			{
				// --- Echec de la connexion ---
				
				request.setAttribute("flash_error", "Mot de passe ou nom d'utilisateur incorrect.");
				this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
			}
			else {
				Cookie usernameCookie = new Cookie("username", username);
				usernameCookie.setMaxAge(60*60*24*365);
				response.addCookie(usernameCookie);
				
				request.setAttribute("flash_success", "Connexion réussie");
				response.sendRedirect(referrer);
			}
		}
		else if (formName.equals("inscription"))
		{
			RequestHelpers.setUsualAttributes(request, "Inscription");
			
			// === Paramètres de la requête de connexion ===

			String username = (String) request.getParameter("username");
			String nom = (String) request.getParameter("nom");
			String prenom = (String) request.getParameter("prenom");
			String adresse = (String) request.getParameter("adresse");
			String password = (String) request.getParameter("password");
			String role = (String) request.getParameter("role");
			
			String mdp_hash = SecurityHelpers.sha256(password);
			
			// === Enregistrer l'utilisateur ===
			
			String currentUsername = userDao.create(username, role, nom, prenom, adresse, mdp_hash);
			if (currentUsername == null)
			{
				// --- Echec de l'inscription ---
				
				request.setAttribute("flash_error", "Echec de l'inscription, nom d'utilisateur déjà existant ?");
				this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
			}
			else {
				Cookie usernameCookie = new Cookie("username", currentUsername);
				usernameCookie.setMaxAge(60*60*24*365);
				response.addCookie(usernameCookie);
				
				request.setAttribute("flash_success", "Inscription réussie, bienvenue !");
				this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
			}
		}
	}

}
