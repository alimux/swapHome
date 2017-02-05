
package SwapHome.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;

/**
 * @author Alexandre DUCREUX & Logan Lepage 2017
 * Class which disconnect user from his session
 */

public class DisconnectServlet extends HttpServlet{
    
    private static final String DISCONNECT = "/index.jsp";
    /**
     * calling servlet, destroy session
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        
        this.getServletContext().getRequestDispatcher(DISCONNECT).forward(request, response);
    }
    
    
}
