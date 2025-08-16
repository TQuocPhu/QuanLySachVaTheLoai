package Models;

public class User {
    private static long AUTO_ID = 1;
    private long id;
    private String username;
    private String password;
    private String fullName;
    private String role; //admin or user

    public User(String username, String password, String fullName, String role) {
        this.id = AUTO_ID++;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public User(long id, String username, String password, String fullName, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', fullName='" + fullName + "'}";
    }
}
