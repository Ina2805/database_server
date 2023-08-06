package DAO.User;

import Models.User.User;
import Networking.DBConnection;

import java.util.List;

public interface IUserDAO {
    List<User> getAllUsersFromDatabase();
    User getUserByIdFromDatabase(String userId);
    boolean saveUser(User user);
    boolean deleteUser(String userid);
}
