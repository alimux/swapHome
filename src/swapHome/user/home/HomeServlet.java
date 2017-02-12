package swapHome.user.home;

import users.db.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import policies.Auth;
/**
*Class which manage the main page of account of swapHome project
*@author Alexandre DUCREUX & Logan Lepage
*@4 novembre 2016
*/

public class HomeServlet extends HttpServlet
  {
    //stub
    //public static PersonDBStub dataBase = new PersonDBStub();
    //real database
    public static IUserDB dataBase = new UserDBStub();
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
        response.sendRedirect(
            Auth.isAuthenticated(request) 
            ? "home/info" 
            : "../user/auth"
        );
    }
}
