package Networking;

import DAO.User.IUserDAO;
import DAO.User.UserDAO;
import Models.User.User;
import com.google.gson.Gson;
import Util.NetworkRequest;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class DBSocketHandler implements Runnable {


    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Gson gson;

    private IUserDAO userDAO = new UserDAO();

    public DBSocketHandler(Socket socket) {
        this.socket = socket;
        gson = new Gson();

        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        byte[] requestBytes = new byte[1024 * 1024];

        try {
            int arrayLength = inputStream.read(requestBytes, 0, requestBytes.length);
            String arrString = new String(requestBytes, 0, arrayLength);
            NetworkRequest networkRequest = gson.fromJson(arrString, NetworkRequest.class);

       switch (networkRequest.getNetworkType())
       {
           case GET_ALL_USERS: {
               System.out.println(networkRequest.getNetworkType());

               try {
                   List<User> userList = userDAO.getAllUsersFromDatabase();
                   String jsonString = new Gson().toJson(userList);
                   byte[] array = jsonString.getBytes();
                   outputStream.write(array, 0, array.length);
                   break;
               }
               catch (Exception e)
               {
                   e.printStackTrace();
               }
           }

           case SAVE_USER: {
               System.out.println("Adding user to the database: " + networkRequest.getObject());
               try
               {
                   User newUser = gson.fromJson(networkRequest.getObject(), User.class);
                   boolean response = userDAO.saveUser(newUser);

                   String jsonString = new Gson().toJson(response);
                   byte[] array = jsonString.getBytes();
                   outputStream.write(array, 0, array.length);
                   break;
               }
               catch (Exception e)
               {
                   e.printStackTrace();
               }
           }

           case DELETE_USER: {
               System.out.println("Deleting User: " + networkRequest.getObject());
               try
               {
                   String userId = gson.fromJson(networkRequest.getObject(), String.class);
                   boolean response = userDAO.deleteUser(userId);
                   String jsonString = new Gson().toJson(response);
                   byte[] array = jsonString.getBytes();
                   outputStream.write(array, 0, array.length);
                   break;
               }
               catch (Exception e)
               {
                   e.printStackTrace();
               }
           }
       }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}