package queries;

import calculators.ViewCalculator;
import fileio.Input;
import fileio.SerialInputData;
import calculators.RatingCalculator;
import utils.PrintingClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ShowAction {
    /**
     * for coding style
     */
    private ShowAction() {
    }

    /**
     * verific ca anul si genra sa coincida daca sunt date
     * @param shows
     * @param filters
     * @return
     */
    public static boolean checkYearGenreCriteria(final SerialInputData shows,
                                                 final List<List<String>> filters) {
        int genreOk = 0;
        String year = String.valueOf(shows.getYear());
        if (filters.get(0).get(0) != null) {
            if (!(filters.get(0).get(0).equals(year))) {
                return false; // verific sa respecte anul
            }
        }
        if (filters.get(1).get(0) != null) {
            for (String requaredGenre : filters.get(1)) {
                for (String genre : shows.getGenres()) {
                    if (requaredGenre.equals(genre)) {
                        genreOk++; //verific sa respecte toate genurile
                    }
                }
            }
            if (genreOk != filters.get(1).size()) {
                return false;
            }
        }
        return true;
    }
    /**
     *
     * @param input
     * @param criteria
     * @param filters
     * @param number
     * @param sortType
     * @return
     */
    public static String showaction(final Input input, final String criteria,
                                    final List<List<String>> filters, final Integer number,
                                    final String sortType) {
        if (criteria.equals("ratings")) {
            Map<String, Double> showRating = new HashMap<>();
            for (var shows: input.getSerials()) {
                if (checkYearGenreCriteria(shows, filters)) {
                    if (RatingCalculator.showRating(shows) > 0) {
                        showRating.put(shows.getTitle(), RatingCalculator.showRating(shows));
                    }
                }
            }
            PrintingClass<Double> readyToPrint = new PrintingClass<>();
            String toPrint = readyToPrint.printing(showRating, number, sortType);
            return "Query result: [" + toPrint + "]";

        } else if (criteria.equals("favorite")) {
            Map<String, Integer> showFavorites = new HashMap<>();
            for (var shows: input.getSerials()) {
                if (checkYearGenreCriteria(shows, filters)) {
                    int noFav = 0;
                    for (var user : input.getUsers()) {
                        for (String favMovie : user.getFavoriteMovies()) {
                            if (favMovie.equals(shows.getTitle())) {
                                noFav++;
                            }
                        }
                    }
                    showFavorites.put(shows.getTitle(), noFav);
                }
            }
            PrintingClass<Integer> readyToPrint = new PrintingClass<>();
            String toPrint = readyToPrint.printing(showFavorites, number, sortType);
            return "Query result: [" + toPrint + "]";

        } else if (criteria.equals("longest")) {
            Map<String, Integer> showDurations = new HashMap<>();
            for (var shows: input.getSerials()) {
                if (checkYearGenreCriteria(shows, filters)) {
                    int duration = 0;
                    for (var season: shows.getSeasons()) {
                        duration = duration + season.getDuration();
                    }
                    showDurations.put(shows.getTitle(), duration);
                }
            }

            PrintingClass<Integer> readyToPrint = new PrintingClass<>();
            String toPrint = readyToPrint.printing(showDurations, number, sortType);
            return "Query result: [" + toPrint + "]";
        } else if (criteria.equals("most_viewed")) {
            Map<String, Integer> showView = new HashMap<>();
            for (var shows: input.getSerials()) {
                if (checkYearGenreCriteria(shows, filters)) {
                    showView.put(shows.getTitle(), ViewCalculator.views(input, shows.getTitle()));
                }
            }

            PrintingClass<Integer> readyToPrint = new PrintingClass<>();
            String toPrint = readyToPrint.printing(showView, number, sortType);
            return "Query result: [" + toPrint + "]";
        }
        return "work in progress";
    }
}
