package project.Sprint2;

import javax.swing.*;
import java.awt.*;

public class MainDriver {

    public MainDriver(){

        JFrame mainWindow = new JFrame("9 Men's Morris");  //initialising the main application window
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //default closing action

        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();  //accessing the dimensions of the screen
        mainWindow.setSize(screenDimension.width, screenDimension.height);  //window size is of the screen size
        mainWindow.setLocation(0,0);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //default closing action

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

        panel.setBackground(Color.GRAY);   //setting background colour of application

        // Adding the panel container that has all the tokens and intersection points to the frame
        mainWindow.add(panel,BorderLayout.CENTER);

        mainWindow.setVisible(true);    //application window visibility
    }

    public static void main(String[] args) {
        new MainDriver();
    }




}
