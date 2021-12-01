package actor;

import java.util.ArrayList;
import java.util.Map;

public class Actor {
    private final String name;
    private final String careerDescription;
    private final ArrayList<String> filmography;
    private final Map<String, Integer> awards;

    public Actor(final String name, final String careerDescription,
                 final ArrayList<String> filmography, final Map<String, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
    }
}
