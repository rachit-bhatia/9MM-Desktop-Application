package game;

import javax.swing.*;
import java.awt.*;

public class IntersectionPoint extends JComponent {

    private final int DIAMETER = 30;

    // The token belongs to this intersection point
    private Token tokenInstance = null;

    // Constructor
    public IntersectionPoint(int x,int y) {

        GameBoard.getInstance().add(this);
        setBounds(x  -  ( DIAMETER /2 ),y-  ( DIAMETER /2 ), DIAMETER + 8, DIAMETER + 8 );

    }

    // Draw the UI of the Intersection Point
    @Override
    public void paintComponent(Graphics tokenShape) {
        tokenShape.setColor(Color.ORANGE);
        tokenShape.fillOval(3, 3, DIAMETER, DIAMETER);  //fill the position to be of orange colour

        Graphics2D tokenShapeEnhance = (Graphics2D) tokenShape;  //Graphics2D class used to change thickness of borders
        tokenShapeEnhance.setStroke(new BasicStroke(4));   //border thickness set to 4
        tokenShapeEnhance.setColor(Color.BLACK);
        tokenShapeEnhance.drawOval(3, 3, DIAMETER, DIAMETER);  //outline border
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
