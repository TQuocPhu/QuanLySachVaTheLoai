package management;

import Models.User;

import java.util.ArrayList;
import java.util.List;

public class UserManagement implements LoginManagement{
    private List<User> users = new ArrayList<>();

    @Override
    public List<User> findAllUser() {
        return users;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        User user = findByUserName(username);
        return (user != null && user.getPassword().equals(password));
    }

    @Override
    public void add(User user) {
        this.users.add(user);
    }

    public User findByUserName(String username){
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
