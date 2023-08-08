package Model.NetworkCommunication;

import Controller.OnlineStorePack.StorePackSender;
import Controller.Utils.JsonUtils;
import Model.Game.OnlineUser;
import Model.NetworkCommunication.Message.InitMessage;
import Model.NetworkCommunication.Message.Message;
import Controller.NetworkCommunication.MessageHandler.MessageHandler;
import Model.NetworkCommunication.Message.MessageType;
import Model.OnlineStorePack.Pack;
import MyProject.MyProject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler extends Thread {

    private final ObjectMapper objectMapper;
    private final Socket socket;
    private final Scanner receiver;
    private final PrintWriter sender;
    private volatile String username = "";
    private volatile boolean isClientOnline;
    private boolean isSentInitData;

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

                // Send Init Data:
                if (!username.equals("") && !isSentInitData) {
                    sendInitData();
                    isSentInitData = true;

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

    private void sendInitData() throws IOException {

        // Send Init Pack
        for (Pack pack : MyProject.getInstance().getDatabase().getPacks()) {
            StorePackSender.getInstance().sendPack(pack);
        }

        OnlineUser signedInUser = MyProject.getInstance().getDatabase().getAllUsers().get(username);
        InitMessage initMessage = new InitMessage();
        initMessage.setMessageType(MessageType.INIT_MESSAGE);
        initMessage.setUserChatScreens(signedInUser.getUserChatScreens());
        ArrayList<String> userFriendsList = new ArrayList<>(signedInUser.getUserFriends());
        initMessage.setUserFriends(userFriendsList);
        initMessage.setClientItems(signedInUser.getUserOnlineItems());
        initMessage.setUserData(MyProject.getInstance().getDatabase().getAllUsers().get(username).getUserData());
        sendMessage(initMessage);

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
