package Media;

import java.util.List;

public class Film extends Media {
    public String getInfo() {
        throw new RuntimeException();
    }

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
