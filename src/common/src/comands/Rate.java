package common.src.comands;

import common.src.fileio.Input;

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
     * @param season
     * @param userName
     * @param title
     * @return
     */
    public static String rate(final Input input, final double grade, final int season,
                              final String userName, final String title) {
        for (var user: input.getUsers()) {
            if (user.getUsername().equals(userName)) {
                if (user.getHistory().containsKey(title)) {
                    if (season == 0) {
                        for (var movie: input.getMovies()) {
                            if (movie.getTitle().equals(title)) {
                                return "i rate here";
                            }
                        }
                    } else {
                        for (var serial: input.getSerials()) {
                            return "i rate here";
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
