package src;

import javax.swing.*;
import java.awt.*;

public abstract class MainWindow {

    private static JFrame mainWindow = new JFrame("9 Men's Morris");  //initialising the main application window;
    private static Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();  //accessing the dimensions of the screen;
    private static JPanel mainPanel = new JPanel();    //instantiating the panel for the main window display;

    private static GameBoard gameBoard;


    public static void setupWindow(){
        /**
         * Setting the window size and preferences
         */
        //setting the dimensions of the main window frame based on the screen size
        mainWindow.setSize(screenDimension.width, screenDimension.height);  //window size is of the screen size
        mainWindow.setLocation(0,0);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //default closing action

        //setting preferences for the main window panel
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(screenDimension.width, screenDimension.height));
    }


    public static void addAllItems(){
        /**
         * Creation and addition of the all items (tokens, intersections, game board) into the game
         */

        //Adding both player's Tokens to the Panel container
        int tokenSpacing = 0;
        for (int i = 1; i <= 9; i++){
            mainPanel.add(new Token(screenDimension.width/8,screenDimension.height/8 + tokenSpacing, Color.BLACK));
            mainPanel.add(new Token(screenDimension.width - screenDimension.width/7,screenDimension.height/8 + tokenSpacing, Color.WHITE));
            tokenSpacing += 70;
        }

        //creation and addition of game board into the main window panel
        gameBoard = GameBoard.getInstance();
        gameBoard.setDimensionsOfBoard(screenDimension);
        gameBoard.addIntersections(gameBoard.getWidth(),gameBoard.getHeight());  //adding intersection points
        mainPanel.add(gameBoard);

        // Adding the panel container that has all the tokens and intersection points to the frame
        mainWindow.getContentPane().add(mainPanel);
        mainWindow.setVisible(true);    //application window visibility
    }
}
