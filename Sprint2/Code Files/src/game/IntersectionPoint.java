package game;

import javax.swing.*;
import java.awt.*;


/**
 * A JComponent representing the Intersection Point
 * <p>
 * Created  by Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by: Shoumil, Rachit Bhatia
 */
public class IntersectionPoint extends JComponent {

    /**
     * The diameter of Intersection Point
     */
    private final int DIAMETER = 30;

    /**
     * The Token belongs to this intersection point
     */
    private Token tokenInstance = null;

    /**
     * Constructor
     * @param x xCoordinate of the intersection point
     * @param y yCoordinate of the intersection point
     */
    public IntersectionPoint(int x,int y) {

        GameBoard.getInstance().add(this);
        setBounds(x  -  ( DIAMETER /2 ),y-  ( DIAMETER /2 ), DIAMETER + 8, DIAMETER + 8 );

    }

    /**
     * Draw the UI of the Intersection Point
     * @param tokenShape the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics tokenShape) {
        tokenShape.setColor(Color.ORANGE);
        tokenShape.fillOval(3, 3, DIAMETER, DIAMETER);  //fill the position to be of orange colour

        Graphics2D tokenShapeEnhance = (Graphics2D) tokenShape;  //Graphics2D class used to change thickness of borders
        tokenShapeEnhance.setStroke(new BasicStroke(4));   //border thickness set to 4
        tokenShapeEnhance.setColor(Color.BLACK);
        tokenShapeEnhance.drawOval(3, 3, DIAMETER, DIAMETER);  //outline border
    }

    /**
     * Adding a Token instance to the IntersectionPoint
     * @param token token to be added
     */
    public void addToken(Token token){
        this.tokenInstance = token;
    }

    /**
     * Remove a token from this intersection point
     */
    public void removeToken(){
        this.tokenInstance = null;
    }

    /**
     * Check if this intersection point has a token
     * @return boolean True if intersection point has a token . False otherwise
     */
    public boolean hasToken(){
        return this.tokenInstance != null;
    }



}
