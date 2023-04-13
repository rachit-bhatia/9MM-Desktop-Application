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
        int boardWidth = (int) (screenDimension.width/2.4);
        int boardHeight = (int) (screenDimension.height/1.35);
        int boardX = (int) (screenDimension.width - boardWidth) / 2;
        int boardY = (int) (screenDimension.height - boardHeight) / 2;

        this.setBounds(boardX, boardY, boardWidth, boardHeight);
    }

    public void addIntersections (int boardWidth, int boardHeight){
        ArrayList<IntersectionPoint> intersectionPointsList = new ArrayList<IntersectionPoint>(8);
//        intersectionPointsList.add(new IntersectionPoint(70,50));
//        intersectionPointsList.add(new IntersectionPoint(70 + ((boardWidth-180)/2),50));
//        intersectionPointsList.add(new IntersectionPoint(boardWidth - 110,50));
//        intersectionPointsList.add(new IntersectionPoint(70, (boardHeight) / 2));
//        intersectionPointsList.add(new IntersectionPoint(70, boardHeight - 110));

        // Add 8 points on the outer square
        intersectionPointsList.add(new IntersectionPoint(boardWidth/8, boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2, boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth-boardWidth/8, boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth-boardWidth/8, boardHeight/2));
        intersectionPointsList.add(new IntersectionPoint(boardWidth-boardWidth/8, boardHeight-boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2, boardHeight-boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/8, boardHeight-boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/8, boardHeight/2));

// Add 8 points on the middle square
        intersectionPointsList.add(new IntersectionPoint(boardWidth/4, boardHeight/4));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2, boardHeight/4));
        intersectionPointsList.add(new IntersectionPoint(boardWidth-boardWidth/4, boardHeight/4));
        intersectionPointsList.add(new IntersectionPoint(boardWidth-boardWidth/4, boardHeight/2));
        intersectionPointsList.add(new IntersectionPoint(boardWidth-boardWidth/4, boardHeight-boardHeight/4));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2, boardHeight-boardHeight/4));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/4, boardHeight-boardHeight/4));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/4, boardHeight/2));

// Add 8 points on the inner square
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2 - boardWidth/8, boardHeight/2 - boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2, boardHeight/2 - boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2 + boardWidth/8, boardHeight/2 - boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2 + boardWidth/8, boardHeight/2));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2 + boardWidth/8, boardHeight/2 + boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2, boardHeight/2 + boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2 - boardWidth/8, boardHeight/2 + boardHeight/8));
        intersectionPointsList.add(new IntersectionPoint(boardWidth/2 - boardWidth/8, boardHeight/2));


        for (IntersectionPoint intersectionPoint : intersectionPointsList) {
            this.add(intersectionPoint);
            intersectionPoints.add(intersectionPoint);
        }
    }

    public ArrayList<IntersectionPoint> getIntersectionPoints(){
        return intersectionPoints;
    }
}
