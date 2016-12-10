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
import beans.User;
import daos.CommandeDao;
import daos.DaoFactory;
import daos.ProduitDao;
import daos.RubriqueDao;
import daos.UserDao;
import helpers.DataHelpers;
import helpers.RequestHelpers;

/**
 * Servlet implementation class Commande
 */
@WebServlet("/Commande")
public class Commande extends HttpServlet 
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

    public Commande() 
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
			request.setAttribute("flash_error", "Connectez-vous avant de commander ;-)");
			RequestHelpers.homePageDoGet(request, response, this);
			return;
		}
		
		request.setAttribute("user", user);
		
		// === Panier en cours à transformer en commande ===
		
		beans.Commande panier = commandeDao.findUserPanier(user.getUsername());
		if (panier == null) {
			// Redirection vers la page d'accueil :
			request.setAttribute("flash_error", "Ajoutez un produit à votre panier avant de commander ;-)");
			RequestHelpers.homePageDoGet(request, response, this);
			return;
		}
		
		request.setAttribute("commande", panier);
		request.setAttribute("total", commandeDao.computeTotal(panier.getId()));
		
		// === Produits du panier ===
		
		List<Produit> produits = DataHelpers.getProduitsInCommande(panier.getId());
		if (produits == null || produits.size() < 1) {
			// Redirection vers la page d'accueil :
			request.setAttribute("flash_error", "Ajoutez un produit à votre panier avant de commander ;-)");
			RequestHelpers.homePageDoGet(request, response, this);
			return;
		}
		
		request.setAttribute("produits", produits);
		request.setAttribute("nb_produits", produits.size());
		
		// === GENERATION DE LA JSP ===
		
		RequestHelpers.setUsualAttributes(request, "Votre commande n°" + panier.getId());
		this.getServletContext().getRequestDispatcher("/WEB-INF/commande.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// === User ===
		
		User user = RequestHelpers.getCurrentUser(request);
		if (user == null) {
			// Redirection vers la page d'accueil :
			request.setAttribute("flash_error", "Connectez-vous avant de commander ;-)");
			RequestHelpers.homePageDoGet(request, response, this);
			return;
		}
		
		// === Panier en cours à transformer en commande ===
		
		beans.Commande panier = commandeDao.findUserPanier(user.getUsername());
		if (panier == null) {
			// Redirection vers la page d'accueil :
			request.setAttribute("flash_error", "Ajoutez un produit à votre panier avant de commander ;-)");
			RequestHelpers.homePageDoGet(request, response, this);
			return;
		}
		
		// --- Changer l'état à "Commandé" et mettre à jour la date de paiement ---
		
		DateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
		Date now = new Date();
		
		commandeDao.setEtat(panier.getId(), Etat.Label.ORDERED.toString());
		commandeDao.setDate(panier.getId(), dateFormat.format(now));
		
		// === Rediriger à l'accueil ===
		
		request.setAttribute("flash_success", "Votre commande a bien été enregistrée, merci !");
		RequestHelpers.homePageDoGet(request, response, this);
	}

}
