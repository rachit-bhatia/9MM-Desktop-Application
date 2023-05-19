package game;


/**
 * Human Player
 * <p>
 * Created  by Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by: -
 */
public class HumanPlayer extends Player{

    /**
     * Set player turn to true that allows all of its tokens to be usable
     * @param bool
     */
    @Override
    public void setPlayerTurn(boolean bool) {

        boolean tokenCanBeUsed = false;
        if (bool){
            tokenCanBeUsed = true;
        }
        for(Token token : super.getTokenList()){
            token.setCanBeUsed(tokenCanBeUsed); // set all the tokens to be usable
        }
    }
}