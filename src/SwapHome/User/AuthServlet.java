package SwapHome.User;


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
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import users.db.*;


/**
Class de gestion des connexions à l'espace personnel pour le projet SwapHome
@author Alexandre DUCREUX & Logan Lepage
@4 novembre 2016
*/

public class AuthServlet extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/user/auth.jsp");
        rd.forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        String emailUser = request.getParameter("emailUser");
        String passwordUser = request.getParameter("passwordUser");
        

        // test si le formulaire est valide
        if(!UserHandler.getDb().isValid(emailUser, passwordUser))
        {
            String error = "Erreur, Identifiant / mot de passe incorrect !";
            request.setAttribute("erreur", error);   
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/user/auth.jsp");
            rd.forward(request, response);
            return;
        }
        User user = new User(emailUser, passwordUser);
        //recupération des infos de l'utilisateur courant
        user = UserHandler.getDb().retrieve(emailUser);
            
        
        
        
        // si OK, on met en session et on affiche home
        HttpSession session = request.getSession();
        session.setAttribute( "emailUser", emailUser );
        session.setAttribute( "passwordUser", passwordUser );
        response.sendRedirect("home");
    }
}
