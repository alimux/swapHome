
package SwapHome.User;

/**
 *
 * @author Alexandre DUCREUX & Logan Lepage 2017
 * Class which disconnect user from his session
 */
import users.db.*;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;

public class DisconnectServlet extends HttpServlet{
    
    private static final String DISCONNECT = "/index.jsp";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        
        this.getServletContext().getRequestDispatcher(DISCONNECT).forward(request, response);
    }
    
    
}
