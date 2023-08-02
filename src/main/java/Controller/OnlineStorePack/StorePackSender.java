package Controller.OnlineStorePack;

import Model.Object.PackItems;
import Model.OnlineStorePack.Pack;

public class StorePackSender {

    public void sendPack(Pack pack) {

        int currentY = 0;
            for (PackItems projectPackItem : pack.packItems) {
                projectPackItem.setX(0);
                projectPackItem.setY(currentY);
                currentY += 100;
            }
        // ToDo: Send through Server.
    }

}
