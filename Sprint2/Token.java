package project.Sprint2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Token extends JLabel {

    Point startPoint;

    int xCoordinate;
    int yCoordinate;

    int offSetX;
    int offSetY;

    Token instance ;


    public Token(int x,int y){

        xCoordinate = x;
        yCoordinate = y;
        instance = this;
        setBounds(xCoordinate,yCoordinate,50,50);
        ImageIcon image = new ImageIcon(new ImageIcon("C:\\Users\\USER\\Desktop\\Year2Sem2\\FIT3077_Team1\\project\\Sprint2\\Circle_-_black_simple_fullpage.svg.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        setIcon(image);




    }





}