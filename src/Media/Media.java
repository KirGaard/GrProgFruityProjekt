package Media;

import java.util.List;

public abstract class Media {
    protected String title;
    protected String release;
    protected double rating;
    protected List<String> genre;

    public String getInfo() { throw new RuntimeException(); }
    public String getTitle() {
        return title;
    }
    public String getRelease() {
        return release;
    }
    public double getRating() {
        return rating;
    }
    public List<String> getGenre() {
        return genre;
    }
}
