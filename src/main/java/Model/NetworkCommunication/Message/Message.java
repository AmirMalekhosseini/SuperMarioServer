package Model.NetworkCommunication.Message;

import Model.Game.OnlineUser;

public abstract class Message {


    private OnlineUser senderUser;

    public Message(OnlineUser senderUser) {
        this.senderUser = senderUser;
    }

    public OnlineUser getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(OnlineUser senderUser) {
        this.senderUser = senderUser;
    }
}
