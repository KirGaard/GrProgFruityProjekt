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
     * Method that instantiates a list with the type Film and used to store films in the list
     */
    private class FilmStorer{
        List<Film> list;
    }

    /**
     * Same as above but with shows
     */
    private class ShowStorer{
        List<Show> list;
    }


    /**
     * The following method loads the list of films by reading the file of films
     * Then parsing the file of films into .json file and adding all the titles of films to an ArrayList media
     * @return list of all films
     */
    public static List<Media> loadFilms(){
        Gson parser = new Gson();
        String json = new FileHandler(filmPath).readFile();

        ArrayList media = new ArrayList<Media>();
        media.addAll(parser.fromJson(json, FilmStorer.class).list);

        return media;
    }

    /**
     * Same as above but with shows
     * @return list of all shows
     */
    public static List<Media> loadShows(){
        Gson parser = new Gson();
        String json = new FileHandler(showPath).readFile();

        ArrayList media = new ArrayList<Media>();
        media.addAll(parser.fromJson(json, ShowStorer.class).list);

        return media;
    }

    // Todo: Hjælp her :/
    /**
     * The following methods loads users by
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
     * The following method saves a user by writing a .json file with the user's information
     * @param user the user that needs to be saved
     */
    public static void saveUser(User user){
        String userPath = usersPath + user.getName() + ".json";
        Gson parser = new Gson();

        String json = parser.toJson(user);
        new FileHandler(userPath).writeFile(json);
    }

    /**
     * The following method deletes a user by replacing the .json file for that user with an empty file
     * Throws exception if user doesn't exist
     * @param userName the user that needs to be deleted
     */
    public static void deleteUser(String userName){
        String path = usersPath + userName + ".json";
        File userFile = new File(path);

        if(!userFile.delete()) throw new RuntimeException("Failed to delete user: " + userName);
    }
}
