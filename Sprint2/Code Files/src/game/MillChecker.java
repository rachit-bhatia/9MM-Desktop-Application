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
      }
    }
    return hasMill;
  }
}
