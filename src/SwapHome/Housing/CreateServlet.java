package SwapHome.Housing;

import housing.db.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import users.db.User;
import users.db.UserHandler;

/**
 * Classe permettant de créer des logements
 * @author Logan Lepage & Alexandre DUCREUX
 */
public class CreateServlet extends HttpServlet
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
        
        //sending informations
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/housing/create.jsp");
        rd.forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // retrieve session information
        HttpSession session = request.getSession();
        String emailUser = (String) session.getAttribute( "emailUser" );
        String passwordUser = (String) session.getAttribute( "passwordUser" );

        // test si l'utilisateur est connecté
        if(!(emailUser != null && passwordUser != null && UserHandler.getDb().isValid(emailUser, passwordUser))) {
            response.sendRedirect("../user/auth");
            return;
        }
        
        String address = request.getParameter("address");
        String zipCode = request.getParameter("zipCode");
        String city = request.getParameter("city");
        String countryP1 = request.getParameter("countryP1");
        String countryP2 = request.getParameter("countryP2");
        String description = request.getParameter("description");
        int surface = Integer.parseInt(request.getParameter("surface"));
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        int monthPrefered = Integer.parseInt(request.getParameter("monthPrefered"));
        int gardenSurface = Integer.parseInt(request.getParameter("gardenSurface"));
 
        
        // recording in DB with hibernate
        Housing housing;
        if(request.getParameter("type") == "House") {
            housing = new House(emailUser, address, zipCode, city, countryP1, 
                countryP2, description, surface, roomNumber, monthPrefered, gardenSurface );
        //} else if(request.getParameter("type") == "Apartment") {
        } else {
            housing = new Apartment(emailUser, address, zipCode, city, countryP1, 
                countryP2, description, surface, roomNumber, monthPrefered );
        }
        this.log("Enregistrement de " + housing);
        HousingHandler.getDb().create(housing);

        //setting session
        response.sendRedirect("../user/home/housing");
    }
   
}
