package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

public class UserList {
    private static final UserList userList = new UserList();

    private final Gson gson;
    private final List<String> listUsers = new ArrayList();

    public static UserList getInstance() {
        return userList;
    }

    private UserList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(User user) {
        if (!listUsers.contains(user.getLogin())) {
            listUsers.add(user.getLogin());
        }
    }

    public synchronized void remove(User user) {
        if (listUsers.contains(user.getLogin())) {
            listUsers.remove(user.getLogin());
        }
    }

    public synchronized String toJSON() {
        return gson.toJson(listUsers);
    }


}
