package queries;

import actor.ActorsAwards;
import calculators.RatingCalculator;
import common.Constants;
import fileio.ActorInputData;
import fileio.Input;
import utils.PrintingClass;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public final class ActorAction {
    /**
     * for coding style
     */
    private ActorAction() {
    }
    /**
     *
     * @param input
     * @param filters
     * @param number
     * @param sortType
     * @return
     */
    public static String actoraction(final Input input, final String criteria,
                                     final List<List<String>> filters, final Integer number,
                                     final String sortType) {
        if (criteria.equals("average")) {
            Map<String, Double> actorRating = new HashMap<>();
            double sum = 0, denominator = 0;
            double avrage = 0;
            for (var actor: input.getActors()) {
                sum = 0;
                denominator = 0;
                for (String movieName : actor.getFilmography()) {
                    for (var movie : input.getMovies()) {
                        if (movie.getTitle().equals(movieName)) {
                            if (movie.getRate().size() > 0) {
                                denominator++;
                                sum = sum + RatingCalculator.movieRating(movie);
                            }
                        }
                    }
                    for (var serial : input.getSerials()) {
                        if (serial.getTitle().equals(movieName)) {
                            avrage = RatingCalculator.showRating(serial);
                            if (avrage > 0) {
                            denominator++;
                            sum = sum + avrage;
                            }
                        }
                    }
                }
                if (denominator != 0.0) {
                    sum = sum / denominator;
                    actorRating.put(actor.getName(), sum);
                }
            }

            PrintingClass<Double> readyToPrint = new PrintingClass<>();
            String toPrint = readyToPrint.printing(actorRating, number, sortType);
            return "Query result: [" + toPrint + "]";

        } else if (criteria.equals("awards")) {
            Map<String, Integer> actorRating = new HashMap<>();
            int noAwards;
            for (var actor: input.getActors()) {
                noAwards = 0;
                int ok = 0;
                for (String awards: filters.get(Constants.MAGIC_NUMBER)) {
                    ActorsAwards award = Utils.stringToAwards(awards);
                    for (Map.Entry<ActorsAwards, Integer> count: actor.getAwards().entrySet()) {
                        if (count.getKey().equals(award)) {
                            ok++;
                        }
                        noAwards = noAwards + count.getValue();
                    }
                }
                if (ok == filters.get(Constants.MAGIC_NUMBER).size()) {
                    actorRating.put(actor.getName(), noAwards);
                }
            }
            PrintingClass<Integer> readyToPrint = new PrintingClass<>();
            String toPrint = readyToPrint.printing(actorRating, number, sortType);
            return "Query result: [" + toPrint + "]";

        } else if (criteria.equals("filter_description")) {

            ArrayList<String> actorRating = new ArrayList<>();
            ArrayList<ActorInputData> actors = new ArrayList<>(input.getActors());
            for (String keyWord: filters.get(2)) {
                Pattern neededWord = Pattern.compile("[ -]" + keyWord + "[ .,]");
                actors.removeIf(x -> !neededWord.matcher(x.getCareerDescription()
                        .toLowerCase(Locale.ROOT)).find());
            }
            for (var actor: actors) {
                actorRating.add(actor.getName());
            }
            if (sortType.equals("asc")) {
                actorRating.sort((a1, a2) -> a1.compareTo(a2));
            } else {
                actorRating.sort((a1, a2) -> a2.compareTo(a1));
            }
            return "Query result: " + actorRating;
        }
        return "still in progress";
    }
}
