package fileio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Information about a movie, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class MovieInputData extends ShowInput {
    /**
     * Duration in minutes of a season
     */
    private final int duration;

    private Map<String, Double> rate = new HashMap<>();

    public MovieInputData(final String title, final ArrayList<String> cast,
                          final ArrayList<String> genres, final int year,
                          final int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
    }

    /**
     * Aici fac ratingul fiecarui film.
     * @param userName
     * @param grade
     * @return
     */
    public int rated(final String userName, final Double grade) {
        if (rate.isEmpty()) {
            rate.put(userName, grade);
            return 0;
        } else {
            if (rate.containsKey(userName)) {
                return 1;
            } else {
                rate.put(userName, grade);
                return 0;
            }
        }
    }

    public int getDuration() {
        return duration;
    }

    public Map<String, Double> getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "MovieInputData{" + "title= "
                + super.getTitle() + "year= "
                + super.getYear() + "duration= "
                + duration + "cast {"
                + super.getCast() + " }\n"
                + "genres {" + super.getGenres() + " }\n ";
    }
}
