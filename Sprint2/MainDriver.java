package project.Sprint2;

import javax.swing.*;
import java.awt.*;
import project.Sprint2.Token;

public class MainDriver {

    public MainDriver(){

        JFrame mainWindow = new JFrame("9 Men's Morris");  //initialising the main application window
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //default closing action

        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();  //accessing the dimensions of the screen
        mainWindow.setSize(screenDimension.width, screenDimension.height);  //window size is of the screen size
        mainWindow.setLocation(0,0);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //default closing action

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(screenDimension.width, screenDimension.height));

        //Adding both player's Tokens to the Panel containt
        int tokenSpacing = 0;
        for (int i = 1; i <= 9; i++){
            mainPanel.add(new Token(screenDimension.width/8,screenDimension.height/8 + tokenSpacing, Color.BLACK));
            mainPanel.add(new Token(screenDimension.width - screenDimension.width/7,screenDimension.height/8 + tokenSpacing, Color.WHITE));
            tokenSpacing += 70;
        }

        // Intersection Points
        IntersectionPoint intersectionPoint1 = new IntersectionPoint(50,50,20,20);
        IntersectionPoint intersectionPoint2 = new IntersectionPoint(200,50,20,20);

//      Adding intersection points to the panel container
        mainPanel.add(intersectionPoint1);
        mainPanel.add(intersectionPoint2);

        // Adding the panel container that has all the tokens and intersection points to the frame
        mainWindow.getContentPane().add(mainPanel);

        mainWindow.setVisible(true);    //application window visibility
    }

    public static void main(String[] args) {
        new MainDriver();
    }




}
