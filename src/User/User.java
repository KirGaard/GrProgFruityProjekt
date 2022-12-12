package User;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String password;
    private List<String> favoriteTitles;

    public User(String name, String password) {
        this.name = name.toLowerCase();
        this.password = password;
    }

    public void addFavorite(String favorite) {
        if (favoriteTitles == null) favoriteTitles = new ArrayList<>();
        favoriteTitles.add(favorite);
    }

    public void getFavorites() {
        for (String favorite : favoriteTitles) {
            System.out.println(favorite);
        }
    }

    public void removeFavorite (String favorite) {
        if (favoriteTitles == null) favoriteTitles = new ArrayList<>();
        favoriteTitles.remove(favorite);
    }

    public void changeUsername (String name) {
        this.name = name;
    }

    public void changePassword (String password) {
        this.password = password;
    }

    public String getName () {
        return name;
    }

    public String getPassword () {
        return password;
    }

    public List<String> getFavoriteTitles () {
        if (favoriteTitles == null) favoriteTitles = new ArrayList<>();
        return favoriteTitles;
    }
}

