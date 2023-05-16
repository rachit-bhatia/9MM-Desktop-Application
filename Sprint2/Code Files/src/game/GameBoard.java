package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * A JPanel representing the GameBoard that has all the intersection points
 * <p>
 * Created  by Shoumil
 *
 * @author Shoumil, Tan Jun Yu
 * Modified by: Rachit Bhatia
 */
public class GameBoard extends JPanel {

    /**
     * An arrayList containing all the intersection points on the GameBoard
     */
    private ArrayList<IntersectionPoint> intersectionPointsList;

    /**
     * Singleton instance of GameBoard
     */
    private static GameBoard instance ;

    /**
     * Constructor
     */
    private GameBoard(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        setLayout(null);
        setBackground(new Color(110, 65, 40).brighter());
        intersectionPointsList = new ArrayList<IntersectionPoint>();
    }

    /**
     * Access GameBoard singleton instance publicly
     * @return GameBoard instance
     */
    public static GameBoard getInstance(){
        if (instance == null) {
            instance = new GameBoard();
        }

        return instance;
    }

    /**
     * Set the dimensions of GameBoard according to the screen size of user
     * @param screenDimension dimensions of the user's screen
     */
    public void setDimensionsOfBoard(Dimension screenDimension){
        int boardWidth = (int) (screenDimension.width/2.4);
        int boardHeight = (int) (screenDimension.height/1.35);
        int boardX = (int) (screenDimension.width - boardWidth) / 2;
        int boardY = (int) (screenDimension.height - boardHeight) / 2;

        this.setBounds(boardX, boardY, boardWidth, boardHeight);
    }

    /**
     * Adding all the intersection points into their respective positions on the GameBoard
     * @param boardWidth width of GameBoard
     * @param boardHeight height of GameBoard
     */
    public void addIntersections (int boardWidth, int boardHeight){

        /*
        The intersection points have to be repeated because of the unique
        coordinates for each point on the board. Thus, they have to be added manually.
        */
        int[][] points = {
            // Add 8 points to the outer square
            {boardWidth/8 , boardHeight/8},
            {boardWidth/2, boardHeight/8},
            {boardWidth-boardWidth/8, boardHeight/8},
            {boardWidth-boardWidth/8, boardHeight/2},
            {boardWidth-boardWidth/8, boardHeight-boardHeight/8},
            {boardWidth/2, boardHeight-boardHeight/8},
            {boardWidth/8, boardHeight-boardHeight/8},
            {boardWidth/8, boardHeight/2},

            // Add 8 points to the middle square
            {boardWidth/4, boardHeight/4},
            {boardWidth/2, boardHeight/4},
            {boardWidth-boardWidth/4, boardHeight/4},
            {boardWidth-boardWidth/4, boardHeight/2},
            {boardWidth-boardWidth/4, boardHeight-boardHeight/4},
            {boardWidth/2, boardHeight-boardHeight/4},
            {boardWidth/4, boardHeight-boardHeight/4},
            {boardWidth/4, boardHeight/2},

            // Add 8 points to the inner square
            {boardWidth/2 - boardWidth/8, boardHeight/2 - boardHeight/8},
            {boardWidth/2, boardHeight/2 - boardHeight/8},
            {boardWidth/2 + boardWidth/8, boardHeight/2 - boardHeight/8},
            {boardWidth/2 + boardWidth/8, boardHeight/2},
            {boardWidth/2 + boardWidth/8, boardHeight/2 + boardHeight/8},
            {boardWidth/2, boardHeight/2 + boardHeight/8},
            {boardWidth/2 - boardWidth/8, boardHeight/2 + boardHeight/8},
            {boardWidth/2 - boardWidth/8, boardHeight/2}
        };

        // Add the intersection points as components to the GameBoard Panel
        for (int[] point : points) {
            IntersectionPoint intersectionPoint = new IntersectionPoint(point[0], point[1]);
            intersectionPointsList.add(intersectionPoint);
            this.add(intersectionPoint);
        }
    }

    /**
     * Drawing the UI of the GameBoard
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D lineShapeEnhance = (Graphics2D) g;
        lineShapeEnhance.setColor(Color.BLACK);
        lineShapeEnhance.setStroke(new BasicStroke(8));

        int adjustment = 19;
        for (int k = 0; k < 24; k += 8) {
            drawLines(k,k+7, 0, 1, 1, g);
        }

        // Joining final two points in each concentric square
        drawLines(7,24, 0 , -7, 8, g);

        //intersection points ID values for inner section of the board (not part of the square)
        int firstID = 0;
        int secondID = 8;

        // Joining inner lines that are not part of the square
        for (int k = 0; k < 4; k++){
            drawLines(1, 10, firstID, secondID, 8, g);

            if (firstID == 0) {
                firstID = 8;
                secondID = 16; }

            firstID -= 2;
            secondID -= 2;
        }
    }

    /**
     * Method for drawing the connecting lines on the board between given coordinates
     * Multiple parameters are needed to meet the reach each unique coordinate in a single loop
     * @return void
     */
    public void drawLines(int startPoint, int endPoint, int firstID, int secondID, int increment, Graphics g) {
        int adjustment = 19;

        // iterate through intersectionPointsList
        for (int i = startPoint; i < endPoint; i += increment) {
            IntersectionPoint point1 = intersectionPointsList.get(i + firstID);
            IntersectionPoint point2 = intersectionPointsList.get(i + secondID);
            // draw a line between point1 and point2
            g.drawLine(point1.getX() + adjustment, point1.getY() + adjustment, point2.getX() + adjustment, point2.getY() + adjustment);
        }
    }

    /**
     * Getter for the intersectionPointsList attribute
     * @return an ArrayList of Intersection Points
     */
    public ArrayList<IntersectionPoint> getIntersectionPoints(){
        return intersectionPointsList;
    }

    /**
     * Reset all the intersection points back to invalid move whenever a new token is picked up by players
     */
    public void resetAllIntersectionPoints(){
        for (IntersectionPoint intersectionPoint : intersectionPointsList){
            intersectionPoint.setMoveValid(false);
        }
    }
}
