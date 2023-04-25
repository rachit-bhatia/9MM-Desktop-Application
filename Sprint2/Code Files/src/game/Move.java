package game;

import javax.swing.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public abstract class Move extends MouseAdapter {


    private Token tokenInstance;
    private int xCoordinate;
    private int yCoordinate;

    private int offSetX;

    private int offSetY;

    public Move(Token tokenInstance, int xCord, int yCord) {
        this.tokenInstance = tokenInstance;
        xCoordinate = xCord;
        yCoordinate = yCord;
    }


    @Override
    public void mouseReleased(MouseEvent cursor) {


        // Location of cursor after mouse is released from dragging

        // All the intersectionPoints in the panel
        ArrayList<IntersectionPoint> intersectionPoints = GameBoard.getInstance().getIntersectionPoints();

        boolean foundIntersectionPoint = false;

        // The coordinate system of the component(Intersection Point)
        Point pointToUse;

        // Look through all the intersectionPoints to find if the token is released at any intersection points
        for (IntersectionPoint intersectionPoint : intersectionPoints) {

            // If the token instance is not yet placed on the board
            if (!tokenInstance.getParent().equals(GameBoard.getInstance())){
                // Change the coordinate system of the intersection point from the GameBoard Panel to the Main Panel which the Token is in
                pointToUse = SwingUtilities.convertPoint(GameBoard.getInstance(),intersectionPoint.getX(),intersectionPoint.getY(),intersectionPoint.getParent().getParent());
            } else { // If the token is already on the board
                // Use the coordinate system of the GameBoard that both the Token and Intersection Point are in
                pointToUse = intersectionPoint.getLocation();
            }

            // If token is released at a valid intersection point , then set it as a new location of the token
            if (tokenInstance.getBounds().contains(pointToUse.getX(), pointToUse.getY(), intersectionPoint.getWidth(), intersectionPoint.getHeight())
                && !intersectionPoint.hasToken()) {

                int newLocationX = intersectionPoint.getX() + (intersectionPoint.getWidth() / 2) - (tokenInstance.getWidth() / 2);
                int newLocationY = intersectionPoint.getY() + (intersectionPoint.getHeight() / 2) - (tokenInstance.getHeight() / 2);

                // Remove this particular token from the Main Panel
                tokenInstance.getParent().remove(tokenInstance);

                // Add the token into the GameBoard panel
                GameBoard.getInstance().add(tokenInstance);

                // If this token previously belongs to another intersection point
                if (tokenInstance.hasAnIntersectionPoint()){
                    IntersectionPoint previousIntersectionPoint = tokenInstance.getIntersectionPoint();
                    // Remove the token from that Intersection Point
                    tokenInstance.removeFromIntersectionPoint();
                    previousIntersectionPoint.removeToken();
                }

                // Add the token to intersection point as an attribute
                tokenInstance.addTokenToIntersectionPoint(intersectionPoint);

                tokenInstance.setIsTokenPlaced(true);


                //setting the order of display on the game board: token appears above intersection point
                GameBoard.getInstance().setComponentZOrder(tokenInstance, 0);
                GameBoard.getInstance().setComponentZOrder(intersectionPoint, GameBoard.getInstance().getComponentCount()-1); //last element


                // Set the token coordinate at the new location relative to the GameBoard's coordinate system
                tokenInstance.setLocation(newLocationX, newLocationY);
                xCoordinate = newLocationX;
                yCoordinate = newLocationY;
                foundIntersectionPoint = true;
                break;
            }
        }

        // if token is dragged to a non valid intersection point
        if (!foundIntersectionPoint) {
            // set it back to its original position
            tokenInstance.setLocation(xCoordinate, yCoordinate);
        }
    }

    @Override
    public void mouseDragged(MouseEvent cursor) {
        // Get the location of the cursor relative to the MainPanel coordinate system
        Point location = SwingUtilities.convertPoint(tokenInstance,cursor.getPoint(), GameBoard.getInstance().getParent());

        // If token is not yet placed on board
        if (!tokenInstance.isTokenPlaced()){
            // set the location of the token using coordinates relative to the MainPanel coordinate system
            tokenInstance.setLocation(location.x - this.offSetX,location.y - this.offSetY);
        }else{ // if token is already placed on the board

            // set the location of the token using coordinates relative to the GameBoard coordinate system
            Point newLocation = SwingUtilities.convertPoint(tokenInstance,cursor.getPoint(), tokenInstance.getParent());
            if (tokenInstance.getParent().getBounds().contains(location)){
                tokenInstance.setLocation(newLocation.x - this.offSetX,newLocation.y - this.offSetY);
            }
        }



    }

    // Getter for token instance
    public Token getTokenInstance() {
        return tokenInstance;
    }

    // Set offsets value
    public void setOffSets(int offSetX,int offSetY) {
        this.offSetX = offSetX;
        this.offSetY = offSetY;
    }



}
