package common.src.users;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
public class User {
    private final String username;
    private final String subscription;
    private final Map<String, Integer> history;
    private final ArrayList<String> favourite;

    public User(final String username, final String subscription,
                final Map<String, Integer> history, final ArrayList<String> favourite) {
        this.username = username;
        this.subscription = subscription;
        this.history = history;
        this.favourite = favourite;
    }

}
