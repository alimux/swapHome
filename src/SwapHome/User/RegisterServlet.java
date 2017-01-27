package SwapHome.User;


/*----------------------------------
class de gestion des inscriptions
----------------------------------*/

//importation
import Users.UserDBStub;
import Users.User;
import Users.IUserDB;
import Users.UserHandler;
import Users.UserHibernateSQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import org.hibernate.SessionFactory;

//temporaire, Uniquement pour simuler base de données
/**
Class de gestion des inscriptions pour le projet SwapHome
@author Alexandre DUCREUX
@4 novembre 2016
*/

public class RegisterServlet extends HttpServlet
  {
    //UserStubDB
    //public static IUserDB UserHandler.getDb() = new UserDBStub();
    //HibernateSql
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/user/register.jsp");
        rd.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
      //Récupération des données du formulaire
      //int idUser = UserHandler.getDb().getNextID();
      String nameUser = request.getParameter("nameUser");
      String firstNameUser = request.getParameter("firstNameUser");
      String adressUser = request.getParameter("adressUser");
      String tmpzipCodeUser = request.getParameter("zipCodeUser");
      int zipCodeUser = Integer.parseInt(tmpzipCodeUser);
      String cityUser = request.getParameter("cityUser");
      String emailUser = request.getParameter("emailUser");
      String countryUser = request.getParameter("countryUser");
      //mettre en place un systeme de gestion de création de mot de passe aléatoire
      String passwordUser = "mdp"; 
      String pathUrl;

      //a voir implementer un algo de génération de mot de passe

      /*temp creation d'utilisateur pour la fausse dB
      A la place de forward, a voir pour remplacer par des sessions & penser à ajouter les attributs à renvoyer dans le formulaire en cas d'email déjà présent
      Le mot de passe est temporaire */
      if(UserHandler.getDb().exists(emailUser))
        {
          String error = "Impossible de créer le compte utilisateur, Email déjà présent!";
          request.setAttribute("erreur", error);
          pathUrl = "/user/register.jsp";

        }
        else
        {
          // on enregistre en base
          User user = new User( nameUser, firstNameUser, adressUser, zipCodeUser, cityUser ,emailUser, passwordUser, countryUser);
          UserHandler.getDb().createUser(user);
          String bienvenue = firstNameUser+" "+nameUser;
          //envoi d'attributs
          request.setAttribute("bienvenue", bienvenue);
          pathUrl = "/user/registered.jsp";

        }

        //envoi des infos
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher(pathUrl);
        rd.forward(request, response);
    }
  }
