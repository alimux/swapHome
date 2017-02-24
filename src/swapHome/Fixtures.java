package swapHome;

import java.util.*;
import users.db.*;
import housing.db.*;
import housingExchange.db.*;
import java.io.*;
import services.*;
import static swapHome.housing.CreateServlet.SAVE_PATH;

/**
 * @author Logan LEPAGE et Alexandre DUCREUX
 * Test class
 */
public class Fixtures {

    private final static String PASSWORD = "123456";
    private final static boolean HOUSE = true;
    private final static boolean APARTMENT = false;

   /**
    * Créer et initialiser les tables
    */
    public static void main(String[] args) {
        User user = new User("admin@mail.com", PASSWORD);
        if(!UserHandler.getDb().exists(user.getEmailUser()))
            UserHandler.getDb().createUser(user);
        User userPopulated = UserHandler.getDb().retrieve(user.getEmailUser());
        userPopulated.setAdminUser(true);
        UserHandler.getDb().update(userPopulated);
    
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader("logements.csv"));
            while ((line = br.readLine()) != null) {
                String cvsSplitBy = ";";
                String[] cols = line.split(cvsSplitBy);
                create(Integer.parseInt(cols[0]), cols[1], cols[2], cols[3], cols[4], cols[5],
                cols[6], cols[7], Integer.parseInt(cols[8]), Integer.parseInt(cols[9]),
                Integer.parseInt(cols[10]), Integer.parseInt(cols[11]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void create(int type, String mail, String address, String zipCode,
        String city, String p1, String p2, String description, int surface,
        int roomNumber, int monthPrefered, int gardenSurface) {

        User user = new User(mail, PASSWORD);
        if(!UserHandler.getDb().exists(user.getEmailUser())) {
            UserHandler.getDb().createUser(user);
        }

        Housing housing;
        if(type == 0) housing = new House( user, address, zipCode, city, p1, p2,
            description, surface, roomNumber, monthPrefered, gardenSurface);
        else housing = new Apartment( user, address, zipCode, city, p1, p2,
            description, surface, roomNumber, monthPrefered);

        HousingHandler.getDb().create(housing);
        HousingExchangeHandler.getDb().create(new HousingExchange(
            housing, user, new Date(), new Date()
        ));
    }
}
