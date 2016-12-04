package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Produit;
import daos.DaoFactory;
import daos.ProduitDao;
import daos.RubriqueDao;
import daos.UserDao;

/**
 * Servlet implementation class Rubrique
 */
@WebServlet("/Rubrique")
public class Rubrique extends HttpServlet 
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

    public Rubrique() 
    {
        super();
    }
    
    // ========================================================================
 	// == INIT
 	// ========================================================================
 	
    public void init() throws ServletException
    {
    	DaoFactory factory = DaoFactory.getInstance();
    	this.userDao = factory.getUserDao();
    	this.rubriqueDao = factory.getRubriqueDao();
    	this.produitDao = factory.getProduitDao();
    }
    
    // ========================================================================
 	// == HTTP
 	// ========================================================================

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// === PARAMETRES DE LA REQUETE ===
		
		String path = request.getPathInfo(); // "/<label>(/...)"
		String[] pathParts = path.split("/"); // {"", "<label>", ...}
		String rubriqueLabel = pathParts[1]; // "<label>"
		
		// === MENUS ===
		
		request.setAttribute("_title", rubriqueLabel);
		request.setAttribute("_rubriques_menu", rubriqueDao.list());
		
		// === Liste des produits de la rubrique ===
		
		List<Produit> produits = produitDao.listByRubrique(rubriqueLabel);
		for (Produit pdt : produits) {
			pdt.setArtistes(produitDao.listArtistes(pdt.getId_produit()));
		}
		
		request.setAttribute("produits", produits);
		
		// === GENERATION DE LA JSP ===
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/liste_produits.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}