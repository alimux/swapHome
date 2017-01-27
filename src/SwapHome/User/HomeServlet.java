package SwapHome.User;


/*----------------------------------
class de gestion des inscriptions
----------------------------------*/

//importation
import Users.UserDBStub;
import Users.User;
import Users.IUserDB;
import Users.UserHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

//temporaire, Uniquement pour simuler base de données
import javax.servlet.http.HttpSession;
/**
Class de gestion des inscriptions pour le projet SwapHome
@author Alexandre DUCREUX
@4 novembre 2016
*/

public class HomeServlet extends HttpServlet
  {
    //temporaire pour la fausse DB
    //public static PersonDBStub dataBase = new PersonDBStub();
    public static IUserDB dataBase = new UserDBStub();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        /* récupération de la session */
        HttpSession session = request.getSession();
        String emailUser = (String) session.getAttribute( "emailUser" );
        String passwordUser = (String) session.getAttribute( "passwordUser" );

        // test si l'utilisateur est connecté
        if(!(emailUser != null && passwordUser != null && UserHandler.getDb().isValid(emailUser, passwordUser))) {
            response.sendRedirect("auth.jsp");
            return;
        }
      
        User user = UserHandler.getDb().retrieve(emailUser);
        String message = user.getFirstNameUser()+" "+user.getNameUser();
        request.setAttribute("message", message);

        //envoi des infos
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/user/home.jsp");
        rd.forward(request, response);
    }
}
