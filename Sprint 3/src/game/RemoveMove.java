package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * RemoveMove as a mouseListener to remove tokens
 * <p>
 * Created by Rachit Bhatia
 *
 * @author Rachit Bhatia
 * Modified by: Shoumil, Tan Jun Yu
 */

public class RemoveMove extends MouseAdapter {

    /**
     * token to be removed
     */
    private Token tokenInstance;

    /**
     * Constructor
     * @param tokenInstance token to remove
     */
    public RemoveMove(Token tokenInstance) {
        this.tokenInstance = tokenInstance;
    }

    /**
     * Resets the remaining tokens and board to their original appearance and removed the selected token from the game
     * @param cursor the mouse pointer
     */
    @Override
    public void mouseClicked(MouseEvent cursor) {

        GameBoard gameBoard = GameBoard.getInstance();
        //resetting token appearance to remove red highlight of border after selection has been made
        for (IntersectionPoint position : gameBoard.getIntersectionPoints()){
            Token curToken = position.getTokenInstance();
            if (curToken != null){
                curToken.toRemove = false ; //removal state false
                curToken.repaint();
            }
        }

        gameBoard.remove(tokenInstance);  //removing token from game board
        tokenInstance.getIntersectionPoint().removeToken();  //removing token from intersection point
        tokenInstance.getPlayer().getTokenList().remove(tokenInstance); //removing token from player's list


        Game game = Game.getInstance();
        for( Token token: game.getPlayer1().getTokenList()){
            token.removeTemporaryListener();
        }

        for( Token token: game.getPlayer2().getTokenList()){
            token.removeTemporaryListener();
        }

        //updating the UI state of the board
        gameBoard.revalidate();
        gameBoard.repaint();

        game.incrementTurn(); //turn increment only after token has been removed

        //checking to remove mills when token removed
        MillChecker.getInstance().checkIfTokenInMill(tokenInstance);
    }
}



