package policies;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import users.db.User;
import users.db.UserHandler;

public class Auth {
    
    public static boolean isAuthenticated(HttpServletRequest request) {
        return getAuthenticated(request) != null;
    }
    
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