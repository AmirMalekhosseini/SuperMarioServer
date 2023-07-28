package org.example;

import MyProject.MyProject;
import View.StoreFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StoreFrame().setVisible(true));
        MyProject.getInstance();
    }
}