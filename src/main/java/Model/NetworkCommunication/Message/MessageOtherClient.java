package Model.NetworkCommunication.Message;

import Model.Game.OnlineUser;

public class MessageOtherClient extends Message {


    private OnlineUser receiverUser;

    public MessageOtherClient(OnlineUser senderUser, OnlineUser receiverUser) {
        super(senderUser);
        this.receiverUser = receiverUser;
    }

    public OnlineUser getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(OnlineUser receiverUser) {
        this.receiverUser = receiverUser;
    }
}
