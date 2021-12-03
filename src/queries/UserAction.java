package queries;

import entertainment.Season;
import fileio.Input;
import utils.PrintingClass;

import java.util.HashMap;
import java.util.Map;

public final class UserAction {
    /**
     * for coding style
     */
    private UserAction() {
    }
    /**
     *
     * @param input
     * @param number
     * @param sortType
     * @return
     */
    public static String useraction(final Input input, final String criteria,
                                    final Integer number, final String sortType) {
        if (criteria.equals("num_ratings")) {
            Map<String, Integer> userRatings = new HashMap<>();
            for (var movie: input.getMovies()) {
                for (Map.Entry<String, Double> val: movie.getRate().entrySet()) {
                    if (userRatings.containsKey(val.getKey())) {
                        userRatings.put(val.getKey(), userRatings.get(val.getKey()) + 1);
                    } else {
                        userRatings.put(val.getKey(), 1);
                    }
                }
            }
            for (var show: input.getSerials()) {
                for (Season season: show.getSeasons()) {
                    for (Map.Entry<String, Double> val: season.getRatings().entrySet()) {
                        if (userRatings.containsKey(val.getKey())) {
                            userRatings.put(val.getKey(), userRatings.get(val.getKey()) + 1);
                        } else {
                            userRatings.put(val.getKey(), 1);
                        }
                    }
                }
            }
            PrintingClass<Integer> readyToPrint = new PrintingClass<>();
            String toPrint = readyToPrint.printing(userRatings, number, sortType);
            return "Query result: [" + toPrint + "]";
        }
        return "Query -> [], adica e empty ba!";
    }
}
