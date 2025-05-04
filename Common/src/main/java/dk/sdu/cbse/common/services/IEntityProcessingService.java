package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * This interface defines a system for processing entities in the {@code world}.
 * The system could process updating of position, rotation, velocity or user input for the entity.
 * It should be called at fixed time step (delta time), independent of frame rate.
 */
public interface IEntityProcessingService {
    /**
     * Called to process all relevant entities in the {@code world} that are handled by the implementation.
     *
     * @param gameData
     * Holds data like display size, key commands and delta time. Delta time is constant
     *                 and not dependent on framerate.
     * @param world Holds data of all the current entities in the world.
     *
     * Pre-condition:
     *              The entity is active.
     * Post-condition:
     *              Entity states like position, rotation and velocity may be updated based on delta time and user input.
     */

    void process(GameData gameData, World world);
}
