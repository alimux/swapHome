package SwapHome.User;


//importation
import users.db.*;
import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.*;


import javax.servlet.http.HttpSession;
/**
*Class which manage registring of swapHome project
*@author Alexandre DUCREUX & Logan Lepage
*@4 novembre 2016
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
