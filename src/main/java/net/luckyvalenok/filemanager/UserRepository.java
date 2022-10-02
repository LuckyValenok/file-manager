package net.luckyvalenok.filemanager;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    public static final UserRepository USER_REPOSITORY = new UserRepository();
    private final Map<String, User> usersByLogin = new HashMap<>();

    public User getUserByCookies(Cookie[] cookies) {
        String login;
        User user = null;
        if ((login = CookieUtil.getValue(cookies, "login")) == null || (user = getUserByLogin(login)) == null || !user.getPassword().equals(CookieUtil.getValue(cookies, "password"))) {
            return null;
        }

        return user;
    }

    public User getUserByLogin(String login) {
        return usersByLogin.get(login);
    }

    public void addUser(User user) {
        usersByLogin.put(user.getLogin(), user);
    }

    public void removeUser(String login) {
        usersByLogin.remove(login);
    }

    public boolean containsUserByLogin(String login) {
        return usersByLogin.containsKey(login);
    }
}
