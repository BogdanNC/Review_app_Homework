package common.src.utils;

import common.src.comands.Favorite;
import common.src.comands.Rate;
import common.src.comands.View;
import common.src.fileio.ActionInputData;
import common.src.fileio.Input;
import common.src.fileio.Writer;
import org.json.simple.JSONArray;

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
        }
    }
}
