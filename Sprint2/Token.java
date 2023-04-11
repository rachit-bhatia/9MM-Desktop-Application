package project.Sprint2;
import javax.swing.*;
import java.awt.*;


public class Token extends JComponent {

    private int xCoordinate;
    private int yCoordinate;
    private Color tokenColour;
    private final int RADIUS = 40;

    public Token(int x, int y, Color color){

        xCoordinate = x;
        yCoordinate = y;
        tokenColour = color;
        setBounds(xCoordinate,yCoordinate, RADIUS, RADIUS);  //setting the position and size of the token

        //enabling the tokens to be moved by mouse actions
        ModMouseAdapter mouseActionRecorder = new ModMouseAdapter(this, xCoordinate,yCoordinate);
        this.addMouseListener(mouseActionRecorder);
        this.addMouseMotionListener(mouseActionRecorder);
    }

    //used for creating a filled oval
    @Override
    public void paintComponent(Graphics tokenShape) {
        tokenShape.setColor(tokenColour);
        tokenShape.fillOval(0, 0, getWidth(), getHeight());  //painting an oval
    }

}