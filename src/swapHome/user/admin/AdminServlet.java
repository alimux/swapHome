package swapHome.user.admin;

import swapHome.user.home.*;
import users.db.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import policies.Auth;

/**
 * Class which manage the main page of account of swapHome project
 * @author Alexandre DUCREUX & Logan Lepage
 */
public class AdminServlet extends HttpServlet
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
            Auth.isAuthenticatedAdmin(request) ? "admin/info" : "auth"
        );
    }
}
