package swapHome.user;

import users.db.UserHandler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import policies.Auth;

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
        response.sendRedirect(
            Auth.isAuthenticated(request) ? "user/home" : "user/auth"
        );
    }
}
