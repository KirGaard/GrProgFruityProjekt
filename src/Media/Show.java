package Media;

import java.util.List;

/**
 * Is used to get the attributes that are specific to shows.
 */
public class Show extends Media {
    /**
     * Stores a shows seasons as a list of strings.
     */
    private List <String> seasons;

    /**
     * Gets all the info from a show.
     * @return all the info as a String.
     */
    public String getInfo() {
        return "Serie " + getTitleNoSpace() + " " + getRelease() + " " + getGenreString() + " " + rating + " " + ("" + seasons).replaceAll(" ", "_");
    }

    /**
     * Gets the directory path of the poster to a specific show.
     * @return the poster as a .jpg file.
     */
    @Override
    public String getPosterPath() {
        return "Data/serieforsider/" + title.replaceAll(" ","_").replaceAll("'", "") + ".jpg";
    }
}
