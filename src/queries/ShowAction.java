package queries;

import fileio.Input;
import fileio.MovieInputData;
import fileio.SerialInputData;
import utils.PrintReverseSortedHash;
import utils.PrintSortedHash;

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
     * @return
     */
    public static String showaction(final Input input, final String criteria,
                                     final List<List<String>> filters, final Integer number) {
        if (criteria.equals("ratings")) {
            Map<String, Double> showRating = new HashMap<>();
            for (var shows: input.getSerials()) {
                if (checkYearGenreCriteria(shows, filters)) {
                    Double serialAverage = 0.0;
                    for(var season: shows.getSeasons()){
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
                        serialAverage = serialAverage/shows.getNumberSeason();
                        showRating.put(shows.getTitle(), serialAverage);
                    } else {
                        showRating.put(shows.getTitle(), 0.0);
                    }
                }
            }

            PrintSortedHash<Double> sorted = new PrintSortedHash<>();
            List<Map.Entry<String, Double>> sortlist = sorted.sortHash(showRating);
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

            PrintSortedHash<Integer> sorted = new PrintSortedHash<>();
            List<Map.Entry<String, Integer>> sortlist = sorted.sortHash(showFavorites);
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
        } else if (criteria.equals("longest")) {
            Map<String, Integer> showDurations = new HashMap<>();
            for (var shows: input.getSerials()) {
                if (checkYearGenreCriteria(shows, filters)) {
                    int duration = 0;
                    for(var season: shows.getSeasons()){
                        duration = duration + season.getDuration();
                    }
                    showDurations.put(shows.getTitle(), duration);
                }
            }

            PrintSortedHash<Integer> sorted = new PrintSortedHash<>();
            List<Map.Entry<String, Integer>> sortlist = sorted.sortHash(showDurations);
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
        } else if (criteria.equals("most_viewed")) {
            Map<String, Integer> showView = new HashMap<>();
            for (var shows: input.getSerials()) {
                if (checkYearGenreCriteria(shows, filters)) {
                    int totalViwes = 0;
                    for (var user: input.getUsers()) {
                        Map<String, Integer> history = user.getHistory();
                        if (history.containsKey(shows.getTitle())) {
                            totalViwes = totalViwes + history.get(shows.getTitle());
                        }
                    }
                    showView.put(shows.getTitle(), totalViwes);
                }
            }

            PrintReverseSortedHash<Integer> sorted = new PrintReverseSortedHash<>();
            List<Map.Entry<String, Integer>> sortlist = sorted.sortHash(showView);
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
        return "work in progress";
    }
}
