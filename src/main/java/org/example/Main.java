package org.example;

import Controller.Utils.HibernateUtils;
import Model.Game.OnlineUser;
import Model.Game.UserData;
import Model.NetworkCommunication.Server;
import MyProject.MyProject;
import View.StoreFrame;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

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

    }
}

// Check Connection to Database Server:
//        Test test = new Test();
//        test.setName("amir1");
//        test.setPassword("1234");
//        ArrayList<Integer> testList = new ArrayList<>();
//        testList.add(1);
//        testList.add(2);
//        test.setIntegerList(testList);
//        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
//            System.out.println("Connected to the database successfully.");
//            Test test1 = session.get(Test.class, 1);
//            System.out.println(test1.getIntegerList());
//            session.beginTransaction();
//            session.save(test);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            System.err.println("Failed to connect to the database.");
//            e.printStackTrace();
//        }