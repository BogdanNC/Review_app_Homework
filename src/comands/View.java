package comands;

import fileio.Input;

public final class View {
    /**
     * for coding style
     */
    private View() {
    }

    /**
     *
     * @param input
     * @param userName
     * @param title
     * @return
     */
    public static String view(final Input input, final String userName, final String title) {
        for (var user: input.getUsers()) {
            if (user.getUsername().equals(userName)) {
                if (user.getHistory().containsKey(title)) {
                    user.getHistory().put(title, user.getHistory().get(title) + 1);
                    return "success -> " + title
                            + " with total views of " + user.getHistory().get(title);
                } else {
                    user.getHistory().putIfAbsent(title, 1);
                    return "success -> " + title
                            + " was viewed with total views of " + user.getHistory().get(title);
                }
            }
        }
        return "error -> " + userName + " has not been found";
    }
}
