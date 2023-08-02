package MyProject;

import Controller.OnlineStorePack.StorePackSender;

public class MyProject {

    private static MyProject myProject;

    private Database database;

    private MyProject() {
        database = new Database();
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
