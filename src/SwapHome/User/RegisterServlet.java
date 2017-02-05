package SwapHome.User;

import users.db.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Class which manage registering
 *
 * @author Alexandre DUCREUX & Logan Lepage
 * @4 novembre 2016
 */
public class RegisterServlet extends HttpServlet {

    //UserStubDB
    //public static IUserDB UserHandler.getDb() = new UserDBStub();
    //HibernateSql
    /**
     * calling servlet
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/user/register.jsp");
        rd.forward(request, response);
    }

    /**
     * retrieve form informations
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Retrieve form info
        String nameUser = request.getParameter("nameUser");
        String firstNameUser = request.getParameter("firstNameUser");
        String adressUser = request.getParameter("adressUser");
        String tmpzipCodeUser = request.getParameter("zipCodeUser");
        int zipCodeUser = Integer.parseInt(tmpzipCodeUser);
        String cityUser = request.getParameter("cityUser");
        String emailUser = request.getParameter("emailUser");
        String countryUser = request.getParameter("countryUser");
        String passwordUser = request.getParameter("passwdUser");
        String pathUrl;

        //if not exist back on previous page
        if (UserHandler.getDb().exists(emailUser)) {
            String error = "Impossible de créer le compte utilisateur... Email déjà présent!";
            request.setAttribute("erreur", error);
            pathUrl = "/user/register.jsp";
            //sending error
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher(pathUrl);
            rd.forward(request, response);

        } else {
            // recording in DB with hibernate
            User user = new User(nameUser, firstNameUser, adressUser, zipCodeUser, cityUser, emailUser, passwordUser, countryUser);
            this.log("Enregistrement de " + user);
            UserHandler.getDb().createUser(user);
            String bienvenue = firstNameUser + " " + nameUser;
            //setting attributes
            request.setAttribute("bienvenue", bienvenue);
            //setting session
            HttpSession session = request.getSession();
            session.setAttribute("emailUser", emailUser);
            session.setAttribute("passwordUser", passwordUser);
            response.sendRedirect("home");

        }

    }
}
