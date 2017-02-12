package swapHome;

import java.util.*;
import users.db.*;
import housing.db.*;
import housingExchange.db.*;

/**
 * @author Logan LEPAGE et Alexandre DUCREUX
 */
public class Fixtures {

    private final static String PASSWORD = "123456";

   /**
    * Créer et initialiser les tables
    */
    public static void main(String[] args) {
        User user1 = new User("pierre@email.com", PASSWORD);
        UserHandler.getDb().createUser(user1);

        create("House", user1, "31 rue du Maréchal Fosh",
            "83570", "CARCES", "FR", "EN", "Une maison comme une autre",
            7, 350, 3, 600);
        create("House", user1, "Place de la Mairie",
            "83440", "TOURRETTES", "FR", "EN", "Une autre maison comme une autre",
            3, 200, 4, 100);
        create("House", "marine@email.com", "Place du 11 Novembre",
            "83250", "LONDE LES MAURES", "FR", "ES", "Petite maison",
            3, 180, 7, 235);
    }

    /** à utiliser si on veut créer un utilisateur */
    private static void create(String type, String mail, String address, String zipCode,
        String city, String p1, String p2, String description, int surface,
        int roomNumber, int monthPrefered, int gardenSurface) {

        User user = new User(mail, PASSWORD);
        UserHandler.getDb().createUser(user);

        create(type, user, address, zipCode, city, p1, p2, description,
            surface, roomNumber, monthPrefered, gardenSurface);
    }

    /** à utiliser si on veut utiliser un utilisateur */
    private static void create(String type, User user, String address, String zipCode,
        String city, String p1, String p2, String description, int surface,
        int roomNumber, int monthPrefered, int gardenSurface) {

        Housing housing;
        if(type.equals("House")) housing = new House( user, address, zipCode, city, p1, p2,
            description, surface, roomNumber, monthPrefered, gardenSurface);
        else housing = new Apartment( user, address, zipCode, city, p1, p2,
            description, surface, roomNumber, monthPrefered);

        HousingHandler.getDb().create(housing);
        HousingExchangeHandler.getDb().create(new HousingExchange(
            housing, user, new Date(), new Date()
        ));
    }
}
