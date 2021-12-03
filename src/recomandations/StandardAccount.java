package recomandations;

import fileio.Input;
import utils.PrintReverseSortedHash;
import calculators.RatingCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class StandardAccount {

    /**
     * for coding style;
     */
    private StandardAccount() {
    }

    /**
     * execut optiunile pentru toate videourile
     * @param input
     * @param type
     * @param userName
     * @return
     */
    public static String standard(final Input input, final String type, final String userName) {
        if (type.equals("standard")) {
            for (var user: input.getUsers()) {
                if (user.getUsername().equals(userName)) {
                    for (var movies: input.getMovies()) {
                        if (!(user.getHistory().containsKey(movies.getTitle()))) {
                            return  "StandardRecommendation result: " + movies.getTitle();
                        }
                    }
                    for (var show: input.getSerials()) {
                        if (!(user.getHistory().containsKey(show.getTitle()))) {
                            return  "StandardRecommendation result: " + show.getTitle();
                        }
                    }
                }
            }
        } else if (type.equals("best_unseen")) {
            Map<String, Double> bestUnseen = new HashMap<>();
            ArrayList<String> bestUnseenRating0 = new ArrayList<>();
            for (var user: input.getUsers()) {
                if (user.getUsername().equals(userName)) {
                    for (var movies: input.getMovies()) {
                        if (!(user.getHistory().containsKey(movies.getTitle()))) {
                            if (RatingCalculator.movieRating(movies) > 0) {
                                bestUnseen.put(movies.getTitle(),
                                        RatingCalculator.movieRating(movies));
                            } else {
                                bestUnseenRating0.add(movies.getTitle());
                            }
                        }
                    }
                    for (var show: input.getSerials()) {
                        if (!(user.getHistory().containsKey(show.getTitle()))) {
                            if (RatingCalculator.showRating(show) > 0) {
                                bestUnseen.put(show.getTitle(), RatingCalculator.showRating(show));
                            } else {
                                bestUnseenRating0.add(show.getTitle());
                            }
                        }
                    }
                    PrintReverseSortedHash<Double> sorted = new PrintReverseSortedHash<>();
                    List<Map.Entry<String, Double>> sortlist = sorted.sortReverseHash(bestUnseen);
                    String toPrint = "";
                    for (int i = 0; i < sortlist.size() && i < 1; i++) {
                        if (sortlist.get(i).getValue() >= 0) {
                            toPrint = toPrint + sortlist.get(i).getKey() + ", ";
                        }
                    }
                    if (!(toPrint.isEmpty())) {
                        toPrint = toPrint.substring(0, toPrint.length() - 2);
                        return "BestRatedUnseenRecommendation result: " + toPrint;
                    } else {
                        if (!(bestUnseenRating0.isEmpty())) {
                            toPrint = bestUnseenRating0.get(0);
                        }
                        if (toPrint.isEmpty()){
                            return "BestRatedUnseenRecommendation cannot be applied!";
                        } else {
                            return "BestRatedUnseenRecommendation result: " + toPrint;
                        }
                    }
                }
            }
        }
        String output = "";
        if (type.equals("best_unseen")) {
            return "BestRatedUnseenRecommendation cannot be applied!";
        } else {
            return "StandardRecommendation cannot be applied!";
        }
    }
}
