package queries;

import fileio.Input;
import fileio.MovieInputData;
import utils.PrintReverseSortedHash;
import utils.PrintSortedHash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MovieAction {
    /**
     * for coding style
     */
    private MovieAction() {
    }

    /**
     * verific ca anul si genra sa coincida daca sunt date
     * @param movies
     * @param filters
     * @return
     */
    public static boolean checkYearGenreCriteria(final MovieInputData movies,
                                                 final List<List<String>> filters) {
        int genreOk = 0;
        String year = String.valueOf(movies.getYear());
        if (filters.get(0).get(0) != null) {
            if (!(filters.get(0).get(0).equals(year))) {
                return false; // verific sa respecte anul
            }
        }
        if (filters.get(1).get(0) != null) {
            for (String requaredGenre : filters.get(1)) {
                for (String genre : movies.getGenres()) {
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
    public static String movieaction(final Input input, final String criteria,
                                     final List<List<String>> filters, final Integer number) {
        if (criteria.equals("ratings")) {
            Map<String, Double> movieRating = new HashMap<>();
            for (var movies: input.getMovies()) {
                if (checkYearGenreCriteria(movies, filters)) {
                    double avrage = 0.0;
                    if (movies.getRate().size() > 0) {
                        avrage = 0.0;
                        for (Map.Entry<String, Double> value : movies.getRate().entrySet()) {
                            avrage = avrage + value.getValue();
                        }
                        avrage = avrage / movies.getRate().size(); // calculez ratingul
                    }
                    movieRating.put(movies.getTitle(), avrage);
                }
            }
            PrintSortedHash<Double> sorted = new PrintSortedHash<>();
            List<Map.Entry<String, Double>> sortlist = sorted.sortHash(movieRating);
            String toPrint = "";
            for (int i = 0; i < sortlist.size(); i++) {
                if (!(sortlist.get(i).getValue().equals(0))) {
                    toPrint = toPrint + sortlist.get(i).getKey() + ", ";
                }
            }
            if (!(toPrint.isEmpty())) {
                toPrint = toPrint.substring(0, toPrint.length() - 2);
            }
            return "Query result: [" + toPrint + "]";
        } else if (criteria.equals("favorite")) {
            Map<String, Integer> movieFavorites = new HashMap<>();
            for (var movies: input.getMovies()) {
                if (checkYearGenreCriteria(movies, filters)) {
                    int noFav = 0;
                    for (var user : input.getUsers()) {
                        for (String favMovie : user.getFavoriteMovies()) {
                            if (favMovie.equals(movies.getTitle())) {
                                noFav++;
                            }
                        }
                    }
                    movieFavorites.put(movies.getTitle(), noFav);
                }
            }
            PrintSortedHash<Integer> sorted = new PrintSortedHash<>();
            List<Map.Entry<String, Integer>> sortlist = sorted.sortHash(movieFavorites);
            String toPrint = "";
            for (int i = 0; i < sortlist.size(); i++) {
                if (!(sortlist.get(i).getValue().equals(0))) {
                    toPrint = toPrint + sortlist.get(i).getKey() + ", ";
                }
            }
            if (!(toPrint.isEmpty())) {
                toPrint = toPrint.substring(0, toPrint.length() - 2);
            }
            return "Query result: [" + toPrint + "]";
        } else if (criteria.equals("longest")) {
            Map<String, Integer> movieDurations = new HashMap<>();
            for (var movies: input.getMovies()) {
                if (checkYearGenreCriteria(movies, filters)) {
                    movieDurations.put(movies.getTitle(), movies.getDuration());
                }
            }
            PrintSortedHash<Integer> sorted = new PrintSortedHash<>();
            List<Map.Entry<String, Integer>> sortlist = sorted.sortHash(movieDurations);
            String toPrint = "";
            for (int i = 0; i < sortlist.size(); i++) {
                if (!(sortlist.get(i).getValue().equals(0))) {
                    toPrint = toPrint + sortlist.get(i).getKey() + ", ";
                }
            }
            if (!(toPrint.isEmpty())) {
                toPrint = toPrint.substring(0, toPrint.length() - 2);
            }
            return "Query result: [" + toPrint + "]";
        } else if (criteria.equals("most_viewed")) {
            Map<String, Integer> movieView = new HashMap<>();
            for (var movies: input.getMovies()) {
                if (checkYearGenreCriteria(movies, filters)) {
                    int totalViwes = 0;
                    for (var user: input.getUsers()) {
                        Map<String, Integer> history = user.getHistory();
                        if (history.containsKey(movies.getTitle())) {
                        totalViwes = totalViwes + history.get(movies.getTitle());
                        }
                    }
                    movieView.put(movies.getTitle(), totalViwes);
                }
            }
            PrintReverseSortedHash<Integer> sorted = new PrintReverseSortedHash<>();
            List<Map.Entry<String, Integer>> sortlist = sorted.sortHash(movieView);
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
