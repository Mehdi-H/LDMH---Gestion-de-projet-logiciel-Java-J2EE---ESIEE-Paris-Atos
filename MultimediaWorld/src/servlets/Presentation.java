package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.DaoFactory;
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
    }
    
    // ========================================================================
 	// == HTTP
 	// ========================================================================

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("users", userDao.list());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/presentation.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
