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
 * Servlet implementation class FicheClient
 */
@WebServlet("/FicheClient")
public class FicheClient extends HttpServlet 
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

    public FicheClient() 
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
			request.setAttribute("flash_error", "Vous devez �tre administrateur pour administrer ;-)");
			RequestHelpers.homePageDoGet(request, response, this);
			return;
		}
		
		// === Clients ===
		
		List<User> clients = userDao.list();
		request.setAttribute("clients", clients);
		
		// --- Client sp�cifi� en GET ? ---
		
		String username = request.getParameter("username_input");
		if (username  != null && !username.isEmpty()) 
		{
			// ___ Client sp�cifi� en param�tre ___

			User client = userDao.find(username);
			if (client != null) 
			{
				List<beans.Commande> commandes_client = commandeDao.listCommandesUser(client.getUsername());
				
				request.setAttribute("client", client);
				request.setAttribute("commandes", commandes_client);
				RequestHelpers.setUsualAttributes(request, "Fiche du client " + client.getUsername());
			}
			else {
				request.setAttribute("flash_error", "Le client "+ username +" n'a pas pu �tre trouv� :S");
				RequestHelpers.setUsualAttributes(request, "Consultation des fiches clients");
			}
		}
		else
		{
			// ___ Pas de client sp�cifi� en param�tre ___
			RequestHelpers.setUsualAttributes(request, "Consultation des fiches clients");
		}
		
		// === GENERATION DE LA JSP ===
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/fiche_client.jsp").forward(request, response);
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
			request.setAttribute("flash_error", "Vous devez �tre administrateur pour administrer ;-)");
			RequestHelpers.homePageDoGet(request, response, this);
			return;
		}
		
		// === Commande � modifier ===
		
		String id_commande = request.getParameter("id_commande");
		if (id_commande == null || id_commande.isEmpty()) {
			request.setAttribute("flash_error", "Aucune commande � modifier :/");
			this.doGet(request, response);
			return;
		}
		
		beans.Commande commande = commandeDao.find(Integer.parseInt(id_commande));
		if (commande == null) {
			request.setAttribute("flash_error", "Commande introuvable :/");
			this.doGet(request, response);
			return;
		}
		
		// === Modifier l'�tat de la commande ===
		
		String etat_input = request.getParameter("etat_index");
		if (etat_input == null || etat_input.isEmpty()) {
			request.setAttribute("flash_error", "Nouvel �tat incompris :/");
			this.doGet(request, response);
			return;
		}
		
		int etat_index = Integer.parseInt(etat_input);
		if (etat_index < 1 || 3 < etat_index) {
			request.setAttribute("flash_error", "Impossible de d�finir cet �tat pour cette commande :/");
			this.doGet(request, response);
			return;
		}
		
		Etat.Label etat = Etat.Label.values()[etat_index];
		commandeDao.setEtat(commande.getId(), etat.toString());
		
		// === Recharger la vue ===
		
		request.setAttribute("flash_success", "Les modifications ont bien �t� prises en compte :)");
		this.doGet(request, response);
	}

}
