package Model.NetworkCommunication;

import MyProject.MyProject;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Server server;
    private final int PORT = 12345;
    private final ServerData serverData;

    private Server() {
        serverData = new ServerData();
    }

    public static synchronized Server getInstance() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }

    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Socket clientSocket = serverSocket.accept();

            // Create a new thread to handle the client
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            // Set ClientHandler Username after SignIn.
            MyProject.getInstance().getDatabase().getClientHandlersMap().put("", clientHandler);

        }

    }

    public ServerData getServerData() {
        return serverData;
    }
}
