// NOTE: HAVE TO FIGURE OUT HOW TO DISTINGUISH BETWEEN WHITE AND BLACK

package game;

import java.util.ArrayList;

public class MillCheck implements NeighbourPositionFinder {
    private static MillCheck instance;

    // TODO : Add an attribute ArrayList of array{3} Token to store all the tokens currently part of Mill


    // Singleton method to get instance
    public static MillCheck getInstance() {
        if (instance == null) {
            instance = new MillCheck();
        }
        return instance;
    }

    /**
     * Check for vertical and horizontal mill
     * @param intersectionPointInstance the intersection point in which a token is just placed on it
     * @return True or False boolean
     */
    public boolean checkMill(IntersectionPoint intersectionPointInstance) {


        ArrayList<Token> tokensInMillHorizontal = new ArrayList<Token>(); // Tokens in Horizontal Mill
        tokensInMillHorizontal.add(intersectionPointInstance.getTokenInstance()); // First add the token that is just placed on this intersection point
        int numberOfTokensAlignedHorizontal = 1; // Number of tokens aligned horizontally


        ArrayList<Token> tokensFoundHorizontal = checkHorizontal(intersectionPointInstance,tokensInMillHorizontal); // Check for tokens aligned horizontally

        while (tokensFoundHorizontal.size() != 0){ // If no tokens aligned horizontally stop the loop

            if (tokensFoundHorizontal.size() == 2){ // If 2 other tokens found aligned with newly placed token
                numberOfTokensAlignedHorizontal += 2;
                break; // mill is formed
            } else { // If only one token is found aligned , proceed to check the third intersection point ( happens for the case where token is placed on the
                // corner most intersection point of a horizontal line)
                numberOfTokensAlignedHorizontal += 1;
                tokensFoundHorizontal = checkHorizontal(tokensFoundHorizontal.get(0).getIntersectionPoint(),tokensInMillHorizontal);
            }
        }

        ArrayList<Token> tokensInMillVertical = new ArrayList<Token>(); // Tokens in Vertical Mill
        int numberOfTokensAlignedVertical = 1; // Number of tokens aligned vertically
        tokensInMillVertical.add(intersectionPointInstance.getTokenInstance()); // First add the token that is just placed on this intersection point

        ArrayList<Token> tokensFoundVertical = checkVertical(intersectionPointInstance,tokensInMillVertical); // Check for tokens aligned vertically

        while (tokensFoundVertical.size() != 0){ // If no tokens aligned vertically stop the loop

            if (tokensFoundVertical.size() == 2){ // If 2 other tokens found aligned with newly placed token
                numberOfTokensAlignedVertical += 2;
                break; // mill is formed
            } else { // If only one token is found aligned , proceed to check the third intersection point ( happens for the case where token is placed on the
                // corner most intersection point of a vertical line)
                numberOfTokensAlignedVertical += 1;
                tokensFoundVertical = checkVertical(tokensFoundVertical.get(0).getIntersectionPoint(),tokensInMillVertical);
            }
        }

        if ( numberOfTokensAlignedHorizontal == 3 && numberOfTokensAlignedHorizontal == 3){
            // TODO : Add both mils formed (tokensInMillHorizontal & tokensInMillVertical to the attribute so that can be checked against later before removal of token
            System.out.println("Double mills test ");
            return true;
        }

        if (numberOfTokensAlignedHorizontal == 3){
            // TODO : Add the mill formed (tokensInMillHorizontal) to the attribute so that can be checked against later before removal of token
            return true;
        }

        if (numberOfTokensAlignedVertical == 3){
            // TODO : Add the mill formed (tokensInMillVertical) to the attribute so that can be checked against later before removal of token
            return true;
        }



        return false;
    }

    /**
     * Check the horizontal neighbour(s) of an intersection point to see if there is another token of same player next to it
     * @param intersectionPoint intersection point to which the neighbours are being checked
     * @param tokensInMillHorizontal current tokens that are already found aligned ( to avoid adding the same token found again to the mill)
     * @return all the tokens found aligned horizontally ( either 1 token or 2 tokens ( middle intersection point) )
     */
    private ArrayList<Token> checkHorizontal(IntersectionPoint intersectionPoint ,ArrayList<Token> tokensInMillHorizontal ){
        ArrayList<IntersectionPoint> neighbours = findNeighbouringIntersections(intersectionPoint);
        ArrayList<Token> tokensFoundAlignedHorizontal = new ArrayList<Token>();

        for (IntersectionPoint neighbour : neighbours){
            if ( neighbour.getY() == intersectionPoint.getY() ){
                if (neighbour.hasToken() && (neighbour.getTokenInstance().getPlayer() == intersectionPoint.getTokenInstance().getPlayer()) &&
                !tokensInMillHorizontal.contains(neighbour.getTokenInstance())){
                    tokensFoundAlignedHorizontal.add(neighbour.getTokenInstance());
                    tokensInMillHorizontal.add(neighbour.getTokenInstance());
                }
            }
        }
        return tokensFoundAlignedHorizontal;
    }


    /**
     * Check the vertical neighbour(s) of an intersection point to see if there is another token of same player next to it
     * @param intersectionPoint intersection point to which the neighbours are being checked
     * @param tokensInMillVertical current tokens that are already found aligned ( to avoid adding the same token found again to the mill)
     * @return all the tokens found aligned vertically ( either 1 token or 2 tokens ( middle intersection point) )
     */
    private ArrayList<Token> checkVertical(IntersectionPoint intersectionPoint ,ArrayList<Token> tokensInMillVertical ){
        ArrayList<IntersectionPoint> neighbours = findNeighbouringIntersections(intersectionPoint);
        ArrayList<Token> tokensFoundAlignedVertical = new ArrayList<Token>();

        for (IntersectionPoint neighbour : neighbours){
            if ( neighbour.getX() == intersectionPoint.getX() ){
                if (neighbour.hasToken() && (neighbour.getTokenInstance().getPlayer() == intersectionPoint.getTokenInstance().getPlayer()) &&
                        !tokensInMillVertical.contains(neighbour.getTokenInstance())){
                    tokensFoundAlignedVertical.add(neighbour.getTokenInstance());
                    tokensInMillVertical.add(neighbour.getTokenInstance());
                }
            }
        }
        return tokensFoundAlignedVertical;
    }
}
