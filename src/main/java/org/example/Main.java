package org.example;

import Controller.Utils.HibernateUtils;
import Model.Game.OnlineUser;
import Model.NetworkCommunication.Server;
import MyProject.MyProject;
import View.StoreFrame;

import javax.swing.*;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StoreFrame().setVisible(true));
        MyProject.getInstance();
        try {
            Server.getInstance().start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        {
//            HibernateUtils hibernateUtils = HibernateUtils.getInstance();
//
//            try (Session session = hibernateUtils.getSessionFactory().openSession()) {
//                Transaction transaction = null;
//                try {
//                    transaction = session.beginTransaction();
//
//                    OnlineUser onlineUser = new OnlineUser();
//                    // Set the properties of the OnlineUser object here
//                    onlineUser.setUsername("sample_username");
//                    onlineUser.getUserData().setUsername("sample_username");
//                    // Set other properties as needed
//
//                    session.save(onlineUser);
//
//                    transaction.commit();
//                } catch (Exception ex) {
//                    if (transaction != null) {
//                        transaction.rollback();
//                    }
//                    ex.printStackTrace();
//                }
//            }
//        }

    }
}