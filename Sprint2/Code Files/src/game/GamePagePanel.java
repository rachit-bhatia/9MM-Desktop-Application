package game;

import javax.swing.*;
import java.awt.*;

public class GamePagePanel extends JPanel {

    private static Dimension screenDimension;

    public GamePagePanel(Dimension dimension) {

        screenDimension = dimension;
        //setting preferences for the main window panel
        setBackground(new Color(77, 100, 110).brighter());
        setLayout(null);
        setPreferredSize(new Dimension(screenDimension.width, screenDimension.height));

        //setting preferences for the title text and adding it
        JLabel titleText = new JLabel("9 Men's Morris");
        titleText.setForeground(new Color(110, 190, 180).darker().darker());
        titleText.setFont(new Font("Title", Font.BOLD, 50));
        titleText.setVisible(true);
        titleText.setBounds((int) ((screenDimension.width - 400) / 2), 40, 400, 50);
        this.add(titleText);
        addAllItems();
    }


    private void addAllItems() {
        /**
         * Creation and addition of the all items (tokens, intersections, game board) into the game
         */

        //creation and addition of game board into the main window panel
        GameBoard gameBoard = GameBoard.getInstance();
        gameBoard.setDimensionsOfBoard(screenDimension);
        gameBoard.addIntersections(gameBoard.getWidth(), gameBoard.getHeight());  //adding intersection points
        this.add(gameBoard);

        //getting board dimensions to place other components according to the board height
        int boardSpacing = gameBoard.getBounds().y;
        int boardHeight = gameBoard.getBounds().height;


        //Adding both player's Tokens to the Panel container
        int tokenSpacing = 0;
        for (int i = 1; i <= 4; i++) {
            Token tokenPlayer1 = new Token((int) (screenDimension.width / 4.5), screenDimension.height / 7 + tokenSpacing, Color.BLACK);
            this.add(tokenPlayer1);
            Game.getInstance().getPlayer1().addToken(tokenPlayer1);

            Token tokenPlayer2 = new Token((3 * screenDimension.width) / 4, screenDimension.height / 7 + tokenSpacing, Color.WHITE);
            this.add(tokenPlayer2);
            Game.getInstance().getPlayer2().addToken(tokenPlayer2);

            tokenSpacing += boardHeight / 9 - 5;
            ;
        }

        //y-coordinate of the player level based on board height
        int textYCoord = boardHeight + boardSpacing - 30;

        //adding the player 1 text label under all black tokens
        JLabel playerLabel1 = new JLabel("Player 1");
        playerLabel1.setForeground(Color.BLACK);    //color of the text
        playerLabel1.setFont(new Font("Player 1", Font.BOLD, 20));   //size and style of the text
        playerLabel1.setVisible(true);
        playerLabel1.setBounds((int) (screenDimension.width / 4.5 - 15), textYCoord, 100, 20);   //size of the label bounds
        this.add(playerLabel1);


        //adding the player 2 text label under all white tokens
        JLabel playerLabel2 = new JLabel("Player 2");
        playerLabel2.setForeground(Color.WHITE);
        playerLabel2.setFont(new Font("Player 2", Font.BOLD, 20));
        playerLabel2.setVisible(true);
        playerLabel2.setBounds((3 * screenDimension.width) / 4 - 15, textYCoord, 100, 20);
        this.add(playerLabel2);

        //setting the game board to be displayed at the bottom of all other elements
        this.setComponentZOrder(gameBoard, this.getComponentCount() - 1);

    }
}
