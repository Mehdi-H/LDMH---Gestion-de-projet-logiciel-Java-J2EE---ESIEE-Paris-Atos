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
 * Servlet implementation class GestionNouveautes
 */
@WebServlet("/GestionNouveautes")
public class GestionNouveautes extends HttpServlet 
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

    public GestionNouveautes() 
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
		
		// === Nouveautés ===
		
		List<Produit> produits = produitDao.listByRubrique("Nouveautés");
		DataHelpers.fillArtistesList(produits);
		
		List<Produit> not_new_produits = produitDao.listNotNew();
		
		request.setAttribute("produits", produits);
		request.setAttribute("nb_produits", produits.size());
		
		request.setAttribute("not_new_produits", not_new_produits);
		
		// === GENERATION DE LA JSP ===
		
		RequestHelpers.setUsualAttributes(request, "Gestion des nouveautés");
		this.getServletContext().getRequestDispatcher("/WEB-INF/gestion_nouveautes.jsp").forward(request, response);
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
		
		// === Nouveautés ===
		
		List<Produit> produits = produitDao.listByRubrique("Nouveautés");
		
		// === AJOUTER / ENLEVER ===
		
		String method = request.getParameter("submit");
		if (method.equals("Ajouter"))
		{
			// --- AJOUTER le produit dans la rubrique Nouveautés ---
			Produit pdt = produitDao.find(Integer.parseInt(request.getParameter("id_produit")));
			if (pdt != null) {
				produitDao.addRubrique(pdt.getId(), "Nouveautés");
			}
			else {
				request.setAttribute("flash_error", "Le produit est introuvable :S");
				this.doGet(request, response);
				return;
			}
		}
		else if (method.equals("Enlever"))
		{
			// --- SUPPRIMER la rubrique Nouveautés du produit ---
			Produit pdt = produitDao.find(Integer.parseInt(request.getParameter("id_produit")));
			if (pdt != null) {
				produitDao.removeRubrique(pdt.getId(), "Nouveautés");
			}
			else {
				request.setAttribute("flash_error", "Le produit est introuvable :S");
				this.doGet(request, response);
				return;
			}
		}
		else {
			request.setAttribute("flash_error", "Action incomprise :/");
			this.doGet(request, response);
			return;
		}
		
		// === Recharger la vue ===
		
		request.setAttribute("flash_success", "Les modifications ont bien été prises en compte :)");
		this.doGet(request, response);
	}

}
