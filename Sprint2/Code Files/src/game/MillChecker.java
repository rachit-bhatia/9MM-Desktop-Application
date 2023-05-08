package game;

import java.util.ArrayList;
import java.util.Arrays;

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
    for (int i = 0; i < intersectionPointsList.size(); i++) {
      if (intersectionPointsList.get(i) == myIntersectionPoint) {
        // if intersection point is at 7, 15, 23
        if (i % 8 == 7) {
          if (intersectionPointsList.get(i - 1).hasToken() && intersectionPointsList.get(i - 7).hasToken()) {
            hasMill = true;
          }
        }
        // if intersection point is at 0, 8, 16
        else if (i % 8 == 0) {
          if (intersectionPointsList.get(i + 1).hasToken() && intersectionPointsList.get(i + 7)
              .hasToken()) {
            hasMill = true;
          }
        }
        // other intersection points
        else {
          if (intersectionPointsList.get(i + 1).hasToken() && intersectionPointsList.get(i - 1).hasToken()) {
            hasMill = true;
          }
        }
        // checks vertically, only if the intersection point is in the middle (need to implement if a token is added to a side intersection point)
        // middle intersection point, hence only odd intersections
        if (i % 2 == 1) {
          if (i - 8 > 0 && i + 8 < 24) {
            if (intersectionPointsList.get(i - 8).hasToken() && intersectionPointsList.get(i + 8)
                .hasToken()) {
              hasMill = true;
            }
          }
        }
        MillChecker tmp = MillChecker.getInstance(this.intersectionPointsList);
        if (i < 8) {
          hasMill =  tmp.checkForMills(intersectionPointsList.get(i + 8));
        }
        if (i > 16) {
          hasMill =  tmp.checkForMills(intersectionPointsList.get(i - 8));
        }
      }
    }
    return hasMill;
  }

  private boolean checkForVerticalMill(IntersectionPoint myIntersectionPoint) {
    boolean hasMill = false;
    int intersectionIndex = -1;
    int[] centreIntersections = {9, 11, 13, 15};
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
      if (intersectionPointsList.get(nextIntersectionIndex).hasToken() && intersectionPointsList.get(prevIntersectionIndex).hasToken() && intersectionPointsList.get(intersectionIndex).hasToken()) {
        hasMill = true;
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
      if (intersectionPointsList.get(nextIntersectionIndex).hasToken() && intersectionPointsList.get(prevIntersectionIndex).hasToken() && intersectionPointsList.get(intersectionIndex).hasToken()) {
        hasMill = true;
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
