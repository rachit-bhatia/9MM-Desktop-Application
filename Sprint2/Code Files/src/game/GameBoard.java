package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard extends JPanel {

    // Intersection Points on the GameBoard
    private ArrayList<IntersectionPoint> intersectionPointsList;

    // GameBoard instance
    private static GameBoard instance ;

    // Constructor
    private GameBoard(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        setLayout(null);
        setBackground(new Color(110, 65, 40).brighter());
        intersectionPointsList = new ArrayList<IntersectionPoint>();
    }

    // Getter for static GameBoard instance
    public static GameBoard getInstance(){
        if (instance == null) {
            instance = new GameBoard();
        }

        return instance;
    }

    // Set the dimensions of the GameBoard according to screen dimension
    public void setDimensionsOfBoard(Dimension screenDimension){
        int boardWidth = (int) (screenDimension.width/2.4);
        int boardHeight = (int) (screenDimension.height/1.35);
        int boardX = (int) (screenDimension.width - boardWidth) / 2;
        int boardY = (int) (screenDimension.height - boardHeight) / 2;

        this.setBounds(boardX, boardY, boardWidth, boardHeight);
    }

    // Add all the intersection points onto the board
    public void addIntersections (int boardWidth, int boardHeight){

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

        for (int[] point : points) {
            intersectionPointsList.add(new IntersectionPoint(point[0], point[1]));
        }

        // Add the intersection points as components to the GameBoard Panel
        for (IntersectionPoint intersectionPoint : intersectionPointsList) {
            this.add(intersectionPoint);
        }
    }

    // Drawing the UI of GameBoard
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D lineShapeEnhance = (Graphics2D) g;
        lineShapeEnhance.setColor(Color.BLACK);
        lineShapeEnhance.setStroke(new BasicStroke(8));

        int adjustment = 19;
        // iterate through intersectionPointsList
        for (int i = 0; i < 7; i++) {
            IntersectionPoint point1 = intersectionPointsList.get(i);
            IntersectionPoint point2 = intersectionPointsList.get(i + 1);
            // draw a line between point1 and point2
            g.drawLine(point1.getX() + adjustment  , point1.getY() + adjustment, point2.getX() + adjustment, point2.getY() + adjustment);
        }
        for (int i = 8; i < 15; i++) {
            IntersectionPoint point1 = intersectionPointsList.get(i);
            IntersectionPoint point2 = intersectionPointsList.get(i + 1);
            // draw a line between point1 and point2
            g.drawLine(point1.getX() + adjustment, point1.getY()+ adjustment, point2.getX()+ adjustment, point2.getY()+ adjustment);
        }
        for (int i = 16; i < 23; i++) {
            IntersectionPoint point1 = intersectionPointsList.get(i);
            IntersectionPoint point2 = intersectionPointsList.get(i + 1);
            // draw a line between point1 and point2
            g.drawLine(point1.getX()+ adjustment, point1.getY()+ adjustment, point2.getX()+ adjustment, point2.getY()+ adjustment);
        }

        // Joining final two points in each concentric square
        for (int i = 7; i < 24; i+=8) {
            IntersectionPoint point1 = intersectionPointsList.get(i);
            IntersectionPoint point2 = intersectionPointsList.get(i - 7);
            // draw a line between point1 and point2
            g.drawLine(point1.getX()+ adjustment, point1.getY()+ adjustment, point2.getX()+ adjustment, point2.getY()+ adjustment);
        }

        // Joining inner lines that are not part of the square
        for (int i = 1; i < 10; i+=8) {
            IntersectionPoint point1 = intersectionPointsList.get(i);
            IntersectionPoint point2 = intersectionPointsList.get(i + 8);
            // draw a line between point1 and point2
            g.drawLine(point1.getX()+ adjustment, point1.getY()+ adjustment, point2.getX()+ adjustment, point2.getY()+ adjustment);

            point1 = intersectionPointsList.get(i + 4);
            point2 = intersectionPointsList.get(i + 12);
            // draw a line between point1 and point2
            g.drawLine(point1.getX()+ adjustment, point1.getY()+ adjustment, point2.getX()+ adjustment , point2.getY()+ adjustment);

            point1 = intersectionPointsList.get(i + 2);
            point2 = intersectionPointsList.get(i + 10);
            // draw a line between point1 and point2
            g.drawLine(point1.getX()+ adjustment, point1.getY()+ adjustment, point2.getX()+ adjustment, point2.getY()+ adjustment);

            point1 = intersectionPointsList.get(i + 6);
            point2 = intersectionPointsList.get(i + 14);
            // draw a line between point1 and point2
            g.drawLine(point1.getX()+ adjustment, point1.getY()+ adjustment, point2.getX()+ adjustment, point2.getY()+ adjustment);
        }

    }

    // Getter for the intersectionPointsList
    public ArrayList<IntersectionPoint> getIntersectionPoints(){
        return intersectionPointsList;
    }
}
