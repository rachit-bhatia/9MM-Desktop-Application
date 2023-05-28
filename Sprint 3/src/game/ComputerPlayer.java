package game;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Computer Player
 * <p>
 * Created  by Tan Jun Yu
 * @author  Tan Jun Yu
 * Modified by: -
 */
public class ComputerPlayer extends Player implements NeighbourPositionFinder,RandomNumberGenerator{
    @Override
    public void setPlayerTurn(boolean bool) {
        if (bool){ // If current turn is ComputerPlayer turn
            Token tokenUsed ; // random token used
            IntersectionPoint intersectionPointUsed ; // random intersection point used

            // If Computer currentStateOfMove is sliding
            if (this.getCurrentStateOfMove() == CurrentStateofMove.SLIDING){
                tokenUsed =  generateRandomSlidingMove();
            }
            else {// If Computer currentStateOfMove is flying or placing
                tokenUsed = generateRandomPlacingFlyingMove();
            }

            MillChecker millChecker = MillChecker.getInstance();
            boolean millFormed = millChecker.checkMill(tokenUsed.getIntersectionPoint());
            if (millFormed){
               generateRandomRemoveMove();
            }

            // Get the new location of the token
            intersectionPointUsed = tokenUsed.getIntersectionPoint();
            int newLocationX = intersectionPointUsed.getX() + (intersectionPointUsed.getWidth() / 2) - (tokenUsed.getWidth() / 2);
            int newLocationY = intersectionPointUsed.getY() + (intersectionPointUsed.getHeight() / 2) - (tokenUsed.getHeight() / 2);

            tokenUsed.setLocation(newLocationX, newLocationY); // Set the token location to the new location found
            Game.getInstance().incrementTurn(); // Increment turn so that turn is switched back to HumanPlayer
            MainWindow.getInstance().repaint();
        }
    }

    private void generateRandomRemoveMove(){
        Player otherPlayer = Game.getInstance().getPlayer1();
        GameBoard gameBoard = GameBoard.getInstance();
        int randomTokenIndex = generateRandomNumber(otherPlayer.getNumberOfTokens());
        Token tokenToBeRemoved = otherPlayer.getTokenList().get(randomTokenIndex);

        //getting removable token which is not part of a mill and which is already on the game board
        while (!(!tokenToBeRemoved.inMill && tokenToBeRemoved.isTokenPlaced())) {
            randomTokenIndex = generateRandomNumber(otherPlayer.getNumberOfTokens());
            tokenToBeRemoved = otherPlayer.getTokenList().get(randomTokenIndex);
        }

        gameBoard.remove(tokenToBeRemoved);  //removing token from game board
        tokenToBeRemoved.getIntersectionPoint().removeToken();  //removing token from intersection point
        tokenToBeRemoved.getPlayer().getTokenList().remove(tokenToBeRemoved); //removing token from player's list

        //resetting token appearance to remove red highlight of border after selection has been made
        for (IntersectionPoint position : gameBoard.getIntersectionPoints()){
            Token curToken = position.getTokenInstance();
            if (curToken != null){
                curToken.toRemove = false ; //removal state false
                curToken.repaint();
            }
        }

        //checking to remove mills when token removed
        MillChecker.getInstance().checkIfTokenInMill(tokenToBeRemoved);
    }


    private Token generateRandomSlidingMove(){
        int randomTokenIndex = generateRandomNumber(this.getNumberOfTokens());
        Token tokenUsed = this.getTokenList().get(randomTokenIndex);

        boolean validSlidingMoveFound = false;

        while (!validSlidingMoveFound){ // while valid sliding move is not found

            // look for an empty neighbour intersection point of the random token selected
            IntersectionPoint intersectionPoint = tokenUsed.getIntersectionPoint();
            ArrayList<IntersectionPoint> neighbours = findNeighbouringIntersections(intersectionPoint);
            for (IntersectionPoint neighbour : neighbours){
                if (!neighbour.hasToken() && !validSlidingMoveFound){
                    intersectionPoint.removeToken();
                    tokenUsed.removeFromIntersectionPoint();
                    tokenUsed.addTokenToIntersectionPoint(neighbour);
                    validSlidingMoveFound = true;
                }
            }

            // if current random token selected has no valid sliding move, proceed to look for another random token
            if(!validSlidingMoveFound){
                randomTokenIndex = generateRandomNumber(this.getNumberOfTokens());
                tokenUsed = this.getTokenList().get(randomTokenIndex);
            }
        }

        return tokenUsed;
    }

    private Token generateRandomPlacingFlyingMove(){

        // Random token selected
        int randomTokenIndex = generateRandomNumber(this.getNumberOfTokens());
        Token tokenUsed = this.getTokenList().get(randomTokenIndex);

        // Random intersection point selected
        int randomIntersectionPointIndex = generateRandomNumber(GameBoard.getInstance().getIntersectionPoints().size());
        IntersectionPoint newIntersectionPoint = GameBoard.getInstance().getIntersectionPoints().get(randomIntersectionPointIndex);

        boolean emptyIntersectionPointFound = false;

        // if it is during placing move
        if (!this.areAllTokensPlaced()){
            while (tokenUsed.isTokenPlaced()){ // Ensure the random token selected is outside the board (not yet placed)
                randomTokenIndex = generateRandomNumber(this.getNumberOfTokens());
                tokenUsed = this.getTokenList().get(randomTokenIndex);
            }
        }

        // While an empty intersection point is not yet found
        while (!emptyIntersectionPointFound){

            if(!newIntersectionPoint.hasToken()){
                if(tokenUsed.getIntersectionPoint() != null){ // If token is already on the board ( happens during FLYING)
                    tokenUsed.getIntersectionPoint().removeToken();
                    tokenUsed.removeFromIntersectionPoint();
                } else { // If it does not belong to any intersection point , still outside the board ( happens during PLACING)

                    // Remove this particular token from the Main Panel
                    tokenUsed.getParent().remove(tokenUsed);
                    // Add the token into the GameBoard panel
                    GameBoard.getInstance().add(tokenUsed);

                    //setting the order of display on the game board: token appears above intersection point
                    GameBoard.getInstance().setComponentZOrder(tokenUsed, 0);
                    GameBoard.getInstance().setComponentZOrder(newIntersectionPoint, GameBoard.getInstance().getComponentCount()-1); //last element
                }
                // Add the token to intersection point as an attribute
                tokenUsed.addTokenToIntersectionPoint(newIntersectionPoint);
                tokenUsed.setIsTokenPlaced(true);
                emptyIntersectionPointFound = true;
            }
            if(!emptyIntersectionPointFound){ // If the random intersection point selected has a token (invalid) , look for another random empty intersection point
                randomIntersectionPointIndex = generateRandomNumber(GameBoard.getInstance().getIntersectionPoints().size());
                newIntersectionPoint = GameBoard.getInstance().getIntersectionPoints().get(randomIntersectionPointIndex);
            }
        }

        return tokenUsed;
    }
}