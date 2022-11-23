package Database;

import DataParser.JSONParser;
import User.User;

import java.util.Comparator;
import java.util.List;

public class UserDatabase {
    private List<User> users;

    public UserDatabase(){
        users = JSONParser.getUsers();
    }

    public List<User> getUsers(){
        return users;
    }
    public boolean ContainsUser(String name){
        boolean found = false;

        for (User user : users) if (user.getName().equals(name)) found = true;

        return found;
    }
    public void addUser(User user){
        users.add(user);
        saveDatabase();
    }

    public void saveDatabase(){
        users.forEach(user -> JSONParser.saveUser(user));
    }
    public void deleteUser(User user) throws UserNotInDatabaseException{
        if (!ContainsUser(user.getName())) throw new UserNotInDatabaseException(user);
        JSONParser.deleteUser(user.getName());
        users.remove(user);
    }

}
