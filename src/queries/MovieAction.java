package queries;

import calculators.ViewCalculator;
import fileio.Input;
import fileio.MovieInputData;
import calculators.RatingCalculator;
import utils.PrintingClass;

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
     * @param sortType
     * @return
     */
    public static String movieaction(final Input input, final String criteria,
                                     final List<List<String>> filters,
                                     final Integer number, final String sortType) {
        if (criteria.equals("ratings")) {
            Map<String, Double> movieRating = new HashMap<>();
            for (var movies: input.getMovies()) {
                if (checkYearGenreCriteria(movies, filters)) {
                    if (RatingCalculator.movieRating(movies) > 0) {
                        movieRating.put(movies.getTitle(), RatingCalculator.movieRating(movies));
                    }
                }
            }
            PrintingClass<Double> readyToPrint = new PrintingClass<>();
            String toPrint = readyToPrint.printing(movieRating, number, sortType);
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
            PrintingClass<Integer> readyToPrint = new PrintingClass<>();
            String toPrint = readyToPrint.printing(movieFavorites, number, sortType);
            return "Query result: [" + toPrint + "]";

        } else if (criteria.equals("longest")) {

            Map<String, Integer> movieDurations = new HashMap<>();
            for (var movies: input.getMovies()) {
                if (checkYearGenreCriteria(movies, filters)) {
                    movieDurations.put(movies.getTitle(), movies.getDuration());
                }
            }
            PrintingClass<Integer> readyToPrint = new PrintingClass<>();
            String toPrint = readyToPrint.printing(movieDurations, number, sortType);
            return "Query result: [" + toPrint + "]";

        } else if (criteria.equals("most_viewed")) {

            Map<String, Integer> movieView = new HashMap<>();
            for (var movies: input.getMovies()) {
                if (checkYearGenreCriteria(movies, filters)) {
                    movieView.put(movies.getTitle(),
                            ViewCalculator.views(input, movies.getTitle()));
                }
            }
            PrintingClass<Integer> readyToPrint = new PrintingClass<>();
            String toPrint = readyToPrint.printing(movieView, number, sortType);
            return "Query result: [" + toPrint + "]";
        }
        return "work in progress";
    }
}
