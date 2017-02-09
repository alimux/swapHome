package SwapHome.Housing;

import housing.db.*;
import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import users.db.User;
import users.db.UserHandler;
import utils.*;

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
        
        List countries =  Utils.getCountriesList();
        List months = Utils.getMonthList();
        request.setAttribute("months", months);
        request.setAttribute("countries", countries);
        this.getServletContext().getRequestDispatcher(
          request.getParameter("type") != null && request.getParameter("type").equals("house")
          ? "/housing/createHouse.jsp"
          : "/housing/createApartment.jsp"
        ).forward(request, response);
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
          //retrieve month enum and push into form
          

        User user = UserHandler.getDb().retrieve(emailUser);
        String address = request.getParameter("address");
        String zipCode = request.getParameter("zipCode");
        String city = request.getParameter("city");
        String countryP1 = request.getParameter("countryP1");
        String countryP2 = request.getParameter("countryP2");
        String description = request.getParameter("description");
        int surface = Integer.parseInt(request.getParameter("surface"));
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        int monthPrefered = Integer.parseInt(request.getParameter("monthPrefered"));
        System.out.println("Parsing month"+monthPrefered);
        Utils u = new Utils();
        if(!u.isValid(monthPrefered)){
            String error = "Merci de sélectionner un mois valide !";
            request.setAttribute("erreur", error);
            this.getServletContext().getRequestDispatcher("/user/housing/createHouse.jsp").forward(request, response);
            
        }

        // recording in DB with hibernate
        Housing housing;
        if(request.getParameter("type").equals("house")) {
          int gardenSurface = Integer.parseInt(request.getParameter("gardenSurface"));
            housing = new House(user, address, zipCode, city, countryP1,
                countryP2, description, surface, roomNumber, monthPrefered, gardenSurface );
        } else {
            housing = new Apartment(user, address, zipCode, city, countryP1,
                countryP2, description, surface, roomNumber, monthPrefered );
        }

        this.log("Enregistrement de " + housing);
        HousingHandler.getDb().create(housing);

        //setting session
        response.sendRedirect("../user/home/housing");
    }

}
