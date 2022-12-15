package Media;

import java.util.List;
/**
 * An abstract class containing all the thing that fields that both Media and Show contains
 */
public abstract class Media {
    protected String posterPath;
    protected String title;
    protected String release;
    protected double rating;
    protected List<String> genre;

    /**
     * @return Returns the specifically formatted info
     */
    public abstract String getInfo();
    public String getTitle() {
        return title;
    }
    public String getTitleNoSpace(){return title.replaceAll(" ", "_");}
    public String getRelease() {
        return release.replaceAll(" ", "_");
    }
    public double getRating() {
        return rating;
    }
    public List<String> getGenre() {
        return genre;
    }
    public String getGenreString(){
        return ("" + genre).replaceAll(" ", "_");
    }

    public abstract String getPosterPath();
}
