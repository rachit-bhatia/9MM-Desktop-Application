package game;

public class HumanPlayer extends Player{

    @Override
    public void setPlayerTurn(boolean bool) {

        boolean tokenCanBeUsed = false;
        if (bool){
            tokenCanBeUsed = true;
        }
        for(Token token : super.getTokenList()){
            token.setCanBeUsed(tokenCanBeUsed);
        }
    }
}