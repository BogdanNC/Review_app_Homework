package common.src.entertainment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Setter
@Getter
public abstract class Video {
    private final String name;
    private final int year;
    private final ArrayList<String> genres;
    private final ArrayList<String> actors;

}
