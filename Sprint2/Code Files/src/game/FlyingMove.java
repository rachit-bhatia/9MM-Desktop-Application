package game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class FlyingMove extends Move {

    public FlyingMove(Token tokenInstance, int xCord, int yCord) {
        super(tokenInstance, xCord, yCord);
    }

    @Override
    public void mousePressed(MouseEvent cursor) {
        // To find the offSet values so that the cursor can be at the same position of the token while being drag
        Point startPoint = SwingUtilities.convertPoint(super.getTokenInstance(), cursor.getPoint(), super.getTokenInstance().getParent());
        super.setOffSetX(startPoint.x - super.getTokenInstance().getBounds().x);
        super.setOffSetY(startPoint.y - super.getTokenInstance().getBounds().y);
    }

}
