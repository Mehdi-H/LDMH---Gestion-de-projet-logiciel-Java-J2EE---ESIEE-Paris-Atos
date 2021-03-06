package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.factory.DataSourceLinkFactory.DataSourceHandler;

import beans.Produit;
import daos.DaoFactory;
import daos.ProduitDao;
import daos.RubriqueDao;
import daos.UserDao;
import helpers.DataHelpers;
import helpers.RequestHelpers;

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
    	this.userDao = DaoFactory.getUserDao();
    	this.rubriqueDao = DaoFactory.getRubriqueDao();
    	this.produitDao = DaoFactory.getProduitDao();
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
		
		// === Liste des produits de la rubrique ===
		
		List<Produit> produits = produitDao.listByRubrique(rubriqueLabel);
		DataHelpers.fillArtistesList(produits);
		
		request.setAttribute("produits", produits);
		
		// === GENERATION DE LA JSP ===
		
		RequestHelpers.setUsualAttributes(request, rubriqueLabel);
		this.getServletContext().getRequestDispatcher("/WEB-INF/liste_produits.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
