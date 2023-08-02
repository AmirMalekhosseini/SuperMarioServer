package Model.NetworkCommunication;

import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.PackMessage;
import Model.NetworkCommunication.MessageHandler.MessageHandler;
import MyProject.MyProject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread {

    private final ObjectMapper objectMapper;
    private final Socket socket;
    private final Scanner receiver;
    private final PrintWriter sender;
    private volatile String username = "";
    private volatile boolean isClientOnline;
    private boolean isSentInitPack;

    public ClientHandler(Socket clientSocket) throws IOException {

        socket = clientSocket;
        receiver = new Scanner(clientSocket.getInputStream());
        sender = new PrintWriter(clientSocket.getOutputStream(), true);
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        isClientOnline = true;
        // Start Receiving Thread
        start();
    }

    @Override
    public void run() {
        try {
            // Read Messages from the Client:
            while (isClientOnline && receiver.hasNextLine()) {
                String receivedJson = receiver.nextLine();
                if (receivedJson == null) {
                    break;
                }

                // Deserialize the JSON message to the appropriate object
                Message receivedMessage = JsonUtils.deserializeFromJson(receivedJson, Message.class);

                // Process the message based on its type
                processMessage(receivedMessage);

                if (!username.equals("") && !isSentInitPack) {
                    PackMessage packMessage = new PackMessage();
                    packMessage.setPacks(MyProject.getInstance().getDatabase().getPacks());
                    sendMessage(packMessage);
                    System.out.println("Sent Init Pack");
                    isSentInitPack = true;
                }

            }

            // Client Disconnected:
            socket.close();
            System.out.println("disconnected");
            MyProject.getInstance().getDatabase().getClientHandlersMap().remove(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to process the received message based on its type
    private void processMessage(Message message) {
        MessageHandler handler = MyProject.getInstance().getDatabase().getMessageHandlerMap().get(message.getMessageType());
        handler.handleMessage(message);
    }

    // Method to send a message to the client
    public synchronized void sendMessage(Message message) throws IOException {
        String jsonString = JsonUtils.serializeToJson(message);
        sender.println(jsonString);
    }

    public synchronized Scanner getReceiver() {
        return receiver;
    }

    public synchronized PrintWriter getSender() {
        return sender;
    }

    public synchronized String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public synchronized ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public synchronized boolean isClientOnline() {
        return isClientOnline;
    }

    public synchronized void setClientOnline(boolean clientOnline) {
        isClientOnline = clientOnline;
    }
}
