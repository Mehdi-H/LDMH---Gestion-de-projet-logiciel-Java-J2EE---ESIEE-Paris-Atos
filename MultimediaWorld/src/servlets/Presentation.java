package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.DaoFactory;
import daos.RubriqueDao;
import daos.UserDao;


@WebServlet("/Presentation")
public class Presentation extends HttpServlet 
{
	// ========================================================================
	// == ATTRIBUTS
	// ========================================================================
	
	private static final long serialVersionUID = 1L;
	
	// === DAOs ===
	
	private UserDao userDao;
	private RubriqueDao rubriqueDao;
	
	// ========================================================================
	// == CONSTRUCTEUR
	// ========================================================================

    public Presentation() 
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
    }
    
    // ========================================================================
 	// == HTTP
 	// ========================================================================

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// === MENUS ===
		
		request.setAttribute("_title", "Présentation");
		request.setAttribute("_rubriques_menu", rubriqueDao.list());
		
		
		// === GENERATION DE LA JSP ===
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/presentation.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
