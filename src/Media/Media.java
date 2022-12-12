package Media;

import java.util.List;

public abstract class Media {
    protected String posterPath;
    protected String title;
    protected String release;
    protected double rating;
    protected List<String> genre;

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
