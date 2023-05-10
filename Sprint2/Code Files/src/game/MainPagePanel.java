package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPagePanel extends JPanel {




    public MainPagePanel(Dimension screenDimension){

        //setting preferences for the main window panel
        setBackground(new Color(77, 100, 110).brighter());
        setLayout(null);
        setPreferredSize(screenDimension);

        // Button for Player Vs Player
        JButton button = new JButton("Player VS Player");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.mainWindow.getContentPane().removeAll();


                Game.getInstance().setPlayers(new HumanPlayer(),new HumanPlayer());
                JPanel gamePanel = new GamePagePanel(screenDimension);
                MainWindow.mainWindow.getContentPane().add(gamePanel);
                MainWindow.mainWindow.getContentPane().revalidate();
                MainWindow.mainWindow.getContentPane().repaint();

                // TODO: Fix the issue ( will no longer be able to switch to Game Page if this is not commented out )
//                Game.getInstance().run();
            }

        });

        button.setVisible(true);
        button.setBounds((int) ((screenDimension.width - 400) / 2), 40, 400, 50);
        this.add(button);

    }
}
