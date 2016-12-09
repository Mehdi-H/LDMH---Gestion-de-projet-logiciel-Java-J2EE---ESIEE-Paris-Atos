package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Commande;
import beans.Commandite;
import beans.Produit;
import beans.User;
import daos.CommandeDao;
import daos.DaoFactory;
import daos.ProduitDao;
import daos.RubriqueDao;
import daos.UserDao;

/**
 * Servlet implementation class AjoutPanier
 */
@WebServlet("/AjoutPanier")
public class AjoutPanier extends HttpServlet 
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

    public AjoutPanier() 
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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// === Récupérer le User ===
		
		User user = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies)
		{
			if (cookie.getName().equals("username")) {
				user = userDao.find(cookie.getValue());
			}
		}
		if (user == null) {
			return;
		}
		
		// === Récupérer/Créer le panier du User ===
		
		Commande panier = commandeDao.findUserPanier(user.getUsername());
		
		if (panier == null) 
		{
			// Créer un panier :
			if (commandeDao.create(user.getUsername()) > -1) {
				panier = commandeDao.findUserPanier(user.getUsername());
			}
		}
		
		// === Récupérer le produit à ajouter ===
		
		Produit produit = produitDao.find(Integer.parseInt(request.getParameter("product_id")));
		if (produit == null) {
			return;
		}
		
		// === Créer une commandite pour ce produit ou incrémenter sa quantité ===
		
		Commandite commandite = commandeDao.findCommandite(panier.getId(), produit.getId());
		if (commandite == null) {
			// Ajouter l'article au panier :
			commandeDao.addCommandite(panier.getId(), produit.getId(), 1, produit.getPrix());
		}
		else {
			// Incrémenter quantité :
			commandeDao.setCommanditeQuantite(panier.getId(), produit.getId(), commandite.getQuantite() + 1);
		}
		
		// === Compter le nombre de produits (commandites) du panier pour le badge ===
		
		int nb_produits_panier = commandeDao.listCommandites(panier.getId()).size();
		
		// === Réponse ===
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(nb_produits_panier + "");
	}
}
