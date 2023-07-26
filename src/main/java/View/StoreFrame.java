package View;

import Model.Item.Online.HealItem;
import Model.Item.Online.InvisibleItem;
import Model.Item.Online.SpeedItem;
import Model.Mairo.Online.DamageBomb;
import Model.Mairo.Online.Hammer;
import Model.Mairo.Online.SpeedBomb;
import Model.Object.StoreButton;
import MyProject.MyProjectData;

import javax.swing.*;
import java.awt.*;

public class StoreFrame extends JFrame {


    protected StoreButton healPotionButton;
    protected StoreButton speedPotionButton;
    protected StoreButton invisiblePotionButton;
    protected StoreButton hammerButton;
    protected StoreButton damageBombButton;
    protected StoreButton speedBombButton;
    protected HealItem healPotionLabel;
    protected InvisibleItem invisiblePotionLabel;
    protected SpeedItem speedPotionLabel;
    protected Hammer hammer;
    protected DamageBomb damageBomb;
    protected SpeedBomb speedBomb;
    protected JPanel panel;
    protected JButton backButton;

    public StoreFrame() {
        super("Store Frame");
        setLayout(null);
        setSize(1000, 800);
        setBackground(Color.RED);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(MyProjectData.getProjectData().getGameIcon().getImage());
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 800);
        panel.setVisible(true);
        setContentPane(panel);

        backButton = new JButton("Back");
        backButton.setBounds(920, 0, 70, 40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusable(false);
        backButton.setFont(MyProjectData.getProjectData().getFont15());
        panel.add(backButton);

        init();

    }

    private void init() {

        healPotionButton = new StoreButton(850, 100, panel);
        speedPotionButton = new StoreButton(850, 200, panel);
        invisiblePotionButton = new StoreButton(850, 300, panel);
        hammerButton = new StoreButton(850, 400, panel);
        damageBombButton = new StoreButton(850, 500, panel);
        speedBombButton = new StoreButton(850, 600, panel);

        healPotionLabel = new HealItem(50, 90);
        panel.add(healPotionLabel);

        speedPotionLabel = new SpeedItem(50, 190);
        panel.add(speedPotionLabel);

        invisiblePotionLabel = new InvisibleItem(50, 290);
        panel.add(invisiblePotionLabel);

        hammer = new Hammer(35, 390, true);
        panel.add(hammer);

        damageBomb = new DamageBomb(35, 490);
        panel.add(damageBomb);

        speedBomb = new SpeedBomb(35, 590);
        panel.add(speedBomb);

    }

    private void addButtonAction() {
        // ToDo
    }

}
