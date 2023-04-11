package project.Sprint2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard extends JPanel {

    private ArrayList<IntersectionPoint> intersectionPoints;

    private static GameBoard instance ;

    private GameBoard(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        setLayout(null);
        setBackground(new Color(110, 65, 40).brighter());
        intersectionPoints = new ArrayList<IntersectionPoint>();
    }

    public static GameBoard getInstance(){
        if (instance == null) {
            instance = new GameBoard();
        }

        return instance;
    }

    public void setDimensionsOfBoard(Dimension screenDimension){
        int boardWidth = (int) (screenDimension.width/1.52);
        int boardHeight = (int) (screenDimension.height/1.2);
        int boardX = (int) (screenDimension.width/5.7);
        int boardY = (int) (screenDimension.height/25);

        this.setBounds(boardX, boardY, boardWidth, boardHeight);
    }

    public void addIntersections (int boardWidth, int boardHeight){

        IntersectionPoint intersectionPoint1 = new IntersectionPoint(70,50);
        IntersectionPoint intersectionPoint2 = new IntersectionPoint(70 + ((boardWidth-180)/2),50);
        IntersectionPoint intersectionPoint3 = new IntersectionPoint(boardWidth - 110,50);

        this.add(intersectionPoint1);
        this.add(intersectionPoint2);
        this.add(intersectionPoint3);

        intersectionPoints.add(intersectionPoint1);
        intersectionPoints.add(intersectionPoint2);
        intersectionPoints.add(intersectionPoint3);

    }

    public ArrayList<IntersectionPoint> getIntersectionPoints(){
        return intersectionPoints;
    }
}
