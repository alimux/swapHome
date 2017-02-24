package policies;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import users.db.User;
import users.db.UserHandler;
/**
 * Servlet using for auth
 * @author Alexandre DUCREUX & Logan Lepage
 */
public class Auth {
    /**
     * check if user is Admin
     * @param request
     * @return boolean
     */
    public static boolean isAuthenticatedAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return getAuthenticated(request) != null && 
            (Boolean) session.getAttribute( "isAdminUser" );
    }
    /**
     * check is user is authenticated
     * @param request
     * @return boolean
     */
    public static boolean isAuthenticated(HttpServletRequest request) {
        return getAuthenticated(request) != null;
    }
    /**
     * retrieve session info & retrieve User and instance it
     * @param request
     * @return 
     */
    public static User getAuthenticated(HttpServletRequest request) {
         // récupère les informations de session
        HttpSession session = request.getSession();
        String emailUser = (String) session.getAttribute( "emailUser" );
        String passwordUser = (String) session.getAttribute( "passwordUser" );

        // test si l'utilisateur est connecté
        return  (emailUser != null 
            && passwordUser != null
            && UserHandler.getDb().isValid(emailUser, passwordUser)) 
        ? new User(emailUser, passwordUser) : null;
    }
    
}