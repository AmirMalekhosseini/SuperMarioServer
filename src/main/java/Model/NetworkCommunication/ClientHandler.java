package Model.NetworkCommunication;

import Model.NetworkCommunication.Message.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread {

    private final Socket socket;
    private final Scanner receiver;
    private final PrintWriter sender;
    private String username;
    private final ObjectMapper objectMapper;

    public ClientHandler(Socket clientSocket) throws IOException {

        socket = clientSocket;
        receiver = new Scanner(clientSocket.getInputStream());
        sender = new PrintWriter(clientSocket.getOutputStream(), true);
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // Read the username sent by the client
        try {
            username = receiver.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            // Read Messages from the Client:
            while (true) {
                String receivedJson = receiver.nextLine();
                if (receivedJson == null) {
                    break;
                }

                // Deserialize the JSON message to the appropriate object
                Message receivedMessage = JsonUtils.deserializeFromJson(receivedJson, Message.class);

                // Process the message based on its type
                processMessage(receivedMessage);
            }

            // Client Disconnected:
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to process the received message based on its type
    private void processMessage(Message message) {
        // Add logic here to handle different types of messages
        // For example,switch statement based on the message type
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
}
