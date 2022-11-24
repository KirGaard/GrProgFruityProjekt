package Media;

import java.util.List;

public class Film extends Media {
    public String getInfo() {
        return title + release + genre + rating;
    }
}
