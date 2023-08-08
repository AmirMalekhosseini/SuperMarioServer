package Controller.Database;

import Controller.Utils.HibernateUtils;
import Model.Game.OnlineUser;
import Model.Item.Online.Bag;
import MyProject.MyProject;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SaveData {

    private static SaveData saveData;

    private SaveData() {

    }

    public static SaveData getInstance() {
        if (saveData == null) {
            saveData = new SaveData();
        }
        return saveData;
    }

    public void saveToDatabase(String username) {

        OnlineUser user = MyProject.getInstance().getDatabase().getAllUsers().get(username);
        user.setUsername(username);
        user.getUserData().getUserHighScore().setUsername(username);

        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            OnlineUser testUser = session.get(OnlineUser.class, username);
            if (testUser == null) {// User is not in Database
                ArrayList<Bag> userBags = new ArrayList<>(user.getUserBags());
                for (Bag bag : userBags) {
                    session.save(bag);
                }
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
            } else {// User is Already in Database:
                deleteRowsFromBlockList(session, user.getUsername());
                ArrayList<Bag> userBags = new ArrayList<>(user.getUserBags());
                for (Bag bag : userBags) {
                    session.merge(bag);
                }
                session.beginTransaction();
                session.merge(user);
                session.getTransaction().commit();
            }

        }

    }

    private void deleteRowsFromBlockList(Session session, String username) {

        session.beginTransaction();

        String hql = "FROM OnlineUser e WHERE :username MEMBER OF e.blockList";
        Query<OnlineUser> query = session.createQuery(hql, OnlineUser.class);
        query.setParameter("username", username);

        List<OnlineUser> entities = query.getResultList();

        for (OnlineUser entity : entities) {
            // To Remove all Rows with that Username
            entity.getBlockList().remove(username);
            session.update(entity);
        }

        session.getTransaction().commit();


    }

}
