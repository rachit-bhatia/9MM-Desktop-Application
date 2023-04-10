package project.Sprint2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Token extends JComponent {

    private int xCoordinate;
    private int yCoordinate;
    private final int radius = 37;

    public Token(int x, int y){

        xCoordinate = x;
        yCoordinate = y;
        setBounds(xCoordinate,yCoordinate,50,50);

        ModMouseAdapter mouseActionRecorder = new ModMouseAdapter(this, xCoordinate,yCoordinate);
        this.addMouseListener(mouseActionRecorder);
        this.addMouseMotionListener(mouseActionRecorder);
        this.setPreferredSize(new Dimension(50, 50));
    }

    //used for creating a filled oval
    @Override
    public void paintComponent(Graphics tokenShape) {
        super.paintComponent(tokenShape);
        tokenShape.setColor(Color.WHITE);
        tokenShape.fillOval(xCoordinate, yCoordinate, 30, 30);  //fill token shape with white colour
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));  //rectangular border around the token component
    }

}