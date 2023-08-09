package DAO.User;

import DAO.User.IUserDAO;
import Models.User.User;
import Networking.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    private DBConnection dbConnection;

    public UserDAO() {
        dbConnection = new DBConnection();
    }

    @Override
    public List<User> getAllUsersFromDatabase() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = dbConnection.getDBConnection()) {
            String query = "SELECT userid, username FROM usertable";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String userId = resultSet.getString("userid");
                String userName = resultSet.getString("username");
                User user = new User(userId, userName);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserByIdFromDatabase(String userId) {
        User user = new User();
        String query = "SELECT * FROM usertable WHERE userid = ?";

        try (Connection connection = dbConnection.getDBConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String fetchedUserId = resultSet.getString("userid");
                String userName = resultSet.getString("username");

                user.setUserId(fetchedUserId);
                user.setUserName(userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean saveUser(User user) {

        Connection connection = null;

        try {
            connection = dbConnection.getDBConnection();
            String query = "INSERT INTO usertable (userid, username) VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getUserName());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(String userid) {
        Connection connection = null;
        try {
            connection = dbConnection.getDBConnection();
            String query = "DELETE FROM usertable WHERE userid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userid);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editUser(User user) {
        Connection connection = null;
        try {
            connection = dbConnection.getDBConnection();
            String query = "UPDATE usertable SET username = ? WHERE userid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}