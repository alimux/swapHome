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
        if(userSession == null) { response.sendRedirect("../auth"); return; }
        
        //sending informations
        User user = UserHandler.getDb().retrieve(userSession.getEmailUser());
        String message = user.getFirstNameUser()+" "+user.getNameUser();
        request.setAttribute("message", message);
        this.getServletContext().getRequestDispatcher("/user/home/info.jsp").forward(request, response);
    }
}
