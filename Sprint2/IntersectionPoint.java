package project.Sprint2;

import javax.swing.*;
import java.awt.*;

public class IntersectionPoint extends JLabel {

    // Coordinates of Intersection Point (Might not be needed as attributes. Can remove later if necessary)
    private int xCoordinate;
    private int yCoordinate;


    public IntersectionPoint(int x,int y ,int width,int height) {
        this.xCoordinate = x;
        this.yCoordinate = y;

//        setLayout(null);

        setBounds(this.xCoordinate,this.yCoordinate,width,height);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int radius = this.getWidth()/2;

        // drawing intersection point as circle
        drawCircle(g, radius, radius, radius); // center (30,30) r=20
    }
    public void drawCircle(Graphics cg, int xCenter, int yCenter, int r) {
        cg.fillOval(xCenter-r, yCenter-r, 2*r, 2*r);
    }

}
