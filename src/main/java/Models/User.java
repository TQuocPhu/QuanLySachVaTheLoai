package Models;

public class User {
    private static long AUTO_ID = 1;
    private long id;
    private String username;
    private String password;
    private String fullName;

    public User(String username, String password, String fullName) {
        this.id = AUTO_ID++;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', fullName='" + fullName + "'}";
    }
}
