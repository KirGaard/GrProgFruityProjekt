package Media;

import java.util.List;

public class Show extends Media {
    private List<String> seasons;
    public String getInfo() {
        return title + " " + release + " " + genre + " " + rating + " " + seasons;
    }
}
