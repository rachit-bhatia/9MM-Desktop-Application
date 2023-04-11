package project.Sprint2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Token extends JComponent {

    private int xCoordinate;
    private int yCoordinate;
    private Color tokenColour;
    private final int radius = 37;

    public Token(int x, int y, Color color){

        xCoordinate = x;
        yCoordinate = y;
        tokenColour = color;
        setBounds(xCoordinate,yCoordinate,radius,radius);  //setting the position and size of the token

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