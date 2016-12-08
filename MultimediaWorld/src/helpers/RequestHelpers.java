package helpers;

import javax.servlet.http.HttpServletRequest;

import daos.DaoFactory;

public abstract class RequestHelpers
{
	public static void setUsualAttributes(HttpServletRequest request, final String title)
	{
		// Titre de la page :
		request.setAttribute("_title", "Présentation");
		
		// Rubriques du menu :
		request.setAttribute("_rubriques_menu", DaoFactory.getRubriqueDao().list());
	}

}
