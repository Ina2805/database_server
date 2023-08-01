import Networking.ServerSocketHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocketHandler serverSocketHandler = new ServerSocketHandler();
        serverSocketHandler.startServer();
    }
}