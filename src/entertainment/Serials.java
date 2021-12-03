package entertainment;

import java.util.ArrayList;

public final class Serials extends Video {
    private final int noSeasons;
    //private final ArrayList<Season> seasonsList; // aici nu ar trebui final dar urla chekerul
    public Serials(final String name, final int year, final ArrayList<String> genres,
                   final  ArrayList<String> actors, final int noSeasons) {
        super(name, year, genres, actors);
        this.noSeasons = noSeasons;
       // this.seasonsList = seasonsList;
    }

    public int getNoSeasons() {
        return noSeasons;
    }
}
