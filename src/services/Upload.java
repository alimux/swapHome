package services;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * @author Alexandre DUCREUX & Logan LEPAGE
 * Class which manage images upload
 */
public class Upload {
    
    /**
     * Creates the save directory if it does not exists
     * @param request
     * @param savePath 
     */
    public static File initDirectory(HttpServletRequest request, String savePath) {
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + savePath + File.separator ;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
            System.out.println("upload directory created : "+fileSaveDir.getAbsolutePath());
        }
        return fileSaveDir;
    }
    /**
     * list of upload files
     * @param request
     * @param name
     * @return
     * @throws IOException
     * @throws ServletException 
     */
    public static List<Part> getParts(HttpServletRequest request, String name) 
    throws IOException, ServletException {
        return request.getParts().stream().filter(part -> name.equals(part.getName())).collect(Collectors.toList());
    } 
    
    /**
     * Import a form input file
     * @param part
     * @param fileSaveDir
     * @param fileName
     * @param buffer
     * @throws IOException 
     */
    public static void importFile(Part part, File fileSaveDir, String fileName, int buffer) 
    throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = part.getInputStream();
            outputStream = new FileOutputStream(fileSaveDir+java.io.File.separator+fileName);
            int read = 0;
            final byte[] bytes = new byte[buffer];
            while((read = inputStream.read(bytes))!=-1) {
                outputStream.write(bytes,0, read);
            }
        } catch(Exception e) {
            e.toString();
            fileName="";
        } finally {
            if(outputStream!=null) outputStream.close();
            if(inputStream!=null) inputStream.close();
            System.out.println("Fichier "+fileName +" ajouté au repertoire : "+fileSaveDir+File.separator+fileName);
        }
    }
    /**
     * Method which remove an image
     * @param fileSaveDir
     * @param fileName
     * @throws IOException 
     */
    public static void removeFile(File fileSaveDir, String fileName) 
    throws IOException {
        try{
            File file = new File(fileSaveDir+fileName);
            if(file.delete()){
                System.out.println(file.getName() + " est supprimé!");
                    
            } else {
                System.out.println("L'opération de suppression à échouée.");
            }
    	} catch(Exception e) {
            e.printStackTrace();
    	}
    }
    
    /**
     * Encode Base64
     * @param str
     * @return 
     */
    public static String encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * Extracts file name from HTTP header content-disposition
     * @param part
     * @return 
     */
    public static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return null;
    }
    /**
     * Check type of file jpeg, png
     * @param part
     * @return boolean
     */
    public static boolean isImage(Part part) {
        return part.getContentType().equals("image/png") || part.getContentType().equals("image/jpeg");
    }
    /**
     * retrieve image format
     * @param part
     * @return 
     */
    public static String getImageFormat(Part part) {
        return part.getContentType().split("/")[1];
    }
}
