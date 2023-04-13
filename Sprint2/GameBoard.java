package project.Sprint2;

import java.lang.reflect.Array;
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
        ArrayList<IntersectionPoint> intersectionPointsList = new ArrayList<IntersectionPoint>(8);
        intersectionPointsList.add(new IntersectionPoint(70,50));
        intersectionPointsList.add(new IntersectionPoint(70 + ((boardWidth-180)/2),50));
        intersectionPointsList.add(new IntersectionPoint(boardWidth - 110,50));
        intersectionPointsList.add(new IntersectionPoint(70, boardHeight / 2));


        for (IntersectionPoint intersectionPoint : intersectionPointsList) {
            this.add(intersectionPoint);
            intersectionPoints.add(intersectionPoint);
        }

    }

    public ArrayList<IntersectionPoint> getIntersectionPoints(){
        return intersectionPoints;
    }
}
