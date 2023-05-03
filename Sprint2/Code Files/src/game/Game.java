package game;

public class Game {

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

        // While both players still have more than 3 tokens, keep the game running
        while (player1.getNumberOfTokens() > 3 && player2.getNumberOfTokens() >3){

            // If the current state of Player 1 is PLACING and all the tokens are already placed
            if (player1.getCurrentStateOfMove() == CurrentStateofMove.FLYING && player1.areAllTokensPlaced()){
                // Change the state of move of Player 1 to SLIDING
                player1.changeStateOfMove(CurrentStateofMove.SLIDING);
            }

            // While a valid move is not yet made by the player 1
            while ( this.turn % 2 == 0){
                player1.setPlayerTurn(true);
            }

            // After player 1 turn is finished
            player1.setPlayerTurn(false);

            // If the current state of Player 2 is PLACING and all the tokens are already placed
            if (player2.getCurrentStateOfMove() == CurrentStateofMove.FLYING && player2.areAllTokensPlaced()){
                // Change the state of move of Player 2 to SLIDING
                player2.changeStateOfMove(CurrentStateofMove.SLIDING);
            }

            // While a valid move is not yet made by the player 2
            while ( this.turn % 2 == 1){
                player2.setPlayerTurn(true);
            }

            // After player 2 turn is finished
            player2.setPlayerTurn(false);

        }

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
