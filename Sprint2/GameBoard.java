package project.Sprint2;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    private static GameBoard instance ;

    private GameBoard(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setLayout(null);
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

    }
}
