package swapHome.user.home;

import housing.db.HousingHandler;
import users.db.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;

/**
 * Classe permettant de lister des logements
 * @author Logan Lepage & Alexandre DUCREUX
 */
public class HousingOffersServlet extends HttpServlet
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
        response.sendRedirect("../../user/auth");
        return;
      }

      // Ajoute en attribut la liste des logements compatibles pour un utilisateur
      User user = UserHandler.getDb().retrieve(emailUser);
      request.setAttribute("housings", HousingHandler.getDb().listCompatibleForUser(user));

      //sending informations
      this.getServletContext().getRequestDispatcher("/user/home/offers.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
      // retrieve session information
      HttpSession session = request.getSession();
      String emailUser = (String) session.getAttribute( "emailUser" );
      String passwordUser = (String) session.getAttribute( "passwordUser" );

      // test si l'utilisateur est connecté
      if(!(emailUser != null && passwordUser != null && UserHandler.getDb().isValid(emailUser, passwordUser))) {
          response.sendRedirect("../../user/auth");
          return;
      }

      User user = UserHandler.getDb().retrieve(emailUser);
      String zipCode = request.getParameter("zipCode") != null && !request.getParameter("zipCode").isEmpty()
      ? request.getParameter("zipCode") : null;
      // La stratégie: on essaye de convertir, si ça ne fonctionne pas ce on utilise la valeur prédéfinie.
      // On met à -1 par défaut pour envoyer des integers à HousingHandler
      int surfaceMin = -1;
      try { surfaceMin = Integer.parseInt(request.getParameter("surfaceMin")); } catch(Exception e){}
      int surfaceMax = -1;
      try { surfaceMax = Integer.parseInt(request.getParameter("surfaceMax")); } catch(Exception e){}
      int roomNumberMin = -1;
      try { roomNumberMin = Integer.parseInt(request.getParameter("roomNumberMin")); } catch(Exception e){}
      int roomNumberMax = -1;
      try { roomNumberMax = Integer.parseInt(request.getParameter("roomNumberMax")); } catch(Exception e){}
      int gardenSurfaceMin = -1;
      try { gardenSurfaceMin = Integer.parseInt(request.getParameter("gardenSurfaceMin")); } catch(Exception e){}
      int gardenSurfaceMax = -1;
      try { gardenSurfaceMax = Integer.parseInt(request.getParameter("gardenSurfaceMax")); } catch(Exception e){}

      // Ajoute en attribut la liste des logements compatibles pour un utilisateur
      request.setAttribute(
        "housings", HousingHandler.getDb().listCompatibleForUser(
          user, zipCode, surfaceMin, surfaceMax, roomNumberMin,
          roomNumberMax, gardenSurfaceMin, gardenSurfaceMax
        )
      );

      request.setAttribute("zipCode", zipCode); // On met à null pour la vue si -1
      request.setAttribute("surfaceMin", surfaceMin != -1 ? surfaceMin : null);
      request.setAttribute("surfaceMax", surfaceMax != -1 ? surfaceMax : null);
      request.setAttribute("roomNumberMin", roomNumberMin != -1 ? roomNumberMin : null);
      request.setAttribute("roomNumberMax", roomNumberMax != -1 ? roomNumberMax : null);
      request.setAttribute("gardenSurfaceMin", gardenSurfaceMin != -1 ? gardenSurfaceMin : null);
      request.setAttribute("gardenSurfaceMax", gardenSurfaceMax != -1 ? gardenSurfaceMax : null);

      //sending informations
      this.getServletContext().getRequestDispatcher("/user/home/offers.jsp").forward(request, response);
    }
}