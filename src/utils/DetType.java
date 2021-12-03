package utils;

import recomandations.PremiumAccount;
import recomandations.StandardAccount;
import comands.Favorite;
import comands.Rate;
import fileio.ActionInputData;
import fileio.Input;
import comands.View;
import fileio.Writer;
import org.json.simple.JSONArray;
import queries.ActorAction;
import queries.MovieAction;
import queries.ShowAction;
import queries.UserAction;

import java.io.IOException;

public final class DetType {
    private static String output;
    /**
     * for coding style
     */
    private DetType() {
    }

    /**
     *
     * @param input
     * @param finalWriter
     * @param arrayResault
     * @throws IOException
     */
    public static void dettype(final Input input, final Writer finalWriter,
                               final JSONArray arrayResault) throws IOException {
        for (ActionInputData command: input.getCommands()) {
            if (command.getActionType().equals("command")) {
                if (command.getType().equals("view")) {
                    output = View.view(input, command.getUsername(), command.getTitle());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
                if (command.getType().equals("favorite")) {
                    output = Favorite.favor(input, command.getUsername(), command.getTitle());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
                if (command.getType().equals("rating")) {
                    output = Rate.rate(input, command.getGrade(), command.getSeasonNumber(),
                            command.getUsername(), command.getTitle());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
            }
            if (command.getActionType().equals("query")) {
                if (command.getObjectType().equals("actors")) {
                    output = ActorAction.actoraction(input, command.getCriteria(),
                            command.getFilters(), command.getNumber(), command.getSortType());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
                if (command.getObjectType().equals("movies")) {
                    output = MovieAction.movieaction(input, command.getCriteria(),
                            command.getFilters(), command.getNumber(), command.getSortType());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
                if (command.getObjectType().equals("shows")) {
                    output = ShowAction.showaction(input, command.getCriteria(),
                            command.getFilters(), command.getNumber(), command.getSortType());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
                if (command.getObjectType().equals("users")) {
                    output = UserAction.useraction(input, command.getCriteria(),
                            command.getNumber(), command.getSortType());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
            }
            if (command.getActionType().equals("recommendation")) {
                if (command.getType().equals("standard")) {
                    output  = StandardAccount.standard(input, command.getType(),
                            command.getUsername());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
                if (command.getType().equals("best_unseen")) {
                    output = StandardAccount.standard(input, command.getType(),
                            command.getUsername());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
                if (command.getType().equals("popular")) {
                    output = PremiumAccount.premium(input, command.getType(),
                            command.getUsername(), command.getGenre());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
                if (command.getType().equals("favorite")) {
                    output = PremiumAccount.premium(input, command.getType(),
                            command.getUsername(), command.getGenre());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
                if (command.getType().equals("search")) {
                    output = PremiumAccount.premium(input, command.getType(),
                            command.getUsername(), command.getGenre());
                    arrayResault.add(finalWriter.writeFile(command.getActionId(), output, output));
                }
            }
        }
    }
}
