package Media;

import java.util.List;

public class Film extends Media {
    public String getInfo() {
        return "Title: " + title + " Release date: " + release + " Genre: " + genre + " Rating: " +  rating;
    }
}
