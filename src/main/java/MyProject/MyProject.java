package MyProject;


import java.util.ArrayList;

public class MyProject {

    private static MyProject myProject;

    private Database database;

    private MyProject() {
        database = new Database();
        for (String user : database.getAllUsers().keySet()) {
            database.getMessageQueueMap().put(user, new ArrayList<>());
        }
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
