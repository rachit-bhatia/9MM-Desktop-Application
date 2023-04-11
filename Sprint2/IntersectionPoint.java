package project.Sprint2;

import javax.swing.*;
import java.awt.*;

public class IntersectionPoint extends JComponent {

    // Coordinates of Intersection Point (Might not be needed as attributes. Can remove later if necessary)
    private int xCoordinate;
    private int yCoordinate;
    private final int RADIUS = 30;

    // The token belongs to this intersection point
    private Token tokenInstance = null;


    public IntersectionPoint(int x,int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;


        setBounds(this.xCoordinate,this.yCoordinate,RADIUS+7,RADIUS+7);
//        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    }

    @Override
    public void paintComponent(Graphics tokenShape) {
        tokenShape.setColor(Color.ORANGE);
        tokenShape.fillOval(3, 3, RADIUS, RADIUS);  //fill the position to be of orange colour

        Graphics2D tokenShapeEnhance = (Graphics2D) tokenShape;  //Graphics2D class used to change thickness of borders
        tokenShapeEnhance.setStroke(new BasicStroke(4));   //border thickness set to 4
        tokenShapeEnhance.setColor(Color.BLACK);
        tokenShapeEnhance.drawOval(3, 3, RADIUS, RADIUS);  //outline border
    }

    // Add a token to this intersection point
    public void addToken(Token token){
        this.tokenInstance = token;
    }

    // Remove a token from this intersection point
    public void removeToken(){
        this.tokenInstance = null;
    }

    // Check if this intersection point has a token
    public boolean hasToken(){
        return this.tokenInstance != null;
    }

}
