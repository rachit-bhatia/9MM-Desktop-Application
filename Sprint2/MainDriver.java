package project.Sprint2;

import javax.swing.*;
import java.awt.*;

public class MainDriver {

    public MainDriver(){

        // Frame initialization
        JFrame jFrame = new JFrame();
        jFrame.setSize(new Dimension(500,500));



        JPanel panel = new JPanel();
        panel.setLayout(null);


        // Intersection Points
        IntersectionPoint intersectionPoint1 = new IntersectionPoint(50,50,20,20);
        IntersectionPoint intersectionPoint2 = new IntersectionPoint(200,50,20,20);

        // Tokens
        Token token = new Token(100,100);

        // Adding tokens and intersection points to the panel container
        panel.add(token);
        panel.add(intersectionPoint1);
        panel.add(intersectionPoint2);


        // Adding the panel container that has all the tokens and intersection points to the frame
        jFrame.add(panel,BorderLayout.CENTER);



        // Frame default options
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("9MM");
        jFrame.setVisible(true);

    }


    public static void main(String[] args) {
        new MainDriver();
    }




}
