package management;

import Models.User;

import java.util.List;

public interface LoginManagement {
    void add(User user);
    boolean checkLogin(String username, String password);
    List<User> findAllUser();
}
