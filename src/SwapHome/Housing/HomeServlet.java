package SwapHome.Housing;

import users.db.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;

/**
 * Classe permettant de lister des logements
 * @author Logan Lepage & Alexandre DUCREUX
 */
public class HomeServlet extends HttpServlet
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

        //sending informations
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/housing/home.jsp");
        rd.forward(request, response);
    }
}
