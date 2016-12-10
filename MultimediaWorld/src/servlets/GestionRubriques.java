package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Etat;
import beans.Produit;
import beans.Role;
import beans.User;
import daos.CommandeDao;
import daos.DaoFactory;
import daos.ProduitDao;
import daos.RubriqueDao;
import daos.UserDao;
import helpers.DataHelpers;
import helpers.RequestHelpers;

/**
 * Servlet implementation class GestionRubriques
 */
@WebServlet("/GestionRubriques")
public class GestionRubriques extends HttpServlet 
{
	// ========================================================================
	// == ATTRIBUTS
	// ========================================================================
	
	private static final long serialVersionUID = 1L;
	
	// === DAOs ===
	
	private UserDao userDao;
	private RubriqueDao rubriqueDao;
	private ProduitDao produitDao;

	private CommandeDao commandeDao;
	
	// ========================================================================
	// == CONSTRUCTEUR
	// ========================================================================

    public GestionRubriques() 
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
    	this.commandeDao = DaoFactory.getCommandeDao();
    }
    
    // ========================================================================
 	// == HTTP
 	// ========================================================================
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// === User ===
		
		User user = RequestHelpers.getCurrentUser(request);
		if (user == null) {
			// Redirection vers la page d'accueil :
			request.setAttribute("flash_error", "Connectez-vous avant d'administrer ;-)");
			RequestHelpers.homePageDoGet(request, response, this);
			return;
		}
		
		if (! user.getRole().equals(Role.Label.ADMIN.toString())) {
			// Redirection vers la page d'accueil :
			request.setAttribute("flash_error", "Vous devez être administrateur pour administrer ;-)");
			RequestHelpers.homePageDoGet(request, response, this);
			return;
		}
		
		// === Rubriques ===
		
		List<beans.Rubrique> rubriques = rubriqueDao.list();
		
		request.setAttribute("rubriques", rubriques);
		request.setAttribute("nb_rubriques", rubriques.size());
		
		// === GENERATION DE LA JSP ===
		
		RequestHelpers.setUsualAttributes(request, "Gestion des rubriques");
		this.getServletContext().getRequestDispatcher("/WEB-INF/gestion_rubriques.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// === User ===
		
		User user = RequestHelpers.getCurrentUser(request);
		if (user == null) {
			// Redirection vers la page d'accueil :
			request.setAttribute("flash_error", "Connectez-vous avant d'administrer ;-)");
			RequestHelpers.homePageDoGet(request, response, this);
			return;
		}
		
		if (! user.getRole().equals(Role.Label.ADMIN.toString())) {
			// Redirection vers la page d'accueil :
			request.setAttribute("flash_error", "Vous devez être administrateur pour administrer ;-)");
			RequestHelpers.homePageDoGet(request, response, this);
			return;
		}
		
		// === Rubriques ===
		
		List<beans.Rubrique> rubriques = rubriqueDao.list();
		
		// === AJOUTER / SUPPRIMER / MODIFIER ===
		
		String method = request.getParameter("submit");
		if (method.equals("Ajouter"))
		{
			// --- AJOUTER la rubrique en dernière place du menu ---
			String label = request.getParameter("label_rubrique");
			rubriqueDao.create(label, rubriques.size());
		}
		else if (method.equals("Valider"))
		{
			// --- MODIFIER la rubrique ---
			// Rubrique concernée :
			beans.Rubrique rub = rubriqueDao.find(request.getParameter("label_rubrique"));
			if (rub != null) 
			{
				// ___ Position ___
				int position = Integer.parseInt(request.getParameter("position"));
				if (rub.getPlace_menu() != position) {
					rubriqueDao.setPlaceMenu(rub.getLabel(), position);
				}
				
				// ___ Renommer ___
				String renom = request.getParameter("nouveau_label_rubrique");
				if (! renom.isEmpty() && renom != null) {
					rubriqueDao.setLabel(rub.getLabel(), renom);
				}
			}
		}
		else if (method.equals("Supprimer"))
		{
			// --- SUPPRIMER la rubrique ---
			beans.Rubrique rub = rubriqueDao.find(request.getParameter("label_rubrique"));
			if (rub != null) {
				rubriqueDao.delete(rub.getLabel());
			}
		}
		
		// === Recharger la vue ===
		
		request.setAttribute("flash_success", "Les modifications ont bien été prises en compte :)");
		this.doGet(request, response);
	}

}
