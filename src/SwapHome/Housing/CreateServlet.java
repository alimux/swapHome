package SwapHome.Housing;

import housing.db.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
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

    /**
     * calling servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public static final int BUFFER = 10240;
    public static final String SAVE_PATH = "housingImages";
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
         // retrieve session information
        HttpSession session = request.getSession();
        String emailUser = (String) session.getAttribute( "emailUser" );
        String passwordUser = (String) session.getAttribute( "passwordUser" );

        // test si l'utilisateur est connecté
        if(!(emailUser != null && passwordUser != null && UserHandler.getDb().isValid(emailUser, passwordUser))) {
            response.sendRedirect("../user/auth");
            return;
        }

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
            throws ServletException, IOException {

        // retrieve session information
        HttpSession session = request.getSession();
        String emailUser = (String) session.getAttribute( "emailUser" );
        String passwordUser = (String) session.getAttribute( "passwordUser" );

        // test si l'utilisateur est connecté
        if(!(emailUser != null && passwordUser != null && UserHandler.getDb().isValid(emailUser, passwordUser))) {
            response.sendRedirect("../user/auth");
            return;
        }
        //Images files form setting
        // gets absolute path of the web application
        //File uploads = new File(SAVE_PATH);
        // constructs path of the directory to save uploaded file
        //String savePath = appPath + File.separator + SAVE_PATH;
        
        // creates the save directory if it does not exists
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + SAVE_PATH + File.separator ;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        System.out.println("upload directory : "+fileSaveDir.getAbsolutePath());
        //list of the differents images
        List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
        InputStream inputStream = null;
        OutputStream outputStream = null;
        for (Part part : fileParts) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            log("Nom du fichier extrait : "+fileName+" repertoire : "+fileSaveDir);
            System.out.println("Nom du fichier extrait : "+fileName +" repertoire : "+fileSaveDir+File.separator+fileName);
            //part.write(uploadFilePath+ File.separator + fileName);
            try{
            inputStream = part.getInputStream();
            outputStream = new FileOutputStream(fileSaveDir+File.separator+fileName);
            int read = 0;
            final byte[] bytes = new byte[BUFFER];
            while((read = inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0, read);
            }
            }catch(Exception e){
                e.toString();
                fileName="";
            }finally{
                if(outputStream!=null){
                    outputStream.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
            }
        }
        request.setAttribute("upload", "upload ok!");
        
        
        User user = UserHandler.getDb().retrieve(emailUser);
        String address = request.getParameter("address");
        String zipCode = request.getParameter("zipCode");
        String city = request.getParameter("city");
        String countryP1 = request.getParameter("countryP1");
        String countryP2 = request.getParameter("countryP2");
        String description = request.getParameter("description");
        int surface = Integer.parseInt(request.getParameter("surface"));
        log("Erreur parsing : "+surface);
        System.out.println("Erreur parsing "+surface);
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        int monthPrefered = Integer.parseInt(request.getParameter("monthPrefered"));
        System.out.println("Parsing month"+monthPrefered);
        Utils u = new Utils();
        if(!u.isValid(monthPrefered)){
            String error = "Merci de sélectionner un mois valide !";
            request.setAttribute("erreur", error);
            this.getServletContext().getRequestDispatcher("/user/housing/createHouse.jsp").forward(request, response);
            
        }

        // recording in DB with hibernate
        Housing housing;
        if(request.getParameter("type").equals("house")) {
          int gardenSurface = Integer.parseInt(request.getParameter("gardenSurface"));
            housing = new House(user, address, zipCode, city, countryP1,
                countryP2, description, surface, roomNumber, monthPrefered, gardenSurface );
        } else {
            housing = new Apartment(user, address, zipCode, city, countryP1,
                countryP2, description, surface, roomNumber, monthPrefered );
        }

        this.log("Enregistrement de " + housing);
        HousingHandler.getDb().create(housing);

        //setting session
        response.sendRedirect("../user/home/housing");
    }
    
   /**
     * Method which extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return null;
    }
  

}
