package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SlidingMove extends Move implements NeighbourPositionFinder {



    public SlidingMove(Token tokenInstance,int xCord, int yCord) {
        super(tokenInstance, xCord, yCord);
    }


    @Override
    public void mousePressed(MouseEvent cursor) {


        if (super.getTokenInstance().canBeUsed()){
            // To find the offSet values so that the cursor can be at the same position of the token while being drag
            Point startPoint = SwingUtilities.convertPoint(super.getTokenInstance(), cursor.getPoint(), super.getTokenInstance().getParent());
            super.setOffSets( startPoint.x - super.getTokenInstance().getBounds().x ,startPoint.y - super.getTokenInstance().getBounds().y );

            IntersectionPoint currIntersectionPoint = super.getTokenInstance().getIntersectionPoint();

            // Find neighbouring intersection points to the currIntersectionPoint and set them as valid intersection point
            ArrayList<IntersectionPoint> neighbourIntersectionPoints = findNeighbouringIntersections(currIntersectionPoint);
            for (IntersectionPoint intersectionPoint : neighbourIntersectionPoints){
                if (!intersectionPoint.hasToken()) {
                    intersectionPoint.setMoveValid(true);
                    intersectionPoint.pointSelected = true;  //set point selection to true
                    intersectionPoint.repaint();  //repainting intersection point with green border
                }
            }

        }
    }


}
