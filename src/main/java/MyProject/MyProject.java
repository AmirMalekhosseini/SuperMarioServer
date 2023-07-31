package MyProject;

import Controller.OnlineStorePack.StorePackCreator;
import Controller.OnlineStorePack.StorePackSender;
import Model.OnlineStorePack.Pack;
import java.util.ArrayList;

public class MyProject {

    private static MyProject myProject;

    private Database database;
    public ArrayList<Pack> packs;
    public StorePackCreator storePackCreator;
    public StorePackSender storePackSender;

    private MyProject() {
        database = new Database();
        packs = new ArrayList<>();
        storePackCreator = new StorePackCreator();
        storePackSender = new StorePackSender();
    }

    public static synchronized MyProject getInstance() {
        if (myProject == null) {
            myProject = new MyProject();
        }
        return myProject;
    }

    public synchronized Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
