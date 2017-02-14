package swapHome.housing;

import housing.db.*;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import policies.Auth;
import services.Upload;
import static swapHome.housing.CreateServlet.BUFFER;
import static swapHome.housing.CreateServlet.SAVE_PATH;
import users.db.User;
import users.db.UserHandler;
import utils.Utils;

/**
 * Classe permettant de créer des logements
 * @author Logan Lepage & Alexandre DUCREUX
 */
 @MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB
                  maxFileSize=1024*1024*50,          // 50 MB
                  maxRequestSize=1024*1024*100,      // 100 MB
                  location="/")
public class EditServlet extends HttpServlet 
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
        //droit à tous les administrateur et à l'utilisateur lié
        User userSession = Auth.getAuthenticated(request);
        if(userSession == null) { response.sendRedirect("../user/auth"); return; }
        User u = UserHandler.getDb().retrieve(userSession.getEmailUser());
        if(request.getParameter("id") == null) {
            response.sendRedirect("../user/home/housing"); return;
        }
        Housing h = HousingHandler.getDb().retrieve(Integer.parseInt(request.getParameter("id")));
        if(h == null || ((u.isAdminUser() == null || !u.isAdminUser()) 
            && !h.getUser().getEmailUser().equals(u.getEmailUser()))) {
            response.sendRedirect("../user/home/housing"); return;
        }

        //sending informations
        List countries =  Utils.getCountriesList();
        List months = Utils.getMonthList();
        request.setAttribute("months", months);
        request.setAttribute("countries", countries);
        request.setAttribute("housing", h);
        this.getServletContext().getRequestDispatcher(
                h.getClass() == Apartment.class
                ? "/housing/editApartment.jsp"
                : "/housing/editHouse.jsp"
        ).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        //droit à tous les administrateur et à l'utilisateur lié
        User userSession = Auth.getAuthenticated(request);
        if(userSession == null) { response.sendRedirect("../user/auth"); return; }
        User u = UserHandler.getDb().retrieve(userSession.getEmailUser());
        if(request.getParameter("id") == null) {
            response.sendRedirect("../user/home/housing"); return;
        }
        Housing h = HousingHandler.getDb().retrieve(Integer.parseInt(request.getParameter("id")));
        if(h == null || ((u.isAdminUser() == null || !u.isAdminUser()) 
            && !h.getUser().getEmailUser().equals(u.getEmailUser()))) {
            response.sendRedirect("../user/home/housing"); return;
        }

        //changement des paramètres
        h.setAddress(       request.getParameter("address"));
        h.setZipCode(       request.getParameter("zipCode"));
        h.setCity(          request.getParameter("city"));
        h.setCountryP1(     request.getParameter("countryP1"));
        h.setCountryP2(     request.getParameter("countryP2"));
        h.setDescription(   request.getParameter("description"));
        h.setSurface(       Integer.parseInt(request.getParameter("surface")));
        h.setRoomNumber(    Integer.parseInt(request.getParameter("roomNumber")));
        h.setMonthPrefered( Integer.parseInt(request.getParameter("monthPrefered")));
        if(request.getParameter("type").equals("house")) {
            ((House) h).setGardenSurface( Integer.parseInt(request.getParameter("gardenSurface")));
        }
        
        /**
         * Initialise le répertoire d'upload, 
         * prépare et importe les images de l'input multiple file[].
         */
        File fileSaveDir = Upload.initDirectory(request, SAVE_PATH);
        List<Part> fileParts = Upload.getParts(request, "file[]");
        HashSet<HousingImage> housingImages = new HashSet<>();
        if(!fileParts.isEmpty()) { // si on veut changer nos images
            boolean isEmpty = true;
                    
            //et on ajoute les nouvelles
            for (Part part : fileParts) {
                if(Upload.isImage(part)) { //si ce n'est pas une image, on n'ajoute pas
                    if(isEmpty) { //au premier passage, on supprime les autres images
                        isEmpty = false;
                        //alors on supprime les anciennes images
                        for(HousingImage image : h.getImages()) {
                            Upload.removeFile(fileSaveDir, File.separator+new File("../"+image.getName()).getName());
                        }
                        HousingHandler.getDb().deleteImages(h);
                    }
                    
                    String fileName = Upload.extractFileName(part);
                    fileName = String.valueOf(System.currentTimeMillis())
                        + userSession.getEmailUser()
                        + new File(fileName).getName(); //refines the fileName in case it is an absolute path
                    String encodedFileName = Upload.encode(fileName)
                        + "." + Upload.getImageFormat(part);
                    housingImages.add(new HousingImage(SAVE_PATH+File.separator+encodedFileName, h));
                    Upload.importFile(part, fileSaveDir, encodedFileName, BUFFER);
                }
            }
            if(!isEmpty)
                h.setImages(housingImages);
        }
        log("Modification de " + h);
        HousingHandler.getDb().update(h);
        
        //setting session
        response.sendRedirect(
            (u.isAdminUser() == null || !u.isAdminUser())
            ? "../user/home/housing"
            : "../user/admin/housing"
        );
    }

}
