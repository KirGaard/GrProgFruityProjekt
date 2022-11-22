package DataParser;

import Media.Media;
import Media.Film;
import Media.Show;
import User.User;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    private static final String filmPath = "Data/parsedFilm.json";
    private static final String showPath = "Data\\parsedSeries.json";

    private static final String usersPath = "Data\\Users";

    private class FilmStorer{
        List<Film> list;
    }

    private class ShowStorer{
        List<Show> list;
    }


    public static List<Media> getFilms(){
        Gson parser = new Gson();
        String json = new FileHandler(filmPath).readFile();

        ArrayList media = new ArrayList<Media>();
        media.addAll(parser.fromJson(json, FilmStorer.class).list);

        return media;
    }

    public static List<Media> getShows(){
        Gson parser = new Gson();
        String json = new FileHandler(showPath).readFile();

        ArrayList media = new ArrayList<Media>();
        media.addAll(parser.fromJson(json, ShowStorer.class).list);

        return media;
    }

    public List<User> getUsers(){
        File userFolder = new File(usersPath);
        File[] listOfUserFiles = userFolder.listFiles();

        List users = new ArrayList<User>();

        for (File file : listOfUserFiles){



        }

        return users;
    }

    public void saveUser(User user){

    }


}
