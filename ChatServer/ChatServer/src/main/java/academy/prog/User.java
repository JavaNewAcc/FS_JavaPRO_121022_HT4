package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {
    private String login;

    public User(String login) {
        this.login = login;
    }

    public static User fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, User.class);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").append("Login: ").append(login).append("] ").toString();
    }

    public String getLogin() {
        return login;
    }
}
