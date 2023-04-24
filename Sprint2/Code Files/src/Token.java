package src;
import javax.swing.*;
import java.awt.*;


public class Token extends JComponent {

    private int xCoordinate;
    private int yCoordinate;
    private Color tokenColour;
    private final int RADIUS = 50;

    // intersection point the token belongs to
    private IntersectionPoint intersectionPoint = null;

    public Token(int x, int y, Color color){

        xCoordinate = x;
        yCoordinate = y;
        tokenColour = color;
        setBounds(xCoordinate,yCoordinate, RADIUS, RADIUS);  //setting the position and size of the token

        //enabling the tokens to be moved by mouse actions
        Move flyingMove = new FlyingMove(this, xCoordinate,yCoordinate);
        this.addMouseListener(flyingMove);
        this.addMouseMotionListener(flyingMove);
    }

    //used for creating a filled oval
    @Override
    public void paintComponent(Graphics tokenShape) {
        tokenShape.setColor(tokenColour);
        tokenShape.fillOval(0, 0, getWidth(), getHeight());  //painting an oval
    }

    // Add the token to an intersection point
    public void addTokenToIntersectionPoint(IntersectionPoint intersectionPoint){
        intersectionPoint.addToken(this);
        this.intersectionPoint = intersectionPoint;
    }

    // Check if the token already has an intersection point
    public boolean hasAnIntersectionPoint(){
        return this.intersectionPoint != null;
    }


    // Getter for intersection point attribute
    public IntersectionPoint getIntersectionPoint(){
        if(hasAnIntersectionPoint()){
            return this.intersectionPoint;
        } else {
            return null;
        }
    }

    // Remove the intersection point from token
    public void removeFromIntersectionPoint(){
        this.intersectionPoint = null;
    }

}