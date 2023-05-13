package game;

import java.util.ArrayList;


public abstract class Player {

    private ArrayList<Token> tokenList ;

    private CurrentStateofMove currentStateofMove;
    public Player(){
        tokenList = new ArrayList<Token>();
        currentStateofMove = CurrentStateofMove.PLACING;
    }


    // Getter for currentStateOfMove
    public CurrentStateofMove getCurrentStateOfMove() {
        return currentStateofMove;
    }

    // Check the Player's number of tokens left and update the stateOfMove accordingly
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
//        else if (currentStateofMove == CurrentStateofMove.REMOVING) {
//
//            //if all tokens are not on board, the player is still placing their tokens
//            if (!areAllTokensPlaced()){
//                currentStateofMove = CurrentStateofMove.PLACING;
//                for (Token token : tokenList){
//                    if (token.isTokenPlaced()){
//                        token.changeListener(null); //token on the board can't move
//                    }
//                    else{
//                        token.changeListener(new FlyingMove(token, token.getX(), token.getY()));
//                    }
//                }
//            }
//
//            //if all tokens are on the board, then player is in sliding or flying state of move
//            else if (areAllTokensPlaced()) {
//
//                if (tokenList.size() > 3) {
//                    currentStateofMove = CurrentStateofMove.SLIDING;
//                    for (Token token : tokenList) {
//                        token.changeListener(new SlidingMove(token, token.getX(), token.getY()));
//                    }
//                }
//
//                //if player has 3 tokens left, they can move their tokens anywhere on the board
//                else if (tokenList.size() == 3) {
//                    currentStateofMove = CurrentStateofMove.FLYING;
//                    for (Token token : tokenList) {
//                        token.changeListener(new FlyingMove(token, token.getX(), token.getY()));
//                    }
//                }
//            }
//        }

    }

    // Getter for tokenList
    public ArrayList<Token> getTokenList() {
        return tokenList;
    }


    // Set to Player's turn
    public abstract void setPlayerTurn(boolean bool);

    // Add token to the Player's tokenList
    public void addToken(Token tokenInstance){
        this.tokenList.add(tokenInstance);
        tokenInstance.addPlayer(this);
    }

    // Get the number of tokens left the Player has
    public int getNumberOfTokens(){
        return this.tokenList.size();
    }

    public CurrentStateofMove getCurrentStateofMove(){
        return currentStateofMove;
    }

    public void setCurrentStateofMove(CurrentStateofMove curState){
        currentStateofMove = curState;
    }

    // Check if all the tokens of the Player are already placed on board
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
