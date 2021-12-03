package recomandations;

import calculators.RatingCalculator;
import calculators.ViewCalculator;
import fileio.Input;
import utils.PrintReverseSortedHash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PremiumAccount {

    /**
     * for coding style;
     */
    private PremiumAccount() {
    }

    /**
     *
     * @param input
     * @param type
     * @param userName
     * @param searchGenre
     * @return
     */
    public static String premium(final Input input, final String type,
                                 final String userName, final String searchGenre) {
        if (type.equals("popular")) {
            for (var user: input.getUsers()) {
                if (user.getUsername().equals(userName)) {
                    if (user.getSubscriptionType().equals("PREMIUM")) {
                        Map<String, Integer> popularGenre = new HashMap<>();
                        for (var movies: input.getMovies()) {
                            for (var gerne: movies.getGenres()) {
                                if (popularGenre.containsKey(gerne)) {
                                    popularGenre.put(gerne, popularGenre.get(gerne) + 1);
                                } else {
                                    popularGenre.put(gerne, 1);
                                }
                            }
                        }
                        for (var shows: input.getSerials()) {
                            for (var gerne: shows.getGenres()) {
                                if (popularGenre.containsKey(gerne)) {
                                    popularGenre.put(gerne, popularGenre.get(gerne) + 1);
                                } else {
                                    popularGenre.put(gerne, 1);
                                }
                            }
                        }
                        PrintReverseSortedHash<Integer> sorted = new PrintReverseSortedHash<>();
                        List<Map.Entry<String, Integer>> sortedGenre
                                = sorted.sortReverseHash(popularGenre);

                        Map<String, Integer> popularVideos = new HashMap<>();
                        for (var shows: input.getSerials()) {
                            popularVideos.put(shows.getTitle(),
                                    ViewCalculator.views(input, shows.getTitle()));
                        }
                        for (var movies: input.getMovies()) {
                            popularVideos.put(movies.getTitle(),
                                    ViewCalculator.views(input, movies.getTitle()));
                        }
                        List<Map.Entry<String, Integer>> sortedVideos = sorted.sortReverseHash(popularVideos);
                        for (Map.Entry<String, Integer> genre: sortedGenre) {
                            for (Map.Entry<String, Integer> popMovie: sortedVideos) {
                                if (!(user.getHistory().containsKey(popMovie.getKey()))){
                                    for (var movie: input.getMovies()) {
                                        if (movie.getTitle().equals(popMovie.getKey())) {
                                            if (movie.getGenres().contains(genre.getKey())) {
                                                return "PopularRecommendation result: " + movie.getTitle();
                                            }
                                        }
                                    }
                                    for (var show: input.getSerials()) {
                                        if (show.getTitle().equals(popMovie.getKey())) {
                                            if (show.getGenres().contains(genre.getKey())) {
                                                return "PopularRecommendation result: " + show.getTitle();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return "PopularRecommendation cannot be applied!";
                }
            }
        }
        if (type.equals("favorite")) {
            for (var user: input.getUsers()) {
                if (user.getUsername().equals(userName)) {
                    if (user.getSubscriptionType().equals("PREMIUM")) {
                        Map<String, Integer> favouriteVideo = new HashMap<>();
                        for (var allUsers: input.getUsers()) {
                            for (String video: allUsers.getFavoriteMovies()) {
                                if (favouriteVideo.containsKey(video)) {
                                    favouriteVideo.put(video, favouriteVideo.get(video) + 1);
                                } else {
                                    favouriteVideo.put(video, 1);
                                }
                            }
                        }
                        PrintReverseSortedHash<Integer> sorted = new PrintReverseSortedHash<>();
                        List<Map.Entry<String, Integer>> sortedFavorites = sorted.sortReverseHash(favouriteVideo);

                        for (Map.Entry<String, Integer> video: sortedFavorites) {
                            if (!(user.getHistory().containsKey(video.getKey()))) {
                                return "FavoriteRecommendation result: " + video.getKey();
                            }
                        }
                    }
                    return "FavoriteRecommendation cannot be applied!";
                }
            }
        }
        if (type.equals("search")) {
            for (var user: input.getUsers()) {
                if (user.getUsername().equals(userName)) {
                    if (user.getSubscriptionType().equals("PREMIUM")) {
                        Map<String, Double> ratedSearch = new HashMap<>();
                        List<String> bestRated0Search = new ArrayList<>();
                        for (var movie: input.getMovies()) {
                            if (!(user.getHistory().containsKey(movie.getTitle()))){
                                if (movie.getGenres().contains(searchGenre)) {
                                    if (RatingCalculator.movieRating(movie) > 0) {
                                        ratedSearch.put(movie.getTitle(),
                                                RatingCalculator.movieRating(movie));
                                    } else {
                                        bestRated0Search.add(movie.getTitle());
                                    }
                                }
                            }
                        }
                        for (var show: input.getSerials()) {
                            if (!(user.getHistory().containsKey(show.getTitle()))) {
                                if (show.getGenres().contains(searchGenre)) {
                                    if (RatingCalculator.showRating(show) > 0) {
                                        ratedSearch.put(show.getTitle(),
                                                RatingCalculator.showRating(show));
                                    } else {
                                        bestRated0Search.add(show.getTitle());
                                    }
                                }
                            }
                        }
                        bestRated0Search.sort((e1, e2) -> e1.compareTo(e2));
                        PrintReverseSortedHash<Double> sorted = new PrintReverseSortedHash<>();
                        List<Map.Entry<String, Double>> sortlist
                                = sorted.sortReverseHash(ratedSearch);
                        String toPrint = "";
                        for (int i = 0; i < sortlist.size(); i++) {
                            if (sortlist.get(i).getValue() > 0) {
                                toPrint = toPrint + sortlist.get(i).getKey() + ", ";
                            }
                        }
                        if (!(bestRated0Search.isEmpty())) {
                            for (int i = 0; i < bestRated0Search.size(); i++) {
                                toPrint = toPrint + bestRated0Search.get(i) + ", ";
                            }
                        }
                        if (!(toPrint.isEmpty())) {
                            toPrint = toPrint.substring(0, toPrint.length() - 2);
                            return "SearchRecommendation result: [" + toPrint + "]";
                        }
                    }
                    return "SearchRecommendation cannot be applied!";
                }
            }
        }
        return "work in progress";
    }
}
