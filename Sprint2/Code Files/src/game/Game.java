package game;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Game implements NeighbourPositionFinder{

    private Player player1;
    private Player player2;

    private static Game instance = null;
    private int turn;

    private Game(){
        this.turn = 0 ;
    }

    public static Game getInstance(){
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }


    public void run(){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String endMessage = "Congratulations "; //message to be displayed at end of game

        //borders acting as player turn identifier
        Border playerIdentifier = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GREEN.brighter().brighter(), Color.GREEN.darker().darker());
        Border identifierThickness = BorderFactory.createEmptyBorder(1,1,1,1);

        MainWindow mainWindow = MainWindow.getInstance();

        boolean player1NoValidMove = false;
        boolean player2NoValidMove = false;
        // While both players still have 3 or more tokens, keep the game running
        while (player1.getNumberOfTokens() >= 3 && player2.getNumberOfTokens() >= 3){

            // update state of move if needed
            player1.updateStateOfMove();

            if (player1.getCurrentStateOfMove() == CurrentStateofMove.SLIDING){
                player1NoValidMove = checkIfPlayerHasValidSlidingMove(player1);
                if (player1NoValidMove){
                    break;
                }
            }


            // While a valid move is not yet made by the player 1
            while (this.turn % 2 == 0 && player1.getNumberOfTokens() >= 3){
                player1.setPlayerTurn(true);

                //setting player label border to show which player's turn it is
                mainWindow.getPlayerLabel2().setBorder(null);
                mainWindow.getPlayerLabel1().setBorder(BorderFactory.createCompoundBorder(identifierThickness, playerIdentifier));
            }

            // After player 1 turn is finished
            player1.setPlayerTurn(false);


            // update state of move if needed
            player2.updateStateOfMove();

            if (player2.getCurrentStateOfMove() == CurrentStateofMove.SLIDING){
                player2NoValidMove = checkIfPlayerHasValidSlidingMove(player2);
                if (player2NoValidMove){
                    break;
                }
            }


            // While a valid move is not yet made by the player 2
            while ( this.turn % 2 == 1 && player2.getNumberOfTokens() >= 3){
                player2.setPlayerTurn(true);

                //setting player label border to show which player's turn it is
                mainWindow.getPlayerLabel1().setBorder(null);
                mainWindow.getPlayerLabel2().setBorder(BorderFactory.createCompoundBorder(identifierThickness, playerIdentifier));
            }

            // After player 2 turn is finished
            player2.setPlayerTurn(false);

        }

        if (player1.getTokenList().size() < 3 || player1NoValidMove){
            endMessage += "Player 2!\nYou win the game!";
        }
        else if (player2.getTokenList().size() < 3 || player2NoValidMove){
            endMessage += "Player 1!\nYou win the game!";
        }

        JOptionPane.showMessageDialog(null, endMessage, "Game Over", JOptionPane.PLAIN_MESSAGE);

    }

    public boolean checkIfPlayerHasValidSlidingMove(Player player){
        ArrayList<Token> tokens = player.getTokenList();

        boolean noValidMoves = true;
        for (Token token : tokens){
            if (token.isTokenPlaced()){
                IntersectionPoint intersectionPoint = token.getIntersectionPoint();

                for ( IntersectionPoint neighbour : findNeighbouringIntersections(intersectionPoint)){
                    if (!neighbour.hasToken()){
                        noValidMoves = false;
                        break;
                    }
                }

                if (!noValidMoves){
                    break;
                }

            }
        }

        return noValidMoves;
    }
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void incrementTurn() {
        this.turn = turn + 1;
    }

    // Set players ( HumanPlayer Vs HumanPlayer or HumanPlayer vs ComputerPlayer)
    public void setPlayers(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }
}
