package SwapHome.User;


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
*Class which manage connection to the app
*@author Alexandre DUCREUX & Logan Lepage
*@4 novembre 2016
*/

public class AuthServlet extends HttpServlet
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
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/user/auth.jsp");
        rd.forward(request, response);
    }
    
    /**
     * managing connection and redirect, record session if authentication is valid 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        String emailUser = request.getParameter("emailUser");
        String passwordUser = request.getParameter("passwordUser");
        
        // validate form test
        if(!UserHandler.getDb().isValid(emailUser, passwordUser))
        {
            String error = "Erreur, Identifiant / mot de passe incorrect !";
            request.setAttribute("erreur", error);   
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/user/auth.jsp");
            rd.forward(request, response);
            return;
        }
        User user = new User(emailUser, passwordUser);
        //retrieving current user
        user = UserHandler.getDb().retrieve(emailUser);
               
        // if ok, set session & redirect
        HttpSession session = request.getSession();
        session.setAttribute( "emailUser", emailUser );
        session.setAttribute( "passwordUser", passwordUser );
        response.sendRedirect("home");
    }
}
