package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * This interface defines a system for post-processing logic after all entities have been updated.
 *  This typically includes collisions that might result in splitting or destroying entities or reducing health for that entity.
 */
public interface IPostEntityProcessingService {

    /**
     * Called once per fixed time step (delta time).
     * It provides a way to process events for entities affected by interactions with other entities in the {@code world}.
     *
     * @param gameData
     * Holds data like display size, key commands and delta time. Delta time is constant
     *                 and not dependent on framerate.
     * @param world Holds data of all the current entities in the world.
     *
     * Pre-conditions:
     *              The entities are active, present in the {@code world} and updated.
     * Post-conditions:
     *              The entities are processed according to interactions with other entities in the {@code world}
     */

    void process(GameData gameData, World world);
}
