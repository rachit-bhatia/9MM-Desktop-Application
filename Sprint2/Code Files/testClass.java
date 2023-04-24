package project.Sprint2;

import java.awt.*;
import java.util.ArrayList;

public class testClass {
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

    public void drawLines(int startPoint, int endPoint, int firstID, int secondID, int increment, Graphics g){
        int adjustment = 19;
        // iterate through intersectionPointsList
        for (int i = startPoint; i < endPoint; i += increment) {
            IntersectionPoint point1 = intersectionPointsList.get(i + firstID);
            IntersectionPoint point2 = intersectionPointsList.get(i + secondID);
            // draw a line between point1 and point2
            g.drawLine(point1.getX() + adjustment, point1.getY() + adjustment, point2.getX() + adjustment, point2.getY() + adjustment);
        }
    }

    public ArrayList<IntersectionPoint> getIntersectionPoints(){
        return intersectionPointsList;
    }
}
