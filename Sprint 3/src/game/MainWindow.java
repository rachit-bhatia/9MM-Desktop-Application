package game;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

    /**
     * Singleton instance of the class
     */
    private static MainWindow mainWindow;

    /**
     * accessing the dimensions of the screen
     */
    private Dimension screenDimension;

    /**
     * instantiating the panel for the main window display
     */
    private JPanel mainPanel;

    /**
     * text label for player 1
     */
    private JLabel playerLabel1;

    /**
     * text label for player 2
     */
    private JLabel playerLabel2;

    /**
     * text label for state of move of player 1
     */
    private JLabel playerStateOfMoveLabel1;

    /**
     * text label for state of move of player 2
     */
    private JLabel playerStateOfMoveLabel2;


    /**
     * private constructor
     */
    private MainWindow(){
        screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        mainPanel  = new JPanel();
    }

    /**
     * static getter
     * @return singleton instance
     */
    public static MainWindow getInstance(){
        if ( mainWindow == null) {
            mainWindow = new MainWindow();
            mainWindow.setTitle("9 Men's Morris");
        }
        return mainWindow;
    }

    public static void regenerate() {
        if (mainWindow != null) {
            mainWindow = new MainWindow();
            mainWindow.setTitle("9 Men's Morris");
        }
    }

    public void setupHomePageWindow(){
        /**
         * Setting the main home page preferences
         */
        //setting the dimensions of the main window frame based on the screen size
        mainWindow.setBounds(0, 0, screenDimension.width, screenDimension.height);  //window size is of the screen size
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);  //ensuring opening of application in fullscreen
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //default closing action

        MainPagePanel mainPage = new MainPagePanel(screenDimension);
        mainWindow.getContentPane().add(mainPage);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    public void setupPlayPageWindow(){
        /**
         * sets the game page with the board and tokens
         */
        //setting preferences for the main window panel
        mainPanel.setBackground(new Color(77, 100, 110).brighter());
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(screenDimension.width, screenDimension.height));

        //setting preferences for the title text and adding it
        JLabel titleText = new JLabel("9 MEN'S MORRIS");
        titleText.setForeground(new Color(84, 56, 23));
        titleText.setFont(new Font("Times New Roman", Font.BOLD, 50));
        titleText.setVisible(true);
        titleText.setBounds((screenDimension.width - 400) / 2, 40, 600, 50);
        mainPanel.add(titleText);
        addAllItems();
    }


    public void addAllItems(){
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


        Game game = Game.getInstance();

        //Adding both player's Tokens to the Panel container
        int tokenSpacing = 0;
        for (int i = 1; i <= 3; i++){
            Token tokenPlayer1 = new Token((int) (screenDimension.width/4.5),screenDimension.height/7 + tokenSpacing, Color.BLACK);
            mainPanel.add(tokenPlayer1);
            game.getPlayer1().addToken(tokenPlayer1);

            Token tokenPlayer2 = new Token((3*screenDimension.width)/4,screenDimension.height/7 + tokenSpacing, Color.WHITE);
            mainPanel.add(tokenPlayer2);
            game.getPlayer2().addToken(tokenPlayer2);

            tokenSpacing += boardHeight/9 - 5;;
        }

        //y-coordinate of the player level based on board height
        int textYCoord = boardHeight + boardSpacing - 30;

        //adding the player 1 text label under all black tokens
        playerLabel1 = new JLabel("Player 1");
        playerLabel1.setForeground(Color.BLACK);    //color of the text
        playerLabel1.setFont(new Font("Player 1", Font.BOLD, 21));   //size and style of the text
        playerLabel1.setVisible(true);
        playerLabel1.setBounds((int) (screenDimension.width/4.5 - 15), textYCoord, 100, 40);   //size of the label bounds
        playerLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel1.setVerticalAlignment(SwingConstants.CENTER);
        mainPanel.add(playerLabel1);

        int ySpacingBelowLabel = 30;
        playerStateOfMoveLabel1  = new JLabel("PLACING");
        playerStateOfMoveLabel1.setForeground(Color.BLACK);
        playerStateOfMoveLabel1.setFont(new Font("PLACING", Font.HANGING_BASELINE, 20));   //size and style of the text
        playerStateOfMoveLabel1.setVisible(true);
        playerStateOfMoveLabel1.setBounds((int) (screenDimension.width/4.5 - 15),textYCoord + ySpacingBelowLabel,100,40);
        playerStateOfMoveLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        playerStateOfMoveLabel1.setVerticalAlignment(SwingConstants.CENTER);
        mainPanel.add(playerStateOfMoveLabel1);


        //adding the player 2 text label under all white tokens
        playerLabel2 = new JLabel("Player 2");
        playerLabel2.setForeground(Color.WHITE);
        playerLabel2.setFont(new Font("Player 2", Font.BOLD, 21));
        playerLabel2.setVisible(true);
        playerLabel2.setBounds((3*screenDimension.width)/4 - 15, textYCoord, 100, 40);
        playerLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel2.setVerticalAlignment(SwingConstants.CENTER);
        mainPanel.add(playerLabel2);

        playerStateOfMoveLabel2  = new JLabel("PLACING");
        playerStateOfMoveLabel2.setForeground(Color.WHITE);
        playerStateOfMoveLabel2.setFont(new Font("PLACING", Font.HANGING_BASELINE, 20));   //size and style of the text
        playerStateOfMoveLabel2.setVisible(true);
        playerStateOfMoveLabel2.setBounds((3*screenDimension.width)/4 - 15,textYCoord + ySpacingBelowLabel,100,40);
        playerStateOfMoveLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        playerStateOfMoveLabel2.setVerticalAlignment(SwingConstants.CENTER);
        mainPanel.add(playerStateOfMoveLabel2);

        // adding the background to the game
        URL image = MainWindow.class.getClassLoader().getResource("gamepage.jpeg");
        JLabel background = new JLabel(new ImageIcon(image));
        background.setBounds(0, 0, screenDimension.width, screenDimension.height);
        mainPanel.add(background);

        //setting the game board to be displayed at the bottom of all other elements
        mainPanel.setComponentZOrder(gameBoard, mainPanel.getComponentCount()-1);
        mainPanel.setComponentZOrder(background, mainPanel.getComponentCount()-1);

        // Adding the panel container that has all the tokens and intersection points to the frame
        mainWindow.getContentPane().add(mainPanel);
        mainWindow.setVisible(true);    //application window visibility
    }

    /**
     * get Player 1 label
     * @return Player 1 label
     */
    public JLabel getPlayerLabel1(){
        return  playerLabel1;
    }

    /**
     * get Player 2 label
     * @return Player 2 label
     */
    public JLabel getPlayerLabel2(){
        return  playerLabel2;
    }

    /**
     * get Player 1 state of move label
     * @return Player 1 state of move label
     */
    public JLabel getPlayerStateOfMoveLabel1() {
        return playerStateOfMoveLabel1;
    }

    /**
     * get Player 2 state of move label
     * @return Player 2 state of move label
     */
    public JLabel getPlayerStateOfMoveLabel2(){
        return playerStateOfMoveLabel2;
    }
}
