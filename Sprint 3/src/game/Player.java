package game;

import java.util.ArrayList;
/**
 * Player
 * <p>
 * Created by Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by: Rachit Bhatia
 */

public abstract class Player {

    /**
     * All the tokens that the Player has
     */
    private ArrayList<Token> tokenList ;

    /**
     * Player's current state of move
     */
    private CurrentStateofMove currentStateofMove;

    /**
     * Constructor
     */
    public Player(){
        tokenList = new ArrayList<Token>();
        currentStateofMove = CurrentStateofMove.PLACING;
    }


    /**
     * Getter for currentStateOfMove
     * @return currentStateOfMove
     */
    public CurrentStateofMove getCurrentStateOfMove() {
        return currentStateofMove;
    }

    /**
     * Check the state of the game the Player is in and update the stateOfMove accordingly
     */
    public void updateStateOfMove(){

        // If current state of move is flying and all the tokens are already placed
        if (currentStateofMove == CurrentStateofMove.PLACING && areAllTokensPlaced()){

            // Change the state of move to SLIDING
            currentStateofMove = CurrentStateofMove.SLIDING;
            for (Token token : tokenList){
                token.changeListener(new SlidingMove(token,token.getX(),token.getY()),false);
            }

        }
        if (currentStateofMove == CurrentStateofMove.SLIDING && tokenList.size() == 3) { // if current state of move is sliding, and player has 3 tokens left


            // Change the state of move to FLYING
            currentStateofMove = CurrentStateofMove.FLYING;

            for (Token token : tokenList){
                token.changeListener(new FlyingMove(token,token.getX(),token.getY()),false);
            }

        }

    }

    /**
     * Getter for tokenList
     * @return all tokens of the player
     */
    public ArrayList<Token> getTokenList() {
        return tokenList;
    }


    /**
     * Settter for player turn
     * @param bool True if is player's turn. False otherwise
     */
    public abstract void setPlayerTurn(boolean bool);

    /**
     * Add token instance to player arrayList of tokens
     * @param tokenInstance token to be added
     */
    public void addToken(Token tokenInstance){
        this.tokenList.add(tokenInstance);
        tokenInstance.addPlayer(this);
    }

    /**
     * Get the number of tokens the player has
     * @return
     */
    public int getNumberOfTokens(){
        return this.tokenList.size();
    }


    /**
     * Check if all the tokens of the player is already placed on the board so that can be used as indicator whether or not to switch to SLIDING
     * @return True if all tokens are placed. False otherwise
     */
    public boolean areAllTokensPlaced(){
        boolean allPlaced = true;

        for (Token token : tokenList){
            if (!token.isTokenPlaced()){
                allPlaced = false;
                break;
            }
        }
        return allPlaced;
    }



}
