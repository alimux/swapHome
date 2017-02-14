package swapHome.user.admin;

import swapHome.user.admin.*;
import users.db.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import policies.Auth;

/**
 * Class which manage the main page of account of swapHome project
 * @author Alexandre DUCREUX & Logan Lepage
 */
public class InfoServlet extends HttpServlet
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
        User userSession = Auth.getAuthenticated(request);
        if(userSession == null || !Auth.isAuthenticatedAdmin(request)) 
            { response.sendRedirect("../auth"); return; }
        
        //sending informations
        User user = UserHandler.getDb().retrieve(userSession.getEmailUser());
        String message = user.getFirstNameUser()+" "+user.getNameUser();
        request.setAttribute("message", message);
        this.getServletContext().getRequestDispatcher("/user/admin/info.jsp").forward(request, response);
    }
}
