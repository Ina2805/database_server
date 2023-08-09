package Models.User;
import java.io.Serializable;


public class User implements Serializable {
    private String userId;
    private String userName;


    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    public User() {

    }


    public String getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

}
