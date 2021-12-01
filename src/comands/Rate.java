package comands;

import entertainment.Season;
import fileio.Input;

import java.util.HashMap;
import java.util.Map;

public final class Rate {
    /**
     * for coding style
     */
    private Rate() {
    }

    /**
     *
     * @param input
     * @param grade
     * @param seasonNr
     * @param userName
     * @param title
     * @return
     */
    public static String rate(final Input input, final double grade, final int seasonNr,
                              final String userName, final String title) {
        int x;
        for (var user: input.getUsers()) {
            if (user.getUsername().equals(userName)) {
                if (user.getHistory().containsKey(title)) {
                    if (seasonNr == 0) {
                        for (var movie: input.getMovies()) {
                            if (movie.getTitle().equals(title)) {
                                x = movie.rated(userName, grade);
                                if (x == 1) {
                                    return "error -> " + title + " has been already rated";
                                } else {
                                    return "success -> " + title
                                            + " was rated with " + grade + " by " + userName;
                                }
                            }
                        }
                    } else {
                        for (var serial: input.getSerials()) {
                            Map<String, Double> ratings;
                            if (serial.getTitle().equals(title)) {
                                Season sson = serial.getSeasons().get(seasonNr - 1);
                                ratings = sson.getRatings();
                                if (ratings.isEmpty()) {
                                    ratings.put(userName, grade);
                                    sson.setRatings(ratings);
                                    return "success -> " + title
                                            + " was rated with " + grade + " by " + userName;
                                } else {
                                    if (sson.getRatings().containsKey(userName)) {
                                        return "error -> " + title + " has been already rated";
                                    } else {
                                        ratings.put(userName, grade);
                                        sson.setRatings(ratings);
                                        return "success -> " + title
                                                + " was rated with " + grade + " by " + userName;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    return "error -> " + title + " is not seen";
                }
            }
        }
        return "error -> " + userName + " has not been found";
    }
}
