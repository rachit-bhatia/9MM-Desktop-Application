package game;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class RemoveMove extends MouseAdapter {

    public RemoveMove(Token tokenInstance) {
    }

    public static void highlightRemovableTokens(Player curPlayer){
        Player oppPlayer;
        if (curPlayer == Game.getInstance().getPlayer1()){
            oppPlayer = Game.getInstance().getPlayer2();
        }
        else {
            oppPlayer = Game.getInstance().getPlayer1();
        }

        for (Token token : oppPlayer.getTokenList()){
            for (Component boardItem: GameBoard.getInstance().getComponents()){
                if (boardItem.equals(token)){
                    token.setToRemove(true);
                    token.repaint();
                }
            }
        }
    }
}
