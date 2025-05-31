package dk.sdu.cbse.common.services;

/**
 * This interface provides a central service for score management.
 * It is responsible for tracking and updating the score througout the game.
 * It is meant to be implemented as a microservice and has to be started before the game starts.
 */
public interface IScoreService {
    /**
     * Retrieves the current game score
     * @return the cumulative score (>= 0).
     *
     * Pre-condition:
     *      The scoring service has been initialized and reachable
     * Post-condition:
     *      Returns a non-negative interger representing the current score.
     */
    public int getScore();

    /**
     * Adds a given number to the current score
     * @param points the number of points to add. Must be > 0.
     *
     * Pre-condition:
     *      Points > 0.
     *      The scoring service in active and reachable.
     *
     * Post-condition:
     *      The internal score is increased by {code points}.
     *      The new score will be: previous score + points.
     */
    public void givePoints(int points);

}
