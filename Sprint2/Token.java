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

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // To find the offSet values so that the cursor can be at the same position of the token while being drag
                startPoint = SwingUtilities.convertPoint(instance, e.getPoint(), instance.getParent());
                offSetX = startPoint.x - instance.getBounds().x;
                offSetY = startPoint.y - instance.getBounds().y;
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                // Location of cursor after mouse is released from dragging

                // All the components in the panel
                Component[] components = instance.getParent().getComponents();

                boolean foundIntersectionPoint = false;

                // Look through all the components to find if the token is released at any intersection points
                for (Component component : components) {
                    // If token is released at a valid intersection point , then set it as a new location of the token
                    if (component.getClass().equals(IntersectionPoint.class) && instance.getBounds().contains(component.getX(), component.getY(), component.getWidth(), component.getHeight())) {
                        int newLocationX = component.getX() + (component.getWidth() / 2) - (instance.getWidth() / 2);
                        int newLocationY = component.getY() + (component.getHeight() / 2) - (instance.getHeight() / 2);
                        instance.setLocation(newLocationX, newLocationY);
                        xCoordinate = newLocationX;
                        yCoordinate = newLocationY;
                        foundIntersectionPoint = true;
                        break;
                    }
                }


                if (!foundIntersectionPoint) {
                    instance.setLocation(xCoordinate, yCoordinate);
                }
            }
        });


        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point location = SwingUtilities.convertPoint(instance,e.getPoint(),instance.getParent());

                instance.setLocation(location.x - offSetX,location.y - offSetY);
            }
        });



    }





}