package common.src.entertainment;

import java.util.ArrayList;

public final class Serials extends Video {
    private int noSeasons;
    private ArrayList<Season> seasonsList;
    public Serials(final String name, final int year, final ArrayList<String> genres,
                   final  ArrayList<String> actors, final int noSeasons,
                   final ArrayList<Season> seasonsList) {
        super(name, year, genres, actors);
        this.noSeasons = noSeasons;
        this.seasonsList = seasonsList;
    }

    public int getNoSeasons() {
        return noSeasons;
    }

    public void setNoSeasons(final int noSeasons) {
        this.noSeasons = noSeasons;
    }

    public ArrayList<Season> getSeasonsList() {
        return seasonsList;
    }

    public void setSeasonsList(ArrayList<Season> seasonsList) {
        this.seasonsList = seasonsList;
    }
}
