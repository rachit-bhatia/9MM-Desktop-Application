// NOTE: HAVE TO FIGURE OUT HOW TO DISTINGUISH BETWEEN WHITE AND BLACK

package game;

import java.util.ArrayList;

public class MillChecker {
  private static MillChecker instance;
  private ArrayList<IntersectionPoint> intersectionPointsList;

  // Constructor
  private MillChecker(ArrayList<IntersectionPoint> intersectionPointsList) {
    this.intersectionPointsList = intersectionPointsList;
  }

  // Singleton method to get instance
  public static MillChecker getInstance(ArrayList<IntersectionPoint> intersectionPointsList){
    if (instance == null) {
      instance = new MillChecker(intersectionPointsList);
    }
    return instance;
  }

  // Main function checks for mills formed around an Intersection point ONLY (for now)
  public boolean checkForMills(IntersectionPoint myIntersectionPoint) {
    boolean hasMill = false;
    MillChecker millChecker = MillChecker.getInstance(this.intersectionPointsList);
    int intersectionIndex = -1;
    for (int i = 0; i < intersectionPointsList.size(); i++) {
      if (intersectionPointsList.get(i) == myIntersectionPoint) {
        intersectionIndex = i;
      }
    }
    // Checking for vertical mills (only works on centre intersections)
    if (intersectionIndex % 2 == 1) {
      if (millChecker.checkForVerticalMill(myIntersectionPoint)) {
        hasMill = true;
      }
    }
    // Checking for horizontal mills (all intersections)
    if (millChecker.checkForHorizontalMill(myIntersectionPoint)) {
      hasMill = true;
    }
    return hasMill;
  }

  private boolean checkForVerticalMill(IntersectionPoint myIntersectionPoint) {
    boolean hasMill = false;
    int intersectionIndex = -1;
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
      MillChecker verticalMillChecker = MillChecker.getInstance(this.intersectionPointsList);
      // check for outer ring
      if (intersectionIndex < 8) {
        hasMill = verticalMillChecker.checkForVerticalMill(intersectionPointsList.get(nextIntersectionIndex));
      }
      // check for inner ring
      if (intersectionIndex > 16) {
        hasMill = verticalMillChecker.checkForVerticalMill(intersectionPointsList.get(prevIntersectionIndex));
      }
    }
    return hasMill;
  }
  private boolean checkForHorizontalMill(IntersectionPoint myIntersectionPoint) {
    boolean hasMill = false;
    int intersectionIndex = -1;
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
      MillChecker horizontalMillChecker = MillChecker.getInstance(this.intersectionPointsList);
      if (horizontalMillChecker.checkForHorizontalMill(intersectionPointsList.get(prevIntersectionIndex)) ||  horizontalMillChecker.checkForHorizontalMill(intersectionPointsList.get(nextIntersectionIndex))) {
        hasMill = true;
      }
    }
    return hasMill;
  }
}
