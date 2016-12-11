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
import javax.servlet.http.Part;

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
 * Servlet implementation class MajCatalogue
 */
@WebServlet("/MajCatalogue")
public class MajCatalogue extends HttpServlet 
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

    public MajCatalogue() 
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
		
		
		
		// === GENERATION DE LA JSP ===
		
		RequestHelpers.setUsualAttributes(request, "Mise à jour du catalogue");
		this.getServletContext().getRequestDispatcher("/WEB-INF/catalogue.jsp").forward(request, response);
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
		
		// === Récupérer le fichier CSV ===
		
		/*Part part = request.getPart("csv");
		
		String nomFichier = RequestHelpers.getNomFichier(part);
		if (nomFichier == null || nomFichier.isEmpty()) {
			request.setAttribute("flash_error", "Erreur lors de l'upload du fichier :S");
			this.doGet(request, response);
			return;
		}*/
		
		
		// === Recharger la vue ===
		
		// request.setAttribute("flash_success", "Le fichier "+ nomFichier +" a bien été importé :)");
		request.setAttribute("flash_error", "Cette fonction n'est pas encore implémentée :(");
		this.doGet(request, response);
	}

}
