package calculators;

import fileio.Input;

import java.util.Map;

public final class ViewCalculator {

    /**
     * for coding style
     */
    private ViewCalculator() {
    }

    /**
     * pentru calculul view-urilor
     * @param input
     * @param videoName
     * @return
     */
    public static Integer views(final Input input, final String videoName) {
        int totalViwes = 0;
        for (var user: input.getUsers()) {
            Map<String, Integer> history = user.getHistory();
            if (history.containsKey(videoName)) {
                totalViwes = totalViwes + history.get(videoName);
            }
        }
        return totalViwes;
    }
}
