package game;

import javax.swing.*;
import java.awt.*;

/**
 * A MainWindow of the Application
 * <p>
 * Created  by Rachit Bhatia
 *
 * @author Rachit Bhatia
 * Modified by: Shoumil
 */
public abstract class MainWindow {

    /**
     * instantiating the main application window
     */
    private static JFrame mainWindow = new JFrame("9 Men's Morris");

    /**
     * accessing the dimensions of the screen
     */
    private static Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * instantiating the panel for the main window display
     */
    private static JPanel mainPanel = new JPanel();



    public static void setupWindow(){
        /**
         * Setting the window size and preferences
         */
        //setting the dimensions of the main window frame based on the screen size
        mainWindow.setSize(screenDimension.width, screenDimension.height);  //window size is of the screen size
        mainWindow.setLocation(0,0);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //default closing action

        //setting preferences for the main window panel
        mainPanel.setBackground(new Color(77, 100, 110).brighter());
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(screenDimension.width, screenDimension.height));

        //setting preferences for the title text and adding it
        JLabel titleText = new JLabel("9 Men's Morris");
        titleText.setForeground(new Color(110, 190, 180).darker().darker());
        titleText.setFont(new Font("Title", Font.BOLD, 50));
        titleText.setVisible(true);
        titleText.setBounds((int) ((screenDimension.width - 400) / 2), 40, 400, 50);
        mainPanel.add(titleText);
    }


    public static void addAllItems(){
        /**
         * Creation and addition of the all items (tokens, intersections, game board) into the game
         */

        //creation and addition of game board into the main window panel
        GameBoard gameBoard = GameBoard.getInstance();
        gameBoard.setDimensionsOfBoard(screenDimension);
        gameBoard.addIntersections(gameBoard.getWidth(),gameBoard.getHeight());  //adding intersection points
        mainPanel.add(gameBoard);

        //getting board dimensions to place other components according to the board height
        int boardSpacing = gameBoard.getBounds().y;
        int boardHeight = gameBoard.getBounds().height;


        //Adding both player's Tokens to the Panel container
        int tokenSpacing = 0;
        for (int i = 1; i <= 5; i++){
            Token tokenPlayer1 = new Token((int) (screenDimension.width/4.5),screenDimension.height/7 + tokenSpacing, Color.BLACK);
            mainPanel.add(tokenPlayer1);
            Game.getInstance().getPlayer1().addToken(tokenPlayer1);

            Token tokenPlayer2 = new Token((3*screenDimension.width)/4,screenDimension.height/7 + tokenSpacing, Color.WHITE);
            mainPanel.add(tokenPlayer2);
            Game.getInstance().getPlayer2().addToken(tokenPlayer2);

            tokenSpacing += boardHeight/9 - 5;;
        }

        //y-coordinate of the player level based on board height
        int textYCoord = boardHeight + boardSpacing - 30;

        //adding the player 1 text label under all black tokens
        JLabel playerLabel1 = new JLabel("Player 1");
        playerLabel1.setForeground(Color.BLACK);    //color of the text
        playerLabel1.setFont(new Font("Player 1", Font.BOLD, 20));   //size and style of the text
        playerLabel1.setVisible(true);
        playerLabel1.setBounds((int) (screenDimension.width/4.5 - 15), textYCoord, 100, 20);   //size of the label bounds
        mainPanel.add(playerLabel1);


        //adding the player 2 text label under all white tokens
        JLabel playerLabel2 = new JLabel("Player 2");
        playerLabel2.setForeground(Color.WHITE);
        playerLabel2.setFont(new Font("Player 2", Font.BOLD, 20));
        playerLabel2.setVisible(true);
        playerLabel2.setBounds((3*screenDimension.width)/4 - 15, textYCoord, 100, 20);
        mainPanel.add(playerLabel2);

        //setting the game board to be displayed at the bottom of all other elements
        mainPanel.setComponentZOrder(gameBoard, mainPanel.getComponentCount()-1);

        // Adding the panel container that has all the tokens and intersection points to the frame
        mainWindow.getContentPane().add(mainPanel);
        mainWindow.setVisible(true);    //application window visibility
    }
}
