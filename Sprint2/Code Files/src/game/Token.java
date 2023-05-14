package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * A JComponent representing the Token
 * <p>
 * Created  by Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by: Shoumil, Rachit Bhatia
 */
public class Token extends JComponent {

    /**
     * Color of Token
     */
    private Color tokenColour;

    /**
     * Radius of Token
     */
    private final int DIAMETER = 55;

    /**
     * intersection point the token belongs to
     */
    private IntersectionPoint intersectionPoint;

    /**
     * If a token is already placed on board
     */
    private boolean isTokenPlaced;

    /**
     * boolean value to indicate if the token is available to be removed
     */
    public boolean toRemove;

    /**
     * boolean value indicating whether the token is in a mill
     */
    public boolean inMill;

    private boolean canBeUsed;

    private MouseAdapter currentMoveListener;

    private Player player ;
    /**
     * constructor
     * @param x x coordinate of the Token
     * @param y  y coordinate of the Token
     * @param color color Black for Player 1 , White for Player 2
     */
    public Token(int x, int y, Color color){

        tokenColour = color;
        setBounds(x,y, DIAMETER, DIAMETER);  //setting the position and size of the token
        isTokenPlaced = false;
        intersectionPoint = null;
        //enabling the tokens to be moved by mouse actions
        Move flyingMove = new FlyingMove(this, x,y);
        currentMoveListener = flyingMove;
        this.addMouseListener(flyingMove);
        this.addMouseMotionListener(flyingMove);

    }

    /**
     * Draw the UI of token
     * @param tokenShape the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics tokenShape) {
        tokenShape.setColor(tokenColour);
        tokenShape.fillOval(2, 2, getWidth()-5, getHeight()-5);  //painting an oval

        if (this.inMill){
            Graphics2D tokenShapeEnhance = (Graphics2D) tokenShape;  //Graphics2D class used to change thickness of borders
            tokenShapeEnhance.setStroke(new BasicStroke(5));   //border thickness set to 5
            tokenShapeEnhance.setColor(Color.BLUE);
            tokenShapeEnhance.drawOval(2,2, getWidth() - 5, getHeight() - 5);
        }

        if (this.toRemove){
            Graphics2D tokenShapeEnhance = (Graphics2D) tokenShape;  //Graphics2D class used to change thickness of borders
            tokenShapeEnhance.setStroke(new BasicStroke(5));   //border thickness set to 5
            tokenShapeEnhance.setColor(Color.RED);
            tokenShapeEnhance.drawOval(2,2, getWidth() - 5, getHeight() - 5);
        }
    }

    public void changeListener(MouseAdapter newMove , boolean temporary){
        this.removeMouseListener(this.currentMoveListener);
        this.removeMouseMotionListener(this.currentMoveListener);
        this.addMouseListener(newMove);
        this.addMouseMotionListener(newMove);

        if (!temporary){ // if not temporary
            this.currentMoveListener = newMove;
        }
    }

    /**
     * Remove temporary listeners and set it back to original listener
     */
    public void removeTemporaryListener(){
        // Remove all the current mouse listeners
        for (MouseListener temporaryMouseListener : this.getMouseListeners()){
            this.removeMouseListener(temporaryMouseListener);
        }
        // Remove all the current mouse motion listeners
        for (MouseMotionListener temporaryMouseMotionListener : this.getMouseMotionListeners()){
            this.removeMouseMotionListener(temporaryMouseMotionListener);
        }

        // Reset the mouse listener and mouse motion listener back to the original move listener
        this.addMouseListener(this.currentMoveListener);
        this.addMouseMotionListener(this.currentMoveListener);
    }




    /**
     *  Add the token to an intersection point
     * @param intersectionPoint intersection point the token is added to
     */
    public void addTokenToIntersectionPoint(IntersectionPoint intersectionPoint){
        intersectionPoint.addToken(this);
        this.intersectionPoint = intersectionPoint;
    }


    /**
     * Getter for intersection point the token belongs to
     * @return intersection point token belongs to or null if token is not belong to any intersection point
     */
    public IntersectionPoint getIntersectionPoint(){
        return this.intersectionPoint;
    }

    /**
     * Remove the intersection point from token
     */
    public void removeFromIntersectionPoint(){
        this.intersectionPoint = null;
    }

    /**
     * Setter for isTokenPlaced
     * @param bool True if token is already placed on the GameBoard, False otherwise ( still not placed currently in MainPanel)
     */
    public void setIsTokenPlaced(boolean bool){
        this.isTokenPlaced = bool;
    }

    /**
     * Getter for isTokenPlaced
     * @return True if token is already placed on the GameBoard, False otherwise ( still not placed currently in MainPanel)
     */
    public boolean isTokenPlaced() {
        return isTokenPlaced;
    }

    public void setCanBeUsed(boolean bool){
        this.canBeUsed = bool;
    }

    public boolean canBeUsed(){
        return this.canBeUsed;
    }

    public void addPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }


}