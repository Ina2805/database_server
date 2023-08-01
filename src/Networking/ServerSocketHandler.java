package Networking;
import DAO.User.IUserDAO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerSocketHandler {

    private final int port = 2910;
    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server listening on " + port + "...");

        while (true) {
            try {
                System.out.println("Waiting for client to connect");
                Socket socket = serverSocket.accept();

                //Passing the socket to the DBSocketHandler for further communication
                DBSocketHandler dbSocketHandler = new DBSocketHandler(socket);
                Thread thread = new Thread(dbSocketHandler);
                thread.setDaemon(true);
                thread.start();
                System.out.println("Client connected to server");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

