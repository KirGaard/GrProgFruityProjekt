package Database;

import DataParser.JSONParser;
import User.User;

import java.util.List;

public class UserDatabase {
    private List<User> users;

    public UserDatabase(){
        JSONParser.getUsers();
    }

    public List<User> getUsers(){
        return users;
    }

    public void saveDatabase(){
        users.forEach(user -> JSONParser.saveUser(user));
    }


}
