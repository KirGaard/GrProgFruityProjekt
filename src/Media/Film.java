package Media;

import java.util.List;

public class Film extends Media {
    public String getInfo() {
        return "Film " + getTitleNoSpace() + " " + getRelease() + " " + getGenreString() + " " + rating;
    }

    /**
     * @return
     */
    @Override
    public String getPosterPath() {
        return "Data/filmplakater/" + title.replaceAll(" ","_").replaceAll("'", "") + ".jpg";
    }

}
