package SwapHome;
/*----------------------------------
class de gestion des connexions
----------------------------------*/

//importation
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

//temporaire, Uniquement pour simuler base de données
import fakeDB.*;

/**
Class de gestion des connexions à l'espace personnel pour le projet SwapHome
@author Alexandre DUCREUX
@4 novembre 2016
*/

public class Connect extends HttpServlet
  {
    //temporaire pour la fausse DB

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
      {
          //récupération des données formulaire
          String emailUser = request.getParameter("emailUser");
          String passwordUser = request.getParameter("passwordUser");
          String pathUrl;

          //test si les valeurs correspondent
          // A modifier pour enregistrer des valeurs de sessions
          if(Inscription.dataBase.isValid(emailUser, passwordUser))
          {
            User user = Inscription.dataBase.retrieve(emailUser);
            String message = user.getFirstNameUser()+" "+user.getNameUser();
            request.setAttribute("message", message);
            pathUrl = "/espacePerso.jsp";
          }
          else
          {
            String error = "Erreur, Identifiant / mot de passe incorrect !";
            request.setAttribute("erreur", error);
            pathUrl = "/connect.jsp";
          }

          //envoi des infos et redirection
          RequestDispatcher rd = this.getServletContext().getRequestDispatcher(pathUrl);
          rd.forward(request, response);

      }
  }
