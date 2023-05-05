package game;

import java.util.ArrayList;

public class MillChecker {
  private static MillChecker instance;
  private ArrayList<IntersectionPoint> intersectionPointsList;

  private MillChecker(ArrayList<IntersectionPoint> intersectionPointsList) {
    this.intersectionPointsList = intersectionPointsList;
  }
  public static MillChecker getInstance(ArrayList<IntersectionPoint> intersectionPointsList){
    if (instance == null) {
      instance = new MillChecker(intersectionPointsList);
    }
    return instance;
  }

  public boolean checkForMills(IntersectionPoint myIntersectionPoint) {
    boolean hasMill = false;
    for (int i = 0; i < intersectionPointsList.size(); i++) {
      if (intersectionPointsList.get(i) == myIntersectionPoint) {
        if (i % 8 == 7) {
          if (intersectionPointsList.get(i - 1).hasToken() && intersectionPointsList.get(i - 7).hasToken()) {
            hasMill = true;
          }
        }
        else if (i % 8 == 0) {
          if (intersectionPointsList.get(i + 1).hasToken() && intersectionPointsList.get(i + 7)
              .hasToken()) {
            hasMill = true;
          }
        }
        else {
          if (intersectionPointsList.get(i + 1).hasToken() && intersectionPointsList.get(i - 1).hasToken()) {
            hasMill = true;
          }
        }
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
