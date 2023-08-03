package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.MessageType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageHandlerCreator {

    private static MessageHandlerCreator creator;

    private MessageHandlerCreator() {
    }

    public static MessageHandlerCreator getInstance() {
        if (creator == null) {
            creator = new MessageHandlerCreator();
        }
        return creator;
    }

    public Map<MessageType, MessageHandler> createMessageHandler() {

        Map<MessageType, MessageHandler> messageHandlerMap = new ConcurrentHashMap<>();
        messageHandlerMap.put(MessageType.SIGN_IN, new SignInHandler());
        messageHandlerMap.put(MessageType.SIGN_UP, new SignUpHandler());
        messageHandlerMap.put(MessageType.BAG_ITEM_MESSAGE, new BagItemHandler());
        messageHandlerMap.put(MessageType.CHOOSE_BAG_MESSAGE, new ChooseBagHandler());
//        messageHandlerMap.put(MessageType.SHOP_MESSAGE, new ShopMessageHandler());
//        messageHandlerMap.put(MessageType.CHAT_MESSAGE, new ChatMessageHandler());
//        messageHandlerMap.put(MessageType.FRIEND_REQUEST, new FriendRequestHandler());
//        messageHandlerMap.put(MessageType.LOG_OUT, new LogOutHandler());

        // TO be Continued...

        return messageHandlerMap;
    }

}