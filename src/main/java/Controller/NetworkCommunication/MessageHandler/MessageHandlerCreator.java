package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.LobbyChatMessage;
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
        messageHandlerMap.put(MessageType.LOG_OUT, new LogOutHandler());
        messageHandlerMap.put(MessageType.BAG_ITEM_MESSAGE, new BagItemHandler());
        messageHandlerMap.put(MessageType.CHOOSE_BAG_MESSAGE, new ChooseBagHandler());
        messageHandlerMap.put(MessageType.CHAT_MESSAGE, new ChatMessageHandler());
        messageHandlerMap.put(MessageType.FRIEND_REQUEST, new FriendRequestHandler());
        messageHandlerMap.put(MessageType.FRIEND_REQUEST_ANSWER, new FriendAnswerHandler());
        messageHandlerMap.put(MessageType.GAME_REQUEST, new GameRequestHandler());
        messageHandlerMap.put(MessageType.GAME_REQUEST_ANSWER, new GameRequestAnswerHandler());
        messageHandlerMap.put(MessageType.ONLINE_SHOP_MESSAGE, new OnlineShopMessageHandler());
        messageHandlerMap.put(MessageType.NEW_LOBBY_MESSAGE, new NewLobbyMessageHandler());
        messageHandlerMap.put(MessageType.JOIN_LOBBY_MESSAGE, new JoinLobbyHandler());
        messageHandlerMap.put(MessageType.LOBBY_CHAT_MESSAGE, new LobbyChatHandler());
        messageHandlerMap.put(MessageType.REMOVE_LOBBY_MEMBER, new RemoveLobbyMemberHandler());
        messageHandlerMap.put(MessageType.ADD_ADMIN_MESSAGE, new AddAdminHandler());
        messageHandlerMap.put(MessageType.REMOVE_ADMIN_MESSAGE, new RemoveAdminHandler());

        // TO be Continued...

        return messageHandlerMap;
    }

}
