package sample;

import sample.Models.DAO.UserDAO;
import sample.Models.User;

public final class Manage {

    public static User statci_user  = null;
    public static String static_search = "";
    public static String static_word = "";

    UserDAO userDAO = new UserDAO(MySQL.getConnection());


}
