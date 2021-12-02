package queries;

import entertainment.Season;
import fileio.Input;
import utils.PrintSortedHash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @return
     */
    public static String actoraction(final Input input, final String criteria,
                                     final List<List<String>> filters, final Integer number) {
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
                                avrage = 0;
                                for (Map.Entry<String, Double> value : movie.getRate().entrySet()) {
                                    avrage = avrage + value.getValue();
                                }
                                avrage = avrage / movie.getRate().size();
                                denominator++;
                                sum = sum + avrage;
                            }
                        }
                    }
                    for (var serial : input.getSerials()) {
                        if (serial.getTitle().equals(movieName)) {
                            double seasonAvrage = 0;
                            double seasonDenominator = 0;
                            avrage = 0;
                            for (Season sson : serial.getSeasons()) {
                                if (sson.getRatings().size() > 0) {
                                    for (Map.Entry<String, Double> value
                                            : sson.getRatings().entrySet()) {
                                        seasonAvrage = seasonAvrage + value.getValue();
                                    }
                                    seasonAvrage = seasonAvrage / sson.getRatings().size();
                                    avrage = avrage + seasonAvrage;
                                }
                                seasonDenominator++;
                            }
                            if (avrage > 0) {
                            avrage = avrage / seasonDenominator;
                            denominator++;
                            sum = sum + avrage;
                            }
                        }
                    }
                }
                if (denominator != 0.0) {
                    sum = sum / denominator;
                    actorRating.put(actor.getName(), sum);
                } else {
                    actorRating.put(actor.getName(), 0.0);
                }
            }
            List<Map.Entry<String, Double>> sortlist = new ArrayList<>(actorRating.entrySet());
            sortlist.sort(new Comparator<Map.Entry<String, Double>>() {
                @Override
                public int compare(final Map.Entry<String, Double> o1,
                                   final Map.Entry<String, Double> o2) {
                    if (o1.getValue() > o2.getValue()) {
                        return 1;
                    } else {
                        if (o1.getValue() < o2.getValue()) {
                            return -1;
                        }
                    }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            String toPrint = "";
            int n = number;
            for (int i = 0; i < n && i < sortlist.size(); i++) {
                if (sortlist.get(i).getValue() > 0) {
                    toPrint = toPrint + sortlist.get(i).getKey() + ", ";
                } else {
                    n++;
                }
            }
            if (!(toPrint.isEmpty())) {
                toPrint = toPrint.substring(0, toPrint.length() - 2);
            }
            return "Query result: [" + toPrint + "]";
        } else if (criteria.equals("awards")) {
            Map<String, Integer> actorRating = new HashMap<>();
            int noAwards;
            for (var actor: input.getActors()) {
                noAwards = 0;
                for (String awards: filters.get(3)) {
                    for (var count: actor.getAwards().entrySet()) {
                        if (awards.equals(count.getKey())) {
                        noAwards = noAwards + count.getValue();
                        }
                    }
                }
                actorRating.put(actor.getName(), noAwards);
            }
            PrintSortedHash<Integer> top = new PrintSortedHash<>();
            List<Map.Entry<String, Integer>> sortlist = top.sortHash(actorRating);
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
            /*List<Map.Entry<String, Integer>> sortlist = new ArrayList<>(actorRating.entrySet());
            sortlist.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(final Map.Entry<String, Integer> o1,
                                   final Map.Entry<String, Integer> o2) {
                    if (o1.getValue() > o2.getValue()) {
                        return -1;
                    } else {
                        if (o1.getValue() < o2.getValue()) {
                            return 1;
                        }
                    }
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            String toPrint = "";
            for (int i = 0; i < sortlist.size(); i++) {
                if (sortlist.get(i).getValue() > 0) {
                    toPrint = toPrint + sortlist.get(i).getKey() + ", ";
                }
            }
            if (!(toPrint.isEmpty())) {
                toPrint = toPrint.substring(0, toPrint.length() - 2);
            }
            return "Query result: [" + toPrint + "]";*/
        } else if (criteria.equals("filter_description")) {

            ArrayList<String> actorRating = new ArrayList<>();
            for (var actor: input.getActors()) {
                int check = 1;
                for (String keyWord: filters.get(2)) {
                    if (!(actor.getCareerDescription().contains(keyWord))) {
                        check = 0;
                    }
                }
                if (check == 1) {
                actorRating.add(actor.getName());
                }
            }
            actorRating.sort((a1, a2) -> a1.compareTo(a2));
            return "Query result: " + actorRating;
        }
        return "still in progress";
    }
}
