package common.src.comands;

import common.src.fileio.Input;

public final class Favorite {
    /**
     * for coding style
     */
    private Favorite() {
    }

    /**
     *
     * @param input
     * @param userName
     * @param title
     * @return
     */
    public static String favor(final Input input,
                               final String userName, final String title) {
        for (var user: input.getUsers()) {
            if (user.getUsername().equals(userName)) {
                if (user.getHistory().containsKey(title)) {
                    if (user.getFavoriteMovies().isEmpty()) {
                        user.getFavoriteMovies().add(title);
                    } else {
                        for (var favMouvie : user.getFavoriteMovies()) {
                            if (favMouvie.equals(title)) {
                                return "error -> " + title + " is already in favourite list";
                            }
                        }
                        user.getFavoriteMovies().add(title);
                        return "success -> " + title + " was added as favourite";
                    }
                } else {
                    return "error -> " + title + " is not seen";
                }
            }
        }
        return "error -> " + userName + " has not been found";
    }
}

