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
     * @return True when a mill is formed . Otherwise, False.
     */
    public boolean checkMill(IntersectionPoint intersectionPointInstance) {


        ArrayList<Token> tokensInMillHorizontal = new ArrayList<Token>(); // Tokens in Horizontal Mill
        tokensInMillHorizontal.add(intersectionPointInstance.getTokenInstance()); // First add the token that is just placed on this intersection point
        int numberOfTokensAlignedHorizontal = 1; // Number of tokens aligned horizontally


        ArrayList<Token> tokensFoundHorizontal = checkHorizontalVertical(intersectionPointInstance,tokensInMillHorizontal,true); // Check for tokens aligned horizontally

        while (tokensFoundHorizontal.size() != 0){ // If no tokens aligned horizontally stop the loop

            if (tokensFoundHorizontal.size() == 2){ // If 2 other tokens found aligned with newly placed token
                numberOfTokensAlignedHorizontal += 2;
                break; // mill is formed
            } else { // If only one token is found aligned , proceed to check the third intersection point ( happens for the case where token is placed on the
                // corner most intersection point of a horizontal line)
                numberOfTokensAlignedHorizontal += 1;
                tokensFoundHorizontal = checkHorizontalVertical(tokensFoundHorizontal.get(0).getIntersectionPoint(),tokensInMillHorizontal,true);
            }
        }

        ArrayList<Token> tokensInMillVertical = new ArrayList<Token>(); // Tokens in Vertical Mill
        int numberOfTokensAlignedVertical = 1; // Number of tokens aligned vertically
        tokensInMillVertical.add(intersectionPointInstance.getTokenInstance()); // First add the token that is just placed on this intersection point

        ArrayList<Token> tokensFoundVertical = checkHorizontalVertical(intersectionPointInstance,tokensInMillVertical,false); // Check for tokens aligned vertically

        while (tokensFoundVertical.size() != 0){ // If no tokens aligned vertically stop the loop

            if (tokensFoundVertical.size() == 2){ // If 2 other tokens found aligned with newly placed token
                numberOfTokensAlignedVertical += 2;
                break; // mill is formed
            } else { // If only one token is found aligned , proceed to check the third intersection point ( happens for the case where token is placed on the
                // corner most intersection point of a vertical line)
                numberOfTokensAlignedVertical += 1;
                tokensFoundVertical = checkHorizontalVertical(tokensFoundVertical.get(0).getIntersectionPoint(),tokensInMillVertical,false);
            }
        }

        if ( numberOfTokensAlignedHorizontal == 3 && numberOfTokensAlignedVertical == 3){
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
     * @param bool True if Checking for horizontal alignment , False if checking for vertical alignment
     * @param intersectionPoint intersection point to which the neighbours are being checked
     * @param tokensInMill current tokens that are already found aligned ( to avoid adding the same token found again to the mill)
     * @return all the tokens found aligned ( either 1 token or 2 tokens ( middle intersection point) )
     */

    private ArrayList<Token> checkHorizontalVertical(IntersectionPoint intersectionPoint ,ArrayList<Token> tokensInMill , boolean bool ){
        ArrayList<IntersectionPoint> neighbours = findNeighbouringIntersections(intersectionPoint); // Find neighbours of intersection point given as parameter
        ArrayList<Token> tokensFoundAligned = new ArrayList<Token>(); // All the tokens found aligned

        for (IntersectionPoint neighbour : neighbours){

            if (bool){ // If checking horizontal alignment
                if ( neighbour.getY() == intersectionPoint.getY() ){
                    // If horizontal neighbour has a token belongs to same player that is not yet been discovered
                    if (neighbour.hasToken() && (neighbour.getTokenInstance().getPlayer() == intersectionPoint.getTokenInstance().getPlayer()) &&
                            !tokensInMill.contains(neighbour.getTokenInstance())){
                        tokensFoundAligned.add(neighbour.getTokenInstance()); // add the token found to the output return
                        tokensInMill.add(neighbour.getTokenInstance()); // add the token found to be part of the mill
                    }
                }
            } else{ // If checking vertical alignment
                if ( neighbour.getX() == intersectionPoint.getX() ){
                    // If vertical neighbour has a token belongs to same player that is not yet been discovered
                    if (neighbour.hasToken() && (neighbour.getTokenInstance().getPlayer() == intersectionPoint.getTokenInstance().getPlayer()) &&
                            !tokensInMill.contains(neighbour.getTokenInstance())){
                        tokensFoundAligned.add(neighbour.getTokenInstance()); // add the token found to the output return
                        tokensInMill.add(neighbour.getTokenInstance()); // add the token found to be part of the mill
                    }
                }
            }

        }
        return tokensFoundAligned;
    }
}
