package calculators;

import fileio.MovieInputData;
import fileio.SerialInputData;

import java.util.Map;

public final class RatingCalculator {
    /**
     * for coding style
     */
    private RatingCalculator() {
    }

    /**
     * calculeaza ratingul unui film
     * @param movies
     * @return
     */
    public static Double movieRating(final MovieInputData movies) {
        double avrage = 0.0;
        if (movies.getRate().size() > 0) {
            avrage = 0.0;
            for (Map.Entry<String, Double> value : movies.getRate().entrySet()) {
                avrage = avrage + value.getValue();
            }
            avrage = avrage / movies.getRate().size(); // calculez ratingul
        }
        return avrage;
    }

    /**
     * calculeaza ratingul unui serial
     * @param shows
     * @return
     */
    public static Double showRating(final SerialInputData shows) {
        Double serialAverage = 0.0;
        for (var season: shows.getSeasons()) {
            Double avrage = 0.0;
            if (season.getRatings().size() > 0) {
                avrage = 0.0;
                for (Map.Entry<String, Double> value : season.getRatings().entrySet()) {
                    avrage = avrage + value.getValue();
                }
                avrage = avrage / season.getRatings().size(); // calculez ratingul sezonului
                serialAverage = serialAverage + avrage;
            }
        }
        if (serialAverage != 0.0) {
            serialAverage = serialAverage / shows.getNumberSeason();
            return serialAverage;
        } else {
            return  0.0;
        }
    }
}
