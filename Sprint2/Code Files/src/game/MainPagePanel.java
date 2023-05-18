package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * class for the home page of the game app
 */
public class MainPagePanel extends JPanel {

    public MainPagePanel(Dimension screenDimension){

        //setting preferences for the main window panel
        setBackground(new Color(77, 100, 110).brighter());
        setLayout(null);
        setPreferredSize(screenDimension);

        // Button for Player Vs Player
        JButton pvpButton = createPlayButton(screenDimension, "PLAY NOW");
        pvpButton.setBounds((screenDimension.width - 300) / 2, screenDimension.height/2, 300, 60);

        MainWindow mainWindow = MainWindow.getInstance();

        //defining the action of displaying game board page once button is clicked
        pvpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.getContentPane().removeAll();

                Game.getInstance().setPlayers(new HumanPlayer(),new HumanPlayer());
                mainWindow.setupPlayPageWindow();
                mainWindow.getContentPane().revalidate();
                mainWindow.getContentPane().repaint();

                Thread backgroundThread = new PerformThread();
                backgroundThread.start();
            }

        });

        mainWindow.pack();
    }

    /**
     * method for creation of button
     */
    public JButton createPlayButton(Dimension screenDimension, String text){
        JButton playButton = new JButton(text);
        Font pvpFont = new Font("Times New Roman", Font.BOLD, 30); //setting font size for button text
        playButton.setFont(pvpFont);

        //setting button's background and border
        playButton.setBackground(new Color(211, 179, 134));
        playButton.setOpaque(true);
        playButton.setFocusPainted(false);
        playButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));

        this.add(playButton);
        playButton.setVisible(true);

        return playButton;
    }


}


