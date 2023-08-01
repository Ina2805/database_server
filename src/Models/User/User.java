package Models.User;
import java.io.Serializable;
import java.util.Objects;


public class User implements Serializable {
    private String userId;
    private String userName;
    private String email;
    private String address;

    public User(String userId, String userName, String email, String address) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.address = address;
    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        return Objects.equals(userId, other.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                // Add other fields as needed
                '}';
    }
}
