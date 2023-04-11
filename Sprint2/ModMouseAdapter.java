package project.Sprint2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ModMouseAdapter extends MouseAdapter {

    private Token tokenInstance;
    private int xCoordinate;
    private int yCoordinate;
    private int offSetX;
    private int offSetY;

    public ModMouseAdapter(Token tokenInstance, int xCord, int yCord) {
        this.tokenInstance = tokenInstance;
        xCoordinate = xCord;
        yCoordinate = yCord;
    }

    @Override
    public void mousePressed(MouseEvent cursor) {
        // To find the offSet values so that the cursor can be at the same position of the token while being drag
        Point startPoint = SwingUtilities.convertPoint(tokenInstance, cursor.getPoint(), tokenInstance.getParent());
        this.offSetX = startPoint.x - tokenInstance.getBounds().x;
        this.offSetY = startPoint.y - tokenInstance.getBounds().y;
    }

    @Override
    public void mouseReleased(MouseEvent cursor) {


        // Location of cursor after mouse is released from dragging

        // All the components in the panel
        Component[] components = GameBoard.getInstance().getComponents();


        boolean foundIntersectionPoint = false;

        // The coordinate system of the component(Intersection Point)
        Point pointToUse;

        // Look through all the components to find if the token is released at any intersection points
        for (Component component : components) {

            // If the token instance is not yet placed on the board
            if (!tokenInstance.getParent().equals(GameBoard.getInstance())){
                // Change the coordinate system of the intersection point from the GameBoard Panel to the Main Panel which the Token is in
                pointToUse = SwingUtilities.convertPoint(GameBoard.getInstance(),component.getX(),component.getY(),component.getParent().getParent());
            } else { // If the token is already on the board
                // Use the coordinate system of the GameBoard that both the Token and Intersection Point are in
                pointToUse = component.getLocation();
            }

            // If token is released at a valid intersection point , then set it as a new location of the token
            if (component.getClass().equals(IntersectionPoint.class) && tokenInstance.getBounds().contains(pointToUse.getX(), pointToUse.getY(), component.getWidth(), component.getHeight())) {
                int newLocationX = component.getX() + (component.getWidth() / 2) - (tokenInstance.getWidth() / 2);
                int newLocationY = component.getY() + (component.getHeight() / 2) - (tokenInstance.getHeight() / 2);

                // Remove this particular token from the Main Panel
                tokenInstance.getParent().remove(tokenInstance);

                // Add the token into the GameBoard panel
                GameBoard.getInstance().add(tokenInstance);

                // Set the token coordinate at the new location relative to the GameBoard's coordinate system
                tokenInstance.setLocation(newLocationX, newLocationY);
                xCoordinate = newLocationX;
                yCoordinate = newLocationY;
                foundIntersectionPoint = true;
                break;
            }
        }

        if (!foundIntersectionPoint) {
            tokenInstance.setLocation(xCoordinate, yCoordinate);
        }
    }

    @Override
    public void mouseDragged(MouseEvent cursor) {
        Point location = SwingUtilities.convertPoint(tokenInstance,cursor.getPoint(), tokenInstance.getParent());

        tokenInstance.setLocation(location.x - this.offSetX,location.y - this.offSetY);
    }

}
