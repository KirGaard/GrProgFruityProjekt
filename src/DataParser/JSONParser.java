package DataParser;

import Media.Media;
import Media.Film;
import Media.Show;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    private static final String filmPath = "Data\\parsedFilm.json";
    private static final String showPath = "Data\\parsedSeries.json";

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

    public static List<Media> getShow(){
        Gson parser = new Gson();
        String json = new FileHandler(showPath).readFile();

        ArrayList media = new ArrayList<Media>();
        media.addAll(parser.fromJson(json, ShowStorer.class).list);

        return media;
    }






}
