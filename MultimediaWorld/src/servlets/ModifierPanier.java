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
import helpers.DataHelpers;
import helpers.RequestHelpers;

/**
 * Servlet implementation class AjoutPanier
 */
@WebServlet("/AjoutPanier")
public class ModifierPanier extends HttpServlet 
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

    public ModifierPanier() 
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
		
		User user = RequestHelpers.getCurrentUser(request);
		if (user == null) {
			return;
		}
		
		// === Récupérer/Créer le panier du User ===
		
		Commande panier = commandeDao.findUserPanier(user.getUsername());
		int id_panier = -1;
		
		if (panier == null) 
		{
			// Créer un panier :
			id_panier = commandeDao.create(user.getUsername(), 2.7); 
			if (id_panier < 0) {
				System.out.println("id_panier : " + id_panier);
				return;
			}
		}
		else 
		{
			id_panier = panier.getId();
		}
		
		// === Récupérer le produit à ajouter ===
		
		Produit produit = produitDao.find(Integer.parseInt(request.getParameter("product_id")));
		if (produit == null) {
			return;
		}
		
		// === AJOUTER / SUPPRIMER / MODIFIER ===
		
		String method = request.getParameter("method");
		
		if (method.equals("ajouter")) 
		{
			// --- Ajouter le produit au panier ---
			DataHelpers.addProduitToCommande(id_panier, produit);
		}
		else if (method.equals("supprimer"))
		{
			// --- Supprimer l'article du panier ---
			commandeDao.removeCommandite(id_panier, produit.getId());
		}
		else if (method.equals("modifier"))
		{
			// --- Modifier la quantité du produit dans le panier ---
			int quantite = Integer.parseInt(request.getParameter("quantite"));
			commandeDao.setCommanditeQuantite(id_panier, produit.getId(), quantite);
		}
		
		// === Compter le nombre de produits dans le panier pour le header ===
		
		int nb_produits_panier = commandeDao.listCommandites(id_panier).size();
		
		// === Réponse ===
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(nb_produits_panier + "");
	}
}
