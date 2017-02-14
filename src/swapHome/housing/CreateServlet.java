package swapHome.housing;

import housing.db.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import policies.Auth;
import services.Upload;
import users.db.User;
import users.db.UserHandler;
import utils.*;


/**
 * Servlet which is using to create housing
 * @author Logan Lepage & Alexandre DUCREUX
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB
                 maxFileSize=1024*1024*50,          // 50 MB
                 maxRequestSize=1024*1024*100,      // 100 MB
                 location="/")
public class CreateServlet extends HttpServlet
{

    public static final int BUFFER = 10240;
    public static final String SAVE_PATH = "housingImages";
    
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
        if(!Auth.isAuthenticated(request))
            { response.sendRedirect("../user/auth"); return; }

        //sending informations
        List countries =  Utils.getCountriesList();
        List months = Utils.getMonthList();
        request.setAttribute("months", months);
        request.setAttribute("countries", countries);
        this.getServletContext().getRequestDispatcher(
          request.getParameter("type") != null && request.getParameter("type").equals("house")
          ? "/housing/createHouse.jsp"
          : "/housing/createApartment.jsp"
        ).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        User userSession = Auth.getAuthenticated(request);
        if(userSession == null) response.sendRedirect("../user/auth");

        User user = UserHandler.getDb().retrieve(userSession.getEmailUser());
        String address = request.getParameter("address");
        String zipCode = request.getParameter("zipCode");
        String city = request.getParameter("city");
        String countryP1 = request.getParameter("countryP1");
        String countryP2 = request.getParameter("countryP2");
        String description = request.getParameter("description");
        int surface = Integer.parseInt(request.getParameter("surface"));
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        int monthPrefered = Integer.parseInt(request.getParameter("monthPrefered"));
        Utils u = new Utils();
        if(!u.isValid(monthPrefered)){
            request.setAttribute("erreur", "Merci de sélectionner un mois valide !");
            this.getServletContext().getRequestDispatcher("/user/housing/createHouse.jsp").forward(request, response);
        }
        
        // Ajout dans la DB
        Housing housing;
        if(request.getParameter("type").equals("house")) {
          int gardenSurface = Integer.parseInt(request.getParameter("gardenSurface"));
            housing = new House(user, address, zipCode, city, countryP1,
                countryP2, description, surface, roomNumber, monthPrefered, gardenSurface );
        } else {
            housing = new Apartment(user, address, zipCode, city, countryP1,
                countryP2, description, surface, roomNumber, monthPrefered );
        }
        
        /**
         * Initialise le répertoire d'upload, 
         * prépare et importe les images de l'input multiple file[].
         */
        File fileSaveDir = Upload.initDirectory(request, SAVE_PATH);
        List<Part> fileParts = Upload.getParts(request, "file[]");
        HashSet<HousingImage> housingImages = new HashSet<>();
        for (Part part : fileParts) {
            if(Upload.isImage(part)) { // si ce n'est pas une image, on n'ajoute pas
                String fileName = Upload.extractFileName(part);
                fileName = String.valueOf(System.currentTimeMillis())
                    + userSession.getEmailUser()
                    + new File(fileName).getName(); // refines the fileName in case it is an absolute path
                String encodedFileName = Upload.encode(fileName)
                    + "." + Upload.getImageFormat(part);
                housingImages.add(new HousingImage(SAVE_PATH+File.separator+encodedFileName, housing));
                Upload.importFile(part, fileSaveDir, encodedFileName, BUFFER);
            }
        }
        housing.setImages(housingImages);

        log("Enregistrement de " + housing);
        HousingHandler.getDb().create(housing);
        response.sendRedirect("../user/home/housing");
    }
}
