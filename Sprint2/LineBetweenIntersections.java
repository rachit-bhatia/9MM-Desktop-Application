package project.Sprint2;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LineBetweenIntersections extends JComponent {

  private int x1, y1, x2, y2;

  public LineBetweenIntersections(IntersectionPoint intersectionPointA, IntersectionPoint intersectionPointB) {
    this.x1 = intersectionPointA.getX();
    this.y1 = intersectionPointA.getY();
    this.x2 = intersectionPointB.getX();
    this.y2 = intersectionPointB.getY();
  }

  @Override
  public void paintComponent(Graphics lineShape) {
    super.paintComponent(lineShape);
    Graphics2D lineShapeEnhance = (Graphics2D) lineShape;
    lineShapeEnhance.setColor(Color.BLACK);
    lineShapeEnhance.setStroke(new BasicStroke(4));
    lineShapeEnhance.drawLine(x1, y1, x2, y2); // draw line
  }
}
