package project.Sprint2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Token extends JLabel {

    private int xCoordinate;
    private int yCoordinate;

    public Token(int x, int y){

        xCoordinate = x;
        yCoordinate = y;
        setBounds(xCoordinate,yCoordinate,50,50);
        ImageIcon image = new ImageIcon(new ImageIcon("/Users/rachit/Documents/Monash/Y2S2/FIT3077/project/Sprint2/Circle_-_black_simple_fullpage.svg.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        setIcon(image);

        ModMouseAdapter mouseActionRecorder = new ModMouseAdapter(this, xCoordinate,yCoordinate);
        this.addMouseListener(mouseActionRecorder);
        this.addMouseMotionListener(mouseActionRecorder);
    }





}