package MyProject;

import Controller.OnlineStorePack.StorePackCreator;
import Controller.OnlineStorePack.StorePackSender;
import Model.OnlineStorePack.Pack;
import java.util.ArrayList;

public class MyProject {

    private static MyProject myProject;

    public ArrayList<Pack> packs;
    public StorePackCreator storePackCreator;
    public StorePackSender storePackSender;

    private MyProject() {
        packs = new ArrayList<>();
        storePackCreator = new StorePackCreator();
        storePackSender = new StorePackSender();
    }

    public static MyProject getInstance() {
        if (myProject == null) {
            myProject = new MyProject();
        }
        return myProject;
    }

}
