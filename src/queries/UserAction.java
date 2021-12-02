package queries;

import entertainment.Season;
import fileio.Input;
import utils.PrintSortedHash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAction {
    /**
     * for coding style
     */
    private UserAction() {
    }
    /**
     *
     * @param input
     * @param number
     * @return
     */
    public static String useraction(final Input input, final String criteria,
                                    final Integer number) {
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
            for (var show: input.getSerials()){
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

            PrintSortedHash<Integer> sorted = new PrintSortedHash<>();
            List<Map.Entry<String, Integer>> sortlist = sorted.sortHash(userRatings);
            String toPrint = "";
            for (int i = 0; i < sortlist.size(); i++) {
                if (sortlist.get(i).getValue() > 0) {
                    toPrint = toPrint + sortlist.get(i).getKey() + ", ";
                }
            }
            if (!(toPrint.isEmpty())) {
                toPrint = toPrint.substring(0, toPrint.length() - 2);
            }
            return "Query result: [" + toPrint + "]";
        }
        return "Query -> [], adica e empty ba!";
    }
}
