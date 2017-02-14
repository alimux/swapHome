package swapHome.user.admin;

import swapHome.user.home.*;
import housing.db.*;
import users.db.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import policies.Auth;

/**
 * Class which manage the main page of account of swapHome project
 * @author Alexandre DUCREUX & Logan Lepage
 */
public class HousingServlet extends HttpServlet
  {
    //stub
    //public static PersonDBStub dataBase = new PersonDBStub();
    //real database
    public static IUserDB dataBase = new UserDBStub();
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
        User userSession = Auth.getAuthenticated(request);
        if(userSession == null || !Auth.isAuthenticatedAdmin(request)) 
            { response.sendRedirect("../auth"); return; }

        // Ajoute en attribut la liste des logements
        request.setAttribute("housings", HousingHandler.getDb().list());

        //sending informations
        this.getServletContext().getRequestDispatcher("/user/admin/housing.jsp").forward(request, response);
    }
}
