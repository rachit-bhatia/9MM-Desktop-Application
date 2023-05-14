// NOTE: HAVE TO FIGURE OUT HOW TO DISTINGUISH BETWEEN WHITE AND BLACK

package game;

import java.util.ArrayList;

public class MillChecker_Alpha {
  private static MillChecker_Alpha instance;


  // Singleton method to get instance
  public static MillChecker_Alpha getInstance(){
    if (instance == null) {
      instance = new MillChecker_Alpha();
    }
    return instance;
  }

  // Main function checks for mills formed around an Intersection point ONLY (for now)
  public boolean checkForMills(IntersectionPoint myIntersectionPoint) {
    boolean hasMill = false;
    int intersectionIndex = -1;

    ArrayList<IntersectionPoint> intersectionPointsList = GameBoard.getInstance().getIntersectionPoints();
    for (int i = 0; i < intersectionPointsList.size(); i++) {
      if (intersectionPointsList.get(i) == myIntersectionPoint) {
        intersectionIndex = i;
      }
    }
    // Checking for vertical mills (only works on centre intersections)
    if (intersectionIndex % 2 == 1) {
      if (this.checkForVerticalMill(myIntersectionPoint)) {
        hasMill = true;
      }
    }
    // Checking for horizontal mills (all intersections)
    if (this.checkForHorizontalMill(myIntersectionPoint)) {
      hasMill = true;
    }
    return hasMill;
  }

  private boolean checkForVerticalMill(IntersectionPoint myIntersectionPoint) {
    boolean hasMill = false;
    int intersectionIndex = -1;

    ArrayList<IntersectionPoint> intersectionPointsList = GameBoard.getInstance().getIntersectionPoints();
    for (int i = 0; i < intersectionPointsList.size(); i++) {
      if (intersectionPointsList.get(i) == myIntersectionPoint) {
        intersectionIndex = i;
      }
    }
    int nextIntersectionIndex = intersectionIndex + 8;
    int prevIntersectionIndex = intersectionIndex - 8;

    // check whether intersection is vertically centered
    if (intersectionIndex > 8 && intersectionIndex < 16) {
      // Check if the adjacent intersections have tokens
      if (intersectionPointsList.get(nextIntersectionIndex).hasToken() &&
          intersectionPointsList.get(prevIntersectionIndex).hasToken() &&
          intersectionPointsList.get(intersectionIndex).hasToken()) {

        if (intersectionPointsList.get(intersectionIndex).getTokenInstance().getPlayer() == intersectionPointsList.get(prevIntersectionIndex).getTokenInstance().getPlayer() &&
            intersectionPointsList.get(intersectionIndex).getTokenInstance().getPlayer() == intersectionPointsList.get(nextIntersectionIndex).getTokenInstance().getPlayer()) {
          hasMill = true;
        }
      }
    }

    // when intersection is not vertically centered
    else {
      // check for outer ring
      if (intersectionIndex < 8) {
        hasMill = this.checkForVerticalMill(intersectionPointsList.get(nextIntersectionIndex));
      }
      // check for inner ring
      if (intersectionIndex > 16) {
        hasMill = this.checkForVerticalMill(intersectionPointsList.get(prevIntersectionIndex));
      }
    }
    return hasMill;
  }
  private boolean checkForHorizontalMill(IntersectionPoint myIntersectionPoint) {
    boolean hasMill = false;
    int intersectionIndex = -1;

    ArrayList<IntersectionPoint> intersectionPointsList = GameBoard.getInstance().getIntersectionPoints();
    for (int i = 0; i < intersectionPointsList.size(); i++) {
      if (intersectionPointsList.get(i) == myIntersectionPoint) {
        intersectionIndex = i;
      }
    }
    int nextIntersectionIndex = intersectionIndex + 1;
    int prevIntersectionIndex = intersectionIndex - 1;
    // If the intersection point is in the centre
    if (intersectionIndex % 2 == 1) {
      // If the intersection point is 7, 15 or 23
      if (intersectionIndex % 8 == 7) {
        nextIntersectionIndex = intersectionIndex - 7;
      }
      // Check if the adjacent intersections have tokens
      if (intersectionPointsList.get(nextIntersectionIndex).hasToken() &&
          intersectionPointsList.get(prevIntersectionIndex).hasToken() &&
          intersectionPointsList.get(intersectionIndex).hasToken()) {

        if (intersectionPointsList.get(intersectionIndex).getTokenInstance().getPlayer() == intersectionPointsList.get(prevIntersectionIndex).getTokenInstance().getPlayer() &&
            intersectionPointsList.get(intersectionIndex).getTokenInstance().getPlayer() == intersectionPointsList.get(nextIntersectionIndex).getTokenInstance().getPlayer()) {
          hasMill = true;
        }
      }
    }

    // If the intersection point is in the corner
    else {
      //  finding adjacent intersections
      if (intersectionIndex % 8 == 0) {
        prevIntersectionIndex = intersectionIndex + 7;
      }

      if (this.checkForHorizontalMill(intersectionPointsList.get(prevIntersectionIndex)) ||  this.checkForHorizontalMill(intersectionPointsList.get(nextIntersectionIndex))) {
        hasMill = true;
      }
    }
    return hasMill;
  }
}
