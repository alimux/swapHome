package SwapHome.User;

import users.db.UserHandler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

/**
*Class which manage redirection if user is connected
*@author Alexandre Ducreux & Logan LEPAGE
*@25 novembre 2016
*/

public class UserServlet extends HttpServlet
{    
    /**
     * calling servlet if user is connected or not redirect on the good page 
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

        // if user is valid redirect to account else authentication
        response.sendRedirect(
            emailUser != null && passwordUser != null && UserHandler.getDb().isValid(emailUser, passwordUser)
            ? "user/home" : "user/auth"
        );
    }
}
