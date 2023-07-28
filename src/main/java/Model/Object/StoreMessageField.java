package Model.Object;

import MyProject.MyProjectData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoreMessageField extends JTextField {

    private String placeholderText;

    public StoreMessageField(String placeholderText, int x, int y) {
        this.placeholderText = placeholderText;
        setForeground(Color.GRAY);
        setText(placeholderText);
        setFont(MyProjectData.getProjectData().getFont15());
        setBounds(x, y, 100, 40);
        setHorizontalAlignment(SwingConstants.CENTER);
        addListeners();
    }

    private void addListeners() {
        // Add a focus listener to handle initial text behavior.
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholderText)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholderText);
                    setForeground(Color.GRAY);
                }
            }
        });

        // Add a mouse listener to handle clearing the text when clicked.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (getText().equals(placeholderText)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }
        });
    }

    public String getPlaceholderText() {
        return placeholderText;
    }

    public void setPlaceholderText(String placeholderText) {
        this.placeholderText = placeholderText;
    }
}
