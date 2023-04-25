package game;
import javax.swing.*;
import java.awt.*;


public class Token extends JComponent {

    private Color tokenColour;
    private final int RADIUS = 50;

    // intersection point the token belongs to
    private IntersectionPoint intersectionPoint = null;

    private boolean isTokenPlaced;

    public Token(int x, int y, Color color){

        tokenColour = color;
        setBounds(x,y, RADIUS, RADIUS);  //setting the position and size of the token
        isTokenPlaced = false;

        //enabling the tokens to be moved by mouse actions
        Move flyingMove = new FlyingMove(this, x,y);
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

    // Setter for isTokenPlaced
    public void setIsTokenPlaced(boolean bool){
        this.isTokenPlaced = bool;
    }

    // Getter for isTokenPlaced
    public boolean isTokenPlaced() {
        return isTokenPlaced;
    }

}