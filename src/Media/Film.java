package Media;

import java.util.List;

/**
 * Is used to get the attributes that are specific to films.
 */
public class Film extends Media {
    /**
     * Gets all the info from a film.
     * @return all the info as a String.
     */
    public String getInfo() {
        return "Film " + getTitleNoSpace() + " " + getRelease() + " " + getGenreString() + " " + rating;
    }

    /**
     * Gets the directory path of the poster to a specific film.
     * @return the poster as a .jpg file.
     */
    @Override
    public String getPosterPath() {
        return "Data/filmplakater/" + title.replaceAll(" ","_").replaceAll("'", "") + ".jpg";
    }

}
