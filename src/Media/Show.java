package Media;

import java.util.List;

public class Show extends Media {
    private List <String> seasons;
    public String getInfo() {
        return "Title: " + title + " Release date: " + release + " Genre: " + genre + " Rating: " + rating + " Seasons: " + seasons;
    }
}
