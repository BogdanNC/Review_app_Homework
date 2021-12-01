package common.src.entertainment;

import java.util.ArrayList;
import java.util.Map;

public class Film extends Video {

    private final int duration;
    private final Map<String, Integer> ratings;
    public Film(final String name, final int year, final ArrayList<String> genres,
                final ArrayList<String> actors, final int duration,
                final Map<String, Integer> ratings) {
        super(name, year, genres, actors);
        this.duration = duration;
        this.ratings = ratings;
    }



}
