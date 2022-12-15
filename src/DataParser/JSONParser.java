package DataParser;

import Media.Media;
import Media.Film;
import Media.Show;
import User.User;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Kasper
 * The following class deals with the responsibilities of the JSON-parser
 */
public class JSONParser {

    /**
     * Instantiate and initialize the path for the .json file for films
     */
    private static final String filmPath = "Data/parsedFilm.json";

    /**
     * Instantiate and initialize the path for the .json file for shows
     */

    private static final String showPath = "Data/parsedSeries.json";

    /**
     * Instantiate and initialize the path for the users
     */

    private static final String usersPath = "Data/Users/";

    /**
     * Class used for the deserialization of the film .json file
     */
    private class FilmStorer{
        List<Film> list;
    }

    /**
     * Class used for the deserialization of the show .json file
     */
    private class ShowStorer{
        List<Show> list;
    }


    /**
     * The following method loads the list of films by deserializing the .json file into a FilmStorer
     * @return the list of all films as media from the FilmStorer
     */
    public static List<Media> loadFilms(){
        Gson parser = new Gson();
        String json = new FileHandler(filmPath).readFile();

        ArrayList media = new ArrayList<Media>();
        media.addAll(parser.fromJson(json, FilmStorer.class).list);

        return media;
    }

    /**
     * The following method loads the list of shows by deserializing the .json file into a ShowStorer
     * @return the list of all films as media from the Showtorer
     */
    public static List<Media> loadShows(){
        Gson parser = new Gson();
        String json = new FileHandler(showPath).readFile();

        ArrayList media = new ArrayList<Media>();
        media.addAll(parser.fromJson(json, ShowStorer.class).list);

        return media;
    }

    /**
     * The following methods loads users by using a Gson parser to deserialize the .json files to User instances
     * The function also creates a new folder if one is missing.
     * @return list of users
     */
    public static List<User> loadUsers(){
        File userFolder = new File(usersPath);
        File[] listOfUserFiles = userFolder.listFiles();
        if (listOfUserFiles == null){
            userFolder.mkdirs();
        }
        listOfUserFiles = userFolder.listFiles();

        Gson parser = new Gson();

        List users = new ArrayList<User>();

        for (File file : listOfUserFiles){
                FileHandler fileHandler = new FileHandler(usersPath + file.getName());
                users.add(parser.fromJson(fileHandler.readFile(), User.class));
        }

        return users;
    }

    /**
     * The following method saves a user by serializing the user object to a .json file
     * @param user the user that needs to be saved
     */
    public static void saveUser(User user){
        String userPath = usersPath + user.getName() + ".json";
        Gson parser = new Gson();

        String json = parser.toJson(user);
        new FileHandler(userPath).writeFile(json);
    }

    /**
     * The following method deletes a user file
     * Throws exception if user doesn't exist
     * @param userName the user that needs to be deleted
     */
    public static void deleteUser(String userName){
        String path = usersPath + userName + ".json";
        File userFile = new File(path);

        if(!userFile.delete()) throw new RuntimeException("Failed to delete user: " + userName);
    }
}
