package SwapHome.User;

/*----------------------------------
class de gestion des connexions
----------------------------------*/

//importation
import SwapHome.User.UserServlet.*;
import users.db.UserHandler;
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
Class de gestion des connexions à l'espace personnel pour le projet SwapHome
@author Logan LEPAGE
@25 novembre 2016
*/

public class UserServlet extends HttpServlet
{    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        /* Création ou récupération de la session */
        HttpSession session = request.getSession();
        String emailUser = (String) session.getAttribute( "emailUser" );
        String passwordUser = (String) session.getAttribute( "passwordUser" );
        String pathUrl;

        // test si l'utilisateur est connecté, et qu'il existe en base de donnée
        if(emailUser != null && passwordUser != null && UserHandler.getDb().isValid(emailUser, passwordUser))
            pathUrl = "user/home";
        else
            pathUrl = "user/auth";       

        response.sendRedirect(pathUrl);
    }
}
