package org.example;

import Model.NetworkCommunication.Server;
import MyProject.MyProject;
import View.StoreFrame;

import javax.swing.*;
import java.io.IOException;

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