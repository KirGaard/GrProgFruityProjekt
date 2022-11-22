package User;

import java.util.List;

public class User {
    private String name;
    private String password;
    private List<String> favoriteTitles;

    public void addFavorite(String favorite) {
        favoriteTitles.add(favorite);
    }

    public void getFavorites() {
        for (String favorite : favoriteTitles) {
            System.out.println(favorite);
        }
    }

    public void removeFavorite (String favorite) {
        favoriteTitles.remove(favorite);
    }

    public void changeUsername (String name) {
        this.name = name;
    }

    public void changePassword (String password) {
        this.password = password;
    }
}

