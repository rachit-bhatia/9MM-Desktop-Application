package game;

import java.util.ArrayList;


public abstract class Player {

    private ArrayList<Token> tokenList ;

    private CurrentStateofMove currentStateofMove;
    public Player(){
        tokenList = new ArrayList<Token>();
        currentStateofMove = CurrentStateofMove.FLYING;
    }


    // Getter for currentStateOfMove
    public CurrentStateofMove getCurrentStateOfMove() {
        return currentStateofMove;
    }

    // Change the stateOfMove
    public void changeStateOfMove(CurrentStateofMove newMoveState){
        this.currentStateofMove = newMoveState;

        if (this.currentStateofMove == CurrentStateofMove.SLIDING){
            for(Token token : tokenList){
                token.changeListener(new SlidingMove(token,token.getX(),token.getY()));
            }
        }

//        if (this.currentStateofMove == CurrentStateofMove.FLYING){
//            for(Token token : tokenList){
//                token.changeListener(new FlyingMove(token,token.getX(),token.getY()));
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
    }

    // Get the number of tokens left the Player has
    public int getNumberOfTokens(){
        return this.tokenList.size();
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
