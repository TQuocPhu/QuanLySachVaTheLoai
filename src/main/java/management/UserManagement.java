package management;

import Models.User;
import dto.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserManagement implements LoginManagement{
    private List<User> users = new ArrayList<>();
    private UserDatabase userDatabase;

    public UserManagement(UserDatabase userDatabase){
        this.userDatabase = userDatabase;
        List<User> userData = userDatabase.readData();
        if(userData!=null){
            users.addAll(userData);
        }
    }

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
        try{
            userDatabase.writeData(users);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public User findByUserName(String username){
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public User login(String username, String password){
        for (User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
