package swapHome.housing;

import housing.db.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import users.db.User;
import users.db.UserHandler;

/**
 * Classe permettant de supprimer des logements
 * @author Logan Lepage & Alexandre DUCREUX
 */
public class DeleteServlet extends HttpServlet
  {

    /**
     * calling servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
         // retrieve session information
        HttpSession session = request.getSession();
        String emailUser = (String) session.getAttribute( "emailUser" );
        String passwordUser = (String) session.getAttribute( "passwordUser" );

        // test si l'utilisateur est connecté
        if(!(emailUser != null && passwordUser != null && UserHandler.getDb().isValid(emailUser, passwordUser))) {
            response.sendRedirect("../user/auth");
            return;
        }

        // Supprime le logement s'il existe 
        if(request.getParameter("id") != null) {
            Housing housing = HousingHandler.getDb().retrieve(
                Integer.parseInt(request.getParameter("id"))
            );
            if(housing != null)
                HousingHandler.getDb().delete(housing);
        }

        //sending informations
        response.sendRedirect("../user/home/housing");
    }
}
