package game;

import java.util.Random;

public interface RandomNumberGenerator {

    /**
     * Generates a random integer between 0 to maxInt.
     * @return an integer
     */
    public default int generateRandomNumber(int maxInt) {
        return new Random().nextInt(maxInt);
    }
}
