package Model.Item.Online;

import Model.Item.ItemsInGame;
import Model.Mairo.Mario;

public abstract class OnlineItems extends ItemsInGame {


    public abstract void action(Mario mario);
    public abstract void removeAction(Mario mario);

}
